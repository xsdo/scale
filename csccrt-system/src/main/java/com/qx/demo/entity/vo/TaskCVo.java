package com.qx.demo.entity.vo;

import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskCVo {

    private PatientIntermediateTask patientIntermediateTask;
    private Map<String,List<Object>> intermediateMap;
    private Map<String,Set<List<Object>>> setMap;

    public Map<String, Set<List<Object>>> getSetMap() {
        return setMap;
    }

    public void setSetMap(Map<String, Set<List<Object>>> setMap) {
        this.setMap = setMap;
    }

    public PatientIntermediateTask getPatientIntermediateTask() {
        return patientIntermediateTask;
    }

    public void setPatientIntermediateTask(PatientIntermediateTask patientIntermediateTask) {
        this.patientIntermediateTask = patientIntermediateTask;
    }

    public Map<String, List<Object>> getIntermediateMap() {
        return intermediateMap;
    }

    public void setIntermediateMap(Map<String, List<Object>> intermediateMap) {
        this.intermediateMap = intermediateMap;
    }

}
