package com.qx.patient.service;

import com.qx.patient.domain.FzyqLog;
import java.util.List;

/**
 * 复杂眼区日志Service接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface IFzyqLogService 
{
    /**
     * 查询复杂眼区日志
     * 
     * @param id 复杂眼区日志ID
     * @return 复杂眼区日志
     */
    public FzyqLog selectFzyqLogById(Long id);

    /**
     * 查询复杂眼区日志列表
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 复杂眼区日志集合
     */
    public List<FzyqLog> selectFzyqLogList(FzyqLog fzyqLog);

    /**
     * 新增复杂眼区日志
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 结果
     */
    public int insertFzyqLog(FzyqLog fzyqLog);

    /**
     * 修改复杂眼区日志
     * 
     * @param fzyqLog 复杂眼区日志
     * @return 结果
     */
    public int updateFzyqLog(FzyqLog fzyqLog);

    /**
     * 批量删除复杂眼区日志
     * 
     * @param ids 需要删除的复杂眼区日志ID
     * @return 结果
     */
    public int deleteFzyqLogByIds(Long[] ids);

    /**
     * 删除复杂眼区日志信息
     * 
     * @param id 复杂眼区日志ID
     * @return 结果
     */
    public int deleteFzyqLogById(Long id);
}
