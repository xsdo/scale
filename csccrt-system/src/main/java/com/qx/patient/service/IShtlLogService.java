package com.qx.patient.service;

import com.qx.patient.domain.ShtlLog;
import java.util.List;

/**
 * 社会推理日志Service接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface IShtlLogService 
{
    /**
     * 查询社会推理日志
     * 
     * @param id 社会推理日志ID
     * @return 社会推理日志
     */
    public ShtlLog selectShtlLogById(Long id);

    /**
     * 查询社会推理日志列表
     * 
     * @param shtlLog 社会推理日志
     * @return 社会推理日志集合
     */
    public List<ShtlLog> selectShtlLogList(ShtlLog shtlLog);

    /**
     * 新增社会推理日志
     * 
     * @param shtlLog 社会推理日志
     * @return 结果
     */
    public int insertShtlLog(ShtlLog shtlLog);

    /**
     * 修改社会推理日志
     * 
     * @param shtlLog 社会推理日志
     * @return 结果
     */
    public int updateShtlLog(ShtlLog shtlLog);

    /**
     * 批量删除社会推理日志
     * 
     * @param ids 需要删除的社会推理日志ID
     * @return 结果
     */
    public int deleteShtlLogByIds(Long[] ids);

    /**
     * 删除社会推理日志信息
     * 
     * @param id 社会推理日志ID
     * @return 结果
     */
    public int deleteShtlLogById(Long id);
}
