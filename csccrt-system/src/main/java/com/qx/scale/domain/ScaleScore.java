package com.qx.scale.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 量表分数对象 scale_score
 * 
 * @author patient
 * @date 2021-10-11
 */
public class ScaleScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 得分 */
    @Excel(name = "得分")
    private Long score;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setScaleId(Long scaleId) 
    {
        this.scaleId = scaleId;
    }

    public Long getScaleId() 
    {
        return scaleId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("scaleId", getScaleId())
            .append("title", getTitle())
            .append("score", getScore())
            .toString();
    }
}
