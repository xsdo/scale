package com.qx.eis.service;

import com.qx.common.core.domain.AjaxResult;
import com.qx.eis.domain.EisRecord;
import com.qx.eis.domain.EisTable;
import com.qx.eis.domain.EisVo;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 14:57
 */
public interface IEisRecordService {


    List<EisRecord> selectEisRecords(Long delFlag);


    List<EisRecord>selectEisRecordByStatus(Long taskStatus);

    List<EisRecord>selectEisRecordByTel(String telNumber);

//    EisRecord selectEisRecordByScaleId(Long scaleId);


    EisRecord selectEisRecordById(Long tableId);

    EisRecord selectEisRecordByScaleId(Long scaleId);

    int insertEisRecord(EisRecord eisRecord);

    int updateEisRecord(EisRecord eisRecord);

    int inUpEisRecord(EisTable eisTable, String telNumber, Long scaleId);

    AjaxResult reviseRecord(List<EisVo> eisVoList, String telNumber, AjaxResult ajax);
}
