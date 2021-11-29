package com.qx.peri.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 围手术期系统患者对象 peri_patient_user
 * 
 * @author Meng
 * @date 2021-07-13
 */
public class PeriPatientUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private Long patientId;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 住院号 */
    @Excel(name = "住院号")
    private Long hospitalNumber;

    /** 病区 */
    @Excel(name = "病区")
    private String ward;

    /** 检测日期 */
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDate;

    /** 职业 */
    @Excel(name = "职业")
    private String job;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 婚姻状况(0未婚,1已婚) */
    @Excel(name = "婚姻状况(0未婚,1已婚)")
    private String maritalStatus;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contanctInformation;

    /** 患者来源 */
    @Excel(name = "患者来源")
    private String source;

    /** 分类编码 */
    @Excel(name = "分类编码")
    private String classificationCoding;

    /** 诊断 */
    @Excel(name = "诊断")
    private String diagnosis;

    /** 是否删除(0存在，1删除) */
    private String delFlag;

    /** 区分模块字段 **/
    private String flag;

    /** 查询任务状态字段 **/
    private String taskStatus;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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
    public void setHospitalNumber(Long hospitalNumber) 
    {
        this.hospitalNumber = hospitalNumber;
    }

    public Long getHospitalNumber() 
    {
        return hospitalNumber;
    }
    public void setWard(String ward) 
    {
        this.ward = ward;
    }

    public String getWard() 
    {
        return ward;
    }
    public void setTestDate(Date testDate) 
    {
        this.testDate = testDate;
    }

    public Date getTestDate() 
    {
        return testDate;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setContanctInformation(String contanctInformation) 
    {
        this.contanctInformation = contanctInformation;
    }

    public String getContanctInformation() 
    {
        return contanctInformation;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setClassificationCoding(String classificationCoding) 
    {
        this.classificationCoding = classificationCoding;
    }

    public String getClassificationCoding() 
    {
        return classificationCoding;
    }
    public void setDiagnosis(String diagnosis) 
    {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() 
    {
        return diagnosis;
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
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("hospitalNumber", getHospitalNumber())
            .append("ward", getWard())
            .append("testDate", getTestDate())
            .append("job", getJob())
            .append("education", getEducation())
            .append("nation", getNation())
            .append("maritalStatus", getMaritalStatus())
            .append("birthday", getBirthday())
            .append("contanctInformation", getContanctInformation())
            .append("source", getSource())
            .append("classificationCoding", getClassificationCoding())
            .append("diagnosis", getDiagnosis())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
