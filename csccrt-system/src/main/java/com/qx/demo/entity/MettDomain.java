package com.qx.demo.entity;

import com.qx.patient.domain.PatientUser;

import java.util.List;
import java.util.Map;

public class MettDomain {

    private List<Map<String,Double>> list;

    private String createTime;

    private Map<String,Double> map1;

    private Map<String,Double> map2;
    private PatientUser patientUser;

    public PatientUser getPatientUser() {
        return patientUser;
    }

    public void setPatientUser(PatientUser patientUser) {
        this.patientUser = patientUser;
    }
    public MettDomain() {
    }

    public List<Map<String, Double>> getList() {
        return list;
    }

    public void setList(List<Map<String, Double>> list) {
        this.list = list;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Map<String, Double> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, Double> map1) {
        this.map1 = map1;
    }

    public Map<String, Double> getMap2() {
        return map2;
    }

    public void setMap2(Map<String, Double> map2) {
        this.map2 = map2;
    }
}
