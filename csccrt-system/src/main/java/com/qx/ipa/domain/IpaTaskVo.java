package com.qx.ipa.domain;

import java.util.List;
import java.util.Map;

/**
 * ipa任务传到前端对象
 */
public class IpaTaskVo {

    /**
     *任务对象
     */
    private List<IpaTask> tasks;

    /**
     * 量表or问题集合
     */
    private Map<String, Object> societyMap;

    private List<IpaQuestion> ipaQuestions;
    /**
     * 焦虑自评量表问题集合
     */
    private List<IpaScaleQuestions> SasList;
    /**
     * 抑郁自评量表问题集合
     */
    private List<IpaScaleQuestions> SdsList;
    /**
     * 阿森斯自评量表问题集合
     */
    private List<IpaScaleQuestions> AisList;



    public List<IpaTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<IpaTask> tasks) {
        this.tasks = tasks;
    }

    public Map<String, Object> getSocietyMap() {
        return societyMap;
    }

    public void setSocietyMap(Map<String, Object> societyMap) {
        this.societyMap = societyMap;
    }
}
