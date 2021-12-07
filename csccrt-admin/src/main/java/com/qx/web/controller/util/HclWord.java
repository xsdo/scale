package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.domain.ScaleQuestions;
import com.qx.scale.domain.ScaleScore;
import com.qx.scale.domain.ScaleTask;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HclWord {

    public static String getDld(ScalePatient scalePatient , ScaleTask scaleTask,String templateurl,List<ScaleScore> scaleScoreList,List<ScaleQuestions>scaleQuestionsList) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String filenameRandom= RandomStringUtils.randomNumeric(6);
        String fileName=scalePatient.getPatientName()+"_"+scaleTask.getTestCoding()+filenameRandom+"_hcl.docx";  // 结果文件
        String returnurl= ProjectConfig.getDownloadPath()+fileName;
        // 替换word模板数据
        XWPFDocument doc=null;
        try {
            InputStream is = new FileInputStream(new File(templateurl));
             doc = new XWPFDocument(is);
            replaceAll(doc,scalePatient,scaleTask,scaleScoreList,scaleQuestionsList);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 保存结果文件
        try {
            File file = new File(returnurl);
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(returnurl);
            doc.write(fos);
            fos.close();
            //Runtime.getRuntime().exec("cmd /c start winword d:\\"+patientUser.getTestCoding()+"-eis.docx");//直接调用cmd打开合成文档
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }


    /**
     * @Description: 替换段落和表格中
     */
    public static void replaceAll(XWPFDocument doc,ScalePatient scalePatient ,ScaleTask scaleTask,List<ScaleScore> scaleScoreList,List<ScaleQuestions>scaleQuestionsList) throws InvalidFormatException, IOException {
        doParagraphs(doc,scalePatient,scaleTask,scaleScoreList,scaleQuestionsList); // 处理段落文字数据，包括文字和表格、图片
//        doCharts(doc,scaleScoreList);  // 处理图表数据，柱状图、折线图、饼图啊之类的
    }


    /**
     * 处理段落文字
     *
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void doParagraphs(XWPFDocument doc,ScalePatient scalePatient ,ScaleTask scaleTask,List<ScaleScore> scaleScoreList,List<ScaleQuestions>scaleQuestionsList) {
         if("0".equals(scalePatient.getSex())){
             scalePatient.setSex("男");
        }else if("1".equals(scalePatient.getSex())){
             scalePatient.setSex("女");
         }
         if("0".equals(scalePatient.getMaritalStatus())){
             scalePatient.setMaritalStatus("未婚");
         }else{
             scalePatient.setMaritalStatus("已婚");
         }
         if(scalePatient.getHospitalNumber()==null){
             scalePatient.setHospitalNumber(0l);
         }
         if(scalePatient.getWard()==null){
             scalePatient.setWard("");
         }

        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        String str="";
        Long score=0L;
        List<String>questionsList=new ArrayList<>();
        for (ScaleScore scaleScore:scaleScoreList){
            score+=scaleScore.getScore();
            for (ScaleQuestions scaleQuestions:scaleQuestionsList){
                if (scaleScore.getTitle().equals(scaleQuestions.getTitle())&&scaleScore.getScore()>0){
                    questionsList.add(scaleQuestions.getContent().replace("我","您"));
                }
            }
        }

        //添加score标签以后续判断是否存在测验结果症状
        textMap.put("score",score+"");

        if (score>=14){
            string="双相障碍筛查阳性。";
        }else {
            string="双相障碍筛查阴性。";
        }

        textMap.put("var", "本次测验总分为"+score+"分，表明"+string);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy.MM.dd");
        Date date =new Date();
        String f=sdf.format(date);
            textMap.put("varA",f);
        // 图片数据
        Map<String, String> imgMap = new HashMap<String, String>();
        imgMap.put("img", "D:\\360Downloads\\aaa.jpg");
        //段落
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        doParagraph(paragraphList, doc, textMap, imgMap,scalePatient,questionsList);

        //表格
        List<XWPFTable> tables = doc.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                    doParagraph(paragraphs, doc, textMap, imgMap,scalePatient,questionsList);
                }
            }
        }
    }
   public static  Boolean checkScaleScore(String[]strings,String str){
        for (int i=0;i<strings.length;i++){
            if (str.equals(strings[i])){
                return true;
            }
        }
        return false;
    }
    public static void doParagraph(List<XWPFParagraph> paragraphList, XWPFDocument
            doc, Map < String, String > textMap, Map<String,String> imgMap,ScalePatient scalePatient,List<String>questionsList){
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i =0;i<runs.size();i++){
//                for (XWPFRun run : runs) {
                    XWPFRun run =runs.get(i);
                    String text = run.getText(0);
                    if (text != null) {

                        // 替换文本信息
                        String tempText = text;
                        String key = tempText.replaceAll("\\{\\{", "").replaceAll("}}", "");
                        if (!StringUtils.isEmpty(textMap.get(key))) {
                            run.setText(textMap.get(key), 0);
                            String runText=textMap.get(key);
                            if (runText.indexOf("\r")>0){
                                String[]texts =runText.split("\r");
                                //直接调用XWPFRun的setText( )方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                                //所以我们不能直接设值，需要先删除当前run ,然后再自己手动插入一个新的run。
                                paragraph.removeRun(i);
                                run=paragraph.insertNewRun(i);
                                for (int f=0;f<texts.length;f++){
                                    if(f==0){
                                        run.setText(texts[f].trim());
                                    }else {
//                                        run.addCarriageReturn();
                                        run.addBreak();
                                        run.setText("    "+texts[f].trim());
                                    }

                                }
                            }
                        }


                        // 替换图片内容 参考：https://blog.csdn.net/a909301740/article/details/84984445
                        String tempImgText = text;
                        String imgkey = tempImgText.replaceAll("\\{\\{@", "").replaceAll("}}", "");
                        if (!StringUtils.isEmpty(imgMap.get(imgkey))) {
                            String imgPath = imgMap.get(imgkey);
                            try {
                                run.setText("", 0);
                                run.addPicture(new FileInputStream(imgPath), Document.PICTURE_TYPE_PNG, "img.png", Units.toEMU(200), Units.toEMU(200));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        // 动态表格
                        if (text.contains("${table1}")) {
                            run.setText("", 0);
                            XmlCursor cursor = paragraph.getCTP().newCursor();
                            XWPFTable tableOne = doc.insertNewTbl(cursor);// ---这个是关键

                            // 设置表格宽度，第一行宽度就可以了，这个值的单位，目前我也还不清楚，还没来得及研究
                            tableOne.setWidth(8500);
                            // 表格第一行，对于每个列，必须使用createCell()，而不是getCell()，因为第一行嘛，肯定是属于创建的，没有create哪里来的get呢
                            XWPFTableRow tableOneRowOne = tableOne.getRow(0);//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.getCell(0), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "姓名："+scalePatient.getPatientName());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "性别："+scalePatient.getSex());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "婚姻："+scalePatient.getMaritalStatus());

                            // 表格第二行
                            XWPFTableRow tableOneRowTwo = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "年龄："+scalePatient.getAge());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "文化程度："+scalePatient.getEducation());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(2), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "职业："+scalePatient.getJob());

                            // 表格第三行
                            XWPFTableRow tableOneRowThree = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(0), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "住院号："+scalePatient.getHospitalNumber());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(1), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "病区："+scalePatient.getWard());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(2), "宋体", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "来源："+scalePatient.getSource());


                            // ....... 可动态添加表格
                            //单元格合并
                            //mergeCellsHorizontal(tableOne,3,1,2);
                        }
                        if (text.contains("${table2}")) {
                            run.setText("", 0);
                            XmlCursor cursor = paragraph.getCTP().newCursor();
                            XWPFTable tableTwo = doc.insertNewTbl(cursor);// ---这个是关键

                            // 设置表格宽度，第一行宽度就可以了，这个值的单位，目前我也还不清楚，还没来得及研究
                            tableTwo.setWidth(8500);
                            // 表格第一行，对于每个列，必须使用createCell()，而不是getCell()，因为第一行嘛，肯定是属于创建的，没有create哪里来的get呢
                            XWPFTableRow tableOneRowOne = tableTwo.getRow(0);//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.getCell(0), "宋体", "10", 1, "center", "top", "#000000", "#ffffff", "10%", "序号");
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "宋体", "10", 1, "center", "top", "#000000", "#ffffff", "80%", "阳性症状");

                            if (textMap.get("score").equals("0")){
                                // 表格第二行
                                XWPFTableRow tableOneRowTwo = tableTwo.createRow();//行
                                new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "宋体", "10", 0, "center", "top", "#000000", "#ffffff", "10%", "");
                                new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "宋体", "10", 0, "center", "top", "#000000", "#ffffff", "80%", "无");
                            }else{
                                for (int j=0;j<questionsList.size();j++){
                                    // 表格第二行
                                    XWPFTableRow tableOneRowTwo = tableTwo.createRow();//行
                                    new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "宋体", "10", 0, "center", "top", "#000000", "#ffffff", "10%", j+1+"");
                                    new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "宋体", "10", 0, "center", "top", "#000000", "#ffffff", "80%", "在“高涨”状态下，"+questionsList.get(j));

                                }

                            }


                            // 表格第三行
               /*             XWPFTableRow tableOneRowThree = tableTwo.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "住院号："+scalePatient.getHospitalNumber());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "病区："+scalePatient.getWard());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "来源："+scalePatient.getSource());
*/

                            // ....... 可动态添加表格
                            //单元格合并
                            //mergeCellsHorizontal(tableOne,3,1,2);
                        }

                    }
                }
            }
        }
    }
    public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // word跨行并单元格
    public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
    public static void doCharts(XWPFDocument doc,List<ScaleScore> scaleScoreList) {
        /**----------------------------处理图表------------------------------------**/
        // 数据准备
        //
        Long scoreA=0L;
        Long scoreB=0L;
        Long scoreC=0L;
        Long scoreD=0L;
        Long scoreE=0L;
        Long scoreF=0L;
        Long scoreG=0L;
        Long scoreH=0L;
        Long scoreI=0L;
        Long scoreJ=0L;
        String[]strA=new String[]{"1","4","12","27","40","42","48","49","52","53","56","58"};
        String[]strB=new String[]{"3","9","10","28","38","45","46","51","55","65"};
        String[]strC=new String[]{"6","21","34","36","37","41","61","69","73"};
        String[]strD=new String[]{"5","14","15","20","22","26","29","30","31","32","54","71","79"};
        String[]strE=new String[]{"2","17","23","33","39","57","72","78","80","86"};
        String[]strF=new String[]{"11","24","63","67","74","81"};
        String[]strG=new String[]{"13","25","47","50","70","75","82"};
        String[]strH=new String[]{"8","18","43","68","76","83"};
        String[]strI=new String[]{"7","16","35","62","77","84","85","87","88","90"};
        String[]strJ=new String[]{"19","44","59","60","64","66","89"};

        for (ScaleScore scaleScore:scaleScoreList){
            if (checkScaleScore(strA,scaleScore.getTitle())){
                scoreA+=scaleScore.getScore();
            }else if (checkScaleScore(strB,scaleScore.getTitle())){
                scoreB+=scaleScore.getScore();
            }else if (checkScaleScore(strC,scaleScore.getTitle())){
                scoreC+=scaleScore.getScore();
            }else if (checkScaleScore(strD,scaleScore.getTitle())){
                scoreD+=scaleScore.getScore();
            }else if (checkScaleScore(strE,scaleScore.getTitle())){
                scoreE+=scaleScore.getScore();
            }else if (checkScaleScore(strF,scaleScore.getTitle())){
                scoreF+=scaleScore.getScore();
            }else if (checkScaleScore(strG,scaleScore.getTitle())){
                scoreG+=scaleScore.getScore();
            }else if (checkScaleScore(strH,scaleScore.getTitle())){
                scoreH+=scaleScore.getScore();
            }else if (checkScaleScore(strI,scaleScore.getTitle())){
                scoreI+=scaleScore.getScore();
            }else if (checkScaleScore(strJ,scaleScore.getTitle())){
                scoreJ+=scaleScore.getScore();
            }
        }
        double s1=(double)(Math.round(scoreA*100/12)/100.0);
        double s2=(double)(Math.round(scoreB*100/10)/100.0);
        double s3=(double)(Math.round(scoreC*100/9)/100.0);
        double s4=(double)(Math.round(scoreD*100/13)/100.0);
        double s5=(double)(Math.round(scoreE*100/10)/100.0);
        double s6=(double)(Math.round(scoreF*100/6)/100.0);
        double s7=(double)(Math.round(scoreG*100/7)/100.0);
        double s8=(double)(Math.round(scoreH*100/6)/100.0);
        double s9=(double)(Math.round(scoreI*100/10)/100.0);
        double s10=(double)(Math.round(scoreJ*100/7)/100.0);


        DecimalFormat df = new DecimalFormat(".00");
/*====================================================================================================*/
       List<String> titleArrA = new ArrayList<String>();// 标题
        titleArrA.add("title");
        titleArrA.add("aaa");
//        titleArrA.add("33");
        List<String> fldNameArrA = new ArrayList<String>();// 字段名
        fldNameArrA.add("item1");
        fldNameArrA.add("item2");


        // 数据集合
        List<Map<String, String>> listItemsByTypeA = new ArrayList<Map<String, String>>();

        // 第一行数据
        Map<String, String> baseA1 = new HashMap<String, String>();
        baseA1.put("item1", "躯体化");
        baseA1.put("item2", s1+"");


        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "强迫症状");
        baseA2.put("item2", s2+"");


        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "人际关系敏感");
        baseA3.put("item2", s3+"");
        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "抑郁");
        baseA4.put("item2", s4+"");

        // 第五行数据
        Map<String, String> baseA5 = new HashMap<String, String>();
        baseA5.put("item1", "焦虑");
        baseA5.put("item2", s5+"");

        // 第六行数据
        Map<String, String> baseA6= new HashMap<String, String>();
        baseA6.put("item1", "敌对");
        baseA6.put("item2", s6+"");

        // 第七行数据
        Map<String, String> baseA7 = new HashMap<String, String>();
        baseA7.put("item1", "恐怖");
        baseA7.put("item2", s7+"");

        Map<String, String> baseA8 = new HashMap<String, String>();
        baseA8.put("item1", "偏执");
        baseA8.put("item2", s8+"");

        Map<String, String> baseA9 = new HashMap<String, String>();
        baseA9.put("item1", "精神病性");
        baseA9.put("item2", s9+"");

        Map<String, String> baseA10 = new HashMap<String, String>();
        baseA10.put("item1", "其他");
        baseA10.put("item2", s10+"");
        listItemsByTypeA.add(baseA1);
        listItemsByTypeA.add(baseA2);
        listItemsByTypeA.add(baseA3);
        listItemsByTypeA.add(baseA4);
        listItemsByTypeA.add(baseA5);
        listItemsByTypeA.add(baseA6);
        listItemsByTypeA.add(baseA7);
        listItemsByTypeA.add(baseA8);
        listItemsByTypeA.add(baseA9);
        listItemsByTypeA.add(baseA10);
        // 获取word模板中的所有图表元素，用map存放
        // 为什么不用list保存：查看doc.getRelations()的源码可知，源码中使用了hashMap读取文档图表元素，
        // 对relations变量进行打印后发现，图表顺序和文档中的顺序不一致，也就是说relations的图表顺序不是文档中从上到下的顺序
        Map<String, POIXMLDocumentPart> chartsMap = new HashMap<String, POIXMLDocumentPart>();
        //动态刷新图表
        List<POIXMLDocumentPart> relations = doc.getRelations();
        for (POIXMLDocumentPart poixmlDocumentPart : relations) {
            if (poixmlDocumentPart instanceof XWPFChart) {  // 如果是图表元素
                String str = poixmlDocumentPart.toString();
                System.out.println("str：" + str);
                String key = str.replaceAll("Name: ", "")
                        .replaceAll(" - Content Type: application/vnd\\.openxmlformats-officedocument\\.drawingml\\.chart\\+xml", "").trim();
                System.out.println("key：" + key);

                chartsMap.put(key, poixmlDocumentPart);
            }
        }

        System.out.println("\n图表数量：" + chartsMap.size() + "\n");


        /*POIXMLDocumentPart poixmlDocumentPart1 = chartsMap.get("/word/charts/chart1.xml");
        new PoiWordTools().replaceBarCharts(poixmlDocumentPart1, titleArr, fldNameArr, listItemsByType);*/
        POIXMLDocumentPart poixmlDocumentPart2 = chartsMap.get("/word/charts/chart1.xml");
        new PoiWordTools().replaceLineCharts(poixmlDocumentPart2, titleArrA, fldNameArrA, listItemsByTypeA);
    }
}
