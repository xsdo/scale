package com.qx.patient.mapper;

import com.qx.patient.domain.EisLog;
import java.util.List;

/**
 * 情绪智力量日志Mapper接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface EisLogMapper 
{
    /**
     * 查询情绪智力量日志
     * 
     * @param id 情绪智力量日志ID
     * @return 情绪智力量日志
     */
    public EisLog selectEisLogById(Long id);

    /**
     * 查询情绪智力量日志列表
     * 
     * @param eisLog 情绪智力量日志
     * @return 情绪智力量日志集合
     */
    public List<EisLog> selectEisLogList(EisLog eisLog);

    /**
     * 新增情绪智力量日志
     * 
     * @param eisLog 情绪智力量日志
     * @return 结果
     */
    public int insertEisLog(EisLog eisLog);

    /**
     * 修改情绪智力量日志
     * 
     * @param eisLog 情绪智力量日志
     * @return 结果
     */
    public int updateEisLog(EisLog eisLog);

    /**
     * 删除情绪智力量日志
     * 
     * @param id 情绪智力量日志ID
     * @return 结果
     */
    public int deleteEisLogById(Long id);

    /**
     * 批量删除情绪智力量日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEisLogByIds(Long[] ids);
}
