package com.qx.patient.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 籍贯对象 sys_hometown
 * 
 * @author qx
 * @date 2020-08-10
 */
public class SysHometown extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 籍贯(省) */
    @Excel(name = "籍贯(省)")
    private String hometown;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHometown(String hometown) 
    {
        this.hometown = hometown;
    }

    public String getHometown() 
    {
        return hometown;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hometown", getHometown())
            .toString();
    }
}
