package com.qx.eis.mapper;

import com.qx.eis.domain.EisTask;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 11:06
 */
public interface EisTaskMapper {

    public List<EisTask>selectEisTaskLists(EisTask eisTask);


    public EisTask selectEisTaskById(Long patient_id);
}
