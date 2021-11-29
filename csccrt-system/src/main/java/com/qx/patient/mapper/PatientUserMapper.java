package com.qx.patient.mapper;

import com.qx.patient.domain.PatientUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 患者Mapper接口
 * 
 * @author patient
 * @date 2020-07-08
 */
public interface PatientUserMapper 
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
     * 删除患者
     * 
     * @param patientId 患者ID
     * @return 结果
     */
    public int deletePatientUserById(Long patientId);

    /**
     * 批量删除患者
     * 
     * @param patientIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientUserByIds(Long[] patientIds);

    /**
     * 查询患者列表
     *
     * @return 患者集合
     */
    public List<PatientUser> selectAllByCreateTime(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("createBy")String CreateBy);
}
