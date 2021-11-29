package com.qx.web.controller.patient;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qx.framework.util.SecurityUtils;
import com.qx.patient.domain.vo.PatientUserTask;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.service.IPatientBasisTaskService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 社会认知任务Controller
 * 
 * @author patient
 * @date 2020-07-14
 */
@RestController
@RequestMapping("/patient/basisTask")
public class PatientBasisTaskController extends BaseController
{
    @Autowired
    private IPatientBasisTaskService patientBasisTaskService;

    /**
     * 查询社会认知任务列表
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(PatientBasisTask patientBasisTask)
    {
        startPage();
        if(!"admin".equals(SecurityUtils.getUsername())){
            patientBasisTask.setCreateBy(SecurityUtils.getUsername());
        }
        PageInfo<PatientUserTask> list = patientBasisTaskService.selectPatientBasisTaskList(patientBasisTask);
        return getDataTable1(list);
    }

    /**
     * 导出社会认知任务列表
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:export')")
    @Log(title = "社会认知任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PatientBasisTask patientBasisTask)
    {
        PageInfo<PatientUserTask> list = patientBasisTaskService.selectPatientBasisTaskList(patientBasisTask);
        ExcelUtil<PatientUserTask> util = new ExcelUtil<PatientUserTask>(PatientUserTask.class);
        return util.exportExcel(list.getList(), "task");
    }

    /**
     * 获取社会认知任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(patientBasisTaskService.selectPatientBasisTaskById(taskId));
    }

    /**
     * 新增社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:add')")
    @Log(title = "社会认知任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setCreateBy(SecurityUtils.getUsername());
        patientUserTask.setCreateTime(new Date());
        return toAjax(patientBasisTaskService.insertPatientBasisTask(patientUserTask));
    }

    /**
     * 修改社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:edit')")
    @Log(title = "社会认知任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setUpdateBy(SecurityUtils.getUsername());
        patientUserTask.setUpdateTime(new Date());
        return toAjax(patientBasisTaskService.updatePatientBasisTask(patientUserTask));
    }

    /**
     * 删除社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:basisTask:remove')")
    @Log(title = "社会认知任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(patientBasisTaskService.deletePatientBasisTaskByIds(taskIds));
    }
    /**
     * 根据患者id获取社会认知任务详细信息
     */
    @GetMapping(value = "patientId/{patientId}")
    public AjaxResult selectTaskByPatientId(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(patientBasisTaskService.selectTaskByPatientId(patientId));
    }
    /**
     * 根据PatientID修改任务
     */

    @PostMapping(value = "/updateByPatientId")

    public AjaxResult editByPatientId(@RequestBody PatientBasisTask patientBasisTask)
    {
        return toAjax(patientBasisTaskService.updateByPatientId(patientBasisTask));
    }

    /**
     * 获取工作站，进行验证
     * @param workstation
     * @return
     */
    @RequestMapping(value = "/getWorkstation",method = RequestMethod.GET)
    public AjaxResult getWorkstation(String workstation,String patientId)
    {
        return AjaxResult.success(patientBasisTaskService.getWebpackVersion(workstation,patientId));
    }
}
