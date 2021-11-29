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

public class BprsWord {

    public static String getDld(ScalePatient scalePatient , ScaleTask scaleTask,String templateurl,List<ScaleScore> scaleScoreList) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String filenameRandom= RandomStringUtils.randomNumeric(6);
        String fileName=scalePatient.getPatientName()+"_"+scaleTask.getTestCoding()+filenameRandom+"_bprs.docx";  // 结果文件
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
        Long scoreE=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("1")||scaleScore.getTitle().equals("2")||scaleScore.getTitle().equals("5")||scaleScore.getTitle().equals("9")){
                scoreA+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("3")||scaleScore.getTitle().equals("13")||scaleScore.getTitle().equals("16")||scaleScore.getTitle().equals("18")){
                scoreB+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("4")||scaleScore.getTitle().equals("8")||scaleScore.getTitle().equals("12")||scaleScore.getTitle().equals("15")){
                scoreC+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("6")||scaleScore.getTitle().equals("7")||scaleScore.getTitle().equals("17")){
                scoreD+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("10")||scaleScore.getTitle().equals("11")||scaleScore.getTitle().equals("14")){
                scoreE+=scaleScore.getScore();
            }
        }
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        String str="";
        List<String>strings=new ArrayList<>();

        Long score=scoreA+scoreB+scoreC+scoreD+scoreE;
        if (score<=18){
            string="表明无精神病性症状。";
        }else if (scoreA>16||scoreB>16||scoreC>16||scoreD>12||scoreE>12){
            if (scoreA>16){
                str+="焦虑忧郁：感到抑郁苦闷，交谈时表现出沮丧的神态、悲伤的面容或心灰意懒的模样。对以往言行过分关心、内疚或懊悔，感到焦虑无法松弛，可能出现交感神经活动亢进的生理体征，如：手掌出汗、轻度颤抖、皮肤发红等，严重时会出现运动性激越。";
            }
            if (scoreB>16){
                str+="缺乏活力：无法实现正常的情感交流，感情基调低，缺乏相应的正常情感反应，言语动作和行为表现减少和缓慢，对人物、地点或时间可能分辨不清。";
            }
            if (scoreC>16){
                str+="思维障碍：表现为联想困难、对问题反应迟钝，有时概念停留很长时间，不能顺利地表达出来，有时再三提问，才能得到回答。或确信自己具有不寻常的才能和权力，出现幻觉或古怪的思维内容。";
            }
            if (scoreD>16){
                str+="激活性：出现躯体性焦虑，如：心跳加快、胸闷、呼吸不畅，甚至有窒息的濒危感，不寻常的或不自然的运动性行为，情感基调增高、激动，对外界反应增强。";
            }
            if (scoreE>16){
                str+="敌对猜疑：对他人的仇恨、敌对和蔑视，认为有人正在或曾经恶意地对待自己，交谈时表现不友好，不满意或不合作的态度。";
            }
            string="表明："+str;
        }else {
            if(scoreA>4&&scoreA<=16){
                strings.add("焦虑忧郁");
            }
            if(scoreB>4&&scoreA<=16){
                strings.add("缺乏活力");
            }
            if(scoreC>4&&scoreA<=16){
                strings.add("思维障碍");
            }
            if(scoreD>4&&scoreA<=12){
                strings.add("激活性");
            }
            if(scoreE>4&&scoreA<=12){
                strings.add("敌对猜疑");
            }
            for (String s:strings){
                if (s!=strings.get(strings.size()-1)){
                    str+=(s+"、");
                }else {
                    str+=s;
                }
            }
            string="其中在"+str+"方面表现出一定的临床症状，需引起重视。";
        }
        textMap.put("var", "本次测验的总分为"+score+"分，"+string);
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
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {

                        // 替换文本信息
                        String tempText = text;
                        String key = tempText.replaceAll("\\{\\{", "").replaceAll("}}", "");
                        if (!StringUtils.isEmpty(textMap.get(key))) {
                            run.setText(textMap.get(key), 0);
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
        Long scoreA=0L;
        Long scoreB=0L;
        Long scoreC=0L;
        Long scoreD=0L;
        Long scoreE=0L;
        for (ScaleScore scaleScore:scaleScoreList){
            if (scaleScore.getTitle().equals("1")||scaleScore.getTitle().equals("2")||scaleScore.getTitle().equals("5")||scaleScore.getTitle().equals("9")){
                scoreA+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("3")||scaleScore.getTitle().equals("13")||scaleScore.getTitle().equals("16")||scaleScore.getTitle().equals("18")){
                scoreB+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("4")||scaleScore.getTitle().equals("8")||scaleScore.getTitle().equals("12")||scaleScore.getTitle().equals("15")){
                scoreC+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("6")||scaleScore.getTitle().equals("7")||scaleScore.getTitle().equals("17")){
                scoreD+=scaleScore.getScore();
            }else if (scaleScore.getTitle().equals("10")||scaleScore.getTitle().equals("11")||scaleScore.getTitle().equals("14")){
                scoreE+=scaleScore.getScore();
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
        baseA1.put("item1", "焦虑忧郁");
        baseA1.put("item2", scoreA+"");


        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "缺乏活力");
        baseA2.put("item2", scoreB+"");


        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "思维障碍");
        baseA3.put("item2", scoreC+"");
        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "激活性");
        baseA4.put("item2", scoreD+"");

        // 第五行数据
        Map<String, String> baseA5 = new HashMap<String, String>();
        baseA5.put("item1", "敌对猜疑");
        baseA5.put("item2", scoreE+"");

        // 第六行数据
//        Map<String, String> baseA6= new HashMap<String, String>();
//        baseA6.put("item1", "攻击性");
//        baseA6.put("item2", "24");


        listItemsByTypeA.add(baseA1);
        listItemsByTypeA.add(baseA2);
        listItemsByTypeA.add(baseA3);
        listItemsByTypeA.add(baseA4);
        listItemsByTypeA.add(baseA5);
//        listItemsByTypeA.add(baseA6);

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
