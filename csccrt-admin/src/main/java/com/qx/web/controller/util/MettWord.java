package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.demo.entity.MettDomain;
import com.qx.patient.domain.PatientUser;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;

public class MettWord {

    public static String getDld(MettDomain mettDomain, String templateurl) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        PatientUser patientUser=mettDomain.getPatientUser();
        String fileName=patientUser.getPatientName()+"_"+patientUser.getTestCoding()+"_mett.docx";  // 结果文件
        String returnurl= ProjectConfig.getDownloadPath()+fileName;
        // 替换word模板数据
        XWPFDocument doc=null;
        try {
            InputStream is = new FileInputStream(new File(templateurl));
             doc = new XWPFDocument(is);
            replaceAll(doc,patientUser,mettDomain);
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
    public static void replaceAll(XWPFDocument doc,PatientUser patientUser,MettDomain mettDomain) throws InvalidFormatException, IOException {
        doParagraphs(doc,patientUser,mettDomain); // 处理段落文字数据，包括文字和表格、图片
        doCharts(doc,mettDomain);  // 处理图表数据，柱状图、折线图、饼图啊之类的
    }


    /**
     * 处理段落文字
     *
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void doParagraphs(XWPFDocument doc,PatientUser patientUser,MettDomain mettDomain) {
         if("0".equals(patientUser.getSex())){
             patientUser.setSex("男");
        }else if("1".equals(patientUser.getSex())){
             patientUser.setSex("女");
         }
         if("0".equals(patientUser.getMaritalStatus())){
             patientUser.setMaritalStatus("未婚");
         }else{
             patientUser.setMaritalStatus("已婚");
         }
         if(patientUser.getHospitalNumber()==null){
             patientUser.setHospitalNumber(0l);
         }
         if(patientUser.getWard()==null){
             patientUser.setWard("");
         }
       /* List<Map.Entry<String,Double>> list1 = new ArrayList<Map.Entry<String,Double>>(mettDomain.getMap1().entrySet());
        Collections.sort(list1,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        String s1="";
        for(Map.Entry<String,Double> mapping:list1){
            s1+=mapping.getKey()+"、";
        }

        List<Map.Entry<String,Double>> list2 = new ArrayList<Map.Entry<String,Double>>(mettDomain.getMap2().entrySet());
        Collections.sort(list2,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        String s2="";
        for(Map.Entry<String,Double> mapping:list2){
            s2+=mapping.getKey()+"、";
        }
        s2.substring(0, s2.length()-1);
*/
        List<Map.Entry<String,Double>> list1 = new ArrayList<Map.Entry<String,Double>>(mettDomain.getMap1().entrySet());
        Collections.sort(list1,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        String s1="";
        String s2="";
        String s3="";
        String s4="";
        Double a=0.0;
        for(Map.Entry<String,Double> mapping:list1) {
            a += mapping.getValue();
            if (mapping.getValue() <= 0.5) {
                s2 += mapping.getKey() + "、";
            } else if (mapping.getValue() > 0.5 && mapping.getValue() < 0.8) {
                s3 += mapping.getKey() + "、";
            } else if (mapping.getValue() >= 0.8) {
                s4 += mapping.getKey() + "、";
            }
        }
        double c=a/7;
        if(c<=0.5){
            s1="较低水平";
        }else if(c>0.5 && c<0.8){
            s1="中等水平";
        }else {
            s1="较高水平";
        }
        if(!"".equals(s4)){
            s4=s4.substring(0, s4.length()-1)+"的识别处于较高水平，";
        }
        if(!"".equals(s3)){
            s3=s3.substring(0, s3.length()-1)+"的识别处于中等水平，";
        }
        if(!"".equals(s2)){
            s2=s2.substring(0, s2.length()-1)+"的识别处于较低水平。";
        }
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();

            /*textMap.put("var", "情绪识别任务旨在测量个体识别基本表情的能力。本次测试结果表明，您对基本表情的识别正确率由高到低依次是："+s1.substring(0, s1.length()-1)+"。反应时由低到高依次是："+s2.substring(0, s2.length()-1)+"。您对表情的识别正确率越高，且反应时越快，说明您对该表情的识别能力越好。");*/
            textMap.put("var","情绪识别任务旨在测量个体识别基本表情的能力。在本次测试中，" +
                    "您的情绪识别能力总体处于"+s1+"，"+
                    "其中对"+s4+s3+s2);
            textMap.put("var1",mettDomain.getCreateTime());
        // 图片数据
        Map<String, String> imgMap = new HashMap<String, String>();
        imgMap.put("img", "D:\\360Downloads\\aaa.jpg");
        //段落
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        doParagraph(paragraphList, doc, textMap, imgMap,patientUser);

        //表格
        List<XWPFTable> tables = doc.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                    doParagraph(paragraphs, doc, textMap, imgMap,patientUser);
                }
            }
        }
    }
    public static void doParagraph(List<XWPFParagraph> paragraphList, XWPFDocument
            doc, Map < String, String > textMap, Map<String,String> imgMap,PatientUser patientUser){
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
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "姓名："+patientUser.getPatientName());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "性别："+patientUser.getSex());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "婚姻："+patientUser.getMaritalStatus());

                            // 表格第二行
                            XWPFTableRow tableOneRowTwo = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "出生年月："+patientUser.getBirth());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "文化程度："+patientUser.getEducation());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "职业："+patientUser.getJob());

                            // 表格第三行
                            XWPFTableRow tableOneRowThree = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "住院号："+patientUser.getHospitalNumber());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "病区："+patientUser.getWard());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "心测号："+patientUser.getTestCoding());


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
    public static void doCharts(XWPFDocument doc,MettDomain mettDomain) {
        /**----------------------------处理图表------------------------------------**/
        // 数据准备
        List<String> titleArr = new ArrayList<String>();// 标题
        /*titleArr.add("姓名");
        titleArr.add("欠款");
        titleArr.add("存款");*/

        List<String> fldNameArr = new ArrayList<String>();// 字段名
        fldNameArr.add("item1");
        //fldNameArr.add("item2");
        fldNameArr.add("item3");

        // 数据集合
        List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();

        // 第一行数据
        Map<String, String> base1 = new HashMap<String, String>();
        base1.put("item1", "高兴");
        base1.put("item3",mettDomain.getList().get(1).get("高兴")+"");

        // 第二行数据
        Map<String, String> base2 = new HashMap<String, String>();
        base2.put("item1", "悲伤");
        base2.put("item3", mettDomain.getList().get(1).get("悲伤")+"");

        // 第三行数据
        Map<String, String> base3 = new HashMap<String, String>();
        base3.put("item1", "愤怒");
        //base3.put("item2", mettDomain.getList().get(2).get("愤怒").get(0)+"");
        base3.put("item3", mettDomain.getList().get(1).get("愤怒")+"");
       // 第四行数据
        Map<String, String> base4 = new HashMap<String, String>();
        base4.put("item1", "厌恶");
        //base4.put("item2", mettDomain.getList().get(3).get("厌恶").get(0)+"");
        base4.put("item3", mettDomain.getList().get(1).get("厌恶")+"");
        // 第五行数据
        Map<String, String> base5 = new HashMap<String, String>();
        base5.put("item1", "惊讶");
        base5.put("item3", mettDomain.getList().get(1).get("惊讶")+"");
        // 第六行数据
        Map<String, String> base6= new HashMap<String, String>();
        base6.put("item1", "恐惧");
        base6.put("item3", mettDomain.getList().get(1).get("恐惧")+"");
        // 第七行数据
        Map<String, String> base7 = new HashMap<String, String>();
        base7.put("item1", "中性");
        base7.put("item3", mettDomain.getList().get(1).get("中性")+"");

        listItemsByType.add(base1);
        listItemsByType.add(base2);
        listItemsByType.add(base3);
        listItemsByType.add(base4);
        listItemsByType.add(base5);
        listItemsByType.add(base6);
        listItemsByType.add(base7);
/*====================================================================================================*/
       List<String> titleArrA = new ArrayList<String>();// 标题
        /*titleArrA.add("姓名");
        titleArrA.add("欠款");
        titleArrA.add("存款");*/
        List<String> fldNameArrA = new ArrayList<String>();// 字段名
        fldNameArrA.add("item1");
        fldNameArrA.add("item2");


        // 数据集合
        List<Map<String, String>> listItemsByTypeA = new ArrayList<Map<String, String>>();

        // 第一行数据
        Map<String, String> baseA1 = new HashMap<String, String>();
        baseA1.put("item1", "高兴");
        baseA1.put("item2", mettDomain.getList().get(0).get("高兴")+"");


        // 第二行数据
        Map<String, String> baseA2 = new HashMap<String, String>();
        baseA2.put("item1", "悲伤");
        baseA2.put("item2", mettDomain.getList().get(0).get("悲伤")+"");


        // 第三行数据
        Map<String, String> baseA3 = new HashMap<String, String>();
        baseA3.put("item1", "愤怒");
        baseA3.put("item2", mettDomain.getList().get(0).get("愤怒")+"");
        // 第四行数据
        Map<String, String> baseA4 = new HashMap<String, String>();
        baseA4.put("item1", "厌恶");
        baseA4.put("item2", mettDomain.getList().get(0).get("厌恶")+"");

        // 第五行数据
        Map<String, String> baseA5 = new HashMap<String, String>();
        baseA5.put("item1", "惊讶");
        baseA5.put("item2", mettDomain.getList().get(0).get("惊讶")+"");

        // 第六行数据
        Map<String, String> baseA6= new HashMap<String, String>();
        baseA6.put("item1", "恐惧");
        baseA6.put("item2", mettDomain.getList().get(0).get("恐惧")+"");

        // 第七行数据
        Map<String, String> baseA7 = new HashMap<String, String>();
        baseA7.put("item1", "中性");
        baseA7.put("item2", mettDomain.getList().get(0).get("中性")+"");
        listItemsByTypeA.add(baseA1);
        listItemsByTypeA.add(baseA2);
        listItemsByTypeA.add(baseA3);
        listItemsByTypeA.add(baseA4);
        listItemsByTypeA.add(baseA5);
        listItemsByTypeA.add(baseA6);
        listItemsByTypeA.add(baseA7);
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


        POIXMLDocumentPart poixmlDocumentPart1 = chartsMap.get("/word/charts/chart1.xml");
        new PoiWordTools().replaceBarCharts(poixmlDocumentPart1, titleArr, fldNameArr, listItemsByType);
        POIXMLDocumentPart poixmlDocumentPart2 = chartsMap.get("/word/charts/chart2.xml");
        new PoiWordTools().replaceLineCharts(poixmlDocumentPart2, titleArrA, fldNameArrA, listItemsByTypeA);
    }
}
