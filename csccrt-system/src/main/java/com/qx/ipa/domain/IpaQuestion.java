package com.qx.ipa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * ipa问题对象 ipa_question
 * 
 * @author Meng
 * @date 2021-07-06
 */
public class IpaQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID列 */
    private Long queId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 对应的第几天 */
    @Excel(name = "对应的第几天")
    private Long day;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String question;

    /** 选项a */
    @Excel(name = "选项a")
    private String optionA;

    /** 选项b */
    @Excel(name = "选项b")
    private String optionB;

    /** 问题解析 */
    @Excel(name = "问题解析")
    private String resolution;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String answer;

    public void setQueId(Long queId) 
    {
        this.queId = queId;
    }

    public Long getQueId() 
    {
        return queId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDay(Long day) 
    {
        this.day = day;
    }

    public Long getDay() 
    {
        return day;
    }
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public String getQuestion() 
    {
        return question;
    }
    public void setOptionA(String optionA) 
    {
        this.optionA = optionA;
    }

    public String getOptionA() 
    {
        return optionA;
    }
    public void setOptionB(String optionB) 
    {
        this.optionB = optionB;
    }

    public String getOptionB() 
    {
        return optionB;
    }
    public void setResolution(String resolution) 
    {
        this.resolution = resolution;
    }

    public String getResolution() 
    {
        return resolution;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("queId", getQueId())
            .append("title", getTitle())
            .append("day", getDay())
            .append("question", getQuestion())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("resolution", getResolution())
            .append("answer", getAnswer())
            .toString();
    }
}
