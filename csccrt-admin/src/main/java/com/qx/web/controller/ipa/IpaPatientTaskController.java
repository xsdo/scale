package com.qx.web.controller.ipa;

import com.qx.common.core.domain.AjaxResult;
import com.qx.ipa.domain.*;
import com.qx.ipa.service.*;
import com.qx.system.domain.SysDictData;
import com.qx.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ipa患者端请求Controller
 *
 * @author qx
 * @date 2021-07-05
 */
@RestController
@RequestMapping("/ipa/patientTask")
public class IpaPatientTaskController {

    @Autowired
    private IIpaTaskPatientService iIpaTaskPatientService;

    @Autowired
    private IIpaQuestionService ipaQuestionService;

    @Autowired
    private IIpaMediaService ipaMediaService;

    @Autowired
    IIpaScaleQuestionsService ipaScaleQuestionsService;

    @Autowired
    ISysDictDataService iSysDictDataService;

    @Autowired
    IIpaTaskService ipaTaskService;

    @Autowired
    IIpaTaskScoreService ipaTaskScoreService;

    /**
     *患者端首页，查询任务，
     */
    @GetMapping("/getTaskIpa")
    public AjaxResult getTaskIpa(String workstation){
        List<IpaTask> task = iIpaTaskPatientService.getTask(workstation);
        return AjaxResult.success(task);
    }

    /**
     *患者端查询新冠问题列表，
     */
    @GetMapping("/getTaskIpaQuestion")
    public AjaxResult getTaskIpaQuestion(String typeids){
        IpaQuestion ipaQuestion = new IpaQuestion();
        ipaQuestion.setDay(Long.parseLong(typeids));
        List<IpaQuestion> ipaQuestions = ipaQuestionService.selectIpaQuestionList(ipaQuestion);

        return AjaxResult.success(ipaQuestions);
    }

    /**
     *患者端查询新冠media列表
     */
    @GetMapping("/getTaskIpaMedia")
    public AjaxResult getTaskIpaMedia(String typeids){
        AjaxResult result = new AjaxResult();
        IpaMedia ipaMedia = ipaMediaService.selectIpaMediaByMark(typeids);
        //治疗音频
        IpaMedia ipaMedia1 = ipaMediaService.selectIpaMediaByMark("9");
        IpaMedia ipaMedia2 = ipaMediaService.selectIpaMediaByMark("10");
        result.put("media",ipaMedia);
        result.put("audio1",ipaMedia1);
        result.put("audio2",ipaMedia2);
        return result;
    }

    /**
     *返回患者端一个随机放松视频
     */
    @GetMapping("/getTaskIpaMediaRelax")
    public AjaxResult getTaskIpaMediaRelax(){
        IpaMedia ipaMedia = ipaMediaService.selectIpaMediaRelax();
        return AjaxResult.success(ipaMedia);
    }

    /**
     *返回患者端量表题目
     */
    @GetMapping("/getTaskIpaScalQuesion")
    public AjaxResult getTaskIpaScalQuesion(){
        IpaScaleQuestions ipaScaleQuestions = new IpaScaleQuestions();
        AjaxResult result = new AjaxResult();
        //查询量表数量 =>分别查对应的量表题目
        List<SysDictData> ipaScaleType = iSysDictDataService.selectDictDataByType("ipa_scale_type");
        for (SysDictData sysDictData : ipaScaleType) {
            ipaScaleQuestions.setScaleId(sysDictData.getDictCode());
            List<IpaScaleQuestions> ipaScaleQuestionsList = ipaScaleQuestionsService.selectIpaScaleQuestionsList(ipaScaleQuestions);
            result.put(sysDictData.getDictCode().toString(),ipaScaleQuestionsList);
        }
        return AjaxResult.success(result);
    }

    /**
     *患者端保存ipa任务得分
     */
    @PostMapping("/insertTaskScore")
    //public AjaxResult insertTaskScore(Long taskId,String scaleId,int score ){
    public AjaxResult insertTaskScore(@RequestBody IpaTaskScore ipaTaskScore ){
        //通过任务id，来获取得分任务中要保存的数据
        IpaTask ipaTask = ipaTaskService.selectIpaTaskById(ipaTaskScore.getTaskId());
        String scaleId="";
        if(ipaTaskScore.getScaleId()!=null) {
            scaleId = ipaTaskScore.getScaleId().toString();
        }
        int i = ipaTaskScoreService.insertIpaTaskScore(ipaTask,scaleId,ipaTaskScore.getScore());
        //如果任务得分数据插入成功，将任务表中的状态更改为3 已完成
        if (i==1){
            ipaTaskService.updateIpaTaskStatus(ipaTask);
        }
        return AjaxResult.success();
    }
}
