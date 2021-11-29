package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 统计1对象 statistics_table1
 * 
 * @author patient
 * @date 2020-09-02
 */
public class StatisticsTable1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计id */
    private Long id;

    /** 年限 */
    @Excel(name = "年限")
    private Long year;

    /** 注册人数 */
    @Excel(name = "注册人数")
    private Long registeredCount=0L;

    /** 治疗完成人数 */
    @Excel(name = "治疗完成人数")
    private Long completeCount=0L;

    /** 男人数量 */
    @Excel(name = "男人数量")
    private Long manCount=0L;

    /** 女人数量 */
    @Excel(name = "女人数量")
    private Long womanCount=0L;

    /** 小于10 */
    @Excel(name = "小于10")
    private Long less18Count=0L;

    /** 18到25之间 */
    @Excel(name = "18到25之间")
    private Long to1825Count=0L;

    /** 26到35之间 */
    @Excel(name = "26到35之间")
    private Long to2635Count=0L;

    /** 36到45之间 */
    @Excel(name = "36到45之间")
    private Long to3645Count=0L;

    /** 46到55之间 */
    @Excel(name = "46到55之间")
    private Long to4655Count=0L;

    /** 56到65之间 */
    @Excel(name = "56到65之间")
    private Long to5665Count=0L;

    /** 大于65 */
    @Excel(name = "大于65")
    private Long more65Count=0L;

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
    public void setRegisteredCount(Long registeredCount) 
    {
        this.registeredCount = registeredCount;
    }

    public Long getRegisteredCount() 
    {
        return registeredCount;
    }
    public void setCompleteCount(Long completeCount) 
    {
        this.completeCount = completeCount;
    }

    public Long getCompleteCount() 
    {
        return completeCount;
    }
    public void setManCount(Long manCount) 
    {
        this.manCount = manCount;
    }

    public Long getManCount() 
    {
        return manCount;
    }
    public void setWomanCount(Long womanCount) 
    {
        this.womanCount = womanCount;
    }

    public Long getWomanCount() 
    {
        return womanCount;
    }
    public void setLess18Count(Long less18Count) 
    {
        this.less18Count = less18Count;
    }

    public Long getLess18Count() 
    {
        return less18Count;
    }
    public void setTo1825Count(Long to1825Count) 
    {
        this.to1825Count = to1825Count;
    }

    public Long getTo1825Count() 
    {
        return to1825Count;
    }
    public void setTo2635Count(Long to2635Count) 
    {
        this.to2635Count = to2635Count;
    }

    public Long getTo2635Count() 
    {
        return to2635Count;
    }
    public void setTo3645Count(Long to3645Count) 
    {
        this.to3645Count = to3645Count;
    }

    public Long getTo3645Count() 
    {
        return to3645Count;
    }
    public void setTo4655Count(Long to4655Count) 
    {
        this.to4655Count = to4655Count;
    }

    public Long getTo4655Count() 
    {
        return to4655Count;
    }
    public void setTo5665Count(Long to5665Count) 
    {
        this.to5665Count = to5665Count;
    }

    public Long getTo5665Count() 
    {
        return to5665Count;
    }
    public void setMore65Count(Long more65Count) 
    {
        this.more65Count = more65Count;
    }

    public Long getMore65Count() 
    {
        return more65Count;
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
            .append("registeredCount", getRegisteredCount())
            .append("completeCount", getCompleteCount())
            .append("manCount", getManCount())
            .append("womanCount", getWomanCount())
            .append("less18Count", getLess18Count())
            .append("to1825Count", getTo1825Count())
            .append("to2635Count", getTo2635Count())
            .append("to3645Count", getTo3645Count())
            .append("to4655Count", getTo4655Count())
            .append("to5665Count", getTo5665Count())
            .append("more65Count", getMore65Count())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
