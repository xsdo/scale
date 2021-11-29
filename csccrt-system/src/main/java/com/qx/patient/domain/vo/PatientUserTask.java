package com.qx.patient.domain.vo;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PatientUserTask extends BaseEntity {
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

    private String birth;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contanctInformation;

    /** 被试来源 */
    @Excel(name = "患者来源")
    private String source;

    /** 分类编码 */
    @Excel(name = "分类编码")
    private String classificationCoding;


    /** 测评任务ID */
    private Long taskId;

    /** 测评人ID */
    @Excel(name = "测评人ID")
    private Long userId;

    /** 测评人姓名 */
    @Excel(name = "测评人姓名")
    private String userName;

    /** 工作站 */
    @Excel(name = "工作站")
    private String workstation;

    /** 测试编码 */
    @Excel(name = "测试编码")
    private String testCoding;

    /** 测评任务 */
    @Excel(name = "测评任务")
    private String typeIds;

    /** 任务状态(1未开始,2进行中,3已结束) */
    @Excel(name = "任务状态(1未开始,2进行中,3已结束)")
    private String taskStatus;

    /** 是否删除(0存在，1删除) */
    private String delFlag;
    private String diagnosis;
    private String typeNames;

    private String scaleId;

    public String getScaleId() {
        return scaleId;
    }

    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    private List<Map<String,String>> arr;

    public List<Map<String, String>> getArr() {
        return arr;
    }

    public void setArr(List<Map<String, String>> arr) {
        this.arr = arr;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(Long hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContanctInformation() {
        return contanctInformation;
    }

    public void setContanctInformation(String contanctInformation) {
        this.contanctInformation = contanctInformation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClassificationCoding() {
        return classificationCoding;
    }

    public void setClassificationCoding(String classificationCoding) {
        this.classificationCoding = classificationCoding;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getTestCoding() {
        return testCoding;
    }

    public void setTestCoding(String testCoding) {
        this.testCoding = testCoding;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }
}
