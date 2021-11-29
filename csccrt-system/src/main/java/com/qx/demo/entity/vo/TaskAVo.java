package com.qx.demo.entity.vo;

import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;

import java.util.List;
import java.util.Map;

public class TaskAVo {
    private PatientSocietyTask patientSocietyTask;
    private Map<String,List<Object>> societyMap;


    public PatientSocietyTask getPatientSocietyTask() {
        return patientSocietyTask;
    }

    public void setPatientSocietyTask(PatientSocietyTask patientSocietyTask) {
        this.patientSocietyTask = patientSocietyTask;
    }

    public Map<String, List<Object>> getSocietyMap() {
        return societyMap;
    }

    public void setSocietyMap(Map<String, List<Object>> societyMap) {
        this.societyMap = societyMap;
    }
}
