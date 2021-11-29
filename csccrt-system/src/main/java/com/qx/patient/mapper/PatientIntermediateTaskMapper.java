package com.qx.patient.mapper;

import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会认知任务Mapper接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface PatientIntermediateTaskMapper 
{
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    public PatientIntermediateTask selectPatientIntermediateTaskById(Long taskId);

    /**
     * 查询社会认知任务列表
     * 
     * @param patientIntermediateTask 社会认知任务
     * @return 社会认知任务集合
     */
    public List<PatientIntermediateTask> selectPatientIntermediateTaskList(PatientIntermediateTask patientIntermediateTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientIntermediateTask 社会认知任务
     * @return 结果
     */
    public int insertPatientIntermediateTask(PatientIntermediateTask patientIntermediateTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientIntermediateTask 社会认知任务
     * @return 结果
     */
    public int updatePatientIntermediateTask(PatientIntermediateTask patientIntermediateTask);

    /**
     * 删除社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientIntermediateTaskById(Long taskId);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientIntermediateTaskByIds(Long[] taskIds);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientIntermediateTask selectPatientTaskByPatientId(@Param("patientId")Long patientId, @Param("delFlag") String delFlag);

    /**
     * 根据PatientID修改任务
     * @param patientIntermediateTask
     * @return
     */
    public int updateByPatientId(PatientIntermediateTask patientIntermediateTask);

    public List<PatientIntermediateTask> selectAllByPatientId(@Param("patientId")Long patientId);

    public PatientIntermediateTask selectTaskByWorkStation(String workStation);
}
