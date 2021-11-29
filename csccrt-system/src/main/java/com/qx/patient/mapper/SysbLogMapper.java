package com.qx.patient.mapper;

import com.qx.patient.domain.SysbLog;
import java.util.List;

/**
 * 失言识别日志Mapper接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface SysbLogMapper 
{
    /**
     * 查询失言识别日志
     * 
     * @param id 失言识别日志ID
     * @return 失言识别日志
     */
    public SysbLog selectSysbLogById(Long id);

    /**
     * 查询失言识别日志列表
     * 
     * @param sysbLog 失言识别日志
     * @return 失言识别日志集合
     */
    public List<SysbLog> selectSysbLogList(SysbLog sysbLog);

    /**
     * 新增失言识别日志
     * 
     * @param sysbLog 失言识别日志
     * @return 结果
     */
    public int insertSysbLog(SysbLog sysbLog);

    /**
     * 修改失言识别日志
     * 
     * @param sysbLog 失言识别日志
     * @return 结果
     */
    public int updateSysbLog(SysbLog sysbLog);

    /**
     * 删除失言识别日志
     * 
     * @param id 失言识别日志ID
     * @return 结果
     */
    public int deleteSysbLogById(Long id);

    /**
     * 批量删除失言识别日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysbLogByIds(Long[] ids);
}
