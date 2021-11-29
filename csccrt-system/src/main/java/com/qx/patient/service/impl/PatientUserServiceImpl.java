package com.qx.patient.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.common.utils.DateUtils;
import com.qx.demo.entity.*;
import com.qx.demo.mapper.*;
import com.qx.patient.domain.*;
import com.qx.patient.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.service.IPatientUserService;

/**
 * 患者Service业务层处理
 * 
 * @author patient
 * @date 2020-07-08
 */
@Service
public class PatientUserServiceImpl implements IPatientUserService 
{
    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private PatientSocietyTaskMapper patientSocietyTaskMapper;
    @Autowired
    private PatientBasisTaskMapper patientBasisTaskMapper;
    @Autowired
    private PatientIntermediateTaskMapper patientIntermediateTaskMapper;
    @Autowired
    private PatientAdvancedTaskMapper patientAdvancedTaskMapper;
    @Autowired
    private StatisticsTable1Mapper statisticsTable1Mapper;
    @Autowired
    private ZzDldMapper zzDldMapper;
    @Autowired
    private ZzEisMapper zzEisMapper;
    @Autowired
    private ZzIriCMapper zzIriCMapper;
    @Autowired
    private ZzMettScoreMapper zzMettScoreMapper;
    @Autowired
    private ZzFzyqScoreMapper zzFzyqScoreMapper;
    @Autowired
    private ZzShtlScoreMapper zzShtlScoreMapper;
    @Autowired
    private ZzSysbScoreMapper zzSysbScoreMapper;

    /**
     * 查询患者
     * 
     * @param patientId 患者ID
     * @return 患者
     */
    @Override
    public PatientUser selectPatientUserById(Long patientId)
    {
        return patientUserMapper.selectPatientUserById(patientId);
    }

    /**
     * 查询患者列表
     * 
     * @param patientUser 患者
     * @return 患者
     */
    @Override
    public List<PatientUser> selectPatientUserList(PatientUser patientUser)
    {
        patientUser.setDelFlag("0");
        List<PatientUser> listg=patientUserMapper.selectPatientUserList(patientUser);
        //Page page = (Page)listg;
        for (PatientUser p:listg) {
            if(patientUser.getTaskType()==null ||"".equals(patientUser.getTaskType())){
                break;
            }
            if(patientUser.getTaskType().equals("0")){
                PatientSocietyTask patientSocietyTask=patientSocietyTaskMapper.selectPatientTaskByPatientId(p.getPatientId(),"0");
                if(patientSocietyTask!=null){
                    p.setTaskStatus(patientSocietyTask.getTaskStatus());
                }
            }else if(patientUser.getTaskType().equals("1")){
                PatientBasisTask patientBasisTask=patientBasisTaskMapper.selectPatientTaskByPatientId(p.getPatientId(),"0");
                if(patientBasisTask!=null){
                    p.setTaskStatus(patientBasisTask.getTaskStatus());
                }
            }else if(patientUser.getTaskType().equals("2")){
                PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskMapper.selectPatientTaskByPatientId(p.getPatientId(),"0");
                if(patientIntermediateTask!=null){
                    p.setTaskStatus(patientIntermediateTask.getTaskStatus());
                }
            }else if(patientUser.getTaskType().equals("3")){
                PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskMapper.selectPatientTaskByPatientId(p.getPatientId(),"0");
                if(patientAdvancedTask!=null){
                    p.setTaskStatus(patientAdvancedTask.getTaskStatus());
                }
            }
        }
        for (PatientUser p:listg) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(p.getBirthday());
            p.setBirth(s);
        }
        //PageInfo<PatientUser> target = new PageInfo<>();
        //target.setList(listg);
        //target.setTotal(page.getTotal());
        //target.setPages(page.getPages());
        return listg;

    }

    /**
     * 新增患者
     * 
     * @param patientUser 患者
     * @return 结果
     */
    @Override
    public int insertPatientUser(PatientUser patientUser)
    {
        SimpleDateFormat abc = new SimpleDateFormat("yyyy");
        String a=abc.format(DateUtils.getNowDate());
        StatisticsTable1 statisticsTable11=statisticsTable1Mapper.selectStatisticsTable1ByYear(Long.parseLong(a));
        if(statisticsTable11==null){
            StatisticsTable1 statisticsTable=new StatisticsTable1();
            statisticsTable.setYear(Long.parseLong(a));
            statisticsTable.setRegisteredCount(1L);
            statisticsTable.setCompleteCount(0L);
            statisticsTable.setCreateTime(DateUtils.getNowDate());
            statisticsTable1Mapper.insertStatisticsTable1(statisticsTable);
        }else{
            StatisticsTable1 statisticsTable1=new StatisticsTable1();
            statisticsTable1.setId(statisticsTable11.getId());
            statisticsTable1.setRegisteredCount(statisticsTable11.getRegisteredCount()+1);
            statisticsTable1.setCompleteCount(statisticsTable11.getCompleteCount());
            statisticsTable1Mapper.updateStatisticsTable1(statisticsTable1);
        }
        patientUser.setDelFlag("0");
        patientUser.setCreateTime(DateUtils.getNowDate());
        return patientUserMapper.insertPatientUser(patientUser);
    }

    /**
     * 修改患者
     * 
     * @param patientUser 患者
     * @return 结果
     */
    @Override
    public int updatePatientUser(PatientUser patientUser)
    {
        patientUser.setDelFlag("0");
        patientUser.setUpdateTime(DateUtils.getNowDate());
        return patientUserMapper.updatePatientUser(patientUser);
    }

    /**
     * 批量删除患者
     * 
     * @param patientIds 需要删除的患者ID
     * @return 结果
     */
    @Override
    public int deletePatientUserByIds(Long[] patientIds)
    {
        for (Long p:patientIds) {
            PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
            patientSocietyTask.setPatientId(p);
            List<PatientSocietyTask> patientSocietyTaskList=patientSocietyTaskMapper.selectPatientSocietyTaskList(patientSocietyTask);
            if(patientSocietyTaskList!=null && patientSocietyTaskList.size()!=0) {
                for (PatientSocietyTask t : patientSocietyTaskList) {
                    patientSocietyTaskMapper.deletePatientSocietyTaskById(t.getTaskId());
                }
            }
            PatientBasisTask patientBasisTask=new PatientBasisTask();
            patientBasisTask.setPatientId(p);
            List<PatientBasisTask> patientBasisTaskList=patientBasisTaskMapper.selectPatientBasisTaskList(patientBasisTask);
            if(patientBasisTaskList!=null && patientBasisTaskList.size()!=0){
                for (PatientBasisTask t:patientBasisTaskList) {
                    patientBasisTaskMapper.deletePatientBasisTaskById(t.getTaskId());
                }
            }
            PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
            patientIntermediateTask.setPatientId(p);
            List<PatientIntermediateTask> patientIntermediateTaskList=patientIntermediateTaskMapper.selectPatientIntermediateTaskList(patientIntermediateTask);
            if(patientIntermediateTaskList!=null &&patientIntermediateTaskList.size()!=0){
                for (PatientIntermediateTask t:patientIntermediateTaskList) {
                    patientIntermediateTaskMapper.deletePatientIntermediateTaskById(t.getTaskId());
                }
            }
            PatientAdvancedTask patientAdvancedTask=new PatientAdvancedTask();
            patientAdvancedTask.setPatientId(p);
            List<PatientAdvancedTask> patientAdvancedTaskList=patientAdvancedTaskMapper.selectPatientAdvancedTaskList(patientAdvancedTask);
            if(patientAdvancedTaskList!=null &&patientAdvancedTaskList.size()!=0){
                for (PatientAdvancedTask t:patientAdvancedTaskList) {
                    patientAdvancedTaskMapper.deletePatientAdvancedTaskById(t.getTaskId());
                }
            }
            ZzDld zzDld=new ZzDld();
            zzDld.setPatientId(p);
            List<ZzDld> zzDldList=zzDldMapper.selectZzDldList(zzDld);
            if(zzDldList!=null && zzDldList.size()!=0){
                for (ZzDld z:zzDldList) {
                    zzDldMapper.deleteZzDldById(z.getId());
                }
            }
            ZzEis zzEis=new ZzEis();
            zzEis.setPatientId(p);
            List<ZzEis> zzEisList=zzEisMapper.selectZzEisList(zzEis);
            if(zzEisList!=null && zzEisList.size()!=0){
                for (ZzEis z:zzEisList) {
                    zzEisMapper.deleteZzEisById(z.getId());
                }
            }
            ZzIriC zzIriC=new ZzIriC();
            zzIriC.setPatientId(p);
            List<ZzIriC> zzIriCList=zzIriCMapper.selectZzIriCList(zzIriC);
            if(zzIriCList!=null && zzIriCList.size()!=0){
                for (ZzIriC z:zzIriCList) {
                    zzIriCMapper.deleteZzIriCById(z.getId());
                }
            }
            ZzMettScore zzMettScore=new ZzMettScore();
            zzMettScore.setPatientId(p);
            List<ZzMettScore> zzMettScoreList=zzMettScoreMapper.selectZzMettScoreList(zzMettScore);
            if(zzMettScoreList!=null && zzMettScoreList.size()!=0){
                for (ZzMettScore z:zzMettScoreList) {
                    zzMettScoreMapper.deleteZzMettScoreById(z.getId());
                }
            }
            ZzFzyqScore zzFzyqScore=new ZzFzyqScore();
            zzFzyqScore.setPatientId(p);
            List<ZzFzyqScore> zzFzyqScoreList=zzFzyqScoreMapper.selectZzFzyqScoreList(zzFzyqScore);
            if(zzFzyqScoreList!=null && zzFzyqScoreList.size()!=0){
                for (ZzFzyqScore z:zzFzyqScoreList) {
                    zzFzyqScoreMapper.deleteZzFzyqScoreById(z.getId());
                }
            }
            ZzShtlScore zzShtlScore=new ZzShtlScore();
            zzShtlScore.setPatientId(p);
            List<ZzShtlScore> zzShtlScoreList=zzShtlScoreMapper.selectZzShtlScoreList(zzShtlScore);
            if(zzShtlScoreList!=null && zzShtlScoreList.size()!=0){
                for (ZzShtlScore z:zzShtlScoreList) {
                    zzShtlScoreMapper.deleteZzShtlScoreById(z.getId());
                }
            }
            ZzSysbScore zzSysbScore=new ZzSysbScore();
            zzSysbScore.setPatientId(p);
            List<ZzSysbScore> zzSysbScoreList=zzSysbScoreMapper.selectZzSysbScoreList(zzSysbScore);
            if(zzSysbScoreList!=null && zzSysbScoreList.size()!=0){
                for (ZzSysbScore z:zzSysbScoreList) {
                    zzSysbScoreMapper.deleteZzSysbScoreById(z.getId());
                }
            }
        }
        return patientUserMapper.deletePatientUserByIds(patientIds);
    }

    @Override
    public int deleteUserTask(Long patientId) {
        int sum=1;
        PatientSocietyTask patientSocietyTask=patientSocietyTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientSocietyTask!=null){
            sum+=patientSocietyTaskMapper.deletePatientSocietyTaskById(patientSocietyTask.getTaskId());
        }
        PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientIntermediateTask!=null){
            sum+=patientIntermediateTaskMapper.deletePatientIntermediateTaskById(patientIntermediateTask.getTaskId());
        }
        PatientBasisTask patientBasisTask=patientBasisTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientBasisTask!=null){
            sum+=patientBasisTaskMapper.deletePatientBasisTaskById(patientBasisTask.getTaskId());
        }
        PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientAdvancedTask!=null){
            sum+=patientAdvancedTaskMapper.deletePatientAdvancedTaskById(patientAdvancedTask.getTaskId());
        }
        return sum;
    }

    /**
     * 删除患者信息
     * 
     * @param patientId 患者ID
     * @return 结果
     */
    @Override
    public int deletePatientUserById(Long patientId)
    {
        PatientSocietyTask patientSocietyTask=patientSocietyTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientSocietyTask!=null){
            return 0;
        }
        PatientBasisTask patientBasisTask=patientBasisTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientBasisTask!=null){
            return 0;
        }
        PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientIntermediateTask!=null){
            return 0;
        }
        PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        if(patientAdvancedTask!=null){
            return 0;
        }
        return patientUserMapper.deletePatientUserById(patientId);
    }

    /**
     * 修改delFlag
     * @param patientUser
     * @return
     */
    public int updateDelFlag(PatientUser patientUser)
    {
        patientUser.setUpdateTime(DateUtils.getNowDate());
        return patientUserMapper.updatePatientUser(patientUser);
    }
}
