package com.qx.scale.service;

import com.qx.patient.domain.PatientUser;
import com.qx.scale.domain.ScalePatient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 量表系统患者Service接口
 * 
 * @author patient
 * @date 2021-10-11
 */
public interface IScalePatientService 
{
    /**
     * 查询量表系统患者
     * 
     * @param patientId 量表系统患者ID
     * @return 量表系统患者
     */
    public ScalePatient selectScalePatientById(Long patientId);

    /**
     * 查询量表系统患者列表
     * 
     * @param scalePatient 量表系统患者
     * @return 量表系统患者集合
     */
    public List<ScalePatient> selectScalePatientList(ScalePatient scalePatient);

    /**
     * 新增量表系统患者
     * 
     * @param scalePatient 量表系统患者
     * @return 结果
     */
    public int insertScalePatient(ScalePatient scalePatient);

    /**
     * 修改量表系统患者
     * 
     * @param scalePatient 量表系统患者
     * @return 结果
     */
    public int updateScalePatient(ScalePatient scalePatient);

    /**
     * 批量删除量表系统患者
     * 
     * @param patientIds 需要删除的量表系统患者ID
     * @return 结果
     */
    public int deleteScalePatientByIds(Long[] patientIds);

    int delAllTask(Long patientId);

    /**
     * 删除量表系统患者信息
     * 
     * @param patientId 量表系统患者ID
     * @return 结果
     */
    public int deleteScalePatientById(Long patientId);


}
