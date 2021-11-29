package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 多伦多量日志对象 dld_log
 * 
 * @author patient
 * @date 2020-09-01
 */
public class DldLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 多伦多日志id */
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

    /** 情感辨别 */
    @Excel(name = "情感辨别")
    private Long qgbb=0L;

    /** 情感描述 */
    @Excel(name = "情感描述")
    private Long qgms=0L;

    /** $column.columnComment */
    @Excel(name = "情感描述")
    private Long wxx=0L;

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
    public void setQgbb(Long qgbb) 
    {
        this.qgbb = qgbb;
    }

    public Long getQgbb() 
    {
        return qgbb;
    }
    public void setQgms(Long qgms) 
    {
        this.qgms = qgms;
    }

    public Long getQgms() 
    {
        return qgms;
    }
    public void setWxx(Long wxx) 
    {
        this.wxx = wxx;
    }

    public Long getWxx() 
    {
        return wxx;
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
            .append("qgbb", getQgbb())
            .append("qgms", getQgms())
            .append("wxx", getWxx())
            .append("sum", getSum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
