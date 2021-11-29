package com.qx.demo.entity.vo;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 结果量表
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzMettTypeVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private String exPartUrl;
     private String exId;

    public String getExPartUrl() {
        return exPartUrl;
    }

    public void setExPartUrl(String exPartUrl) {
        this.exPartUrl = exPartUrl;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }
}
