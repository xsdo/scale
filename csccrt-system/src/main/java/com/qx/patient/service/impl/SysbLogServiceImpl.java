package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.SysbLogMapper;
import com.qx.patient.domain.SysbLog;
import com.qx.patient.service.ISysbLogService;

/**
 * 失言识别日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class SysbLogServiceImpl implements ISysbLogService 
{
    @Autowired
    private SysbLogMapper sysbLogMapper;

    /**
     * 查询失言识别日志
     * 
     * @param id 失言识别日志ID
     * @return 失言识别日志
     */
    @Override
    public SysbLog selectSysbLogById(Long id)
    {
        return sysbLogMapper.selectSysbLogById(id);
    }

    /**
     * 查询失言识别日志列表
     * 
     * @param sysbLog 失言识别日志
     * @return 失言识别日志
     */
    @Override
    public List<SysbLog> selectSysbLogList(SysbLog sysbLog)
    {
        return sysbLogMapper.selectSysbLogList(sysbLog);
    }

    /**
     * 新增失言识别日志
     * 
     * @param sysbLog 失言识别日志
     * @return 结果
     */
    @Override
    public int insertSysbLog(SysbLog sysbLog)
    {
        sysbLog.setCreateTime(DateUtils.getNowDate());
        return sysbLogMapper.insertSysbLog(sysbLog);
    }

    /**
     * 修改失言识别日志
     * 
     * @param sysbLog 失言识别日志
     * @return 结果
     */
    @Override
    public int updateSysbLog(SysbLog sysbLog)
    {
        return sysbLogMapper.updateSysbLog(sysbLog);
    }

    /**
     * 批量删除失言识别日志
     * 
     * @param ids 需要删除的失言识别日志ID
     * @return 结果
     */
    @Override
    public int deleteSysbLogByIds(Long[] ids)
    {
        return sysbLogMapper.deleteSysbLogByIds(ids);
    }

    /**
     * 删除失言识别日志信息
     * 
     * @param id 失言识别日志ID
     * @return 结果
     */
    @Override
    public int deleteSysbLogById(Long id)
    {
        return sysbLogMapper.deleteSysbLogById(id);
    }
}
