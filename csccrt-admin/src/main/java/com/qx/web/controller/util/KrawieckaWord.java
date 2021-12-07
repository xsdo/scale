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

public class KrawieckaWord {

    public static String getDld(ScalePatient scalePatient , ScaleTask scaleTask,String templateurl,List<ScaleScore> scaleScoreList) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String filenameRandom= RandomStringUtils.randomNumeric(6);
        String fileName=scalePatient.getPatientName()+"_"+scaleTask.getTestCoding()+filenameRandom+"_krawiecka.docx";  // 结果文件
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
//        doCharts(doc);  // 处理图表数据，柱状图、折线图、饼图啊之类的
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
        Long scoreE=0L;
        Long scoreF=0L;
        Long scoreG=0L;
        Long scoreH=0L;

        Long score=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("1")){ scoreA=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("2")){ scoreB=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("3")){ scoreC=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("4")){ scoreD=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("5")){ scoreE=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("6")){ scoreF=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("7")){ scoreG=scaleScore.getScore(); }
            if (scaleScore.getTitle().equals("8")){ scoreH=scaleScore.getScore(); }
        }
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        String str="";
        String str1="";
        String n="\r";
        List<String>strings=new ArrayList<>();

        if (scoreA<2&&scoreB<2&&scoreC<2&&scoreD<2&&scoreE<2&&scoreF<2&&scoreG<2&&scoreH<2){
            string="无明显的精神病性症状。";
        }else if (scoreA<3&&scoreB<3&&scoreC<3&&scoreD<3&&scoreE<3&&scoreF<3&&scoreG<3&&scoreH<3){
            if (scoreA==2){strings.add("抑郁");}
            if (scoreB==2){strings.add("焦虑");}
            if (scoreC==2){strings.add("情感平淡或不协调");}
            if (scoreD==2){strings.add("精神运动性迟缓");}
            if (scoreE==2){strings.add("妄想");}
            if (scoreF==2){strings.add("幻觉");}
            if (scoreG==2){strings.add("言语散漫或不连贯");}
            if (scoreH==2){strings.add("言语贫乏与缄默");}
            for (String s:strings){
                if (s!=strings.get(strings.size()-1)){
                    str+=(s+"、");
                }else {
                    str+=s;
                }
            }
            string="其中"+str+"症状（得分为2的项目）达到病态程度。";
        }else {
            if (scoreA>=3){ strings.add("抑郁");
            str1+=n+"抑郁：感到抑郁苦闷，交谈时表现出沮丧的神态、悲伤的面容或心灰意懒的模样。";
            }
            if (scoreB>=3){strings.add("焦虑");
                str1+=n+"焦虑：感到焦虑无法松弛，可能出现交感神经活动亢进的生理体征，如：手掌出汗、轻度颤抖、皮肤发红等，严重时会出现运动性激越。";
            }
            if (scoreC>=3){strings.add("情感平淡或不协调");
                str1+=n+"情感平淡或不协调：应该有的情感反应在幅度和范围上有缺损，在谈到自己情况时并不表达出客观事件对自己的心理影响，如谈到亲近的人们时没有热情。";
            }
            if (scoreD>=3){strings.add("精神运动性迟缓");
                str1+=n+"精神运动性迟缓：表现为思维、语言及—般动作的缓慢或缺乏主动性，严重时发展到抑郁性、木僵，此时自主动作完全消失。";
            }
            if (scoreE>=3){strings.add("妄想");
                str1+=n+"妄想：表现为思维内容的异常，会产生一些错误观念，及坚信的妄想。";
            }
            if (scoreF>=3){strings.add("幻觉");
                str1+=n+"幻觉：表现为知觉方面的异常，在没有相应外界刺激的情况下发生虚幻的知觉。";
            }
            if (scoreG>=3){ strings.add("言语散漫或不连贯");
                str1+=n+"言语散漫或不连贯：言语上杂乱，没有主题，交流时言语不连贯，让人难以理解。";
            }
            if (scoreH>=3){strings.add("言语贫乏与缄默");
                str1+=n+"言语贫乏与缄默：自发言语的语量有限，回答问题简单肤浅，很少有自发的补充说明，很可能是单词甚至不回答，严重时很难做有意义的交谈。";
            }
            for (String s:strings){
                if (s!=strings.get(strings.size()-1)){
                    str+=(s+"、");
                }else {
                    str+=s;
                }
            }
            string="其中在"+str+"症状上（呈现得分≥3的项目）表现突出。具体表现为："+str1;
        }
        score=scoreA+scoreB+scoreC+scoreD+scoreE+scoreF+scoreG+scoreH;
        textMap.put("varA",scoreA+"");
        textMap.put("varB",scoreB+"");
        textMap.put("varC",scoreC+"");
        textMap.put("varD",scoreD+"");
        textMap.put("varE",scoreE+"");
        textMap.put("varF",scoreF+"");
        textMap.put("varG",scoreG+"");
        textMap.put("varH",scoreH+"");
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
    public static void doCharts(XWPFDocument doc) {
        /**----------------------------处理图表------------------------------------**/
        // 数据准备
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
        baseA1.put("item1", "反应缺乏");
        baseA1.put("item2", "12");


        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "思维障碍");
        baseA2.put("item2", "20");


        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "激活性");
        baseA3.put("item2", "12");
        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "偏执");
        baseA4.put("item2", "15");

        // 第五行数据
        Map<String, String> baseA5 = new HashMap<String, String>();
        baseA5.put("item1", "抑郁");
        baseA5.put("item2", "16");

        // 第六行数据
        Map<String, String> baseA6= new HashMap<String, String>();
        baseA6.put("item1", "攻击性");
        baseA6.put("item2", "24");


        listItemsByTypeA.add(baseA1);
        listItemsByTypeA.add(baseA2);
        listItemsByTypeA.add(baseA3);
        listItemsByTypeA.add(baseA4);
        listItemsByTypeA.add(baseA5);
        listItemsByTypeA.add(baseA6);

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
