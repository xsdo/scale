package com.qx.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 情绪智力量(EIS)对象 zz_eis
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzEis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 得分 */
    @Excel(name = "得分")
    private String point;

    /** 测试日期 */
    @Excel(name = "测试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDate;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 患者id */
    @Excel(name = "患者id")
    private Long patientId;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 他评或自评 */
    @Excel(name = "工作站")
    private String workstation;

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPoint(String point) 
    {
        this.point = point;
    }

    public String getPoint() 
    {
        return point;
    }
    public void setTestDate(Date testDate) 
    {
        this.testDate = testDate;
    }

    public Date getTestDate() 
    {
        return testDate;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setScaleId(Long scaleId) 
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId() 
    {
        return scaleId;
    }

}
