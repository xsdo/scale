package com.qx.ipa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 身心调节-量-所有题目对象 ipa_scale_questions
 * 
 * @author meng
 * @date 2021-07-05
 */
public class IpaScaleQuestions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String content;

    /** 选项a */
    @Excel(name = "选项a")
    private String answerA;

    /** 选项b */
    @Excel(name = "选项b")
    private String answerB;

    /** 选项c */
    @Excel(name = "选项c")
    private String answerC;

    /** 选项d */
    @Excel(name = "选项d")
    private String answerD;

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
    public void setAnswerA(String answerA) 
    {
        this.answerA = answerA;
    }

    public String getAnswerA() 
    {
        return answerA;
    }
    public void setAnswerB(String answerB) 
    {
        this.answerB = answerB;
    }

    public String getAnswerB() 
    {
        return answerB;
    }
    public void setAnswerC(String answerC) 
    {
        this.answerC = answerC;
    }

    public String getAnswerC() 
    {
        return answerC;
    }
    public void setAnswerD(String answerD) 
    {
        this.answerD = answerD;
    }

    public String getAnswerD() 
    {
        return answerD;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scaleId", getScaleId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("answerA", getAnswerA())
            .append("answerB", getAnswerB())
            .append("answerC", getAnswerC())
            .append("answerD", getAnswerD())
            .toString();
    }
}
