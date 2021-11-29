package com.qx.peri.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 围手术期任务对象 peri_task
 * 
 * @author Meng
 * @date 2021-07-13
 */
public class PeriTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
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
    private String typeids;

    /** 任务状态(1未开始,2进行中,3已结束) */
    @Excel(name = "任务状态(1未开始,2进行中,3已结束)")
    private String taskStatus;

    /** 是否删除(0存在，1删除) */
    private String delFlag;

    /** 已完成量表 */
    @Excel(name = "已完成量表")
    private String scaleId;

    /** 任务所属系统（0新冠 1量表） */
    @Excel(name = "任务所属系统", readConverterExp = "0=新冠,1=量表")
    private String typeFlag;

    /** 查询任务名称字段 **/
    private String typeNames;

    /** 测评任务天数 */
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
    public void setTypeids(String typeids) 
    {
        this.typeids = typeids;
    }

    public String getTypeids() 
    {
        return typeids;
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
    public void setScaleId(String scaleId) 
    {
        this.scaleId = scaleId;
    }

    public String getScaleId() 
    {
        return scaleId;
    }
    public void setTypeFlag(String typeFlag) 
    {
        this.typeFlag = typeFlag;
    }

    public String getTypeFlag() 
    {
        return typeFlag;
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
            .append("typeids", getTypeids())
            .append("taskStatus", getTaskStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("scaleId", getScaleId())
            .append("typeFlag", getTypeFlag())
            .toString();
    }
}
