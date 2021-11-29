package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.EisLogMapper;
import com.qx.patient.domain.EisLog;
import com.qx.patient.service.IEisLogService;

/**
 * 情绪智力量日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class EisLogServiceImpl implements IEisLogService 
{
    @Autowired
    private EisLogMapper eisLogMapper;

    /**
     * 查询情绪智力量日志
     * 
     * @param id 情绪智力量日志ID
     * @return 情绪智力量日志
     */
    @Override
    public EisLog selectEisLogById(Long id)
    {
        return eisLogMapper.selectEisLogById(id);
    }

    /**
     * 查询情绪智力量日志列表
     * 
     * @param eisLog 情绪智力量日志
     * @return 情绪智力量日志
     */
    @Override
    public List<EisLog> selectEisLogList(EisLog eisLog)
    {
        return eisLogMapper.selectEisLogList(eisLog);
    }

    /**
     * 新增情绪智力量日志
     * 
     * @param eisLog 情绪智力量日志
     * @return 结果
     */
    @Override
    public int insertEisLog(EisLog eisLog)
    {
        eisLog.setCreateTime(DateUtils.getNowDate());
        return eisLogMapper.insertEisLog(eisLog);
    }

    /**
     * 修改情绪智力量日志
     * 
     * @param eisLog 情绪智力量日志
     * @return 结果
     */
    @Override
    public int updateEisLog(EisLog eisLog)
    {
        return eisLogMapper.updateEisLog(eisLog);
    }

    /**
     * 批量删除情绪智力量日志
     * 
     * @param ids 需要删除的情绪智力量日志ID
     * @return 结果
     */
    @Override
    public int deleteEisLogByIds(Long[] ids)
    {
        return eisLogMapper.deleteEisLogByIds(ids);
    }

    /**
     * 删除情绪智力量日志信息
     * 
     * @param id 情绪智力量日志ID
     * @return 结果
     */
    @Override
    public int deleteEisLogById(Long id)
    {
        return eisLogMapper.deleteEisLogById(id);
    }
}
