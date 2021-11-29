package com.qx.ipa.service.impl;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.qx.common.utils.StringUtils;
import com.qx.ipa.domain.IpaQuestion;
import com.qx.ipa.domain.IpaScaleQuestions;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.domain.IpaTaskVo;
import com.qx.ipa.mapper.IpaQuestionMapper;
import com.qx.ipa.mapper.IpaTaskMapper;
import com.qx.ipa.mapper.IpaScaleQuestionsMapper;
import com.qx.ipa.service.IIpaTaskPatientService;
import com.qx.system.domain.SysDictData;
import com.qx.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IIpaTaskPatientServiceImpl implements IIpaTaskPatientService {

    @Autowired
    private IpaTaskMapper ipaTaskMapper;

    @Autowired
    private IpaQuestionMapper ipaQuestionMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private IpaScaleQuestionsMapper ipaScaleQuestionsMapper;

    /***
     *
     * @param workStation 工作站
     * @return List<IpaTask> 任务集合
     */
    @Override
    public List<IpaTask> getTask(String workStation) {

        //查询工作站下对应的未完成任务
        IpaTask ipaTask = new IpaTask();
        ipaTask.setWorkstation(workStation);
        List<IpaTask> ipaTasks = ipaTaskMapper.selectIpaTaskLists(ipaTask);
        for (IpaTask task : ipaTasks) {
            String day = task.getDay();
            task.setDay(StringUtils.substring(day,1,2));
        }
        return ipaTasks;
    }

    /***
     *
     * @param workStation 工作站
     * @return 新冠问题列表
     */
    @Override
    public List<IpaTask> getTaskA(String workStation) {

        //查询工作站下对应的未完成任务
        IpaTask ipaTask = new IpaTask();
        ipaTask.setWorkstation(workStation);
        List<IpaTask> ipaTasks = ipaTaskMapper.selectIpaTaskList(ipaTask);
        for (IpaTask task : ipaTasks) {
            //查询新冠问题列表
            if("0".equals(task.getTypeFlag())){
                ipaTask=task;
            }
            String day = task.getDay();
            task.setDay(StringUtils.substring(day,1,2));
        }
        return null;
    }
    /***
     *
     * @param workStation 工作站
     * @return 新冠问题列表
     */

    public List<IpaTask> get(String workStation) {

        IpaTaskVo ipaTaskVo = new IpaTaskVo();
        //查询工作站下对应的未完成任务
        List<IpaTask> ipaTasks = ipaTaskMapper.selectIpaTaskByworkstation(workStation);

        Map<String, Object> societyMap = new HashMap<>();
        ipaTaskVo.setTasks(ipaTasks);
        for (int i = 0; i <ipaTasks.size() ; i++) {
            //0为新冠    1为量表
            if (ipaTasks.get(i)!=null && "0".equals(ipaTasks.get(i).getTypeFlag())) {
                List<IpaQuestion> ipaQuestions = null;
                //查询任务下的day
                String[] arr=ipaTasks.get(i).getTypeids().split(",");
                IpaQuestion ipaQuestion = new IpaQuestion();
                for (int j = 0; j <arr.length ; j++) {
                    ipaQuestion.setDay(Long.parseLong(arr[j]));
                     ipaQuestions = ipaQuestionMapper.selectIpaQuestionList(ipaQuestion);
                }
                societyMap.put("0", ipaQuestions);
            }
            if (ipaTasks.get(i)!=null && "1".equals(ipaTasks.get(i).getTypeFlag())) {
                //查询任务下的量表
                String[] arr=ipaTasks.get(i).getTypeids().split(",");
                Map<String, List<Object>> mapo=new HashMap<>();
                IpaScaleQuestions ipaScaleQuestions = new IpaScaleQuestions();
                List<SysDictData> ipaScaleType = sysDictDataMapper.selectDictDataByType("ipa_scale_type");
                for (int k = 0; k <ipaScaleType.size() ; k++) {
                    ipaScaleQuestions.setScaleId(ipaScaleType.get(k).getDictCode());
                    List<IpaScaleQuestions> ipaScaleQuestionsList = ipaScaleQuestionsMapper.selectIpaScaleQuestionsList(ipaScaleQuestions);
                    mapo.put(ipaScaleType.get(k).getDictCode().toString(), Collections.singletonList(ipaScaleQuestionsList));
                }
                societyMap.put("1", mapo);
            }


        }
        ipaTaskVo.setSocietyMap(societyMap);
        return null;
    }

}
