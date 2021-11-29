package com.qx.patient.service;

import com.qx.patient.domain.MettLog;
import java.util.List;

/**
 * 情绪识别日志Service接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface IMettLogService 
{
    /**
     * 查询情绪识别日志
     * 
     * @param id 情绪识别日志ID
     * @return 情绪识别日志
     */
    public MettLog selectMettLogById(Long id);

    /**
     * 查询情绪识别日志列表
     * 
     * @param mettLog 情绪识别日志
     * @return 情绪识别日志集合
     */
    public List<MettLog> selectMettLogList(MettLog mettLog);

    /**
     * 新增情绪识别日志
     * 
     * @param mettLog 情绪识别日志
     * @return 结果
     */
    public int insertMettLog(MettLog mettLog);

    /**
     * 修改情绪识别日志
     * 
     * @param mettLog 情绪识别日志
     * @return 结果
     */
    public int updateMettLog(MettLog mettLog);

    /**
     * 批量删除情绪识别日志
     * 
     * @param ids 需要删除的情绪识别日志ID
     * @return 结果
     */
    public int deleteMettLogByIds(Long[] ids);

    /**
     * 删除情绪识别日志信息
     * 
     * @param id 情绪识别日志ID
     * @return 结果
     */
    public int deleteMettLogById(Long id);
}
