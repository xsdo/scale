package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 统计3对象 statistics_table3
 * 
 * @author patient
 * @date 2020-09-02
 */
public class StatisticsTable3 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计id */
    private Long id;

    /** 年限 */
    @Excel(name = "年限")
    private Long year;

    /** 焦虑症人数 */
    @Excel(name = "焦虑症人数")
    private Long jlzCount=0L;

    /** 抑郁症人数 */
    @Excel(name = "抑郁症人数")
    private Long yyzCount=0L;

    /** 强迫症人数 */
    @Excel(name = "强迫症人数")
    private Long qpzCount=0L;

    /** 精神分裂症rens */
    @Excel(name = "精神分裂症rens")
    private Long jsflzCount=0L;

    /** 失眠障碍 */
    @Excel(name = "失眠障碍")
    private Long smzaCount=0L;

    /** 双向情感障碍 */
    @Excel(name = "双向情感障碍")
    private Long sxqgzaCount=0L;

    /** 其他人数 */
    @Excel(name = "其他人数")
    private Long qtCount=0L;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 是否删除 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setYear(Long year) 
    {
        this.year = year;
    }

    public Long getYear() 
    {
        return year;
    }
    public void setJlzCount(Long jlzCount) 
    {
        this.jlzCount = jlzCount;
    }

    public Long getJlzCount() 
    {
        return jlzCount;
    }
    public void setYyzCount(Long yyzCount) 
    {
        this.yyzCount = yyzCount;
    }

    public Long getYyzCount() 
    {
        return yyzCount;
    }
    public void setQpzCount(Long qpzCount) 
    {
        this.qpzCount = qpzCount;
    }

    public Long getQpzCount() 
    {
        return qpzCount;
    }
    public void setJsflzCount(Long jsflzCount) 
    {
        this.jsflzCount = jsflzCount;
    }

    public Long getJsflzCount() 
    {
        return jsflzCount;
    }
    public void setSmzaCount(Long smzaCount) 
    {
        this.smzaCount = smzaCount;
    }

    public Long getSmzaCount() 
    {
        return smzaCount;
    }
    public void setSxqgzaCount(Long sxqgzaCount) 
    {
        this.sxqgzaCount = sxqgzaCount;
    }

    public Long getSxqgzaCount() 
    {
        return sxqgzaCount;
    }
    public void setQtCount(Long qtCount) 
    {
        this.qtCount = qtCount;
    }

    public Long getQtCount() 
    {
        return qtCount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("year", getYear())
            .append("jlzCount", getJlzCount())
            .append("yyzCount", getYyzCount())
            .append("qpzCount", getQpzCount())
            .append("jsflzCount", getJsflzCount())
            .append("smzaCount", getSmzaCount())
            .append("sxqgzaCount", getSxqgzaCount())
            .append("qtCount", getQtCount())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
