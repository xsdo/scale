package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 高级认知任务对象 patient_advanced_task
 *
 * @author qx
 * @date 2020-07-31
 */
public class PatientAdvancedTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评任务ID */
    private Long taskId;

    /** 测评人ID */
    @Excel(name = "测评人ID")
    private Long userId;

    /** 测评人姓名 */
    @Excel(name = "测评人姓名")
    private String userName;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long patientId;

    /** 患者名称 */
    @Excel(name = "患者名称")
    private String patientName;

    /** 工作站 */
    @Excel(name = "工作站")
    private String workstation;

    /** 测试编码 */
    @Excel(name = "测试编码")
    private String testCoding;

    /** 测评任务 */
    @Excel(name = "测评任务")
    private String typeIds;

    /** 任务状态(1未开始,2进行中,3已结束) */
    @Excel(name = "任务状态(1未开始,2进行中,3已结束)")
    private String taskStatus;

    /** 是否删除(0存在，1删除) */
    private String delFlag;

    private String typeNames;
    private String scaleId;

    public String getScaleId() {
        return scaleId;
    }

    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }
    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId()
    {
        return patientId;
    }
    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientName()
    {
        return patientName;
    }
    public void setWorkstation(String workstation)
    {
        this.workstation = workstation;
    }

    public String getWorkstation()
    {
        return workstation;
    }
    public void setTestCoding(String testCoding)
    {
        this.testCoding = testCoding;
    }

    public String getTestCoding()
    {
        return testCoding;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public void setTaskStatus(String taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus()
    {
        return taskStatus;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("patientId", getPatientId())
                .append("patientName", getPatientName())
                .append("workstation", getWorkstation())
                .append("testCoding", getTestCoding())
                .append("typeIds", getTypeIds())
                .append("taskStatus", getTaskStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
