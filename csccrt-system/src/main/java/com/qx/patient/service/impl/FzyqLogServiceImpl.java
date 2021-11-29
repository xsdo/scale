package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.FzyqLogMapper;
import com.qx.patient.domain.FzyqLog;
import com.qx.patient.service.IFzyqLogService;

/**
 * 复杂眼区日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class FzyqLogServiceImpl implements IFzyqLogService 
{
    @Autowired
    private FzyqLogMapper fzyqLogMapper;

    /**
     * 查询复杂眼区日志
     * 
     * @param id 复杂眼区日志ID
     * @return 复杂眼区日志
     */
    @Override
    public FzyqLog selectFzyqLogById(Long id)
    {
        return fzyqLogMapper.selectFzyqLogById(id);
    }

    /**
     * 查询复杂眼区日志列表
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 复杂眼区日志
     */
    @Override
    public List<FzyqLog> selectFzyqLogList(FzyqLog fzyqLog)
    {
        return fzyqLogMapper.selectFzyqLogList(fzyqLog);
    }

    /**
     * 新增复杂眼区日志
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 结果
     */
    @Override
    public int insertFzyqLog(FzyqLog fzyqLog)
    {
        fzyqLog.setCreateTime(DateUtils.getNowDate());
        return fzyqLogMapper.insertFzyqLog(fzyqLog);
    }

    /**
     * 修改复杂眼区日志
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 结果
     */
    @Override
    public int updateFzyqLog(FzyqLog fzyqLog)
    {
        return fzyqLogMapper.updateFzyqLog(fzyqLog);
    }

    /**
     * 批量删除复杂眼区日志
     * 
     * @param ids 需要删除的复杂眼区日志ID
     * @return 结果
     */
    @Override
    public int deleteFzyqLogByIds(Long[] ids)
    {
        return fzyqLogMapper.deleteFzyqLogByIds(ids);
    }

    /**
     * 删除复杂眼区日志信息
     * 
     * @param id 复杂眼区日志ID
     * @return 结果
     */
    @Override
    public int deleteFzyqLogById(Long id)
    {
        return fzyqLogMapper.deleteFzyqLogById(id);
    }
}
