package com.qx.web.controller.patient;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qx.framework.util.SecurityUtils;
import com.qx.patient.domain.vo.PatientUserTask;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.service.IPatientIntermediateTaskService;

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
@RequestMapping("/patient/intermediateTask")
public class PatientIntermediateTaskController extends BaseController
{
    @Autowired
    private IPatientIntermediateTaskService patientIntermediateTaskService;

    /**
     * 查询社会认知任务列表
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(PatientIntermediateTask patientIntermediateTask)
    {
        startPage();
        if(!"admin".equals(SecurityUtils.getUsername())){
            patientIntermediateTask.setCreateBy(SecurityUtils.getUsername());
        }
        PageInfo<PatientUserTask> list = patientIntermediateTaskService.selectPatientIntermediateTaskList(patientIntermediateTask);
        return getDataTable1(list);
    }

    /**
     * 导出社会认知任务列表
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:export')")
    @Log(title = "社会认知任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PatientIntermediateTask patientIntermediateTask)
    {
        PageInfo<PatientUserTask> list = patientIntermediateTaskService.selectPatientIntermediateTaskList(patientIntermediateTask);
        ExcelUtil<PatientUserTask> util = new ExcelUtil<PatientUserTask>(PatientUserTask.class);
        return util.exportExcel(list.getList(), "task");
    }

    /**
     * 获取社会认知任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(patientIntermediateTaskService.selectPatientIntermediateTaskById(taskId));
    }

    /**
     * 新增社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:add')")
    @Log(title = "社会认知任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setCreateBy(SecurityUtils.getUsername());
        patientUserTask.setCreateTime(new Date());
        return toAjax(patientIntermediateTaskService.insertPatientIntermediateTask(patientUserTask));
    }

    /**
     * 修改社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:edit')")
    @Log(title = "社会认知任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setUpdateBy(SecurityUtils.getUsername());
        patientUserTask.setUpdateTime(new Date());
        return toAjax(patientIntermediateTaskService.updatePatientIntermediateTask(patientUserTask));
    }

    /**
     * 删除社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:intermediateTask:remove')")
    @Log(title = "社会认知任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(patientIntermediateTaskService.deletePatientIntermediateTaskByIds(taskIds));
    }
    /**
     * 根据患者id获取社会认知任务详细信息
     */
    @GetMapping(value = "patientId/{patientId}")
    public AjaxResult selectTaskByPatientId(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(patientIntermediateTaskService.selectTaskByPatientId(patientId));
    }

    /**
     * 根据PatientID修改任务
     */

    @PostMapping(value = "/updateByPatientId")

    public AjaxResult editByPatientId(@RequestBody PatientIntermediateTask patientIntermediateTask)
    {
        return toAjax(patientIntermediateTaskService.updateByPatientId(patientIntermediateTask));
    }
    /**
     * 获取工作站，进行验证
     * @param workstation
     * @return
     */
    @RequestMapping(value = "/getWorkstation",method = RequestMethod.GET)
    public AjaxResult getWorkstation(String workstation,String patientId)
    {
        return AjaxResult.success(patientIntermediateTaskService.getWebpackVersion(workstation,patientId));
    }
}
