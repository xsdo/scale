package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.IricLogMapper;
import com.qx.patient.domain.IricLog;
import com.qx.patient.service.IIricLogService;

/**
 * 人际反应指针日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class IricLogServiceImpl implements IIricLogService 
{
    @Autowired
    private IricLogMapper iricLogMapper;

    /**
     * 查询人际反应指针日志
     * 
     * @param id 人际反应指针日志ID
     * @return 人际反应指针日志
     */
    @Override
    public IricLog selectIricLogById(Long id)
    {
        return iricLogMapper.selectIricLogById(id);
    }

    /**
     * 查询人际反应指针日志列表
     * 
     * @param iricLog 人际反应指针日志
     * @return 人际反应指针日志
     */
    @Override
    public List<IricLog> selectIricLogList(IricLog iricLog)
    {
        return iricLogMapper.selectIricLogList(iricLog);
    }

    /**
     * 新增人际反应指针日志
     * 
     * @param iricLog 人际反应指针日志
     * @return 结果
     */
    @Override
    public int insertIricLog(IricLog iricLog)
    {
        iricLog.setCreateTime(DateUtils.getNowDate());
        return iricLogMapper.insertIricLog(iricLog);
    }

    /**
     * 修改人际反应指针日志
     * 
     * @param iricLog 人际反应指针日志
     * @return 结果
     */
    @Override
    public int updateIricLog(IricLog iricLog)
    {
        return iricLogMapper.updateIricLog(iricLog);
    }

    /**
     * 批量删除人际反应指针日志
     * 
     * @param ids 需要删除的人际反应指针日志ID
     * @return 结果
     */
    @Override
    public int deleteIricLogByIds(Long[] ids)
    {
        return iricLogMapper.deleteIricLogByIds(ids);
    }

    /**
     * 删除人际反应指针日志信息
     * 
     * @param id 人际反应指针日志ID
     * @return 结果
     */
    @Override
    public int deleteIricLogById(Long id)
    {
        return iricLogMapper.deleteIricLogById(id);
    }
}
