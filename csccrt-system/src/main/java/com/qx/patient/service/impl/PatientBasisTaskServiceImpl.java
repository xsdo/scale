package com.qx.patient.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.*;
import com.qx.patient.domain.vo.PatientUserTask;
import com.qx.patient.mapper.*;
import com.qx.patient.util.Zlsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.service.IPatientBasisTaskService;

import javax.annotation.Resource;

/**
 * 社会认知任务Service业务层处理
 * 
 * @author patient
 * @date 2020-07-14
 */
@Service
public class PatientBasisTaskServiceImpl implements IPatientBasisTaskService 
{
    @Autowired
    private PatientBasisTaskMapper patientBasisTaskMapper;

    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;
    @Autowired
    private Zlsl zlsl;
    @Autowired
    private PatientSocietyTaskMapper patientSocietyTaskMapper;
    @Resource
    private PatientIntermediateTaskMapper patientIntermediateTaskMapper;
    @Resource
    private PatientAdvancedTaskMapper patientAdvancedTaskMapper;
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    @Override
    public PatientBasisTask selectPatientBasisTaskById(Long taskId)
    {
        return patientBasisTaskMapper.selectPatientBasisTaskById(taskId);
    }

    /**
     * 查询社会认知任务列表
     * 
     * @param patientBasisTask 社会认知任务
     * @return 社会认知任务
     */
    @Override
    public PageInfo<PatientUserTask> selectPatientBasisTaskList(PatientBasisTask patientBasisTask)
    {
        List<PatientUserTask> userTaskList=new ArrayList<>();
        if(patientBasisTask.getPatientId()==null &&patientBasisTask.getPatientName()!=null){
            PatientUser user=new PatientUser();
            user.setPatientName(patientBasisTask.getPatientName());
            List<PatientUser> userList=patientUserMapper.selectPatientUserList(user);
            patientBasisTask.setPatientId(userList.get(0).getPatientId());
        }
        List<PatientBasisTask> u=new ArrayList<>();

        List<PatientBasisTask> listg=patientBasisTaskMapper.selectPatientBasisTaskList(patientBasisTask);
        Page page = (Page)listg;
        for (PatientBasisTask p:listg) {
            PatientUserTask patientUserTask=new PatientUserTask();
            patientUserTask.setPatientId(p.getPatientId());
            patientUserTask.setTaskId(p.getTaskId());
            patientUserTask.setPatientName(p.getPatientName());
            patientUserTask.setWorkstation(p.getWorkstation());
            patientUserTask.setTaskStatus(p.getTaskStatus());
            patientUserTask.setTestCoding(p.getTestCoding());
            patientUserTask.setCreateTime(p.getCreateTime());
            String typeIds=p.getTypeIds();
            if("".equals(typeIds) ||typeIds==null){
                patientUserTask.setTypeIds(typeIds);
                patientUserTask.setTypeNames("");
            }else {
                String[] arr = typeIds.split(",");
                String string = "";
                for (String s : arr) {
                    EvaluationType evaluationType = evaluationTypeMapper.selectEvaluationTypeById(Long.valueOf(s));
                    string += evaluationType.getTypeName() + ",";
                }
                patientUserTask.setTypeNames(string.substring(0, string.length() - 1));
                patientUserTask.setTypeIds(typeIds);
            }
            userTaskList.add(patientUserTask);
        }
        //Collections.sort(userTaskList);
        PageInfo<PatientUserTask> target = new PageInfo<>();
        target.setList(userTaskList);
        target.setTotal(page.getTotal());
        target.setPages(page.getPages());
        return target;
    }

    /**
     * 新增社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    @Override
    public int insertPatientBasisTask(PatientUserTask patientUserTask)
    {
       List<PatientSocietyTask> societyTaskList=patientSocietyTaskMapper.selectAllByPatientId(patientUserTask.getPatientId());
       int b=0;
        for (PatientSocietyTask p:societyTaskList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String a=sdf.format(p.getCreateTime());
            String aa=sdf.format(DateUtils.getNowDate());
            if(aa.equals(a)){
                ++b;
                break;
            }
        }
        if(b==0) {
            zlsl.zlsl();
        }
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientBasisTask patientBasisTask=new PatientBasisTask();
        patientBasisTask.setPatientId(patientUserTask.getPatientId());
        patientBasisTask.setPatientName(patientUserTask.getPatientName());
        patientBasisTask.setWorkstation(patientUserTask.getWorkstation());
        patientBasisTask.setTypeIds(patientUserTask.getTypeIds());
        patientBasisTask.setTestCoding(patientUserTask.getTestCoding());
        patientBasisTask.setUserId(patientUserTask.getUserId());
        patientBasisTask.setUserName(patientUserTask.getUserName());
        patientBasisTask.setTaskStatus("0");
        patientBasisTask.setDelFlag("0");
        patientBasisTask.setCreateBy(patientUserTask.getCreateBy());
        patientBasisTask.setCreateTime(DateUtils.getNowDate());
        return patientBasisTaskMapper.insertPatientBasisTask(patientBasisTask);
    }

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    @Override
    public int updatePatientBasisTask(PatientUserTask patientUserTask)
    {
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientBasisTask patientBasisTask=new PatientBasisTask();
        patientBasisTask.setPatientId(patientUserTask.getPatientId());
        patientBasisTask.setTaskId(patientUserTask.getTaskId());
        patientBasisTask.setPatientName(patientBasisTask.getPatientName());
        patientBasisTask.setWorkstation(patientUserTask.getWorkstation());
        patientBasisTask.setTypeIds(patientUserTask.getTypeIds());
        patientBasisTask.setTestCoding(patientUserTask.getTestCoding());
        patientBasisTask.setUserId(patientUserTask.getUserId());
        patientBasisTask.setUserName(patientUserTask.getUserName());
        patientBasisTask.setTaskStatus("0");
        patientBasisTask.setDelFlag("0");
        patientBasisTask.setUpdateBy(patientUserTask.getCreateBy());
        patientBasisTask.setUpdateTime(DateUtils.getNowDate());
        return patientBasisTaskMapper.updatePatientBasisTask(patientBasisTask);
    }

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    @Override
    public int deletePatientBasisTaskByIds(Long[] taskIds)
    {
        int str=1;
        for (Long t:taskIds) {
            int a=patientBasisTaskMapper.deletePatientBasisTaskById(t);
            str+=a;
        }
        return str;
    }

    /**
     * 删除社会认知任务信息
     * 
     * @param taskId 社会认知任务ID
     * @return 结果
     */
    @Override
    public int deletePatientBasisTaskById(Long taskId)
    {
        return patientBasisTaskMapper.deletePatientBasisTaskById(taskId);
    }
    public PatientUserTask selectTaskByPatientId(Long patientId){
        PatientBasisTask patientBasisTask=patientBasisTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        PatientUserTask patientUserTask=new PatientUserTask();
        patientUserTask.setPatientId(patientBasisTask.getPatientId());
        PatientUser patientUser=patientUserMapper.selectPatientUserById(patientId);
        patientUserTask.setPatientName(patientUser.getPatientName());
        patientUserTask.setTaskId(patientBasisTask.getTaskId());
        patientUserTask.setTypeIds(patientBasisTask.getTypeIds());
        patientUserTask.setTestCoding(patientBasisTask.getTestCoding());
        patientUserTask.setContanctInformation(patientUser.getContanctInformation());
        patientUserTask.setWorkstation(patientBasisTask.getWorkstation());
        patientUserTask.setUserId(patientBasisTask.getUserId());
        patientUserTask.setUserName(patientBasisTask.getUserName());

        String typeIds=patientBasisTask.getTypeIds();
        patientUserTask.setTypeIds(typeIds);
        if("".equals(typeIds) ||typeIds==null){
            patientUserTask.setTypeNames("");
        }else {
            String[] arr = typeIds.split(",");
            String string = "";
            List<Map<String,String>> list=new ArrayList<>();
            for (String s : arr) {
                Map<String,String> map=new HashMap<>();
                map.put("typeId",s);
                EvaluationType evaluationType = evaluationTypeMapper.selectEvaluationTypeById(Long.valueOf(s));
                map.put("typeName",evaluationType.getTypeName());
                string += evaluationType.getTypeName() + ",";
                list.add(map);
            }
            patientUserTask.setArr(list);
            patientUserTask.setTypeNames(string.substring(0, string.length() - 1));
            patientUserTask.setTypeIds(typeIds);
        }
        return patientUserTask;
    }

    public int updateByPatientId(PatientBasisTask patientBasisTask){
        patientBasisTask.setDelFlag("0");
        return patientBasisTaskMapper.updateByPatientId(patientBasisTask);
    }

    @Override
    public Boolean getWebpackVersion(String webpackVersion,String patientId) {
        PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
        List<PatientSocietyTask> list=patientSocietyTaskMapper.selectPatientSocietyTaskList(patientSocietyTask);
        for (PatientSocietyTask p:list) {
            if(!"3".equals(p.getTaskStatus())){
                if(webpackVersion.equals(p.getWorkstation())){
                    if(Long.parseLong(patientId)!=p.getPatientId()){
                        return true;
                    }
                }
            }

        }
        PatientBasisTask patientBasisTask=new PatientBasisTask();
        List<PatientBasisTask> list1=patientBasisTaskMapper.selectPatientBasisTaskList(patientBasisTask);
        for (PatientBasisTask p:list1) {
            if(!"3".equals(p.getTaskStatus())) {
                if (webpackVersion.equals(p.getWorkstation())) {
                    if(Long.parseLong(patientId)!=p.getPatientId()){
                        return true;
                    }
                }
            }
        }
        PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
        List<PatientIntermediateTask> list2=patientIntermediateTaskMapper.selectPatientIntermediateTaskList(patientIntermediateTask);
        for (PatientIntermediateTask p:list2) {
            if(!"3".equals(p.getTaskStatus())) {
                if (webpackVersion.equals(p.getWorkstation())) {
                    if(Long.parseLong(patientId)!=p.getPatientId()){
                        return true;
                    }
                }
            }
        }
        PatientAdvancedTask patientAdvancedTask=new PatientAdvancedTask();
        List<PatientAdvancedTask> list3=patientAdvancedTaskMapper.selectPatientAdvancedTaskList(patientAdvancedTask);
        for (PatientAdvancedTask p:list3) {
            if(!"3".equals(p.getTaskStatus())) {
                if (webpackVersion.equals(p.getWorkstation())) {
                    if(Long.parseLong(patientId)!=p.getPatientId()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Boolean getWebpackVersion(String webpackVersion,Long taskId) {
        PatientBasisTask patientIntermediateTask=new PatientBasisTask();
        List<PatientBasisTask> list=patientBasisTaskMapper.selectPatientBasisTaskList(patientIntermediateTask);
        for (PatientBasisTask p:list) {
            if(!"3".equals(p.getTaskStatus())) {
                if(p.getTaskId()!=taskId) {
                    if (webpackVersion.equals(p.getWorkstation())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
