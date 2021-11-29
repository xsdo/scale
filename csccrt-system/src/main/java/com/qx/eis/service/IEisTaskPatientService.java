package com.qx.eis.service;

import com.qx.eis.domain.EisTask;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 11:01
 */
public interface IEisTaskPatientService {
    List<EisTask> getTask(String workStation);


    EisTask selectEisTaskById(Long patient_id);

}
