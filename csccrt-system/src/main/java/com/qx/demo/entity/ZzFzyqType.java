package com.qx.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.Set;

/**
 * 复杂眼区类型对象 zz_fzyq_type
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzFzyqType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 等级（测试,基础,进阶） */
    @Excel(name = "等级", readConverterExp = "测=试,基础,进阶")
    private String grade;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String picUrl;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String picUrla;

    /** 视频路径 */
    @Excel(name = "视频路径")
    private String videoUrl;

    /** 图片类型 */
    @Excel(name = "图片类型")
    private String picType;

    private Set<String> set;

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
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
    public void setPicUrl(String picUrl) 
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl() 
    {
        return picUrl;
    }
    public void setPicUrla(String picUrla) 
    {
        this.picUrla = picUrla;
    }

    public String getPicUrla() 
    {
        return picUrla;
    }
    public void setVideoUrl(String videoUrl) 
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() 
    {
        return videoUrl;
    }
    public void setPicType(String picType) 
    {
        this.picType = picType;
    }

    public String getPicType() 
    {
        return picType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("grade", getGrade())
            .append("picUrl", getPicUrl())
            .append("picUrla", getPicUrla())
            .append("videoUrl", getVideoUrl())
            .append("picType", getPicType())
            .toString();
    }
}
