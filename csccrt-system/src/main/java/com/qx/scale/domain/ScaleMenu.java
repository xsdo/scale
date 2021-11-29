package com.qx.scale.domain;

import com.qx.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 量表目录对象 scale_menu
 * 
 * @author patient
 * @date 2021-09-27
 */
public class ScaleMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id */
    private Long id;

    /** 表名称 */
    @Excel(name = "表名称")
    private String scaleName;

    /** 5自评6他评 */
    @Excel(name = "5自评6他评")
    private String grade;

    /** 父id */
    @Excel(name = "父id")
    private Long pid;

    /** 适用范围 */
    @Excel(name = "适用范围")
    private String applyScope;

    private Long commonly;

    private String instruction;
    /** 是否删除0存在1删除 */
    private String delFlag;

    /** 父名称 */
    private String pName;
    /** 子 */
    private List<ScaleMenu> children = new ArrayList<ScaleMenu>();


    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Long getCommonly() {
        return commonly;
    }

    public void setCommonly(Long commonly) {
        this.commonly = commonly;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public List<ScaleMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ScaleMenu> children) {
        this.children = children;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScaleName(String scaleName) 
    {
        this.scaleName = scaleName;
    }

    public String getScaleName() 
    {
        return scaleName;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setApplyScope(String applyScope) 
    {
        this.applyScope = applyScope;
    }

    public String getApplyScope() 
    {
        return applyScope;
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
            .append("id", getId())
            .append("scaleName", getScaleName())
            .append("grade", getGrade())
            .append("pid", getPid())
            .append("applyScope", getApplyScope())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
