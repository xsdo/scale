package com.qx.patient.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分类目录对象 patient_categories
 * 
 * @author qx
 * @date 2020-08-10
 */
public class PatientCategories extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 分类编码 */
    @Excel(name = "分类编码")
    private String classificationCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClassificationCode(String classificationCode) 
    {
        this.classificationCode = classificationCode;
    }

    public String getClassificationCode() 
    {
        return classificationCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("classificationCode", getClassificationCode())
            .toString();
    }
}
