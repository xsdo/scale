package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 情绪智力量日志对象 eis_log
 * 
 * @author patient
 * @date 2020-09-01
 */
public class EisLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志id */
    private Long id;

    /** 患者id */
    @Excel(name = "患者id")
    private Long patientId;

    /** 患者名称 */
    @Excel(name = "患者名称")
    private String patientName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 文化程度 */
    @Excel(name = "文化程度")
    private String education;

    /** 职业 */
    @Excel(name = "职业")
    private String job;

    /** 诊断 */
    @Excel(name = "诊断")
    private String diagnosis;

    /** 测试日期 */
    @Excel(name = "测试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDay;

    /** 情绪知觉 */
    @Excel(name = "情绪知觉")
    private Long qxzj=0L;

    /** 自我情绪管理 */
    @Excel(name = "自我情绪管理")
    private Long zwqx=0L;

    /** 他人情绪管理 */
    @Excel(name = "他人情绪管理")
    private Long trqx=0L;

    /** 情绪表达 */
    @Excel(name = "情绪表达")
    private Long qxbd=0L;

    /** 总分 */
    @Excel(name = "总分")
    private Long sum=0L;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setDiagnosis(String diagnosis) 
    {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() 
    {
        return diagnosis;
    }
    public void setTestDay(Date testDay) 
    {
        this.testDay = testDay;
    }

    public Date getTestDay() 
    {
        return testDay;
    }
    public void setQxzj(Long qxzj) 
    {
        this.qxzj = qxzj;
    }

    public Long getQxzj() 
    {
        return qxzj;
    }
    public void setZwqx(Long zwqx) 
    {
        this.zwqx = zwqx;
    }

    public Long getZwqx() 
    {
        return zwqx;
    }
    public void setTrqx(Long trqx) 
    {
        this.trqx = trqx;
    }

    public Long getTrqx() 
    {
        return trqx;
    }
    public void setQxbd(Long qxbd) 
    {
        this.qxbd = qxbd;
    }

    public Long getQxbd() 
    {
        return qxbd;
    }
    public void setSum(Long sum) 
    {
        this.sum = sum;
    }

    public Long getSum() 
    {
        return sum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("education", getEducation())
            .append("job", getJob())
            .append("diagnosis", getDiagnosis())
            .append("testDay", getTestDay())
            .append("qxzj", getQxzj())
            .append("zwqx", getZwqx())
            .append("trqx", getTrqx())
            .append("qxbd", getQxbd())
            .append("sum", getSum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
