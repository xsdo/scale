package com.qx.demo.entity.vo;

import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;

import java.util.List;
import java.util.Map;

public class TaskDVo {

    private PatientAdvancedTask patientAdvancedTask;
    private Map<String,Object> advancedMap;

    public PatientAdvancedTask getPatientAdvancedTask() {
        return patientAdvancedTask;
    }

    public void setPatientAdvancedTask(PatientAdvancedTask patientAdvancedTask) {
        this.patientAdvancedTask = patientAdvancedTask;
    }

    public Map<String, Object> getAdvancedMap() {
        return advancedMap;
    }

    public void setAdvancedMap(Map<String, Object> advancedMap) {
        this.advancedMap = advancedMap;
    }
}
