package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 统计2对象 statistics_table2
 * 
 * @author patient
 * @date 2020-09-02
 */
public class StatisticsTable2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计id */
    private Long id;

    /** 年限 */
    @Excel(name = "年限")
    private Long year;

    /** 一月人数 */
    @Excel(name = "一月人数")
    private Long januaryCount;

    /** 二月人数 */
    @Excel(name = "二月人数")
    private Long februaryCount;

    /** 三月 */
    @Excel(name = "三月")
    private Long march;

    /** 四月 */
    @Excel(name = "四月")
    private Long april;

    /** 五月 */
    @Excel(name = "五月")
    private Long may;

    /** 六月 */
    @Excel(name = "六月")
    private Long june;

    /** 七月 */
    @Excel(name = "七月")
    private Long july;

    /** 八月  */
    @Excel(name = "八月 ")
    private Long august;

    /** 九月 */
    @Excel(name = "九月")
    private Long september;

    /** 十月 */
    @Excel(name = "十月")
    private Long october;

    /** 十一月 */
    @Excel(name = "十一月")
    private Long november;

    /** 十二月人数 */
    @Excel(name = "十二月人数")
    private Long december;

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
    public void setJanuaryCount(Long januaryCount) 
    {
        this.januaryCount = januaryCount;
    }

    public Long getJanuaryCount() 
    {
        return januaryCount;
    }
    public void setFebruaryCount(Long februaryCount) 
    {
        this.februaryCount = februaryCount;
    }

    public Long getFebruaryCount() 
    {
        return februaryCount;
    }
    public void setMarch(Long march) 
    {
        this.march = march;
    }

    public Long getMarch() 
    {
        return march;
    }
    public void setApril(Long april) 
    {
        this.april = april;
    }

    public Long getApril() 
    {
        return april;
    }
    public void setMay(Long may) 
    {
        this.may = may;
    }

    public Long getMay() 
    {
        return may;
    }
    public void setJune(Long june) 
    {
        this.june = june;
    }

    public Long getJune() 
    {
        return june;
    }
    public void setJuly(Long july) 
    {
        this.july = july;
    }

    public Long getJuly() 
    {
        return july;
    }
    public void setAugust(Long august) 
    {
        this.august = august;
    }

    public Long getAugust() 
    {
        return august;
    }
    public void setSeptember(Long september) 
    {
        this.september = september;
    }

    public Long getSeptember() 
    {
        return september;
    }
    public void setOctober(Long october) 
    {
        this.october = october;
    }

    public Long getOctober() 
    {
        return october;
    }
    public void setNovember(Long november) 
    {
        this.november = november;
    }

    public Long getNovember() 
    {
        return november;
    }
    public void setDecember(Long december) 
    {
        this.december = december;
    }

    public Long getDecember() 
    {
        return december;
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
            .append("januaryCount", getJanuaryCount())
            .append("februaryCount", getFebruaryCount())
            .append("march", getMarch())
            .append("april", getApril())
            .append("may", getMay())
            .append("june", getJune())
            .append("july", getJuly())
            .append("august", getAugust())
            .append("september", getSeptember())
            .append("october", getOctober())
            .append("november", getNovember())
            .append("december", getDecember())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
