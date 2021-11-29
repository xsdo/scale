package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

public class DldDomain {
  //情感辨别
   private int qgbb=0;
   //情感描述
   private int qgms=0;
   //外向性
   private int wxx=0;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public DldDomain() {
    }

    public int getQgbb() {
        return qgbb;
    }

    public void setQgbb(int qgbb) {
        this.qgbb = qgbb;
    }

    public int getQgms() {
        return qgms;
    }

    public void setQgms(int qgms) {
        this.qgms = qgms;
    }

    public int getWxx() {
        return wxx;
    }

    public void setWxx(int wxx) {
        this.wxx = wxx;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
