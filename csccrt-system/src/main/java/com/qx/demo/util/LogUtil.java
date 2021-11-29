package com.qx.demo.util;

import com.qx.demo.entity.ZzIriC;
import com.qx.demo.entity.vo.ResultVo;
import com.qx.patient.domain.*;
import com.qx.patient.mapper.PatientUserMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Component
public class LogUtil {
    public DldLog addDldLog(List<ResultVo> list,PatientUser patientUser){

        DldLog dldLog=new DldLog();
        for (ResultVo z:list) {
            if("1".equals(z.getTitle()) ||"3".equals(z.getTitle()) ||"6".equals(z.getTitle()) ||"7".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"14".equals(z.getTitle())){
                dldLog.setQgbb(dldLog.getQgbb()+Integer.parseInt(z.getPoint()));
            }else if("2".equals(z.getTitle()) ||"4".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"17".equals(z.getTitle())){
                dldLog.setQgms(dldLog.getQgms()+Integer.parseInt(z.getPoint()));
            }else if("5".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"16".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"19".equals(z.getTitle()) ||"20".equals(z.getTitle())){
                dldLog.setWxx(dldLog.getWxx()+Integer.parseInt(z.getPoint()));
            }
        }
        dldLog.setSum(dldLog.getQgbb()+dldLog.getQgms()+dldLog.getWxx());
        dldLog.setTestDay(new Date());
        dldLog.setPatientId(patientUser.getPatientId());
        dldLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            dldLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            dldLog.setSex(patientUser.getSex());
        }
        dldLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            dldLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            dldLog.setJob(patientUser.getJob());
        }
        return dldLog;
    }
    public EisLog addEisLog(List<ResultVo> list,PatientUser patientUser){
        EisLog eisLog=new EisLog();
        for (ResultVo z:list) {
            if("2".equals(z.getTitle()) ||"3".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"14".equals(z.getTitle()) ||"16".equals(z.getTitle())||"22".equals(z.getTitle())||"23".equals(z.getTitle())||"28".equals(z.getTitle())||"30".equals(z.getTitle())||"31".equals(z.getTitle())){
                eisLog.setQxzj(eisLog.getQxzj()+Integer.parseInt(z.getPoint()));
            }else if("7".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"17".equals(z.getTitle()) ||"20".equals(z.getTitle())||"24".equals(z.getTitle())){
                eisLog.setZwqx(eisLog.getZwqx()+Integer.parseInt(z.getPoint()));
            }else if("4".equals(z.getTitle()) ||"6".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"19".equals(z.getTitle()) ||"21".equals(z.getTitle()) ||"26".equals(z.getTitle()) ||"27".equals(z.getTitle())||"29".equals(z.getTitle())||"32".equals(z.getTitle())){
                eisLog.setTrqx(eisLog.getTrqx()+Integer.parseInt(z.getPoint()));
            }else if("1".equals(z.getTitle()) ||"5".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"25".equals(z.getTitle()) ||"33".equals(z.getTitle()) ){
                eisLog.setQxbd(eisLog.getQxbd()+Integer.parseInt(z.getPoint()));
            }
        }
        eisLog.setSum(eisLog.getQxbd()+eisLog.getQxzj()+eisLog.getTrqx()+eisLog.getZwqx());
        eisLog.setTestDay(new Date());
        eisLog.setPatientId(patientUser.getPatientId());
        eisLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            eisLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            eisLog.setSex(patientUser.getSex());
        }
        eisLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            eisLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            eisLog.setJob(patientUser.getJob());
        }
        return eisLog;
    }
    public IricLog addIricLog(List<ResultVo> list,PatientUser patientUser){
        IricLog iricLog=new IricLog();
        for (ResultVo z:list) {
            if("6".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"19".equals(z.getTitle())||"22".equals(z.getTitle()) ){
                iricLog.setGdcz(iricLog.getGdcz()+Integer.parseInt(z.getPoint()));
            }else if("4".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"21".equals(z.getTitle())){
                iricLog.setGrtk(iricLog.getGrtk()+Integer.parseInt(z.getPoint()));
            }else if("3".equals(z.getTitle()) ||"5".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"17".equals(z.getTitle()) ||"20".equals(z.getTitle()) ){
                iricLog.setXx(iricLog.getXx()+Integer.parseInt(z.getPoint()));
            }else if("1".equals(z.getTitle()) ||"2".equals(z.getTitle()) ||"7".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"14".equals(z.getTitle())||"16".equals(z.getTitle()) ){
                iricLog.setGqgx(iricLog.getGqgx()+Integer.parseInt(z.getPoint()));
            }
        }
        iricLog.setSum(iricLog.getGdcz()+iricLog.getGqgx()+iricLog.getGrtk()+iricLog.getXx());
        iricLog.setTestDay(new Date());
        iricLog.setPatientId(patientUser.getPatientId());
        iricLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            iricLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            iricLog.setSex(patientUser.getSex());
        }
        iricLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            iricLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            iricLog.setJob(patientUser.getJob());
        }
        return iricLog;
    }
    public MettLog addMettLog(List<ResultVo> list,PatientUser patientUser){
        MettLog mettLog=new MettLog();
        Long a1=0L;
        Long a2=0L;
        Long a3=0L;
        Long a4=0L;
        Long a5=0L;
        Long a6=0L;
        Long a7=0L;
        Double b1=0.0;
        Double b2=0.0;
        Double b3=0.0;
        Double b4=0.0;
        Double b5=0.0;
        Double b6=0.0;
        Double b7=0.0;
            for (ResultVo r:list) {
                if("高兴".equals(r.getExId())) {
                    a1 += Long.parseLong(r.getPoint());
                    b1+=r.getReactionTime();
                }
                if("悲伤".equals(r.getExId())) {
                    a2 += Long.parseLong(r.getPoint());
                    b2+=r.getReactionTime();
                }
                if("愤怒".equals(r.getExId())) {
                    a3 += Long.parseLong(r.getPoint());
                    b3+=r.getReactionTime();
                }
                if("厌恶".equals(r.getExId())) {
                    a4 += Long.parseLong(r.getPoint());
                    b4+=r.getReactionTime();
                }
                if("惊讶".equals(r.getExId())) {
                    a5 += Long.parseLong(r.getPoint());
                    b5+=r.getReactionTime();
                }
                if("恐惧".equals(r.getExId())) {
                    a6 += Long.parseLong(r.getPoint());
                    b6+=r.getReactionTime();
                }
                if("中性".equals(r.getExId())) {
                    a7 += Long.parseLong(r.getPoint());
                    b7+=r.getReactionTime();
                }
            }
            mettLog.setGxCorrect(a1/4);
            mettLog.setGxReply(b1/4);
            mettLog.setBsCorrect(a2/4);
            mettLog.setBsReply(b2/4);
            mettLog.setFnCorrect(a3/4);
            mettLog.setFnReply(b3/4);
            mettLog.setYwCorrect(a4/4);
            mettLog.setYwReply(b4/4);
            mettLog.setJyCorrect(a5/4);
            mettLog.setJyReply(b5/4);
            mettLog.setKjCorrect(a6/4);
            mettLog.setKjReply(b6/4);
            mettLog.setZxCorrect(a7/4);
            mettLog.setZxReply(b7/4);
        mettLog.setTestDay(new Date());
        mettLog.setPatientId(patientUser.getPatientId());
        mettLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            mettLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            mettLog.setSex(patientUser.getSex());
        }
        mettLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            mettLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            mettLog.setJob(patientUser.getJob());
        }
        return mettLog;
    }
    public FzyqLog addFzyqLog(List<ResultVo> list,PatientUser patientUser){
        FzyqLog fzyqLog=new FzyqLog();
        Long a=0l;
        Double b=0.0;
        for (ResultVo z:list) {
            a+=Integer.parseInt(z.getPoint());
            b+=z.getReactionTime();
        }
        fzyqLog.setCorrect(a/list.size()*100);
        fzyqLog.setAverage(b/list.size());
        fzyqLog.setTestDay(new Date());
        fzyqLog.setPatientId(patientUser.getPatientId());
        fzyqLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            fzyqLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            fzyqLog.setSex(patientUser.getSex());
        }
        fzyqLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            fzyqLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            fzyqLog.setJob(patientUser.getJob());
        }
        return fzyqLog;
    }
    public ShtlLog addShtlLog(List<ResultVo> list,PatientUser patientUser){
        ShtlLog shtlLog=new ShtlLog();
        Long a=0L;
        //Double b=0.0;
        for (ResultVo z:list) {
            a+=Long.parseLong(z.getPoint());
            //b+=z.getReactionTime();
        }
        shtlLog.setCorrect(a/list.size()*100);
        //shtlLog.setAverage(b/list.size());
        shtlLog.setTestDay(new Date());
        shtlLog.setPatientId(patientUser.getPatientId());
        shtlLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            shtlLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            shtlLog.setSex(patientUser.getSex());
        }
        shtlLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            shtlLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            shtlLog.setJob(patientUser.getJob());
        }
        return shtlLog;
    }
    public SysbLog addSysbLog(List<ResultVo> list,PatientUser patientUser){
        SysbLog sysbLog=new SysbLog();
        for (ResultVo z : list) {
            if ("1".equals(z.getTitle()) || "2".equals(z.getTitle())) {
                sysbLog.setKzwt(sysbLog.getKzwt() + Integer.parseInt(z.getPoint()));
            } else if ("3".equals(z.getTitle()) || "4".equals(z.getTitle()) || "5".equals(z.getTitle()) || "6".equals(z.getTitle()) || "7".equals(z.getTitle()) || "8".equals(z.getTitle())) {
                sysbLog.setSywt(sysbLog.getSywt() + Integer.parseInt(z.getPoint()));
            }
        }
        sysbLog.setTestDay(new Date());
        sysbLog.setPatientId(patientUser.getPatientId());
        sysbLog.setPatientName(patientUser.getPatientName());
        if(patientUser.getAge()!=null) {
            sysbLog.setAge(patientUser.getAge());
        }
        if(patientUser.getSex()!=null ) {
            sysbLog.setSex(patientUser.getSex());
        }
        sysbLog.setDiagnosis(patientUser.getDiagnosis());
        if(patientUser.getEducation()!=null){
            sysbLog.setEducation(patientUser.getEducation());
        }
        if(patientUser.getJob()!=null){
            sysbLog.setJob(patientUser.getJob());
        }
        return sysbLog;
    }
}
