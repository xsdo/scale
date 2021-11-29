package com.qx.scale.controller;

import java.util.List;

import com.qx.framework.util.SecurityUtils;
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
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.service.IScalePatientService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 量表系统患者Controller
 * 
 * @author patient
 * @date 2021-10-11
 */
@RestController
@RequestMapping("/scale/patient")
public class ScalePatientController extends BaseController
{
    @Autowired
    private IScalePatientService scalePatientService;

    /**
     * 查询量表系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScalePatient scalePatient)
    {
        startPage();
        if(!"admin".equals(SecurityUtils.getUsername())){
            scalePatient.setCreateBy(SecurityUtils.getUsername());
        }
        List<ScalePatient> list = scalePatientService.selectScalePatientList(scalePatient);
        return getDataTable(list);
    }

    /**
     * 导出量表系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:export')")
    @Log(title = "量表系统患者", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScalePatient scalePatient)
    {
        List<ScalePatient> list = scalePatientService.selectScalePatientList(scalePatient);
        ExcelUtil<ScalePatient> util = new ExcelUtil<ScalePatient>(ScalePatient.class);
        return util.exportExcel(list, "patient");
    }

    /**
     * 获取量表系统患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(scalePatientService.selectScalePatientById(patientId));
    }

    /**
     * 新增量表系统患者
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:add')")
    @Log(title = "量表系统患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScalePatient scalePatient)
    {
        return toAjax(scalePatientService.insertScalePatient(scalePatient));
    }

    /**
     * 修改量表系统患者
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:edit')")
    @Log(title = "量表系统患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScalePatient scalePatient)
    {
        return toAjax(scalePatientService.updateScalePatient(scalePatient));
    }

    /**
     * 删除量表系统患者
     */
    @PreAuthorize("@ss.hasPermi('scale:patient:remove')")
    @Log(title = "量表系统患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(scalePatientService.deleteScalePatientByIds(patientIds));
    }

    /**
     * 删除患者未完成所有任务
     */
    @DeleteMapping("/delAllTask/{patientIds}")
    public AjaxResult delAllTask(@PathVariable Long[] patientIds)
    {
        int a=0;
        if (patientIds!=null&&patientIds.length>0){
            for (Long l:patientIds) {
                a+= scalePatientService.delAllTask(l);
            }
        }

        return toAjax(a);
    }
}
