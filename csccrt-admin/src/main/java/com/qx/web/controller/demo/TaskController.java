package com.qx.web.controller.demo;

import com.qx.common.core.domain.AjaxResult;
import com.qx.demo.entity.vo.*;
import com.qx.demo.service.TaskAllService;
import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;
import com.qx.patient.service.IPatientAdvancedTaskService;
import com.qx.patient.service.IPatientBasisTaskService;
import com.qx.patient.service.IPatientIntermediateTaskService;
import com.qx.patient.service.IPatientSocietyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("demo/TaskController")
public class TaskController {
      @Autowired
      private TaskAllService taskAllService;
      @Resource
      private IPatientSocietyTaskService patientSocietyTaskService;
      @Resource
      private IPatientBasisTaskService patientBasisTaskService;
      @Resource
      private IPatientIntermediateTaskService patientIntermediateTaskService;
      @Resource
      private IPatientAdvancedTaskService patientAdvancedTaskService;
      @GetMapping("/getTaskA")
      //@CrossOrigin
      public AjaxResult getTask(String workStation){
         TaskAVo taskAVo = taskAllService.getTaskA(workStation);
         return AjaxResult.success(taskAVo);
      }
    @GetMapping("/getTaskB")
    //@CrossOrigin
    public AjaxResult getTaskB(String workStation){
        TaskBVo taskBVo = taskAllService.getTaskB(workStation);
        return AjaxResult.success(taskBVo);
    }
    @GetMapping("/getTaskC")
    //@CrossOrigin
    public AjaxResult getTaskC(String workStation){
        TaskCVo taskCVo = taskAllService.getTaskC(workStation);
        return AjaxResult.success(taskCVo);
    }
    @GetMapping("/getTaskD")
    //@CrossOrigin
    public AjaxResult getTaskD(String workStation){
        TaskDVo taskDVo = taskAllService.getTaskD(workStation);
        return AjaxResult.success(taskDVo);
    }
      @PostMapping("/addResult")
      //@CrossOrigin
      public AjaxResult addScResult(@RequestBody List<ResultVo> result){
          PatientSocietyTask patientSocietyTask=patientSocietyTaskService.selectPatientSocietyTaskById(result.get(0).getTaskId());
          if(!"".equals(patientSocietyTask.getScaleId())&&patientSocietyTask.getScaleId()!=null) {
              String[] arr = patientSocietyTask.getScaleId().split(",");
              for (String s : arr) {
                  if (!"".equals(s) && result.get(0).getScaleId() == Long.parseLong(s)) {
                      return AjaxResult.error("数据已存在");
                  }
              }
          }
         return AjaxResult.success(taskAllService.addResult(result));
      }
      @GetMapping("/updateBasisTask")
      public AjaxResult updateBasisTask(String taskId,String scaleId){
          PatientBasisTask patientBasisTask=patientBasisTaskService.selectPatientBasisTaskById(Long.parseLong(taskId));
          if(!"".equals(patientBasisTask.getScaleId())&&patientBasisTask.getScaleId()!=null) {
              String[] arr = patientBasisTask.getScaleId().split(",");
              for (String s : arr) {
                  if (!"".equals(s) && scaleId.equals(s)) {
                      return AjaxResult.error("数据已存在");
                  }
              }
          }
          return AjaxResult.success(taskAllService.updateBasisTask(taskId,scaleId));
      }
    @GetMapping("/updateIntermediateTask")
    public AjaxResult updateIntermediateTask(String taskId,String scaleId){
        PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskService.selectPatientIntermediateTaskById(Long.parseLong(taskId));
        if(!"".equals(patientIntermediateTask.getScaleId())&&patientIntermediateTask.getScaleId()!=null) {
            String[] arr = patientIntermediateTask.getScaleId().split(",");
            for (String s : arr) {
                if (!"".equals(s) && scaleId.equals(s)) {
                    return AjaxResult.error("数据已存在");
                }
            }
        }
        return AjaxResult.success(taskAllService.updateIntermediateTask(taskId,scaleId));
    }
    @GetMapping("/updateAdvancedTask")
    public AjaxResult updateAdvancedTask(String taskId,String scaleId){
        PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskService.selectPatientAdvancedTaskById(Long.parseLong(taskId));
        if(!"".equals(patientAdvancedTask.getScaleId())&&patientAdvancedTask.getScaleId()!=null) {
            String[] arr = patientAdvancedTask.getScaleId().split(",");
            for (String s : arr) {
                if (!"".equals(s) && scaleId.equals(s)) {
                    return AjaxResult.error("数据已存在");
                }
            }
        }
        return AjaxResult.success(taskAllService.updateAdvancedTask(taskId,scaleId));
    }
}
