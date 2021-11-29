package com.qx.peri.mapper;

import com.qx.ipa.domain.IpaTask;
import com.qx.peri.domain.PeriTask;
import java.util.List;

/**
 * 围手术期任务Mapper接口
 * 
 * @author Meng
 * @date 2021-07-13
 */
public interface PeriTaskMapper 
{
    /**
     * 查询围手术期任务
     * 
     * @param taskId 围手术期任务ID
     * @return 围手术期任务
     */
    public PeriTask selectPeriTaskById(Long taskId);

    /**
     * 查询围手术期任务列表
     * 
     * @param periTask 围手术期任务
     * @return 围手术期任务集合
     */
    public List<PeriTask> selectPeriTaskList(PeriTask periTask);

    /**
     * 新增围手术期任务
     * 
     * @param periTask 围手术期任务
     * @return 结果
     */
    public int insertPeriTask(PeriTask periTask);

    /**
     * 修改围手术期任务
     * 
     * @param periTask 围手术期任务
     * @return 结果
     */
    public int updatePeriTask(PeriTask periTask);

    /**
     * 前端启动任务成功后，更改任务状态
     *
     * @param periTask 围手术期任务
     * @return 结果
     */
    public int updateByPatientId(PeriTask periTask);

    /**
     * 删除围手术期任务
     * 
     * @param taskId 围手术期任务ID
     * @return 结果
     */
    public int deletePeriTaskById(Long taskId);

    /**
     * 批量删除围手术期任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePeriTaskByIds(Long[] taskIds);

    /**
     * 根据工作站查询任务列表
     *
     * @param  workstation 工作站
     * @return 智能化心身调节任务集合
     */
    public PeriTask selectPeriTaskByworkstation(String workstation);
}
