package com.qx.ipa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 视频地址对象 ipa_media
 * 
 * @author Meng
 * @date 2021-07-08
 */
public class IpaMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    private String title;

    /** 位置 */
    @Excel(name = "位置")
    private String local;

    /** 对应的哪天 */
    @Excel(name = "对应的哪天")
    private String mark;

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
    public void setLocal(String local) 
    {
        this.local = local;
    }

    public String getLocal() 
    {
        return local;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("local", getLocal())
            .append("mark", getMark())
            .toString();
    }
}
