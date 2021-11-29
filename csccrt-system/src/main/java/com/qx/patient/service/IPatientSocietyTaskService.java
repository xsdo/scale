package com.qx.patient.service;

import com.github.pagehelper.PageInfo;
import com.qx.patient.domain.PatientSocietyTask;
import com.qx.patient.domain.vo.PatientUserTask;

import java.util.List;

/**
 * 社会认知任务Service接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface IPatientSocietyTaskService 
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
    public PageInfo<PatientUserTask> selectPatientSocietyTaskList(PatientSocietyTask patientSocietyTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int insertPatientSocietyTask(PatientUserTask patientUserTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int updatePatientSocietyTask(PatientUserTask patientUserTask);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    public int deletePatientSocietyTaskByIds(Long[] taskIds);

    /**
     * 删除社会认知任务信息
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientSocietyTaskById(Long taskId);

    /**
     * 查询社会认知任务
     *
     * @param patientId
     * @return 社会认知任务
     */
    public PatientUserTask selectTaskByPatientId(Long patientId);

    /**
     * 根据PatientID修改任务
     * @param patientSocietyTask
     * @return
     */
    public int updateByPatientId(PatientSocietyTask patientSocietyTask);
    /**
     * 根据PatientID修改任务
     * @param webpackVersion
     * @return
     */
    public Boolean getWebpackVersion(String webpackVersion,String patientId);


}
