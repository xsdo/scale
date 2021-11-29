package com.qx.web.controller.patient;

import java.util.List;

import com.qx.patient.domain.*;
import com.qx.patient.service.*;
import com.qx.web.controller.util.ScaleLogExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;


/**
 * 统计1Controller
 * 
 * @author patient
 * @date 2020-09-02
 */
@RestController
@RequestMapping("/patient/statisticsTable1")
public class StatisticsTable1Controller extends BaseController
{
    @Autowired
    private IStatisticsTable1Service statisticsTable1Service;
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
     * 查询统计1列表
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:list')")
    @GetMapping("/list")
    public AjaxResult list(StatisticsTable1 statisticsTable1)
    {
        startPage();
        StatisticsTable1 statisticsTable1List = statisticsTable1Service.selectStatisticsTable1List(statisticsTable1);
        return AjaxResult.success(statisticsTable1List);
    }

    /**
     * 导出统计1列表
     */
   /* @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:export')")
    @Log(title = "统计1", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StatisticsTable1 statisticsTable1)
    {
        List<StatisticsTable1> list = statisticsTable1Service.selectStatisticsTable1List(statisticsTable1);
        ExcelUtil<StatisticsTable1> util = new ExcelUtil<StatisticsTable1>(StatisticsTable1.class);
        return util.exportExcel(list, "statisticsTable1");
    }*/

    /**
     * 获取统计1详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(statisticsTable1Service.selectStatisticsTable1ById(id));
    }

    /**
     * 新增统计1
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:add')")
    @Log(title = "统计1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StatisticsTable1 statisticsTable1)
    {
        return toAjax(statisticsTable1Service.insertStatisticsTable1(statisticsTable1));
    }

    /**
     * 修改统计1
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:edit')")
    @Log(title = "统计1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StatisticsTable1 statisticsTable1)
    {
        return toAjax(statisticsTable1Service.updateStatisticsTable1(statisticsTable1));
    }

    /**
     * 删除统计1
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable1:remove')")
    @Log(title = "统计1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(statisticsTable1Service.deleteStatisticsTable1ByIds(ids));
    }
    /**
     * 导出多伦多量日志列表
     */
    @Log(title = "多伦多量日志", businessType = BusinessType.EXPORT)
    @GetMapping("/dldExport")
    public AjaxResult export()
    {
        List<DldLog> list1 = dldLogService.selectDldLogList(new DldLog());
        for (DldLog d:list1) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<EisLog> list2=eisLogService.selectEisLogList(new EisLog());
        for (EisLog d:list2) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<IricLog> list3=iricLogService.selectIricLogList(new IricLog());
        for (IricLog d:list3) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<MettLog> list4=mettLogService.selectMettLogList(new MettLog());
        for (MettLog d:list4) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<FzyqLog> list5=fzyqLogService.selectFzyqLogList(new FzyqLog());
        for (FzyqLog d:list5) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<ShtlLog> list6=shtlLogService.selectShtlLogList(new ShtlLog());
        for (ShtlLog d:list6) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        List<SysbLog> list7=sysbLogService.selectSysbLogList(new SysbLog());
        for (SysbLog d:list7) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ScaleLogExcelUtil util1 = new ScaleLogExcelUtil(DldLog.class,EisLog.class,IricLog.class,MettLog.class,FzyqLog.class,ShtlLog.class,SysbLog.class);
        return util1.init(list1,list2,list3,list4,list5,list6,list7);
    }
    @GetMapping("/exportDldLog")
    public AjaxResult exportDldLog()
    {
        List<DldLog> list = dldLogService.selectDldLogList(new DldLog());
        for (DldLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<DldLog> util = new ExcelUtil<DldLog>(DldLog.class);
        return util.exportExcel(list, "DldLog");
    }
    @GetMapping("/exportEisLog")
    public AjaxResult exportEisLog()
    {
        List<EisLog> list = eisLogService.selectEisLogList(new EisLog());
        for (EisLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<EisLog> util = new ExcelUtil<EisLog>(EisLog.class);
        return util.exportExcel(list, "EisLog");
    }
    @GetMapping("/exportIricLog")
    public AjaxResult exportIricLog()
    {
        List<IricLog> list = iricLogService.selectIricLogList(new IricLog());
        for (IricLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<IricLog> util = new ExcelUtil<IricLog>(IricLog.class);
        return util.exportExcel(list, "IricLog");
    }
    @GetMapping("/exportMettLog")
    public AjaxResult exportMettLog()
    {
        List<MettLog> list = mettLogService.selectMettLogList(new MettLog());
        for (MettLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<MettLog> util = new ExcelUtil<MettLog>(MettLog.class);
        return util.exportExcel(list, "MettLog");
    }
    @GetMapping("/exportFzyqLog")
    public AjaxResult exportFzyqLog()
    {
        List<FzyqLog> list = fzyqLogService.selectFzyqLogList(new FzyqLog());
        for (FzyqLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<FzyqLog> util = new ExcelUtil<FzyqLog>(FzyqLog.class);
        return util.exportExcel(list, "FzyqLog");
    }
    @GetMapping("/exportShtlLog")
    public AjaxResult exportShtlLog()
    {
        List<ShtlLog> list = shtlLogService.selectShtlLogList(new ShtlLog());
        for (ShtlLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<ShtlLog> util = new ExcelUtil<ShtlLog>(ShtlLog.class);
        return util.exportExcel(list, "ShtlLog");
    }
    @GetMapping("/exportSysbLog")
    public AjaxResult exportSysbLog()
    {
        List<SysbLog> list = sysbLogService.selectSysbLogList(new SysbLog());
        for (SysbLog d:list) {
            if("0".equals(d.getSex())){
                d.setSex("男");
            }else{
                d.setSex("女");
            }
        }
        ExcelUtil<SysbLog> util = new ExcelUtil<SysbLog>(SysbLog.class);
        return util.exportExcel(list, "SysbLog");
    }
}
