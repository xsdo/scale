package com.qx.web.controller.util;

import com.qx.common.annotation.Excel;
import com.qx.common.annotation.Excel.ColumnType;
import com.qx.common.annotation.Excel.Type;
import com.qx.common.annotation.Excels;
import com.qx.common.config.ProjectConfig;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.text.Convert;
import com.qx.common.exception.CustomException;
import com.qx.common.utils.DateUtils;
import com.qx.common.utils.StringUtils;
import com.qx.common.utils.poi.ExcelUtil;
import com.qx.common.utils.reflect.ReflectUtils;
import com.qx.patient.domain.*;
import com.qx.patient.service.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Excel相关处理
 *
 * @author suhp
 */
public class ScaleLogExcelUtil
{
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    @Autowired
    private IDldLogService dldLogService;
    @Autowired
    private IEisLogService eisLogService;
    @Autowired
    private IIricLogService iricLogService;
    @Autowired
    private IMettLogService mettLogService;
    @Autowired
    private IFzyqLogService fzyqLogService;
    @Autowired
    private IShtlLogService shtlLogService;
    @Autowired
    private ISysbLogService sysbLogService;
    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 65536;

    /**
     * 工作表名称
     */
    private String sheetName1;
    private String sheetName2;
    private String sheetName3;
    private String sheetName4;
    private String sheetName5;
    private String sheetName6;
    private String sheetName7;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Type type=Type.EXPORT;

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;


    /**
     * 导入导出数据列表
     */
    private List<DldLog> list1;
    private List<EisLog> list2;
    private List<IricLog> list3;
    private List<MettLog> list4;
    private List<FzyqLog> list5;
    private List<ShtlLog> list6;
    private List<SysbLog> list7;


    /**
     * 注解列表
     */
    private List<Object[]> field1;
    private List<Object[]> field2;
    private List<Object[]> field3;
    private List<Object[]> field4;
    private List<Object[]> field5;
    private List<Object[]> field6;
    private List<Object[]> field7;
    /**
     * 实体对象
     */
    public Class<DldLog> clazz1;
    public Class<EisLog> clazz2;
    public Class<IricLog> clazz3;
    public Class<MettLog> clazz4;
    public Class<FzyqLog> clazz5;
    public Class<ShtlLog> clazz6;
    public Class<SysbLog> clazz7;

    public ScaleLogExcelUtil(Class<DldLog> clazz1,Class<EisLog> clazz2,Class<IricLog> clazz3,Class<MettLog> clazz4,Class<FzyqLog> clazz5,Class<ShtlLog> clazz6,Class<SysbLog> clazz7) {
        this.clazz1=clazz1;
        this.clazz2=clazz2;
        this.clazz3=clazz3;
        this.clazz4=clazz4;
        this.clazz5=clazz5;
        this.clazz6=clazz6;
        this.clazz7=clazz7;
    }


    public AjaxResult init(List<DldLog> list1,List<EisLog> list2,List<IricLog> list3,List<MettLog> list4,List<FzyqLog> list5,List<ShtlLog> list6,List<SysbLog> list7)
    {

        this.list1= list1;
        this.sheetName1 = "多伦多量表";
        createExcelField1();


        this.list2= list2;
        this.sheetName2 = "情绪智力量表";
        createExcelField2();


        this.list3= list3;
        this.sheetName3 = "人际反应指针";
        createExcelField3();


        this.list4= list4;
        this.sheetName4 = "情绪识别";
        createExcelField4();


        this.list5= list5;
        this.sheetName5 = "复杂眼区";
        createExcelField5();

        this.list6= list6;
        this.sheetName6 = "社会推理";
        createExcelField6();

        this.list7= list7;
        this.sheetName7 = "多伦多量表";
        createExcelField7();
        createWorkbook();
        return exportExcel();

    }



    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @return 结果
     */
    public AjaxResult exportExcel()
    {
        OutputStream out = null;
        try
        {
            List<List<Object[]>> fileds=new LinkedList<>();
            fileds.add(field1);
            fileds.add(field2);
            fileds.add(field3);
            fileds.add(field4);
            fileds.add(field5);
            fileds.add(field6);
            fileds.add(field7);
            // 取出一共有多少个sheet.
            //double sheetNo = Math.ceil(list.size() / sheetSize);
            double sheetNo=6;
            String filename="";
               for (int index = 0; index <= sheetNo; index++) {
                   createSheet(sheetNo, index,index);
                   // 产生一行
                   Row row = sheet.createRow(0);
                   int column = 0;
                   // 写入各个字段的列头名称
                   for (Object[] os : fileds.get(index)) {
                       Excel excel = (Excel) os[1];
                       this.createCell(excel, row, column++);
                   }
                   if (Type.EXPORT.equals(type)) {
                       fillExcelData(index, row,fileds.get(index),index);
                   }
                   /*if(index==0){

                   }else if(index==1){
                       filename = encodingFilename(sheetName2);
                   }else if(index==2){
                       filename = encodingFilename(sheetName3);
                   }else if(index==3){
                       filename = encodingFilename(sheetName4);
                   }else if(index==4){
                       filename = encodingFilename(sheetName5);
                   }else if(index==5){
                       filename = encodingFilename(sheetName6);
                   }else{
                       filename = encodingFilename(sheetName7);
                   }*/

               }
            filename = encodingFilename("社会认知日志表");
            out = new FileOutputStream(getAbsoluteFile(filename));
            wb.write(out);
            return AjaxResult.success(filename);
        }
        catch (Exception e)
        {
            log.error("导出Excel异常{}", e.getMessage());
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        }
        finally
        {
            if (wb != null)
            {
                try
                {
                    wb.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row 单元格行
     */
    public void fillExcelData(int index, Row row,List<Object[]> fields,Integer a)
    {

        int startNo = 0 * sheetSize;
        int endNo;
        if(a==0){
            endNo = Math.min(startNo + sheetSize, list1.size());
        }else if(a==1){
            endNo = Math.min(startNo + sheetSize, list2.size());
        }else if(a==2){
            endNo = Math.min(startNo + sheetSize, list3.size());
        }else if(a==3){
            endNo = Math.min(startNo + sheetSize, list4.size());
        }else if(a==4){
            endNo = Math.min(startNo + sheetSize, list5.size());
        }
        else if(a==5){
            endNo = Math.min(startNo + sheetSize, list6.size());
        }else {
            endNo = Math.min(startNo + sheetSize, list7.size());
        }
        Object vo = null;
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 - startNo);
            // 得到导出对象.
            if (a==0){
                vo = list1.get(i);
            }else if (a==1) {
                vo = list2.get(i);
            }else if (a==2) {
                vo = list3.get(i);
            }else if (a==3) {
                vo = list4.get(i);
            }else if (a==4) {
                vo = list5.get(i);
            }else if (a==5) {
                vo = list6.get(i);
            }else {
                vo = list7.get(i);
            }
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++, a);
            }
        }
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb)
    {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * 创建单元格
     */
    public Cell createCell(Excel attr, Row row, int column)
    {
        // 创建列
        Cell cell = row.createCell(column);
        // 写入列信息
        cell.setCellValue(attr.name());
        setDataValidation(attr, row, column);
        cell.setCellStyle(styles.get("header"));
        return cell;
    }

    /**
     * 设置单元格信息
     *
     * @param value 单元格值
     * @param attr 注解相关
     * @param cell 单元格信息
     */
    public void setCellVo(Object value, Excel attr, Cell cell)
    {
        if (ColumnType.STRING == attr.cellType())
        {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        }
        else if (ColumnType.NUMERIC == attr.cellType())
        {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(value + ""));
        }
    }

    /**
     * 创建表格样式
     */
    public void setDataValidation(Excel attr, Row row, int column)
    {
        if (attr.name().indexOf("注：") >= 0)
        {
            sheet.setColumnWidth(column, 6000);
        }
        else
        {
            // 设置列宽
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
        }
        // 如果设置了提示信息则鼠标放上去提示.
        if (StringUtils.isNotEmpty(attr.prompt()))
        {
            // 这里默认设了2-101列提示.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        // 如果设置了combo属性则本列只能选择不能输入
        if (attr.combo().length > 0)
        {
            // 这里默认设了2-101列只能选择不能输入.
            setXSSFValidation(sheet, attr.combo(), 1, 100, column, column);
        }
    }

    /**
     * 添加单元格
     */
    public Cell addCell(Excel attr, Row row, Object vo, Field field, int column,Integer a)
    {
        Cell cell = null;
        try
        {
            // 设置行高
            row.setHeight((short) (attr.height() * 20));
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport())
            {
                // 创建cell
                cell = row.createCell(column);
                cell.setCellStyle(styles.get("data"));

                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr,a);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                }
                else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(convertByExp(String.valueOf(value), readConverterExp));
                }
                else
                {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
            }
        }
        catch (Exception e)
        {
            log.error("导出Excel失败{}", e);
        }
        return cell;
    }

    /**
     * 设置 POI XSSFSheet 单元格提示
     *
     * @param sheet 表单
     * @param promptTitle 提示标题
     * @param promptContent 提示内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                              int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet 要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation)
        {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        }
        else
        {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @return 解析后值
     * @throws Exception
     */
    public static String convertByExp(String propertyValue, String converterExp) throws Exception
    {
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (itemArray[0].equals(propertyValue))
                {
                    return itemArray[1];
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return propertyValue;
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @return 解析后值
     * @throws Exception
     */
    public static String reverseByExp(String propertyValue, String converterExp) throws Exception
    {
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (itemArray[1].equals(propertyValue))
                {
                    return itemArray[0];
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return propertyValue;
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename)
    {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        return filename;
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename)
    {
        String downloadPath = ProjectConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 获取bean中的属性值
     *
     * @param vo 实体对象
     * @param field 字段
     * @param excel 注解
     * @return 最终的属性值
     * @throws Exception
     */
    private Object getTargetValue(Object vo, Field field, Excel excel,Integer a) throws Exception
    {
        Object o;
        if(a==0){
            o = field.get((DldLog)vo);
        }else if(a==1){
            o = field.get((EisLog)vo);
        }else if(a==2){
            o = field.get((IricLog)vo);
        }else if(a==3){
            o = field.get((MettLog)vo);
        }else if(a==4){
            o = field.get((FzyqLog)vo);
        }else if(a==5){
            o = field.get((ShtlLog)vo);
        }else{
            o = field.get((SysbLog)vo);
        }

        if (StringUtils.isNotEmpty(excel.targetAttr()))
        {
            String target = excel.targetAttr();
            if (target.indexOf(".") > -1)
            {
                String[] targets = target.split("[.]");
                for (String name : targets)
                {
                    o = getValue(o, name,a);
                }
            }
            else
            {
                o = getValue(o, target,a);
            }
        }
        return o;
    }

    /**
     * 以类的属性的get方法方法形式获取值
     *
     * @param o
     * @param name
     * @return value
     * @throws Exception
     */
    private Object getValue(Object o, String name,Integer a) throws Exception
    {
        Class<?> clazz=null;
        for(int i=0;i<=6;i++) {
            if(i==a && i==0){
                clazz=clazz1;
            }else if(i==a && i==1){
                clazz=clazz2;
            }else if(i==a && i==2){
                clazz=clazz3;
            }else if(i==a && i==3){
                clazz=clazz4;
            }else if(i==a && i==4){
                clazz=clazz5;
            }else if(i==a && i==5){
                clazz=clazz6;
            }else if(i==a && i==6){
                clazz=clazz7;
            }

            if (StringUtils.isNotEmpty(name)) {
                 clazz = o.getClass();
                String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Method method = clazz.getMethod(methodName);
                o = method.invoke(o);
            }
        }
        return o;
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField1()
    {
        this.field1 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz1.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz1.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField1(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField1(field, excel);
                }
            }
        }
    }
    private void createExcelField2()
    {
        this.field2 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz2.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz2.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField2(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField2(field, excel);
                }
            }
        }
    }
    private void createExcelField3()
    {
        this.field3 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz3.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz3.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField3(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField3(field, excel);
                }
            }
        }
    }
    private void createExcelField4()
    {
        this.field4 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz4.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz4.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField4(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField4(field, excel);
                }
            }
        }
    }
    private void createExcelField5()
    {
        this.field5 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz5.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz5.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField5(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField5(field, excel);
                }
            }
        }
    }
    private void createExcelField6()
    {
        this.field6 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz6.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz6.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField6(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField6(field, excel);
                }
            }
        }
    }
    private void createExcelField7()
    {
        this.field7 = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz7.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz7.getDeclaredFields()));
        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField7(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField7(field, excel);
                }
            }
        }
    }

    /**
     * 放到字段集合中
     */
    private void putToField1(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field1.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField2(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field2.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField3(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field3.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField4(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field4.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField5(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field5.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField6(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field6.add(new Object[] { field, attr });
        }
    }
    /**
     * 放到字段集合中
     */
    private void putToField7(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type))
        {
            this.field7.add(new Object[] { field, attr });
        }
    }


    /**
     * 创建一个工作簿
     */
    public void createWorkbook()
    {
        this.wb = new SXSSFWorkbook(500);
    }

    /**
     * 创建工作表
     *
     * @param sheetNo sheet数量
     * @param index 序号
     */
    public void createSheet(double sheetNo, int index,Integer a) {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        String sheetName="";
        if(a==0){
            sheetName=sheetName1;
        }else if(1==a){
            sheetName=sheetName2;
        }
        else if(2==a){
            sheetName=sheetName3;
        }
        else if(3==a){
            sheetName=sheetName4;
        }
        else if(4==a){
            sheetName=sheetName5;
        }
        else if(5==a){
            sheetName=sheetName6;
        }
        else{
            sheetName=sheetName7;
        }

        // 设置工作表的名称.
        if (sheetNo == 0) {
            wb.setSheetName(index, sheetName);
        } else {
            wb.setSheetName(index, sheetName + index);
        }

    }
    /**
     * 获取单元格值
     *
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column)
    {
        if (row == null)
        {
            return row;
        }
        Object val = "";
        try
        {
            Cell cell = row.getCell(column);
            if (cell != null)
            {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
                {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell))
                    {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    }
                    else
                    {
                        if ((Double) val % 1 > 0)
                        {
                            val = new DecimalFormat("0.00").format(val);
                        }
                        else
                        {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                }
                else if (cell.getCellTypeEnum() == CellType.STRING)
                {
                    val = cell.getStringCellValue();
                }
                else if (cell.getCellTypeEnum() == CellType.BOOLEAN)
                {
                    val = cell.getBooleanCellValue();
                }
                else if (cell.getCellTypeEnum() == CellType.ERROR)
                {
                    val = cell.getErrorCellValue();
                }

            }
        }
        catch (Exception e)
        {
            return val;
        }
        return val;
    }
}