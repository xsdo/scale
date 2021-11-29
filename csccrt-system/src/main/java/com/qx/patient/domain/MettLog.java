package com.qx.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 情绪识别日志对象 mett_log
 * 
 * @author patient
 * @date 2020-09-01
 */
public class MettLog extends BaseEntity
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

    /** 高兴正确率 */
    @Excel(name = "高兴正确率")
    private Long gxCorrect=0L;

    /** 悲伤正确率 */
    @Excel(name = "悲伤正确率")
    private Long bsCorrect=0L;

    /** 愤怒正确率 */
    @Excel(name = "愤怒正确率")
    private Long fnCorrect=0L;

    /** 厌恶正确率 */
    @Excel(name = "厌恶正确率")
    private Long ywCorrect=0L;

    /** 惊讶正确率 */
    @Excel(name = "惊讶正确率")
    private Long jyCorrect=0L;

    /** 恐惧正确率 */
    @Excel(name = "恐惧正确率")
    private Long kjCorrect=0L;

    /** 中性正确率 */
    @Excel(name = "中性正确率")
    private Long zxCorrect=0L;

    /** 高兴反应时 */
    @Excel(name = "高兴反应时")
    private Double gxReply=0.0;

    /** 悲伤反应时 */
    @Excel(name = "悲伤反应时")
    private Double bsReply=0.0;

    /** 愤怒反应时 */
    @Excel(name = "愤怒反应时")
    private Double fnReply=0.0;

    /** 厌恶反应时 */
    @Excel(name = "厌恶反应时")
    private Double ywReply=0.0;

    /** 惊讶反应时 */
    @Excel(name = "惊讶反应时")
    private Double jyReply=0.0;

    /** 恐惧反应时 */
    @Excel(name = "恐惧反应时")
    private Double kjReply=0.0;

    /** 中性反应时 */
    @Excel(name = "中性反应时")
    private Double zxReply=0.0;

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
    public void setGxCorrect(Long gxCorrect) 
    {
        this.gxCorrect = gxCorrect;
    }

    public Long getGxCorrect() 
    {
        return gxCorrect;
    }
    public void setBsCorrect(Long bsCorrect) 
    {
        this.bsCorrect = bsCorrect;
    }

    public Long getBsCorrect() 
    {
        return bsCorrect;
    }
    public void setFnCorrect(Long fnCorrect) 
    {
        this.fnCorrect = fnCorrect;
    }

    public Long getFnCorrect() 
    {
        return fnCorrect;
    }
    public void setYwCorrect(Long ywCorrect) 
    {
        this.ywCorrect = ywCorrect;
    }

    public Long getYwCorrect() 
    {
        return ywCorrect;
    }
    public void setJyCorrect(Long jyCorrect) 
    {
        this.jyCorrect = jyCorrect;
    }

    public Long getJyCorrect() 
    {
        return jyCorrect;
    }
    public void setKjCorrect(Long kjCorrect) 
    {
        this.kjCorrect = kjCorrect;
    }

    public Long getKjCorrect() 
    {
        return kjCorrect;
    }
    public void setZxCorrect(Long zxCorrect) 
    {
        this.zxCorrect = zxCorrect;
    }

    public Long getZxCorrect() 
    {
        return zxCorrect;
    }

    public Double getGxReply() {
        return gxReply;
    }

    public void setGxReply(Double gxReply) {
        this.gxReply = gxReply;
    }

    public Double getBsReply() {
        return bsReply;
    }

    public void setBsReply(Double bsReply) {
        this.bsReply = bsReply;
    }

    public Double getFnReply() {
        return fnReply;
    }

    public void setFnReply(Double fnReply) {
        this.fnReply = fnReply;
    }

    public Double getYwReply() {
        return ywReply;
    }

    public void setYwReply(Double ywReply) {
        this.ywReply = ywReply;
    }

    public Double getJyReply() {
        return jyReply;
    }

    public void setJyReply(Double jyReply) {
        this.jyReply = jyReply;
    }

    public Double getKjReply() {
        return kjReply;
    }

    public void setKjReply(Double kjReply) {
        this.kjReply = kjReply;
    }

    public Double getZxReply() {
        return zxReply;
    }

    public void setZxReply(Double zxReply) {
        this.zxReply = zxReply;
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
            .append("gxCorrect", getGxCorrect())
            .append("bsCorrect", getBsCorrect())
            .append("fnCorrect", getFnCorrect())
            .append("ywCorrect", getYwCorrect())
            .append("jyCorrect", getJyCorrect())
            .append("kjCorrect", getKjCorrect())
            .append("zxCorrect", getZxCorrect())
            .append("gxReply", getGxReply())
            .append("bsReply", getBsReply())
            .append("fnReply", getFnReply())
            .append("ywReply", getYwReply())
            .append("jyReply", getJyReply())
            .append("kjReply", getKjReply())
            .append("zxReply", getZxReply())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
