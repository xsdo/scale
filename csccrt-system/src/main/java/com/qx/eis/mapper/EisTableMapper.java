package com.qx.eis.mapper;

import com.qx.eis.domain.EisTable;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 15:14
 */
public interface EisTableMapper {

    public List<EisTable> selectEisTables(Long id);

    public EisTable selectEisTableByScaleId(Long scaleId);

}
