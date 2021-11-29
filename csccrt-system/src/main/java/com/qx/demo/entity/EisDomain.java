package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

public class EisDomain {
     //情绪知觉
    private int qxzj=0;
     //自我情绪管理
    private int zwqx=0;
    //他人情绪管理
    private int trqx=0;
    //情绪表达
    private int qxbd=0;

    private int sum;

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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public EisDomain() {
    }

    public int getQxzj() {
        return qxzj;
    }

    public void setQxzj(int qxzj) {
        this.qxzj = qxzj;
    }

    public int getZwqx() {
        return zwqx;
    }

    public void setZwqx(int zwqx) {
        this.zwqx = zwqx;
    }

    public int getTrqx() {
        return trqx;
    }

    public void setTrqx(int trqx) {
        this.trqx = trqx;
    }

    public int getQxbd() {
        return qxbd;
    }

    public void setQxbd(int qxbd) {
        this.qxbd = qxbd;
    }
}
