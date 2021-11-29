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
import com.qx.patient.service.IPatientIntermediateTaskService;

import javax.annotation.Resource;

/**
 * 社会认知任务Service业务层处理
 * 
 * @author patient
 * @date 2020-07-14
 */
@Service
public class PatientIntermediateTaskServiceImpl implements IPatientIntermediateTaskService 
{
    @Autowired
    private PatientIntermediateTaskMapper patientIntermediateTaskMapper;

    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;
    @Autowired
    private Zlsl zlsl;
    @Autowired
    private PatientSocietyTaskMapper patientSocietyTaskMapper;
    @Autowired
    private PatientBasisTaskMapper patientBasisTaskMapper;
    @Resource
    private PatientAdvancedTaskMapper patientAdvancedTaskMapper;
    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    @Override
    public PatientIntermediateTask selectPatientIntermediateTaskById(Long taskId)
    {
        return patientIntermediateTaskMapper.selectPatientIntermediateTaskById(taskId);
    }

    /**
     * 查询社会认知任务列表
     * 
     * @param patientIntermediateTask 社会认知任务
     * @return 社会认知任务
     */
    @Override
    public PageInfo<PatientUserTask> selectPatientIntermediateTaskList(PatientIntermediateTask patientIntermediateTask)
    {
        List<PatientUserTask> userTaskList=new ArrayList<>();
        if(patientIntermediateTask.getPatientId()==null &&patientIntermediateTask.getPatientName()!=null){
            PatientUser user=new PatientUser();
            user.setPatientName(patientIntermediateTask.getPatientName());
            List<PatientUser> userList=patientUserMapper.selectPatientUserList(user);
            patientIntermediateTask.setPatientId(userList.get(0).getPatientId());
        }
        List<PatientIntermediateTask> listg=patientIntermediateTaskMapper.selectPatientIntermediateTaskList(patientIntermediateTask);
        Page page = (Page)listg;
        for (PatientIntermediateTask p:listg) {
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
    public int insertPatientIntermediateTask(PatientUserTask patientUserTask)
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
        List<PatientBasisTask> basisTaskList=patientBasisTaskMapper.selectAllByPatientId(patientUserTask.getPatientId());
        int c=0;
        for (PatientBasisTask p:basisTaskList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String a=sdf.format(p.getCreateTime());
            String aa=sdf.format(DateUtils.getNowDate());
            if(aa.equals(a)){
                ++c;
                break;
            }
        }
        if(b==0 && c==0) {
            zlsl.zlsl();
        }
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
        patientIntermediateTask.setPatientId(patientUserTask.getPatientId());
        patientIntermediateTask.setPatientName(patientUserTask.getPatientName());
        patientIntermediateTask.setWorkstation(patientUserTask.getWorkstation());
        patientIntermediateTask.setTypeIds(patientUserTask.getTypeIds());
        patientIntermediateTask.setTestCoding(patientUserTask.getTestCoding());
        patientIntermediateTask.setTaskStatus("0");
        patientIntermediateTask.setDelFlag("0");
        patientIntermediateTask.setUserId(patientUserTask.getUserId());
        patientIntermediateTask.setUserName(patientUserTask.getUserName());
        patientIntermediateTask.setCreateBy(patientUserTask.getCreateBy());
        patientIntermediateTask.setCreateTime(DateUtils.getNowDate());

        return patientIntermediateTaskMapper.insertPatientIntermediateTask(patientIntermediateTask);
    }

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    @Override
    public int updatePatientIntermediateTask(PatientUserTask patientUserTask)
    {
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
        patientIntermediateTask.setTaskId(patientUserTask.getTaskId());
        patientIntermediateTask.setPatientId(patientUserTask.getPatientId());
        patientIntermediateTask.setPatientName(patientUserTask.getPatientName());
        patientIntermediateTask.setWorkstation(patientUserTask.getWorkstation());
        patientIntermediateTask.setTypeIds(patientUserTask.getTypeIds());
        patientIntermediateTask.setTestCoding(patientUserTask.getTestCoding());
        patientIntermediateTask.setTaskStatus("0");
        patientIntermediateTask.setDelFlag("0");
        patientIntermediateTask.setUserId(patientUserTask.getUserId());
        patientIntermediateTask.setUserName(patientUserTask.getUserName());
        patientIntermediateTask.setUserName(patientUserTask.getCreateBy());
        patientIntermediateTask.setUpdateTime(DateUtils.getNowDate());
        return patientIntermediateTaskMapper.updatePatientIntermediateTask(patientIntermediateTask);
    }

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    @Override
    public int deletePatientIntermediateTaskByIds(Long[] taskIds)
    {
        int str=1;
        for (Long t:taskIds) {
            int a=patientIntermediateTaskMapper.deletePatientIntermediateTaskById(t);
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
    public int deletePatientIntermediateTaskById(Long taskId)
    {
        return patientIntermediateTaskMapper.deletePatientIntermediateTaskById(taskId);
    }

    public PatientUserTask selectTaskByPatientId(Long patientId){
        PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        PatientUserTask patientUserTask=new PatientUserTask();
        patientUserTask.setPatientId(patientIntermediateTask.getPatientId());
        PatientUser patientUser=patientUserMapper.selectPatientUserById(patientId);
        patientUserTask.setPatientName(patientUser.getPatientName());
        patientUserTask.setTaskId(patientIntermediateTask.getTaskId());
        patientUserTask.setTestCoding(patientIntermediateTask.getTestCoding());
        patientUserTask.setContanctInformation(patientUser.getContanctInformation());
        patientUserTask.setWorkstation(patientIntermediateTask.getWorkstation());
        patientUserTask.setUserId(patientIntermediateTask.getUserId());
        patientUserTask.setUserName(patientIntermediateTask.getUserName());

        String typeIds=patientIntermediateTask.getTypeIds();
        patientUserTask.setTypeIds(typeIds);
        if("".equals(typeIds) ||typeIds==null){
            patientUserTask.setTypeIds(typeIds);
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

    public int updateByPatientId(PatientIntermediateTask patientIntermediateTask){
        patientIntermediateTask.setDelFlag("0");
        return patientIntermediateTaskMapper.updateByPatientId(patientIntermediateTask);
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
        PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
        List<PatientIntermediateTask> list=patientIntermediateTaskMapper.selectPatientIntermediateTaskList(patientIntermediateTask);
        for (PatientIntermediateTask p:list) {
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
