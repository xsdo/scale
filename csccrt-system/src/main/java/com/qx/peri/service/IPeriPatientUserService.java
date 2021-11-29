package com.qx.peri.service;

import com.qx.peri.domain.PeriPatientUser;
import java.util.List;

/**
 * 围手术期系统患者Service接口
 * 
 * @author Meng
 * @date 2021-07-13
 */
public interface IPeriPatientUserService 
{
    /**
     * 查询围手术期系统患者
     * 
     * @param patientId 围手术期系统患者ID
     * @return 围手术期系统患者
     */
    public PeriPatientUser selectPeriPatientUserById(Long patientId);

    /**
     * 查询围手术期系统患者列表
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 围手术期系统患者集合
     */
    public List<PeriPatientUser> selectPeriPatientUserList(PeriPatientUser periPatientUser);

    /**
     * 新增围手术期系统患者
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 结果
     */
    public int insertPeriPatientUser(PeriPatientUser periPatientUser);

    /**
     * 修改围手术期系统患者
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 结果
     */
    public int updatePeriPatientUser(PeriPatientUser periPatientUser);

    /**
     * 批量删除围手术期系统患者
     * 
     * @param patientIds 需要删除的围手术期系统患者ID
     * @return 结果
     */
    public int deletePeriPatientUserByIds(Long[] patientIds);

    /**
     * 删除围手术期系统患者信息
     * 
     * @param patientId 围手术期系统患者ID
     * @return 结果
     */
    public int deletePeriPatientUserById(Long patientId);

    /**
     * 批量删除患者未完成任务
     *
     * @param patientId 需要删除患者ID
     * @return 结果
     */
    public int delAllTask(Long patientId);
}
