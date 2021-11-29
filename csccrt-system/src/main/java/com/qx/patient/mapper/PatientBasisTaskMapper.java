package com.qx.patient.mapper;

import com.qx.patient.domain.PatientBasisTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会认知任务Mapper接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface PatientBasisTaskMapper 
{
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    public PatientBasisTask selectPatientBasisTaskById(Long taskId);

    /**
     * 查询社会认知任务列表
     * 
     * @param patientBasisTask 社会认知任务
     * @return 社会认知任务集合
     */
    public List<PatientBasisTask> selectPatientBasisTaskList(PatientBasisTask patientBasisTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientBasisTask 社会认知任务
     * @return 结果
     */
    public int insertPatientBasisTask(PatientBasisTask patientBasisTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientBasisTask 社会认知任务
     * @return 结果
     */
    public int updatePatientBasisTask(PatientBasisTask patientBasisTask);

    /**
     * 删除社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientBasisTaskById(Long taskId);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientBasisTaskByIds(Long[] taskIds);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientBasisTask selectPatientTaskByPatientId(@Param("patientId")Long patientId, @Param("delFlag") String delFlag);

    /**
     * 根据PatientID修改任务
     * @param patientBasisTask
     * @return
     */
    public int updateByPatientId(PatientBasisTask patientBasisTask);

    public List<PatientBasisTask> selectAllByPatientId(@Param("patientId")Long patientId);

    public PatientBasisTask selectTaskByWorkStation(String workStation);
}
