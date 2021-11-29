package com.qx.eis.service.impl;

import com.qx.common.utils.StringUtils;
import com.qx.eis.domain.EisTask;
import com.qx.eis.mapper.EisTaskMapper;
import com.qx.eis.service.IEisTaskPatientService;
import com.qx.ipa.domain.IpaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 11:04
 */
@Service
public class EisTaskPatientServiceImpl implements IEisTaskPatientService {

    @Autowired
    private EisTaskMapper eisTaskMapper;

    @Override
    public List<EisTask> getTask(String workStation) {

        //查询工作站下对应的未完成任务
        EisTask eisTask = new EisTask();
        eisTask.setWorkstation(workStation);
        List<EisTask> epaTasks = eisTaskMapper.selectEisTaskLists(eisTask);
        for (EisTask task : epaTasks) {
            String day = task.getDay();
            task.setDay(StringUtils.substring(day,1,2));
        }
        return epaTasks;
    }

    @Override
    public EisTask selectEisTaskById(Long patient_id){

        return eisTaskMapper.selectEisTaskById(patient_id);
    };

}
