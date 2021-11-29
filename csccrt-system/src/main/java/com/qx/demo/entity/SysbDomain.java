package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

public class SysbDomain {
    //失言问题
    private Integer sywt=0;
    //控制问题
    private Integer kzwt=0;
    //总分
    private Integer sum=0;
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

    public SysbDomain() {
    }

    public Integer getSywt() {
        return sywt;
    }

    public void setSywt(Integer sywt) {
        this.sywt = sywt;
    }

    public Integer getKzwt() {
        return kzwt;
    }

    public void setKzwt(Integer kzwt) {
        this.kzwt = kzwt;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
