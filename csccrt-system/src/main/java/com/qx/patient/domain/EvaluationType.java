package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 测评类型对象 evaluation_type
 * 
 * @author patient
 * @date 2020-07-09
 */
public class EvaluationType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评类型ID */
    private Long typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 父类型ID */
    @Excel(name = "父类型ID")
    private Long parentId;

    /** 子分类 */
    private List<EvaluationType> children = new ArrayList<EvaluationType>();

    public List<EvaluationType> getChildren() {
        return children;
    }

    public void setChildren(List<EvaluationType> children) {
        this.children = children;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
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
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("parentId", getParentId())
            .toString();
    }
}
