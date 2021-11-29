package com.qx.patient.mapper;

import com.qx.patient.domain.IricLog;
import java.util.List;

/**
 * 人际反应指针日志Mapper接口
 * 
 * @author patient
 * @date 2020-09-01
 */
public interface IricLogMapper 
{
    /**
     * 查询人际反应指针日志
     * 
     * @param id 人际反应指针日志ID
     * @return 人际反应指针日志
     */
    public IricLog selectIricLogById(Long id);

    /**
     * 查询人际反应指针日志列表
     * 
     * @param iricLog 人际反应指针日志
     * @return 人际反应指针日志集合
     */
    public List<IricLog> selectIricLogList(IricLog iricLog);

    /**
     * 新增人际反应指针日志
     * 
     * @param iricLog 人际反应指针日志
     * @return 结果
     */
    public int insertIricLog(IricLog iricLog);

    /**
     * 修改人际反应指针日志
     * 
     * @param iricLog 人际反应指针日志
     * @return 结果
     */
    public int updateIricLog(IricLog iricLog);

    /**
     * 删除人际反应指针日志
     * 
     * @param id 人际反应指针日志ID
     * @return 结果
     */
    public int deleteIricLogById(Long id);

    /**
     * 批量删除人际反应指针日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIricLogByIds(Long[] ids);
}
