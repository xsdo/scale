package com.qx.web.controller.patient;

import java.util.List;
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
import com.qx.patient.domain.StatisticsTable2;
import com.qx.patient.service.IStatisticsTable2Service;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 统计2Controller
 * 
 * @author patient
 * @date 2020-09-02
 */
@RestController
@RequestMapping("/patient/statisticsTable2")
public class StatisticsTable2Controller extends BaseController
{
    @Autowired
    private IStatisticsTable2Service statisticsTable2Service;

    /**
     * 查询统计2列表
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:list')")
    @GetMapping("/list")
    public TableDataInfo list(StatisticsTable2 statisticsTable2)
    {
        startPage();

        List<StatisticsTable2> list = statisticsTable2Service.selectStatisticsTable2List(statisticsTable2);
        return getDataTable(list);
    }

    /**
     * 导出统计2列表
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:export')")
    @Log(title = "统计2", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StatisticsTable2 statisticsTable2)
    {
        List<StatisticsTable2> list = statisticsTable2Service.selectStatisticsTable2List(statisticsTable2);
        ExcelUtil<StatisticsTable2> util = new ExcelUtil<StatisticsTable2>(StatisticsTable2.class);
        return util.exportExcel(list, "statisticsTable2");
    }

    /**
     * 获取统计2详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(statisticsTable2Service.selectStatisticsTable2ById(id));
    }

    /**
     * 新增统计2
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:add')")
    @Log(title = "统计2", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StatisticsTable2 statisticsTable2)
    {
        return toAjax(statisticsTable2Service.insertStatisticsTable2(statisticsTable2));
    }

    /**
     * 修改统计2
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:edit')")
    @Log(title = "统计2", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StatisticsTable2 statisticsTable2)
    {
        return toAjax(statisticsTable2Service.updateStatisticsTable2(statisticsTable2));
    }

    /**
     * 删除统计2
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable2:remove')")
    @Log(title = "统计2", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(statisticsTable2Service.deleteStatisticsTable2ByIds(ids));
    }
}
