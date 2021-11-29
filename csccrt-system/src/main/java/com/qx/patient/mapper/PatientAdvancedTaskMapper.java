package com.qx.patient.mapper;

import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会认知任务Mapper接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface PatientAdvancedTaskMapper 
{
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    public PatientAdvancedTask selectPatientAdvancedTaskById(Long taskId);

    /**
     * 查询社会认知任务列表
     * 
     * @param patientAdvancedTask 社会认知任务
     * @return 社会认知任务集合
     */
    public List<PatientAdvancedTask> selectPatientAdvancedTaskList(PatientAdvancedTask patientAdvancedTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientAdvancedTask 社会认知任务
     * @return 结果
     */
    public int insertPatientAdvancedTask(PatientAdvancedTask patientAdvancedTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientAdvancedTask 社会认知任务
     * @return 结果
     */
    public int updatePatientAdvancedTask(PatientAdvancedTask patientAdvancedTask);

    /**
     * 删除社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientAdvancedTaskById(Long taskId);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientAdvancedTaskByIds(Long[] taskIds);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientAdvancedTask selectPatientTaskByPatientId(@Param("patientId")Long patientId, @Param("delFlag") String delFlag);

    /**
     * 根据PatientID修改任务
     * @param patientAdvancedTask
     * @return
     */
    public int updateByPatientId(PatientAdvancedTask patientAdvancedTask);

    public List<PatientAdvancedTask> selectAllByPatientId(@Param("patientId")Long patientId);
    public PatientAdvancedTask selectTaskByWorkStation(String workStation);
}
