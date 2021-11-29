package com.qx.eis.service.impl;

import com.qx.eis.domain.EisTaskScore;
import com.qx.eis.mapper.EisTaskScareMapper;
import com.qx.eis.service.IEisTaskScoreService;
import com.qx.ipa.domain.IpaTaskScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 14:44
 */
@Service
public class EisTaskScoreServiceImpl implements IEisTaskScoreService {

    @Autowired
    private EisTaskScareMapper eisTaskScareMapper;

    @Override
    public EisTaskScore selectEisTaskScoreById(Long id)
    {
        return eisTaskScareMapper.selectEisTaskScoreById(id);
    }
}
