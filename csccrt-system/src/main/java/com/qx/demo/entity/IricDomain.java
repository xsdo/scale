package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

public class IricDomain {
    //观点采择
    private int gdcz=0;
    //想象
    private int xx=0;
    //共情关心
    private int gqgx=0;
    //个人痛苦
    private int grtk=0;
    //总分
    private int sum=0;

    private String createTime;
    private PatientUser patientUser;

    public PatientUser getPatientUser() {
        return patientUser;
    }

    public void setPatientUser(PatientUser patientUser) {
        this.patientUser = patientUser;
    }
    public IricDomain() {
    }

    public int getGdcz() {
        return gdcz;
    }

    public void setGdcz(int gdcz) {
        this.gdcz = gdcz;
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getGqgx() {
        return gqgx;
    }

    public void setGqgx(int gqgx) {
        this.gqgx = gqgx;
    }

    public int getGrtk() {
        return grtk;
    }

    public void setGrtk(int grtk) {
        this.grtk = grtk;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
