package com.qx.ipa.service;

import com.qx.demo.entity.vo.TaskAVo;
import com.qx.demo.entity.vo.TaskBVo;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.domain.IpaTaskVo;

import java.util.List;

public interface IIpaTaskPatientService {
    /****
     *
     * @param workStation 工作站
     * @return ipa任务前端返回对象
     */
    public List<IpaTask> getTask(String workStation);

    public List<IpaTask> getTaskA(String workStation);

}
