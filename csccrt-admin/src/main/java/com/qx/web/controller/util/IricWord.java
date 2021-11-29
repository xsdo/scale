package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.demo.entity.IricDomain;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IricWord {

    public static String getDld(IricDomain iricDomain, String templateurl) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        PatientUser patientUser=iricDomain.getPatientUser();
        String fileName=patientUser.getPatientName()+"_"+patientUser.getTestCoding()+"_iric.docx";  // 结果文件
        String returnurl= ProjectConfig.getDownloadPath()+fileName;
        // 替换word模板数据
        XWPFDocument doc=null;
        try {
            InputStream is = new FileInputStream(new File(templateurl));
             doc = new XWPFDocument(is);
            replaceAll(doc,patientUser,iricDomain);
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
    public static void replaceAll(XWPFDocument doc,PatientUser patientUser,IricDomain iricDomain) throws InvalidFormatException, IOException {
        doParagraphs(doc,patientUser,iricDomain); // 处理段落文字数据，包括文字和表格、图片
        doCharts(doc,iricDomain);  // 处理图表数据，柱状图、折线图、饼图啊之类的
    }


    /**
     * 处理段落文字
     *
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void doParagraphs(XWPFDocument doc,PatientUser patientUser,IricDomain iricDomain) {
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
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        String string="";
        if(iricDomain.getSum()>64){
            string=string+"共情能力很强。";
        }else if(44<iricDomain.getSum() && iricDomain.getSum()<=64){
            string=string+"共情能力较强。";
        }else if(24<=iricDomain.getSum() && iricDomain.getSum()<=44){
            string=string+"共情能力较差。";
        }else{
            string=string+"共情能力很差。";
        }
            textMap.put("var", "人际反应指针问卷旨在测量个体的认知共情和情感共情能力。包括个体自发地站在他人角度看问题的一种倾向；对虚构人物的感同身受的反应；对他人的温暖、同情和关心程度；看到他人负性生活经历时，所产生的恐惧、焦虑与不快程度。得分越高表明个体所具有的共情能力越强。该问卷总分为88分，本次测量总分为"+iricDomain.getSum()+"分，"+string);
            textMap.put("var1",iricDomain.getCreateTime());
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
        /**----------------------------处理段落------------------------------------**/
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
    public static void doCharts(XWPFDocument doc,IricDomain iricDomain) {
        /**----------------------------处理图表------------------------------------**/

        // 数据准备
        List<String> titleArr = new ArrayList<String>();// 标题
        titleArr.add("title");
        titleArr.add("aaa");

        List<String> fldNameArr = new ArrayList<String>();// 字段名
        fldNameArr.add("item1");
        fldNameArr.add("item2");

        // 数据集合
        List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();

        // 第一行数据
        Map<String, String> base1 = new HashMap<String, String>();
        base1.put("item1", "观点采择");
        base1.put("item2", String.valueOf(iricDomain.getGdcz()));

        // 第二行数据
        Map<String, String> base2 = new HashMap<String, String>();
        base2.put("item1", "想象");
        base2.put("item2", String.valueOf(iricDomain.getXx()));

        // 第三行数据
        Map<String, String> base3 = new HashMap<String, String>();
        base3.put("item1", "共情关心");
        base3.put("item2", String.valueOf(iricDomain.getGqgx()));
       // 第四行数据
        Map<String, String> base4 = new HashMap<String, String>();
        base4.put("item1", "个人痛苦");
        base4.put("item2", String.valueOf(iricDomain.getGrtk()));

        // 第五行数据
        Map<String, String> base5 = new HashMap<String, String>();
        base5.put("item1", "总分");
        base5.put("item2", String.valueOf(iricDomain.getSum()));
        listItemsByType.add(base1);
        listItemsByType.add(base2);
        listItemsByType.add(base3);
        listItemsByType.add(base4);
        listItemsByType.add(base5);


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


        // 柱状图
        POIXMLDocumentPart poixmlDocumentPart1 = chartsMap.get("/word/charts/chart1.xml");
        new PoiWordTools().replaceBarCharts(poixmlDocumentPart1, titleArr, fldNameArr, listItemsByType);


    }
}
