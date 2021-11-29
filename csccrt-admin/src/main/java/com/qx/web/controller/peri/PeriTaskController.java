package com.qx.web.controller.peri;

import java.util.List;

import com.qx.framework.util.SecurityUtils;
import com.qx.ipa.domain.IpaTask;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.peri.domain.PeriTask;
import com.qx.peri.service.IPeriTaskService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 围手术期任务Controller
 * 
 * @author Meng
 * @date 2021-07-13
 */
@RestController
@RequestMapping("/peri/task")
public class PeriTaskController extends BaseController
{
    @Autowired
    private IPeriTaskService periTaskService;

    /**
     * 查询围手术期任务列表
     */
    @PreAuthorize("@ss.hasPermi('peri:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(PeriTask periTask)
    {
        startPage();
        List<PeriTask> list = periTaskService.selectPeriTaskList(periTask);
        return getDataTable(list);
    }

    /**
     * 导出围手术期任务列表
     */
    @PreAuthorize("@ss.hasPermi('peri:task:export')")
    @Log(title = "围手术期任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PeriTask periTask)
    {
        List<PeriTask> list = periTaskService.selectPeriTaskList(periTask);
        ExcelUtil<PeriTask> util = new ExcelUtil<PeriTask>(PeriTask.class);
        return util.exportExcel(list, "peri");
    }

    /**
     * 获取围手术期任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('peri:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(periTaskService.selectPeriTaskById(taskId));
    }

    /**
     * 新增围手术期任务
     */
    @PreAuthorize("@ss.hasPermi('peri:task:add')")
    @Log(title = "围手术期任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PeriTask periTask)
    {
        periTask.setCreateBy(SecurityUtils.getUsername());
        return toAjax(periTaskService.insertPeriTask(periTask));
    }

    /**
     * 修改围手术期任务
     */
    @PreAuthorize("@ss.hasPermi('peri:task:edit')")
    @Log(title = "围手术期任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PeriTask periTask)
    {
        return toAjax(periTaskService.updatePeriTask(periTask));
    }

    /**
     * 删除围手术期任务
     */
    @PreAuthorize("@ss.hasPermi('peri:task:remove')")
    @Log(title = "围手术期任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(periTaskService.deletePeriTaskByIds(taskIds));
    }

    /**
     * 前端启动任务成功后，更改任务状态
     */

    @PostMapping(value = "/updateByPatientId")
    public AjaxResult editByPatientId(@RequestBody PeriTask periTask)
    {
        return toAjax(periTaskService.updateByPatientId(periTask));
    }

    /**
     * 获取工作站，进行验证
     * @param workstation
     * @param patientId
     * @return
     */
    @RequestMapping(value = "/getWorkstation",method = RequestMethod.GET)
    public AjaxResult getWorkstation(String workstation,String patientId)
    {
        return AjaxResult.success(periTaskService.getWebpackVersion(workstation,patientId));
    }
}
