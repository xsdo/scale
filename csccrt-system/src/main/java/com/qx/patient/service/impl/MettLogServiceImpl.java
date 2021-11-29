package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.MettLogMapper;
import com.qx.patient.domain.MettLog;
import com.qx.patient.service.IMettLogService;

/**
 * 情绪识别日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class MettLogServiceImpl implements IMettLogService 
{
    @Autowired
    private MettLogMapper mettLogMapper;

    /**
     * 查询情绪识别日志
     * 
     * @param id 情绪识别日志ID
     * @return 情绪识别日志
     */
    @Override
    public MettLog selectMettLogById(Long id)
    {
        return mettLogMapper.selectMettLogById(id);
    }

    /**
     * 查询情绪识别日志列表
     * 
     * @param mettLog 情绪识别日志
     * @return 情绪识别日志
     */
    @Override
    public List<MettLog> selectMettLogList(MettLog mettLog)
    {
        return mettLogMapper.selectMettLogList(mettLog);
    }

    /**
     * 新增情绪识别日志
     * 
     * @param mettLog 情绪识别日志
     * @return 结果
     */
    @Override
    public int insertMettLog(MettLog mettLog)
    {
        mettLog.setCreateTime(DateUtils.getNowDate());
        return mettLogMapper.insertMettLog(mettLog);
    }

    /**
     * 修改情绪识别日志
     * 
     * @param mettLog 情绪识别日志
     * @return 结果
     */
    @Override
    public int updateMettLog(MettLog mettLog)
    {
        return mettLogMapper.updateMettLog(mettLog);
    }

    /**
     * 批量删除情绪识别日志
     * 
     * @param ids 需要删除的情绪识别日志ID
     * @return 结果
     */
    @Override
    public int deleteMettLogByIds(Long[] ids)
    {
        return mettLogMapper.deleteMettLogByIds(ids);
    }

    /**
     * 删除情绪识别日志信息
     * 
     * @param id 情绪识别日志ID
     * @return 结果
     */
    @Override
    public int deleteMettLogById(Long id)
    {
        return mettLogMapper.deleteMettLogById(id);
    }
}
