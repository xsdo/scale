package com.qx.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 失言识别类型对象 zz_sysb_type
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzSysbType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

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

    /** $column.columnComment */
    @Excel(name = "选项d")
    private String optionCorrect;

    /** 父id */
    @Excel(name = "父id")
    private Long parentId;
    private List<ZzSysbType> list;

    public List<ZzSysbType> getList() {
        return list;
    }

    public void setList(List<ZzSysbType> list) {
        this.list = list;
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
    public void setOptionCorrect(String optionCorrect) 
    {
        this.optionCorrect = optionCorrect;
    }

    public String getOptionCorrect() 
    {
        return optionCorrect;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("optionC", getOptionC())
            .append("optionD", getOptionD())
            .append("optionCorrect", getOptionCorrect())
            .append("parentId", getParentId())
            .toString();
    }
}
