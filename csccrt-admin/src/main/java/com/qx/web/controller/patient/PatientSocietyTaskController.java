package com.qx.web.controller.patient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.PageInfo;
import com.qx.common.config.ProjectConfig;
import com.qx.demo.entity.*;
import com.qx.demo.service.*;
import com.qx.framework.util.SecurityUtils;
import com.qx.patient.domain.PatientUser;
import com.qx.patient.domain.vo.PatientUserTask;
import com.qx.patient.service.IPatientUserService;
import com.qx.web.controller.util.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.patient.domain.PatientSocietyTask;
import com.qx.patient.service.IPatientSocietyTaskService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 社会认知任务Controller
 * 
 * @author patient
 * @date 2020-07-14
 */
@RestController
@RequestMapping("/patient/societyTask")
public class PatientSocietyTaskController extends BaseController
{
    @Autowired
    private IPatientSocietyTaskService patientSocietyTaskService;
    @Autowired
    private IPatientUserService patientUserService;
    @Resource
     private IZzDldService zzDldService;
    @Resource
     private IZzEisService zzEisService;
    @Resource
     private IZzIriCService zzIriCService;
    @Resource
     private IZzMettScoreService zzMettScoreService;
    @Resource
     private IZzFzyqScoreService zzFzyqScoreService;
    @Resource
     private IZzShtlScoreService zzShtlScoreService;
    @Resource
     private IZzSysbScoreService zzSysbScoreService;

    /**
     * 查询社会认知任务列表
     */
    //@PreAuthorize("@ss.hasPermi('patient:societyTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(PatientSocietyTask patientSocietyTask)
    {
        startPage();
        if(!"admin".equals(SecurityUtils.getUsername())){
            patientSocietyTask.setCreateBy(SecurityUtils.getUsername());
        }
        PageInfo<PatientUserTask> list = patientSocietyTaskService.selectPatientSocietyTaskList(patientSocietyTask);
        return getDataTable1(list);
    }

    /**
     * 导出社会认知任务列表
     */
    @PreAuthorize("@ss.hasPermi('patient:societyTask:export')")
    @Log(title = "社会认知任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PatientSocietyTask patientSocietyTask)
    {
        PageInfo<PatientUserTask> list = patientSocietyTaskService.selectPatientSocietyTaskList(patientSocietyTask);
        ExcelUtil<PatientUserTask> util = new ExcelUtil<PatientUserTask>(PatientUserTask.class);
        return util.exportExcel(list.getList(), "task");
    }

    /**
     * 获取社会认知任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:societyTask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(patientSocietyTaskService.selectPatientSocietyTaskById(taskId));
    }

    /**
     * 新增社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:societyTask:add')")
    @Log(title = "社会认知任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setCreateBy(SecurityUtils.getUsername());
        patientUserTask.setCreateTime(new Date());
        return toAjax(patientSocietyTaskService.insertPatientSocietyTask(patientUserTask));
    }

    /**
     * 修改社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:societyTask:edit')")
    @Log(title = "社会认知任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientUserTask patientUserTask)
    {
        patientUserTask.setUpdateBy(SecurityUtils.getUsername());
        patientUserTask.setUpdateTime(new Date());
        return toAjax(patientSocietyTaskService.updatePatientSocietyTask(patientUserTask));
    }

    /**
     * 删除社会认知任务
     */
    @PreAuthorize("@ss.hasPermi('patient:societyTask:remove')")
    @Log(title = "社会认知任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(patientSocietyTaskService.deletePatientSocietyTaskByIds(taskIds));
    }

    /**
     * 根据患者id获取社会认知任务详细信息
     */
    @GetMapping(value = "patientId/{patientId}")
    public AjaxResult selectTaskByPatientId(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(patientSocietyTaskService.selectTaskByPatientId(patientId));
    }

    /**
     * 修改社会认知任务
     */

    @RequestMapping(value = "/updateByPatientId",method = RequestMethod.POST)

    public AjaxResult editByPatientId(@RequestBody PatientSocietyTask patientSocietyTask)
    {
        return toAjax(patientSocietyTaskService.updateByPatientId(patientSocietyTask));
    }

    /**
     * 获取工作站，进行验证
     * @param workstation
     * @return
     */
    @RequestMapping(value = "/getWorkstation",method =RequestMethod.GET)
    public AjaxResult getWorkstation(String workstation,String patientId)
    {
        return AjaxResult.success(patientSocietyTaskService.getWebpackVersion(workstation,patientId));
    }
    @PostMapping(value = "/downloadScale")
    public AjaxResult downloadScale(@RequestBody PatientSocietyTask patientSocietyTask, HttpServletRequest request){
        PatientUser patientUser=patientUserService.selectPatientUserById(patientSocietyTask.getPatientId());
        patientUser.setTestCoding(patientSocietyTask.getTestCoding());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String a = sdf.format(patientUser.getBirthday());
        patientUser.setBirth(a);
        String[] typeIds=patientSocietyTask.getTypeIds().split(",");
       Map<String,String> map=new HashMap<>();
        for (String s:typeIds) {
            if("19".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/dld.docx";
                DldDomain dldDomain=zzDldService.selectListByTaskId(patientSocietyTask.getTaskId());
                dldDomain.setPatientUser(patientUser);
                String fileName=DldWord.getDld(dldDomain,string);
                map.put(s,fileName);
            }
            if("20".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/eis.docx";
                EisDomain eisDomain=zzEisService.selectListByTaskId(patientSocietyTask.getTaskId());
                eisDomain.setPatientUser(patientUser);
                String fileName=EisWord.getDld(eisDomain,string);
                map.put(s,fileName);
            }
            if("21".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/iric.docx";
                IricDomain iricDomain=zzIriCService.selectListByTaskId(patientSocietyTask.getTaskId());
                iricDomain.setPatientUser(patientUser);
                String fileName=IricWord.getDld(iricDomain,string);
                map.put(s,fileName);
            }
            if("22".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/mett.docx";
                MettDomain mettDomain=zzMettScoreService.selectListByTaskId(patientSocietyTask.getTaskId());
                mettDomain.setPatientUser(patientUser);
                String fileName=MettWord.getDld(mettDomain,string);
                map.put(s,fileName);
            }
            if("23".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/fzyq.docx";
                FzyqDomain fzyqDomain=zzFzyqScoreService.selectListByTaskId(patientSocietyTask.getTaskId());
                fzyqDomain.setPatientUser(patientUser);
                String fileName=FzyqWord.getDld(fzyqDomain,string);
                map.put(s,fileName);
            }
            if("24".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/shtl.docx";
                ShtlDomain shtlDomain=zzShtlScoreService.selectListByTaskId(patientSocietyTask.getTaskId());
                shtlDomain.setPatientUser(patientUser);
                String fileName=ShtlWord.getDld(shtlDomain,string);
                map.put(s,fileName);
            }
            if("25".equals(s)){
                String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sysb.docx";
                SysbDomain sysbDomain=zzSysbScoreService.selectListByTaskId(patientSocietyTask.getTaskId());
                sysbDomain.setPatientUser(patientUser);
                String fileName=SysbWord.getDld(sysbDomain,string);
                map.put(s,fileName);
            }
        }
        return AjaxResult.success(map);
    }
}
