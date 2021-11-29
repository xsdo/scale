package com.qx.patient.mapper;

import com.qx.patient.domain.DldLog;
import java.util.List;

/**
 * 多伦多量日志Mapper接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface DldLogMapper 
{
    /**
     * 查询多伦多量日志
     * 
     * @param id 多伦多量日志ID
     * @return 多伦多量日志
     */
    public DldLog selectDldLogById(Long id);

    /**
     * 查询多伦多量日志列表
     * 
     * @param dldLog 多伦多量日志
     * @return 多伦多量日志集合
     */
    public List<DldLog> selectDldLogList(DldLog dldLog);

    /**
     * 新增多伦多量日志
     * 
     * @param dldLog 多伦多量日志
     * @return 结果
     */
    public int insertDldLog(DldLog dldLog);

    /**
     * 修改多伦多量日志
     * 
     * @param dldLog 多伦多量日志
     * @return 结果
     */
    public int updateDldLog(DldLog dldLog);

    /**
     * 删除多伦多量日志
     * 
     * @param id 多伦多量日志ID
     * @return 结果
     */
    public int deleteDldLogById(Long id);

    /**
     * 批量删除多伦多量日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDldLogByIds(Long[] ids);
}
