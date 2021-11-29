package com.qx.scale.domain;

import com.qx.scale.domain.vo.ScaleScoreVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 量表得分保存对象 scale_task_score
 * 
 * @author patient
 * @date 2021-10-11
 */
public class ScaleTaskScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 量表名字 */
    @Excel(name = "量表名字")
    private Long scaleId;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 病人id */
    @Excel(name = "病人id")
    private Long patientId;

    /** 得分 */
    @Excel(name = "得分")
    private Long score;

    /** 工作站 */
    @Excel(name = "工作站")
    private String workstation;

    /** 测试日期 */
    @Excel(name = "测试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDate;

    List<ScaleScoreVo>scaleScoreVos;

    public List<ScaleScoreVo> getScaleScoreVos() {
        return scaleScoreVos;
    }

    public void setScaleScoreVos(List<ScaleScoreVo> scaleScoreVos) {
        this.scaleScoreVos = scaleScoreVos;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScaleId(Long scaleId) 
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId() 
    {
        return scaleId;
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
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setWorkstation(String workstation) 
    {
        this.workstation = workstation;
    }

    public String getWorkstation() 
    {
        return workstation;
    }
    public void setTestDate(Date testDate) 
    {
        this.testDate = testDate;
    }

    public Date getTestDate() 
    {
        return testDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scaleId", getScaleId())
            .append("taskId", getTaskId())
            .append("patientId", getPatientId())
            .append("score", getScore())
            .append("workstation", getWorkstation())
            .append("testDate", getTestDate())
            .toString();
    }
}
