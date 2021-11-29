package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.ShtlLogMapper;
import com.qx.patient.domain.ShtlLog;
import com.qx.patient.service.IShtlLogService;

/**
 * 社会推理日志Service业务层处理
 * 
 * @author patient
 * @date 2020-09-01
 */
@Service
public class ShtlLogServiceImpl implements IShtlLogService 
{
    @Autowired
    private ShtlLogMapper shtlLogMapper;

    /**
     * 查询社会推理日志
     * 
     * @param id 社会推理日志ID
     * @return 社会推理日志
     */
    @Override
    public ShtlLog selectShtlLogById(Long id)
    {
        return shtlLogMapper.selectShtlLogById(id);
    }

    /**
     * 查询社会推理日志列表
     * 
     * @param shtlLog 社会推理日志
     * @return 社会推理日志
     */
    @Override
    public List<ShtlLog> selectShtlLogList(ShtlLog shtlLog)
    {
        return shtlLogMapper.selectShtlLogList(shtlLog);
    }

    /**
     * 新增社会推理日志
     * 
     * @param shtlLog 社会推理日志
     * @return 结果
     */
    @Override
    public int insertShtlLog(ShtlLog shtlLog)
    {
        shtlLog.setCreateTime(DateUtils.getNowDate());
        return shtlLogMapper.insertShtlLog(shtlLog);
    }

    /**
     * 修改社会推理日志
     * 
     * @param shtlLog 社会推理日志
     * @return 结果
     */
    @Override
    public int updateShtlLog(ShtlLog shtlLog)
    {
        return shtlLogMapper.updateShtlLog(shtlLog);
    }

    /**
     * 批量删除社会推理日志
     * 
     * @param ids 需要删除的社会推理日志ID
     * @return 结果
     */
    @Override
    public int deleteShtlLogByIds(Long[] ids)
    {
        return shtlLogMapper.deleteShtlLogByIds(ids);
    }

    /**
     * 删除社会推理日志信息
     * 
     * @param id 社会推理日志ID
     * @return 结果
     */
    @Override
    public int deleteShtlLogById(Long id)
    {
        return shtlLogMapper.deleteShtlLogById(id);
    }
}
