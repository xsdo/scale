package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.domain.ScaleScore;
import com.qx.scale.domain.ScaleTask;
import com.qx.scale.mapper.ScaleScoreMapper;
import com.qx.scale.mapper.ScaleTaskMapper;
import com.qx.scale.service.IScaleScoreService;
import com.qx.scale.service.IScaleTaskScoreService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GhqWord {


    public static String getDld(ScalePatient scalePatient , ScaleTask scaleTask,String templateurl,List<ScaleScore> scaleScoreList) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String filenameRandom= RandomStringUtils.randomNumeric(6);
        String fileName=scalePatient.getPatientName()+"_"+scaleTask.getTestCoding()+filenameRandom+"_ghq12.docx";  // 结果文件
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

        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        String str="";

        Long score=0L;
        //测验分类分数数据
        Map<String,String> strMap=new HashMap<>();
        strMap.put("1","睡眠减少：近期您的睡眠时间较以往有所较少。");
        strMap.put("2","精神紧张：近期您容易担心焦虑，可能伴有心慌、胸闷及头晕等躯体症状。");
        strMap.put("3","注意力不集中：近期您感觉到容易分心，不能集中注意力做事。");
        strMap.put("4","无用感：近期您感觉自己在各方面都起不到很有用的作用。");
        strMap.put("5","不能面对问题：近期您感觉到很难面对自己的问题，常有逃避的想法。");
        strMap.put("6","决定困难：近期您感到自己很难做出决定，面对选择或决定常犹豫不绝。");
        strMap.put("7","不能克服困难：近期您感到自己很无力，不能解决所遇到的困难。");
        strMap.put("8","愉悦感丧失：最近您经常感到不开心，感觉做什么事情都无法让自己愉悦。");
        strMap.put("9","兴趣丧失：最近您感觉对什么事情都提不起兴趣，感到没有什么想做的事。");
        strMap.put("10","抑郁：近期您感觉生活苦闷，兴趣减退，动力缺乏，失望悲观等。");
        strMap.put("11","自信心丧失：近期您感觉不自信，不相信自己能做到或完成事情。");
        strMap.put("12","无价值感：近期您感到自己是个没用的人，找不到自身的价值。");
        for (int i=0;i<scaleScoreList.size();i++){
            textMap.put("var"+scaleScoreList.get(i).getTitle(),scaleScoreList.get(i).getScore()+string);
            score+=scaleScoreList.get(i).getScore();
            if (scaleScoreList.get(i).getScore()==1){
                str+="\r"+score+"、"+strMap.get(scaleScoreList.get(i).getTitle());
            }
        }
//        XWPFRun R=doc.createParagraph().createRun();
        if (score==0){
            textMap.put("var", "本次测验总分为"+score+"分，具临床意义的症状有"+score+"项。表明近期您的身心处于健康水平，请继续保持！");
        }else {
            textMap.put("var", "本次测验总分为"+score+"分，具临床意义的症状有"+score+"项。"+"\r"+"表明："
            +str);
//            R.addBreak();
        }

        SimpleDateFormat sdf =new SimpleDateFormat("yyyy.MM.dd");
        Date date =new Date();
        String f=sdf.format(date);
            textMap.put("varA",f);

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
        baseA1.put("item1", "躯体化");
        baseA1.put("item2", "2");


        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "强迫症状");
        baseA2.put("item2", "4");


        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "人际关系敏感");
        baseA3.put("item2", "4");
        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "抑郁");
        baseA4.put("item2", "3.77");

        // 第五行数据
        Map<String, String> baseA5 = new HashMap<String, String>();
        baseA5.put("item1", "焦虑");
        baseA5.put("item2", "2.70");

        // 第六行数据
        Map<String, String> baseA6= new HashMap<String, String>();
        baseA6.put("item1", "敌对");
        baseA6.put("item2", "3");

        // 第七行数据
        Map<String, String> baseA7 = new HashMap<String, String>();
        baseA7.put("item1", "恐怖");
        baseA7.put("item2", "1");

        Map<String, String> baseA8 = new HashMap<String, String>();
        baseA8.put("item1", "偏执");
        baseA8.put("item2", "1");

        Map<String, String> baseA9 = new HashMap<String, String>();
        baseA9.put("item1", "精神病性");
        baseA9.put("item2", "1");

        Map<String, String> baseA10 = new HashMap<String, String>();
        baseA10.put("item1", "其他");
        baseA10.put("item2", "1");
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
