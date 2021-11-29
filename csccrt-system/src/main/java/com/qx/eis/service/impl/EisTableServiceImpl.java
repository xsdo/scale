package com.qx.eis.service.impl;

import com.qx.eis.domain.EisTable;
import com.qx.eis.mapper.EisTableMapper;
import com.qx.eis.service.IEisTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 15:18
 */
@Service
public class EisTableServiceImpl implements IEisTableService {

    @Autowired
    private EisTableMapper eisTableMapper;

    @Override
    public List<EisTable> selectEisTables(Long id){
        return eisTableMapper.selectEisTables(id);
    };

    @Override
    public EisTable selectEisTableByScaleId(Long scaleId){
        return eisTableMapper.selectEisTableByScaleId(scaleId);
    }

}
