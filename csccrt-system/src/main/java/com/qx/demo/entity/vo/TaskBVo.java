package com.qx.demo.entity.vo;

import com.qx.patient.domain.PatientAdvancedTask;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.domain.PatientIntermediateTask;
import com.qx.patient.domain.PatientSocietyTask;

import java.util.List;
import java.util.Map;

public class TaskBVo {
    private PatientBasisTask patientBasisTask;
    private Map<String,List<Object>> basisMap;




    public PatientBasisTask getPatientBasisTask() {
        return patientBasisTask;
    }

    public void setPatientBasisTask(PatientBasisTask patientBasisTask) {
        this.patientBasisTask = patientBasisTask;
    }

    public Map<String, List<Object>> getBasisMap() {
        return basisMap;
    }

    public void setBasisMap(Map<String, List<Object>> basisMap) {
        this.basisMap = basisMap;
    }


}
