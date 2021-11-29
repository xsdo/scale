package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

public class FzyqDomain {
    //正确率
  private Integer correct=0;
   //平均分
  private Double average=0.0;

    private String createTime;
    private PatientUser patientUser;

    public PatientUser getPatientUser() {
        return patientUser;
    }

    public void setPatientUser(PatientUser patientUser) {
        this.patientUser = patientUser;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public FzyqDomain() {
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
