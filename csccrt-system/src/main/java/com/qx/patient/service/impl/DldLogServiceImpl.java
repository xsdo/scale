package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.DldLogMapper;
import com.qx.patient.domain.DldLog;
import com.qx.patient.service.IDldLogService;

/**
 * 多伦多量日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class DldLogServiceImpl implements IDldLogService 
{
    @Autowired
    private DldLogMapper dldLogMapper;

    /**
     * 查询多伦多量日志
     * 
     * @param id 多伦多量日志ID
     * @return 多伦多量日志
     */
    @Override
    public DldLog selectDldLogById(Long id)
    {
        return dldLogMapper.selectDldLogById(id);
    }

    /**
     * 查询多伦多量日志列表
     * 
     * @param dldLog 多伦多量日志
     * @return 多伦多量日志
     */
    @Override
    public List<DldLog> selectDldLogList(DldLog dldLog)
    {
        return dldLogMapper.selectDldLogList(dldLog);
    }

    /**
     * 新增多伦多量日志
     * 
     * @param dldLog 多伦多量日志
     * @return 结果
     */
    @Override
    public int insertDldLog(DldLog dldLog)
    {
        dldLog.setCreateTime(DateUtils.getNowDate());
        return dldLogMapper.insertDldLog(dldLog);
    }

    /**
     * 修改多伦多量日志
     * 
     * @param dldLog 多伦多量日志
     * @return 结果
     */
    @Override
    public int updateDldLog(DldLog dldLog)
    {
        return dldLogMapper.updateDldLog(dldLog);
    }

    /**
     * 批量删除多伦多量日志
     * 
     * @param ids 需要删除的多伦多量日志ID
     * @return 结果
     */
    @Override
    public int deleteDldLogByIds(Long[] ids)
    {
        return dldLogMapper.deleteDldLogByIds(ids);
    }

    /**
     * 删除多伦多量日志信息
     * 
     * @param id 多伦多量日志ID
     * @return 结果
     */
    @Override
    public int deleteDldLogById(Long id)
    {
        return dldLogMapper.deleteDldLogById(id);
    }
}
