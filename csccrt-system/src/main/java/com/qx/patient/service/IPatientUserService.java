package com.qx.patient.service;

import com.github.pagehelper.PageInfo;
import com.qx.patient.domain.PatientUser;
import java.util.List;

/**
 * 患者Service接口
 * 
 * @author patient
 * @date 2020-07-08
 */
public interface IPatientUserService 
{
    /**
     * 查询患者
     * 
     * @param patientId 患者ID
     * @return 患者
     */
    public PatientUser selectPatientUserById(Long patientId);

    /**
     * 查询患者列表
     * 
     * @param patientUser 患者
     * @return 患者集合
     */
    public List<PatientUser> selectPatientUserList(PatientUser patientUser);

    /**
     * 新增患者
     * 
     * @param patientUser 患者
     * @return 结果
     */
    public int insertPatientUser(PatientUser patientUser);

    /**
     * 修改患者
     * 
     * @param patientUser 患者
     * @return 结果
     */
    public int updatePatientUser(PatientUser patientUser);

    /**
     * 批量删除患者
     * 
     * @param patientIds 需要删除的患者ID
     * @return 结果
     */
    public int deletePatientUserByIds(Long[] patientIds);

    /**
     * 删除患者未完成的任务
     * @param patientIds
     * @return
     */
    public int deleteUserTask(Long patientIds);

    /**
     * 删除患者信息
     * 
     * @param patientId 患者ID
     * @return 结果
     */
    public int deletePatientUserById(Long patientId);

    public int updateDelFlag(PatientUser patientUser);
}
