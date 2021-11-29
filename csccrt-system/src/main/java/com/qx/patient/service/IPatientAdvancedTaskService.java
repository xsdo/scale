package com.qx.patient.service;

import com.github.pagehelper.PageInfo;
import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.vo.PatientUserTask;

import java.util.List;

/**
 * 社会认知任务Service接口
 * 
 * @author patient
 * @date 2020-07-14
 */
public interface IPatientAdvancedTaskService 
{
    
    public Long total = null;
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
    public PageInfo<PatientUserTask> selectPatientAdvancedTaskList(PatientAdvancedTask patientAdvancedTask);

    /**
     * 新增社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int insertPatientAdvancedTask(PatientUserTask patientUserTask);

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    public int updatePatientAdvancedTask(PatientUserTask patientUserTask);

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    public int deletePatientAdvancedTaskByIds(Long[] taskIds);

    /**
     * 删除社会认知任务信息
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    public int deletePatientAdvancedTaskById(Long taskId);

    /**
     * 根据患者ID查询任务
     * @param patientId
     * @return
     */
    public PatientUserTask selectTaskByPatientId(Long patientId);

    /**
     * 根据patientid修改任务
     * @param patientAdvancedTask
     * @return
     */
    public int updateByPatientId(PatientAdvancedTask patientAdvancedTask);

    /**
     * 根据PatientID修改任务
     * @param webpackVersion
     * @return
     */
    public Boolean getWebpackVersion(String webpackVersion,String patientId);
}
