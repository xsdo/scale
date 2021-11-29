package com.qx.scale.service;

import com.qx.scale.domain.ScaleTask;
import java.util.List;

/**
 * 量表系统任务Service接口
 * 
 * @author patient
 * @date 2021-10-11
 */
public interface IScaleTaskService 
{
    /**
     * 查询量表系统任务
     * 
     * @param taskId 量表系统任务ID
     * @return 量表系统任务
     */
    public ScaleTask selectScaleTaskById(Long taskId);

    /**
     * 查询量表系统任务列表
     * 
     * @param scaleTask 量表系统任务
     * @return 量表系统任务集合
     */
    public List<ScaleTask> selectScaleTaskList(ScaleTask scaleTask);

    List<ScaleTask> getTask(String workstation);

    int updateTaskStatus(ScaleTask scaleTask);

    int updateByPatientId(ScaleTask scaleTask);

    Boolean getWebpackVersion(String workstation, String patientId);

    /**
     * 新增量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    public int insertScaleTask(ScaleTask scaleTask);

    int insertScaleTaskByScaleId(ScaleTask scaleTask);

    /**
     * 修改量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    public int updateScaleTask(ScaleTask scaleTask);

    /**
     * 批量删除量表系统任务
     * 
     * @param taskIds 需要删除的量表系统任务ID
     * @return 结果
     */
    public int deleteScaleTaskByIds(Long[] taskIds);

    /**
     * 删除量表系统任务信息
     * 
     * @param taskId 量表系统任务ID
     * @return 结果
     */
    public int deleteScaleTaskById(Long taskId);
}
