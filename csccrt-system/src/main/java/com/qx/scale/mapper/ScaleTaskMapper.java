package com.qx.scale.mapper;

import com.qx.scale.domain.ScaleTask;
import java.util.List;

/**
 * 量表系统任务Mapper接口
 * 
 * @author patient
 * @date 2021-10-11
 */
public interface ScaleTaskMapper 
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


    public List<ScaleTask> selectScaleTaskLists(ScaleTask scaleTask);


    /**
     * 新增量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    public int insertScaleTask(ScaleTask scaleTask);

    /**
     * 修改量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    public int updateScaleTask(ScaleTask scaleTask);

    /**
     * 删除量表系统任务
     * 
     * @param taskId 量表系统任务ID
     * @return 结果
     */
    public int deleteScaleTaskById(Long taskId);

    /**
     * 批量删除量表系统任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteScaleTaskByIds(Long[] taskIds);
}
