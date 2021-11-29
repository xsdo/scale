package com.qx.web.controller.ipa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qx.framework.util.SecurityUtils;
import com.qx.ipa.domain.*;
import com.qx.ipa.service.IIpaPatientUserService;
import com.qx.ipa.service.IIpaTaskScoreService;
import com.qx.web.controller.util.IpaScalWord;
import com.qx.web.controller.util.IpaWord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.ipa.service.IIpaTaskService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * 智能化心身调节任务Controller
 * 
 * @author qx
 * @date 2021-07-05
 */
@RestController
@RequestMapping("/ipa/task")
public class IpaTaskController extends BaseController
{
    @Autowired
    private IIpaTaskService ipaTaskService;

    @Autowired
    IIpaPatientUserService iIpaPatientUserService;

    @Autowired
    IIpaTaskScoreService ipaTaskScoreService;


    /**
     * 查询智能化心身调节任务列表
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(IpaTask ipaTask)
    {
        startPage();
        List<IpaTask> list = ipaTaskService.selectIpaTaskList(ipaTask);
        return getDataTable(list);
    }

    /**
     * 导出智能化心身调节任务列表
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:export')")
    @Log(title = "智能化心身调节任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(IpaTask ipaTask)
    {
        List<IpaTask> list = ipaTaskService.selectIpaTaskList(ipaTask);
        ExcelUtil<IpaTask> util = new ExcelUtil<IpaTask>(IpaTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取智能化心身调节任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(ipaTaskService.selectIpaTaskById(taskId));
    }

    /**
     * 新增智能化心身调节任务
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:add')")
    @Log(title = "智能化心身调节任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IpaTask ipaTask)
    {
        ipaTask.setCreateBy(SecurityUtils.getUsername());
        ipaTask.setCreateTime(new Date());
        ipaTask.setTaskStatus("0");
        ipaTask.setDelFlag("0");
        return toAjax(ipaTaskService.insertIpaTask(ipaTask));
    }

    /**
     * 修改智能化心身调节任务
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:edit')")
    @Log(title = "智能化心身调节任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IpaTask ipaTask)
    {
        return toAjax(ipaTaskService.updateIpaTask(ipaTask));
    }

    /**
     * 删除智能化心身调节任务
     */
    @PreAuthorize("@ss.hasPermi('ipa:task:remove')")
    @Log(title = "智能化心身调节任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(ipaTaskService.deleteIpaTaskByIds(taskIds));
    }

    /**
     * 根据PatientID修改任务
     */

    @PostMapping(value = "/updateByPatientId")
    public AjaxResult editByPatientId(@RequestBody IpaTask ipaTask)
    {
        return toAjax(ipaTaskService.updateByPatientId(ipaTask));
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
        return AjaxResult.success(ipaTaskService.getWebpackVersion(workstation,patientId));
    }

    /**
     * 下载报告
     * @param ipaTask
     * @param
     * @return
     */
    @PostMapping(value = "/downloadScale")
    public AjaxResult downloadScale(@RequestBody IpaTask ipaTask, HttpServletRequest request){
        IpaPatientUser ipaPatientUser = iIpaPatientUserService.selectIpaPatientUserById(ipaTask.getPatientId());
        ipaPatientUser.setTestCoding(ipaTask.getTestCoding());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(ipaPatientUser.getBirthday());
        ipaPatientUser.setBirth(date);
        String typeFlag = ipaTask.getTypeFlag();
        Map<String,String> map=new HashMap<>();
        if("1".equals(typeFlag)){
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+ "scaleTemplate/ipaScal.docx";
            IpaDomain ipaDomain = ipaTaskScoreService.selectListByTaskId(ipaTask.getTaskId());
            ipaDomain.setIpaPatientUser(ipaPatientUser);
            String fileName= IpaScalWord.getIpa(ipaDomain,string);
            map.put(date,fileName);
        }else if("0".equals(typeFlag)){
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+ "scaleTemplate/ipa.docx";
            IpaDomain ipaDomain = ipaTaskScoreService.selectListByTaskId(ipaTask.getTaskId());
            ipaDomain.setIpaPatientUser(ipaPatientUser);
            String fileName= IpaWord.getIpa(ipaDomain,string);
            map.put(date,fileName);
        }
        return AjaxResult.success(map);
    }

}
