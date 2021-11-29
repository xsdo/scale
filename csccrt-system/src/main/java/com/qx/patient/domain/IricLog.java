package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 人际反应指针日志对象 iric_log
 * 
 * @author patient
 * @date 2020-09-01
 */
public class IricLog extends BaseEntity
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

    /** 观点采择 */
    @Excel(name = "观点采择")
    private Long gdcz=0L;

    /** 想象 */
    @Excel(name = "想象")
    private Long xx=0L;

    /** 共情关心 */
    @Excel(name = "共情关心")
    private Long gqgx=0L;

    /** 个人痛苦 */
    @Excel(name = "个人痛苦")
    private Long grtk=0L;

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
    public void setGdcz(Long gdcz) 
    {
        this.gdcz = gdcz;
    }

    public Long getGdcz() 
    {
        return gdcz;
    }
    public void setXx(Long xx) 
    {
        this.xx = xx;
    }

    public Long getXx() 
    {
        return xx;
    }
    public void setGqgx(Long gqgx) 
    {
        this.gqgx = gqgx;
    }

    public Long getGqgx() 
    {
        return gqgx;
    }
    public void setGrtk(Long grtk) 
    {
        this.grtk = grtk;
    }

    public Long getGrtk() 
    {
        return grtk;
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
            .append("gdcz", getGdcz())
            .append("xx", getXx())
            .append("gqgx", getGqgx())
            .append("grtk", getGrtk())
            .append("sum", getSum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
