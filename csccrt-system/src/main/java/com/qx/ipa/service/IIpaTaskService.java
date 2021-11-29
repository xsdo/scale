package com.qx.ipa.service;

import com.qx.demo.entity.DldDomain;
import com.qx.ipa.domain.IpaDomain;
import com.qx.ipa.domain.IpaTask;
import java.util.List;

/**
 * 智能化心身调节任务Service接口
 * 
 * @author qx
 * @date 2021-07-05
 */
public interface IIpaTaskService 
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
     * 批量删除智能化心身调节任务
     * 
     * @param taskIds 需要删除的智能化心身调节任务ID
     * @return 结果
     */
    public int deleteIpaTaskByIds(Long[] taskIds);

    /**
     * 删除智能化心身调节任务信息
     * 
     * @param taskId 智能化心身调节任务ID
     * @return 结果
     */
    public int deleteIpaTaskById(Long taskId);

    int updateByPatientId(IpaTask ipaTask);

    Boolean getWebpackVersion(String workstation, String patientId);

    /**
     * 根据工作站查询任务列表
     *
     * @param  workstation 工作站
     * @return 智能化心身调节任务集合
     */
    public List<IpaTask> selectIpaTaskByworkstation(String workstation);

    /***
     * 患者端答题后 更新任务表完成状态status
     * @param ipaTask
     * @return
     */
    public int updateIpaTaskStatus(IpaTask ipaTask);

}
