package com.qx.web.controller.patient;

import java.util.List;

import com.qx.patient.mapper.PatientUserMapper;
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
import com.qx.patient.domain.StatisticsTable3;
import com.qx.patient.service.IStatisticsTable3Service;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 统计3Controller
 * 
 * @author patient
 * @date 2020-09-02
 */
@RestController
@RequestMapping("/patient/statisticsTable3")
public class StatisticsTable3Controller extends BaseController
{
    @Autowired
    private IStatisticsTable3Service statisticsTable3Service;

    /**
     * 查询统计3列表
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:list')")
    @GetMapping("/list")
    public AjaxResult list(StatisticsTable3 statisticsTable3)
    {
        startPage();
        StatisticsTable3 table3List = statisticsTable3Service.selectStatisticsTable3List(statisticsTable3);
        return AjaxResult.success(table3List);
    }

    /**
     * 导出统计3列表
     */
   /* @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:export')")
    @Log(title = "统计3", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StatisticsTable3 statisticsTable3)
    {
        List<StatisticsTable3> list = statisticsTable3Service.selectStatisticsTable3List(statisticsTable3);
        ExcelUtil<StatisticsTable3> util = new ExcelUtil<StatisticsTable3>(StatisticsTable3.class);
        return util.exportExcel(list, "statisticsTable3");
    }*/

    /**
     * 获取统计3详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(statisticsTable3Service.selectStatisticsTable3ById(id));
    }

    /**
     * 新增统计3
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:add')")
    @Log(title = "统计3", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StatisticsTable3 statisticsTable3)
    {
        return toAjax(statisticsTable3Service.insertStatisticsTable3(statisticsTable3));
    }

    /**
     * 修改统计3
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:edit')")
    @Log(title = "统计3", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StatisticsTable3 statisticsTable3)
    {
        return toAjax(statisticsTable3Service.updateStatisticsTable3(statisticsTable3));
    }

    /**
     * 删除统计3
     */
    @PreAuthorize("@ss.hasPermi('patient:statisticsTable3:remove')")
    @Log(title = "统计3", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(statisticsTable3Service.deleteStatisticsTable3ByIds(ids));
    }
}
