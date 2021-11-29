package com.qx.patient.mapper;

import com.qx.patient.domain.PatientSocietyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会认知任务Mapper接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface PatientSocietyTaskMapper 
{
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    public PatientSocietyTask selectPatientSocietyTaskById(Long taskId);

    /**
     * 查询社会认知任务列表
     * 
     * @param patientSocietyTask 社会认知任务
     * @return 社会认知任务集合
     */
    public List<PatientSocietyTask> selectPatientSocietyTaskList(PatientSocietyTask patientSocietyTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientSocietyTask 社会认知任务
     * @return 结果
     */
    public int insertPatientSocietyTask(PatientSocietyTask patientSocietyTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientSocietyTask 社会认知任务
     * @return 结果
     */
    public int updatePatientSocietyTask(PatientSocietyTask patientSocietyTask);

    /**
     * 删除社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientSocietyTaskById(Long taskId);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientSocietyTaskByIds(Long[] taskIds);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientSocietyTask selectPatientTaskByPatientId(@Param("patientId")Long patientId, @Param("delFlag") String delFlag);

    /**
     * 根据PatientID修改任务
     * @param patientSocietyTask
     * @return
     */
    public int updateByPatientId(PatientSocietyTask patientSocietyTask);

    public List<PatientSocietyTask> selectAllByPatientId(@Param("patientId")Long patientId);

    public PatientSocietyTask selectTaskByWorkStation(String workStation);
}
