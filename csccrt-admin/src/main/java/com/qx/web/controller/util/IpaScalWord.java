package com.qx.web.controller.util;

import com.qx.common.config.ProjectConfig;
import com.qx.ipa.domain.IpaDomain;
import com.qx.ipa.domain.IpaPatientUser;
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

public class IpaScalWord {

    public static String getIpa(IpaDomain ipaDomain, String templateurl) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String fileName=ipaDomain.getIpaPatientUser().getPatientName()+"_"+ipaDomain.getIpaPatientUser().getTestCoding()+"_ipaScal.docx";
        String returnurl= ProjectConfig.getDownloadPath()+fileName;
        // 替换word模板数据
        XWPFDocument doc=null;
        try {
            InputStream is = new FileInputStream(new File(templateurl));
             doc = new XWPFDocument(is);
            replaceAll(doc,ipaDomain);
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
            doc.close();
            //Runtime.getRuntime().exec("cmd /c start winword d:\\"+patientUser.getTestCoding()+"-dld.docx");//直接调用cmd打开合成文档
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }


    /**
     * @Description: 替换段落和表格中
     */
    public static void replaceAll(XWPFDocument doc,IpaDomain ipaDomain) throws InvalidFormatException, IOException {
        doParagraphs(doc,ipaDomain); // 处理段落文字数据，包括文字和表格、图片
        doCharts(doc,ipaDomain);  // 处理图表数据，柱状图、折线图、饼图啊之类的
    }


    /**
     * 处理段落文字
     *
     * @param doc
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void doParagraphs(XWPFDocument doc,IpaDomain ipaDomain) {
        IpaPatientUser ipaPatientUser=ipaDomain.getIpaPatientUser();
         if("0".equals(ipaPatientUser.getSex())){
             ipaPatientUser.setSex("男");
        }else if("1".equals(ipaPatientUser.getSex())){
             ipaPatientUser.setSex("女");
         }
         if("0".equals(ipaPatientUser.getMaritalStatus())){
             ipaPatientUser.setMaritalStatus("未婚");
         }else{
             ipaPatientUser.setMaritalStatus("已婚");
         }
         if(ipaPatientUser.getHospitalNumber()==null){
             ipaPatientUser.setHospitalNumber(0l);
         }
         if(ipaPatientUser.getWard()==null){
             ipaPatientUser.setWard("");
         }
        // 文本数据
        Map<String, String> textMap = new HashMap<String, String>();
        int sas = (int)(ipaDomain.getSas()*1.25);
        if(sas<50){
            textMap.put("varA", "焦虑自评量表分数为"+sas+"分，正常。");
        }else if(sas>=50 && sas<=60){
            textMap.put("varA", "焦虑自评量表分数为"+sas+"分，轻度焦虑。");
        }else if(sas>60 && sas<=70){
            textMap.put("varA", "焦虑自评量表分数为"+sas+"分，中度焦虑。");
        }else{
            textMap.put("varA", "焦虑自评量表分数为"+sas+"分，重度焦虑。");
        }
        int sds = (int)(ipaDomain.getSds()*1.25);
        if(sds<50){
            textMap.put("varB", "抑郁自评量表分数为"+sds+"分，正常。");
        }else if(sds>=50 && sds<=60){
            textMap.put("varB", "抑郁自评量表分数为"+sds+"分，轻度焦虑。");
        }else if(sds>60 && sds<=70){
            textMap.put("varB", "抑郁自评量表分数为"+sds+"分，中度焦虑。");
        }else{
            textMap.put("varB", "抑郁自评量表分数为"+sds+"分，重度焦虑。");
        }
        int ais = ipaDomain.getAis();
        if(ais<4){
            textMap.put("varC", "阿森斯失眠量表分数为"+ais+"分，无睡眠障碍。");
        }else if(ais>=4 && ais<=6){
            textMap.put("varC", "阿森斯失眠量表分数为"+ais+"分，可疑失眠。");
        }else{
            textMap.put("varC", "阿森斯失眠量表分数为"+ais+"分，失眠。");
        }

        textMap.put("var1", ipaDomain.getCreateTime());
        // 图片数据
        Map<String, String> imgMap = new HashMap<String, String>();
        imgMap.put("img", "D:\\360Downloads\\aaa.jpg");
        //段落
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        doParagraph(paragraphList, doc, textMap, imgMap,ipaPatientUser);

        //表格
        List<XWPFTable> tables = doc.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                    doParagraph(paragraphs, doc, textMap, imgMap,ipaPatientUser);
                }
            }
        }
    }
    public static void doParagraph(List<XWPFParagraph> paragraphList, XWPFDocument
            doc, Map < String, String > textMap, Map<String,String> imgMap, IpaPatientUser ipaPatientUser){
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
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "姓名："+ipaPatientUser.getPatientName());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "性别："+ipaPatientUser.getSex());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowOne.createCell(), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "婚姻："+ipaPatientUser.getMaritalStatus());

                            // 表格第二行
                            XWPFTableRow tableOneRowTwo = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "出生年月："+ipaPatientUser.getBirth());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "文化程度："+ipaPatientUser.getEducation());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowTwo.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "职业："+ipaPatientUser.getJob());

                            // 表格第三行
                            XWPFTableRow tableOneRowThree = tableOne.createRow();//行
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(0), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "住院号："+ipaPatientUser.getHospitalNumber());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(1), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "病区："+ipaPatientUser.getWard());
                            new PoiWordTools().setWordCellSelfStyle(tableOneRowThree.getCell(2), "微软雅黑", "12", 0, "left", "top", "#000000", "#ffffff", "30%", "心测号："+ipaPatientUser.getTestCoding());


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
    public static void doCharts(XWPFDocument doc, IpaDomain ipaDomain) {
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
        base1.put("item1", "焦虑自评量表");
        base1.put("item2", String.valueOf((int)(ipaDomain.getSas()*1.25)));

        // 第二行数据
        Map<String, String> base2 = new HashMap<String, String>();
        base2.put("item1", "抑郁自评量表");
        base2.put("item2", String.valueOf((int)(ipaDomain.getSds()*1.25)));

        // 第三行数据
        Map<String, String> base3 = new HashMap<String, String>();
        base3.put("item1", "阿森斯失眠量表");
        base3.put("item2", String.valueOf(ipaDomain.getAis()));

        // 第四行数据
        Map<String, String> base4 = new HashMap<String, String>();
        base4.put("item1", "总分");
        base4.put("item2", String.valueOf((int)(ipaDomain.getSas()*1.25)+(int)(ipaDomain.getSds()*1.25)+ipaDomain.getAis()));


        listItemsByType.add(base1);
        listItemsByType.add(base2);
        listItemsByType.add(base3);
        listItemsByType.add(base4);


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
