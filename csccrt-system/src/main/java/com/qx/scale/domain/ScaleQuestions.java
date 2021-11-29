package com.qx.scale.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 量表题目及分数对象 scale_questions
 * 
 * @author patient
 * @date 2021-09-30
 */
public class ScaleQuestions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 选项 */
    @Excel(name = "选项")
    private String options;

    /** 答案 */
    @Excel(name = "答案")
    private String answers;

    /** 问题解析 */
    @Excel(name = "问题解析")
    private String resolution;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setOptions(String options) 
    {
        this.options = options;
    }

    public String getOptions() 
    {
        return options;
    }
    public void setAnswers(String answers) 
    {
        this.answers = answers;
    }

    public String getAnswers() 
    {
        return answers;
    }
    public void setResolution(String resolution) 
    {
        this.resolution = resolution;
    }

    public String getResolution() 
    {
        return resolution;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scaleId", getScaleId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("options", getOptions())
            .append("answers", getAnswers())
            .append("resolution", getResolution())
            .append("remarks", getRemarks())
            .toString();
    }
}
