package com.qx.web.controller.peri;

import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.StringUtils;
import com.qx.ipa.domain.IpaMedia;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.domain.IpaTaskScore;
import com.qx.ipa.service.IIpaMediaService;
import com.qx.peri.domain.PeriTask;
import com.qx.peri.service.IPeriTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * peri患者端请求Controller
 *
 * @author Meng
 * @date 2021-07-15
 */
@RestController
@RequestMapping("/peri/patientTask")
public class PeriPatientTaskController {

    @Autowired
    private IPeriTaskService iPeriTaskService;

    @Autowired
    private IIpaMediaService ipaMediaService;

    /**
     *患者端首页，查询任务，
     */
    @GetMapping("/getTaskPeri")
    public AjaxResult getTaskPeri(String workstation){
        PeriTask task = iPeriTaskService.selectPeriTaskByworkstation(workstation);
        return AjaxResult.success(task);
    }

    /**
     *患者端查询围手术期视频列表
     */
    @GetMapping("/getTaskPeriMedia")
    public AjaxResult getTaskPeriMedia(String typeids){
        AjaxResult result = new AjaxResult();
        IpaMedia ipaMedia = new IpaMedia();
        ipaMedia.setMark(typeids);
        //返回治疗视频和放松视频
        List<IpaMedia> ipaMedias = ipaMediaService.selectIpaMediaList(ipaMedia);
        return AjaxResult.success(ipaMedias);
    }

    /**
     *患者端完成任务后，更改任务状态
     */
    @GetMapping("/updatePeriTask")
    public AjaxResult insertTaskScore(String taskId ){
        if (StringUtils.isNotEmpty(taskId)){
            PeriTask periTask = iPeriTaskService.selectPeriTaskById(Long.parseLong(taskId));
            periTask.setTaskStatus("3");
            periTask.setDelFlag("1");
            iPeriTaskService.updatePeriTask(periTask);
        }
        return AjaxResult.success();
    }
}
