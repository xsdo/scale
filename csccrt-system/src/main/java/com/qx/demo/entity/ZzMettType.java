package com.qx.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qx.demo.entity.vo.ZzMettTypeVo;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * METT微情识别范式类型对象 zz_mett_type
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzMettType extends BaseEntity
{
    private static final long serialVersionUID = 8645954L;

    /** id */
    private Long id;

    /** 表情类型 */
    @Excel(name = "表情类型")
    private String exId;

    /** 局部表情地址 */
    @Excel(name = "局部表情地址")
    private String exPartUrl;

    /** 全身表情地址 */
    @Excel(name = "全身表情地址")
    private String exAllUrl;

    private String exPartRemark;
    private String exAllRemark;
    private  Map<Long,ZzMettTypeVo> map;

    public Map<Long, ZzMettTypeVo> getMap() {
        return map;
    }

    public void setMap(Map<Long, ZzMettTypeVo> map) {
        this.map = map;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public String getExPartUrl() {
        return exPartUrl;
    }

    public void setExPartUrl(String exPartUrl) {
        this.exPartUrl = exPartUrl;
    }

    public String getExAllUrl() {
        return exAllUrl;
    }

    public void setExAllUrl(String exAllUrl) {
        this.exAllUrl = exAllUrl;
    }

    public String getExPartRemark() {
        return exPartRemark;
    }

    public void setExPartRemark(String exPartRemark) {
        this.exPartRemark = exPartRemark;
    }

    public String getExAllRemark() {
        return exAllRemark;
    }

    public void setExAllRemark(String exAllRemark) {
        this.exAllRemark = exAllRemark;
    }


}
