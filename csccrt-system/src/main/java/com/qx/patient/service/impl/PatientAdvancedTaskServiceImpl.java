package com.qx.patient.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.*;
import com.qx.patient.domain.vo.PatientUserTask;
import com.qx.patient.mapper.*;
import com.qx.patient.util.Zlsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.service.IPatientAdvancedTaskService;

/**
 * 社会认知任务Service业务层处理
 * 
 * @author patient
 * @date 2020-07-14
 */
@Service
public class PatientAdvancedTaskServiceImpl implements IPatientAdvancedTaskService 
{
    @Autowired
    private PatientAdvancedTaskMapper patientAdvancedTaskMapper;

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
    @Autowired
    private PatientIntermediateTaskMapper patientIntermediateTaskMapper;

    /**
     * 查询社会认知任务
     * 
     * @param taskId 社会认知任务ID
     * @return 社会认知任务
     */
    @Override
    public PatientAdvancedTask selectPatientAdvancedTaskById(Long taskId)
    {
        return patientAdvancedTaskMapper.selectPatientAdvancedTaskById(taskId);
    }

    /**
     * 查询社会认知任务列表
     * 
     * @param patientAdvancedTask 社会认知任务
     * @return 社会认知任务
     */
    @Override
    public PageInfo<PatientUserTask> selectPatientAdvancedTaskList(PatientAdvancedTask patientAdvancedTask)
    {
        List<PatientUserTask> userTaskList=new ArrayList<>();
        if(patientAdvancedTask.getPatientId()==null &&patientAdvancedTask.getPatientName()!=null){
            PatientUser user=new PatientUser();
            user.setPatientName(patientAdvancedTask.getPatientName());
            List<PatientUser> userList=patientUserMapper.selectPatientUserList(user);
            patientAdvancedTask.setPatientId(userList.get(0).getPatientId());
        }
        List<PatientAdvancedTask> listg=patientAdvancedTaskMapper.selectPatientAdvancedTaskList(patientAdvancedTask);
        Page page = (Page)listg;
        for (PatientAdvancedTask p:listg) {
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
    public int insertPatientAdvancedTask(PatientUserTask patientUserTask)
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
        List<PatientIntermediateTask> intermediateTaskList=patientIntermediateTaskMapper.selectAllByPatientId(patientUserTask.getPatientId());
        int d=0;
        for (PatientIntermediateTask p:intermediateTaskList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String a=sdf.format(p.getCreateTime());
            String aa=sdf.format(DateUtils.getNowDate());
            if(aa.equals(a)){
                ++d;
                break;
            }
        }
        if(b==0 && c==0 && d==0) {
            zlsl.zlsl();
        }
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientAdvancedTask patientAdvancedTask=new PatientAdvancedTask();
        patientAdvancedTask.setPatientId(patientUserTask.getPatientId());
        patientAdvancedTask.setPatientName(patientUserTask.getPatientName());
        patientAdvancedTask.setWorkstation(patientUserTask.getWorkstation());
        patientAdvancedTask.setTypeIds(patientUserTask.getTypeIds());
        patientAdvancedTask.setTestCoding(patientUserTask.getTestCoding());
        patientAdvancedTask.setTaskStatus("0");
        patientAdvancedTask.setDelFlag("0");
        patientAdvancedTask.setUserId(patientUserTask.getUserId());
        patientAdvancedTask.setUserName(patientUserTask.getUserName());
        patientAdvancedTask.setCreateBy(patientUserTask.getCreateBy());
        patientAdvancedTask.setCreateTime(DateUtils.getNowDate());
        return patientAdvancedTaskMapper.insertPatientAdvancedTask(patientAdvancedTask);
    }

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    @Override
    public int updatePatientAdvancedTask(PatientUserTask patientUserTask)
    {
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientAdvancedTask patientAdvancedTask=new PatientAdvancedTask();
        patientAdvancedTask.setPatientId(patientUserTask.getPatientId());
        patientAdvancedTask.setTaskId(patientUserTask.getTaskId());
        patientAdvancedTask.setPatientName(patientUserTask.getPatientName());
        patientAdvancedTask.setWorkstation(patientUserTask.getWorkstation());
        patientAdvancedTask.setTypeIds(patientUserTask.getTypeIds());
        patientAdvancedTask.setTestCoding(patientUserTask.getTestCoding());
        patientAdvancedTask.setTaskStatus("0");
        patientAdvancedTask.setDelFlag("0");
        patientAdvancedTask.setUserId(patientUserTask.getUserId());
        patientAdvancedTask.setUserName(patientUserTask.getUserName());
        patientAdvancedTask.setUpdateBy(patientUserTask.getCreateBy());
        patientAdvancedTask.setUpdateTime(DateUtils.getNowDate());
        return patientAdvancedTaskMapper.updatePatientAdvancedTask(patientAdvancedTask);
    }

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    @Override
    public int deletePatientAdvancedTaskByIds(Long[] taskIds)
    {
        int str=1;
        for (Long t:taskIds) {
            int a=patientAdvancedTaskMapper.deletePatientAdvancedTaskById(t);
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
    public int deletePatientAdvancedTaskById(Long taskId)
    {
        return patientAdvancedTaskMapper.deletePatientAdvancedTaskById(taskId);
    }

    public PatientUserTask selectTaskByPatientId(Long patientId){
        PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskMapper.selectPatientTaskByPatientId(patientId,"0");
        PatientUserTask patientUserTask=new PatientUserTask();
        patientUserTask.setPatientId(patientAdvancedTask.getPatientId());
        PatientUser patientUser=patientUserMapper.selectPatientUserById(patientId);
        patientUserTask.setPatientName(patientUser.getPatientName());
        patientUserTask.setTaskId(patientAdvancedTask.getTaskId());
        patientUserTask.setTypeIds(patientAdvancedTask.getTypeIds());
        patientUserTask.setTestCoding(patientAdvancedTask.getTestCoding());
        patientUserTask.setContanctInformation(patientUser.getContanctInformation());
        patientUserTask.setWorkstation(patientAdvancedTask.getWorkstation());
        patientUserTask.setUserId(patientAdvancedTask.getUserId());
        patientUserTask.setUserName(patientAdvancedTask.getUserName());

        String typeIds=patientAdvancedTask.getTypeIds();
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

    public int updateByPatientId(PatientAdvancedTask patientAdvancedTask){
        patientAdvancedTask.setDelFlag("0");
        return patientAdvancedTaskMapper.updateByPatientId(patientAdvancedTask);
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
        PatientAdvancedTask patientIntermediateTask=new PatientAdvancedTask();
        List<PatientAdvancedTask> list=patientAdvancedTaskMapper.selectPatientAdvancedTaskList(patientIntermediateTask);
        for (PatientAdvancedTask p:list) {
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
