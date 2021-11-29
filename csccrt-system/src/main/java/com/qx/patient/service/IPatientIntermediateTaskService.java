package com.qx.patient.service;

import com.github.pagehelper.PageInfo;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.vo.PatientUserTask;

import java.util.List;

/**
 * 社会认知任务Service接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface IPatientIntermediateTaskService 
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
    public PageInfo<PatientUserTask> selectPatientIntermediateTaskList(PatientIntermediateTask patientIntermediateTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int insertPatientIntermediateTask(PatientUserTask patientUserTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int updatePatientIntermediateTask(PatientUserTask patientUserTask);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    public int deletePatientIntermediateTaskByIds(Long[] taskIds);

    /**
     * 删除社会认知任务信息
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientIntermediateTaskById(Long taskId);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientUserTask selectTaskByPatientId(Long patientId);

    /**
     * 根据PatientID修改任务
     * @param patientIntermediateTask
     * @return
     */
    public int updateByPatientId(PatientIntermediateTask patientIntermediateTask);

    /**
     * 根据PatientID修改任务
     * @param webpackVersion
     * @return
     */
    public Boolean getWebpackVersion(String webpackVersion,String patientId);
}
