package com.qx.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Set;

/**
 * 量题目对象 scale_title
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ScaleTitle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 他评量表or自评量表 */
    @Excel(name = "他评量表or自评量表")
    private String grade;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 选项a */
    @Excel(name = "选项a")
    private String optionA;

    /** 选项b */
    @Excel(name = "选项b")
    private String optionB;

    /** 选项c */
    @Excel(name = "选项c")
    private String optionC;

    /** 选项d */
    @Excel(name = "选项d")
    private String optionD;

    /** 选项e */
    @Excel(name = "选项e")
    private String optionE;

    /** 选项f */
    @Excel(name = "选项f")
    private String optionF;

    /** 选项g */
    @Excel(name = "选项g")
    private String optionG;
    List<String> stringList;
    Set<ZzMettType> set;

    public Set<ZzMettType> getSet() {
        return set;
    }

    public void setSet(Set<ZzMettType> set) {
        this.set = set;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
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
    public void setOptionC(String optionC) 
    {
        this.optionC = optionC;
    }

    public String getOptionC() 
    {
        return optionC;
    }
    public void setOptionD(String optionD) 
    {
        this.optionD = optionD;
    }

    public String getOptionD() 
    {
        return optionD;
    }
    public void setOptionE(String optionE) 
    {
        this.optionE = optionE;
    }

    public String getOptionE() 
    {
        return optionE;
    }
    public void setOptionF(String optionF) 
    {
        this.optionF = optionF;
    }

    public String getOptionF() 
    {
        return optionF;
    }
    public void setOptionG(String optionG) 
    {
        this.optionG = optionG;
    }

    public String getOptionG() 
    {
        return optionG;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("grade", getGrade())
            .append("scaleId", getScaleId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("optionC", getOptionC())
            .append("optionD", getOptionD())
            .append("optionE", getOptionE())
            .append("optionF", getOptionF())
            .append("optionG", getOptionG())
            .toString();
    }
}
