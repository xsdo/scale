package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.scale.domain.ScalePatient;
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
import java.text.SimpleDateFormat;
import java.util.*;

public class SapsWord {

    public static String getDld(ScalePatient scalePatient , ScaleTask scaleTask,String templateurl,List<ScaleScore> scaleScoreList) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String filenameRandom= RandomStringUtils.randomNumeric(6);
        String fileName=scalePatient.getPatientName()+"_"+scaleTask.getTestCoding()+filenameRandom+"_saps.docx";  // 结果文件
        String returnurl= ProjectConfig.getDownloadPath()+fileName;
        // 替换word模板数据
        XWPFDocument doc=null;
        try {
            InputStream is = new FileInputStream(new File(templateurl));
             doc = new XWPFDocument(is);
            replaceAll(doc,scalePatient,scaleTask,scaleScoreList);
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
    public static void replaceAll(XWPFDocument doc,ScalePatient scalePatient ,ScaleTask scaleTask,List<ScaleScore> scaleScoreList) throws InvalidFormatException, IOException {
        doParagraphs(doc,scalePatient,scaleTask,scaleScoreList); // 处理段落文字数据，包括文字和表格、图片
        doCharts(doc,scaleScoreList);  // 处理图表数据，柱状图、折线图、饼图啊之类的
    }


    /**
     * 处理段落文字
     *
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void doParagraphs(XWPFDocument doc,ScalePatient scalePatient ,ScaleTask scaleTask,List<ScaleScore> scaleScoreList) {
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
        //
        Long scoreA=0L;
        Long scoreB=0L;
        Long scoreC=0L;
        Long scoreD=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("1")||scaleScore.getTitle().equals("2")||scaleScore.getTitle().equals("3")||
                    scaleScore.getTitle().equals("4")||scaleScore.getTitle().equals("5")||scaleScore.getTitle().equals("6")){
                scoreA+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("8")||scaleScore.getTitle().equals("9")||scaleScore.getTitle().equals("10")
                    ||scaleScore.getTitle().equals("11")||scaleScore.getTitle().equals("12")||scaleScore.getTitle().equals("13")
                    ||scaleScore.getTitle().equals("14")||scaleScore.getTitle().equals("15")||scaleScore.getTitle().equals("16")
                    ||scaleScore.getTitle().equals("17")||scaleScore.getTitle().equals("18")||scaleScore.getTitle().equals("19")){
                scoreB+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("21")||scaleScore.getTitle().equals("22")||scaleScore.getTitle().equals("23")||scaleScore.getTitle().equals("24")){
                scoreC+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("26")||scaleScore.getTitle().equals("27")||scaleScore.getTitle().equals("28")
                    ||scaleScore.getTitle().equals("29")||scaleScore.getTitle().equals("30")||scaleScore.getTitle().equals("31")
                    ||scaleScore.getTitle().equals("32")||scaleScore.getTitle().equals("33")){
                scoreD+=scaleScore.getScore();
            }
        }
        //
        Long score1=0L;
        Long score2=0L;
        Long score3=0L;
        Long score4=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("7")){
                score1=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("20")){
                score2=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("25")){
                score3=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("34")){
                score4=scaleScore.getScore();
            }
        }
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        String str="";
        String str1="";
        String n="\r";
        List<String>strings=new ArrayList<>();

        if (score1<3&&score2<3&&score3<3&&score4<3){
            string="无明显阳性症状。";
        }else {
            if (score1>=3){
                strings.add("幻觉");
                str1+=n+"幻觉：知觉异常，即在没有相应外界刺激的情况下发生虚幻的知觉。听到语声、杂音或其他声音；体验到特殊的躯体感觉，如灼烧感、刺痛感，或感到身体的形状或大小发生变化；能闻到令其不愉快的气味；能看见实际上并不存在的人或物。";
            }
            if (score2>=3){
                strings.add("妄想");
                str1+=n+"妄想：思维内容异常，即存在不能以自身文化背景来解释的错误信念。感到被人跟踪或迫害；认为配偶与某人有不正当的男女关系；自认犯有可怕的罪行或拥有特殊的权利或能力；沉湎于带宗教色彩的错误信念；自认身体有病或不正常；认为无关紧要的谈话、评述或事件都与自己有关；体验到感情或行动被外界力量控制；认为人们能读出自己的心理；相信自己思想被广播，被抽走或被插入。";
            }
            if (score3>=3){
                strings.add("怪异行为");
                str1+=n+"怪异行为：行为很不寻常，怪异或带幻想性。衣着奇特或以其他稀奇古怪的方式来改变其外观；做出一些与社会一般规范不相称的事；行为方式具有攻击性和激越性，常常难以预料；做出一套重复性或仪式性的动作，往往认为这些动作有象征意义，或可以影响他人，或可使自己免受影响。";
            }
            if (score4>=3){
                strings.add("阳性思维形式障碍");
                str1+=n+"阳性思维形式障碍：讲话虽流利，但常突然从一个话题滑到另一间接有关或完全无关的话题；对问题的回答含糊、不切题，甚至无关；言语不连贯；言语的推理结论明显不合逻辑；表达主题迂回曲折；与日常习惯相比，自发性语量较多，语速快且难打断；在交谈时，话讲到一半就转移到有关周围事物的主题上；根据词音而不是词意来选用词汇，言语含糊，难以理解。";
            }
            for (String s:strings){
                if (s!=strings.get(strings.size()-1)){
                    str+=(s+"、");
                }else {
                    str+=s;
                }
            }
            string="其中在"+str+"症状上（呈现分量表总评项≥3的症状）表现突出。具体表现为："+str1;
        }
        Long score =scoreA+scoreB+scoreC+scoreD;
        textMap.put("varA",scoreA+"");
        textMap.put("varB",scoreB+"");
        textMap.put("varC",scoreC+"");
        textMap.put("varD",scoreD+"");
        textMap.put("varE",score+"");
        textMap.put("var", "本次测验总分为"+score+"分，"+string);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy.MM.dd");
        Date date =new Date();
        String f=sdf.format(date);
            textMap.put("var1",f);
        // 图片数据
        Map<String, String> imgMap = new HashMap<String, String>();
        imgMap.put("img", "D:\\360Downloads\\aaa.jpg");
        //段落
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        doParagraph(paragraphList, doc, textMap, imgMap,scalePatient);

        //表格
        List<XWPFTable> tables = doc.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                    doParagraph(paragraphs, doc, textMap, imgMap,scalePatient);
                }
            }
        }
    }
    public static void doParagraph(List<XWPFParagraph> paragraphList, XWPFDocument
            doc, Map < String, String > textMap, Map<String,String> imgMap,ScalePatient scalePatient){
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
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "姓名："+scalePatient.getPatientName());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "性别："+scalePatient.getSex());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "婚姻："+scalePatient.getMaritalStatus());

                            // 表格第二行
                            XWPFTableRow tableOneRowTwo = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "年龄："+scalePatient.getAge());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "文化程度："+scalePatient.getEducation());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "职业："+scalePatient.getJob());

                            // 表格第三行
                            XWPFTableRow tableOneRowThree = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "住院号："+scalePatient.getHospitalNumber());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "病区："+scalePatient.getWard());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "来源："+scalePatient.getSource());


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
        Long score1=0L;
        Long score2=0L;
        Long score3=0L;
        Long score4=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("7")){
                score1=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("20")){
                score2=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("25")){
                score3=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("34")){
                score4=scaleScore.getScore();
            }
        }
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
        baseA1.put("item1", "幻觉");
        baseA1.put("item2", score1+"");

        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "妄想");
        baseA2.put("item2", score2+"");

        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "怪异行为");
        baseA3.put("item2", score3+"");

        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "阳性思维形式障碍");
        baseA4.put("item2", score4+"");

        listItemsByTypeA.add(baseA1);
        listItemsByTypeA.add(baseA2);
        listItemsByTypeA.add(baseA3);
        listItemsByTypeA.add(baseA4);


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
        new PoiWordTools().replaceBarCharts(poixmlDocumentPart2, titleArrA, fldNameArrA, listItemsByTypeA);
    }
}
