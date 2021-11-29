package com.qx.eis.service;

import com.qx.eis.domain.EisTable;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 15:17
 */
public interface IEisTableService {

    List<EisTable> selectEisTables(Long id);


    EisTable selectEisTableByScaleId(Long scaleId);
}
