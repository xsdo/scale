package com.qx.ipa.mapper;

import com.qx.ipa.domain.IpaTask;

import java.util.List;

/**
 * 智能化心身调节任务Mapper接口
 * 
 * @author qx
 * @date 2021-07-05
 */
public interface IpaTaskMapper 
{
    /**
     * 查询智能化心身调节任务
     * 
     * @param taskId 智能化心身调节任务ID
     * @return 智能化心身调节任务
     */
    public IpaTask selectIpaTaskById(Long taskId);

    /**
     * 查询智能化心身调节任务列表
     *
     * @param ipaTask 智能化心身调节任务
     * @return 智能化心身调节任务集合
     */
    public List<IpaTask> selectIpaTaskList(IpaTask ipaTask);

    /**
     * 患者端首页，查询任务
     *
     * @param ipaTask 智能化心身调节任务
     * @return 智能化心身调节任务集合
     */
    public List<IpaTask> selectIpaTaskLists(IpaTask ipaTask);

    /**
     * 新增智能化心身调节任务
     * 
     * @param ipaTask 智能化心身调节任务
     * @return 结果
     */
    public int insertIpaTask(IpaTask ipaTask);

    /**
     * 修改智能化心身调节任务
     * 
     * @param ipaTask 智能化心身调节任务
     * @return 结果
     */
    public int updateIpaTask(IpaTask ipaTask);

    /**
     * 删除智能化心身调节任务
     * 
     * @param taskId 智能化心身调节任务ID
     * @return 结果
     */
    public int deleteIpaTaskById(Long taskId);

    /**
     * 批量删除智能化心身调节任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIpaTaskByIds(Long[] taskIds);

    public int updateByPatientId(IpaTask ipaTask);

    /**
     * 根据工作站查询任务列表
     *
     * @param  workstation 工作站
     * @return 智能化心身调节任务集合
     */
    public List<IpaTask> selectIpaTaskByworkstation(String workstation);

}
