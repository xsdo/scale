package com.qx.eis.mapper;

import com.qx.eis.domain.EisRecord;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 14:48
 */
public interface EisRecordMapper {

    public List<EisRecord>selectEisRecords(Long delFlag);

    public List<EisRecord>selectEisRecordByTel(String telNumber);


    public List<EisRecord>selectEisRecordByStatus(Long taskStatus);

    public int insertEisRecord(EisRecord eisRecord);

    public EisRecord selectEisRecordByScaleId(Long scaleId );

    public EisRecord selectEisRecordById(Long tableId );

    public int updateEisRecord(EisRecord eisRecord);
}
