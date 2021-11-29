package com.qx.ipa.domain;

import com.qx.patient.domain.PatientUser;

/**
 * ipa量表下载报告封装对象
 */
public class IpaDomain {

    //新冠问题得分
    private int  covid=0;
    //焦虑自评量表
    private int sas=0;
    //抑郁自评量表
    private int sds=0;
    //阿森斯自评量表
    private int ais=0;
    //总分
    private int sum=0;

    private String createTime;

    private IpaPatientUser ipaPatientUser;

    public int getCovid() {
        return covid;
    }

    public void setCovid(int covid) {
        this.covid = covid;
    }

    public IpaDomain() {

    }

    public int getSas() {
        return sas;
    }

    public void setSas(int sas) {
        this.sas = sas;
    }

    public int getSds() {
        return sds;
    }

    public void setSds(int sds) {
        this.sds = sds;
    }

    public int getAis() {
        return ais;
    }

    public void setAis(int ais) {
        this.ais = ais;
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

    public IpaPatientUser getIpaPatientUser() {
        return ipaPatientUser;
    }

    public void setIpaPatientUser(IpaPatientUser ipaPatientUser) {
        this.ipaPatientUser = ipaPatientUser;
    }

    public IpaDomain(int covid, int sas, int sds, int ais, int sum, String createTime, IpaPatientUser ipaPatientUser) {
        this.covid = covid;
        this.sas = sas;
        this.sds = sds;
        this.ais = ais;
        this.sum = sum;
        this.createTime = createTime;
        this.ipaPatientUser = ipaPatientUser;
    }

    @Override
    public String toString() {
        return "IpaDomain{" +
                "covid=" + covid +
                ", sas=" + sas +
                ", sds=" + sds +
                ", ais=" + ais +
                ", sum=" + sum +
                ", createTime='" + createTime + '\'' +
                ", ipaPatientUser=" + ipaPatientUser +
                '}';
    }
}
