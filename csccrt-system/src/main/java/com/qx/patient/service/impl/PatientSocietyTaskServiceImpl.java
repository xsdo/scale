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
import com.qx.patient.service.IPatientSocietyTaskService;

import javax.annotation.Resource;

/**
 * 社会认知任务Service业务层处理
 * 
 * @author patient
 * @date 2020-07-14
 */
@Service
public class PatientSocietyTaskServiceImpl implements IPatientSocietyTaskService 
{
    @Autowired
    private PatientSocietyTaskMapper patientSocietyTaskMapper;

    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;
    @Autowired
    private Zlsl zlsl;
    @Resource
    private PatientBasisTaskMapper patientBasisTaskMapper;
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
    public PatientSocietyTask selectPatientSocietyTaskById(Long taskId)
    {
        return patientSocietyTaskMapper.selectPatientSocietyTaskById(taskId);
    }

    /**
     * 查询社会认知任务列表
     * 
     * @param patientSocietyTask 社会认知任务
     * @return 社会认知任务
     */
    @Override
    public PageInfo<PatientUserTask> selectPatientSocietyTaskList(PatientSocietyTask patientSocietyTask)
    {
        List<PatientUserTask> userTaskList=new ArrayList<>();
        if(patientSocietyTask.getPatientId()==null &&patientSocietyTask.getPatientName()!=null){
            PatientUser user=new PatientUser();
            user.setPatientName(patientSocietyTask.getPatientName());
            List<PatientUser> userList=patientUserMapper.selectPatientUserList(user);
            patientSocietyTask.setPatientId(userList.get(0).getPatientId());
        }
        List<PatientSocietyTask> listg=patientSocietyTaskMapper.selectPatientSocietyTaskList(patientSocietyTask);
        Page page = (Page)listg;
        for (PatientSocietyTask p:listg) {
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
                patientUserTask.setTypeIds(typeIds);
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
    public int insertPatientSocietyTask(PatientUserTask patientUserTask)
    {
        zlsl.zlsl();
      Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
          return 0;
        }
        PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
        patientSocietyTask.setPatientId(patientUserTask.getPatientId());
        patientSocietyTask.setPatientName(patientUserTask.getPatientName());
        patientSocietyTask.setWorkstation(patientUserTask.getWorkstation());
        patientSocietyTask.setTypeIds(patientUserTask.getTypeIds());
        patientSocietyTask.setTestCoding(patientUserTask.getTestCoding());
        patientSocietyTask.setTaskStatus("0");
        patientSocietyTask.setDelFlag("0");
        patientSocietyTask.setUserId(patientUserTask.getUserId());
        patientSocietyTask.setUserName(patientUserTask.getUserName());
        patientSocietyTask.setCreateBy(patientUserTask.getCreateBy());
        patientSocietyTask.setCreateTime(DateUtils.getNowDate());
        return patientSocietyTaskMapper.insertPatientSocietyTask(patientSocietyTask);
    }

    /**
     * 修改社会认知任务
     * 
     * @param patientUserTask 社会认知任务
     * @return 结果
     */
    @Override
    public int updatePatientSocietyTask(PatientUserTask patientUserTask)
    {
        Boolean flag=getWebpackVersion(patientUserTask.getWorkstation(),patientUserTask.getTaskId());
        if(flag){
            return 0;
        }
        PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
        patientSocietyTask.setTaskId(patientUserTask.getTaskId());
        patientSocietyTask.setPatientId(patientUserTask.getPatientId());
        patientSocietyTask.setPatientName(patientUserTask.getPatientName());
        patientSocietyTask.setWorkstation(patientUserTask.getWorkstation());
        patientSocietyTask.setTypeIds(patientUserTask.getTypeIds());
        patientSocietyTask.setTestCoding(patientUserTask.getTestCoding());
        patientSocietyTask.setTaskStatus("0");
        patientSocietyTask.setDelFlag("0");
        patientSocietyTask.setUserId(patientUserTask.getUserId());
        patientSocietyTask.setUserName(patientUserTask.getUserName());
        patientSocietyTask.setUpdateBy(patientUserTask.getCreateBy());
        patientSocietyTask.setUpdateTime(DateUtils.getNowDate());
        return patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
    }

    /**
     * 批量删除社会认知任务
     * 
     * @param taskIds 需要删除的社会认知任务ID
     * @return 结果
     */
    @Override
    public int deletePatientSocietyTaskByIds(Long[] taskIds)
    {
        int str=1;
        for (Long t:taskIds) {
               int a=patientSocietyTaskMapper.deletePatientSocietyTaskById(t);
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
    public int deletePatientSocietyTaskById(Long taskId)
    {
        return patientSocietyTaskMapper.deletePatientSocietyTaskById(taskId);
    }

    public PatientUserTask selectTaskByPatientId(Long patientId){
       PatientSocietyTask patientSocietyTask=patientSocietyTaskMapper.selectPatientTaskByPatientId(patientId,"0");
       PatientUserTask patientUserTask=new PatientUserTask();
       patientUserTask.setTaskId(patientSocietyTask.getTaskId());
       patientUserTask.setPatientId(patientSocietyTask.getPatientId());
       PatientUser patientUser=patientUserMapper.selectPatientUserById(patientId);
       patientUserTask.setPatientName(patientUser.getPatientName());
       patientUserTask.setTestCoding(patientSocietyTask.getTestCoding());
       patientUserTask.setContanctInformation(patientUser.getContanctInformation());
       patientUserTask.setWorkstation(patientSocietyTask.getWorkstation());
       patientUserTask.setUserId(patientSocietyTask.getUserId());
       patientUserTask.setUserName(patientSocietyTask.getUserName());
        String typeIds=patientSocietyTask.getTypeIds();
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
        /*---------------------------------------------------------------------------------------------------*/



       return patientUserTask;
    }
    public int updateByPatientId(PatientSocietyTask patientSocietyTask){
        patientSocietyTask.setDelFlag("0");
        return patientSocietyTaskMapper.updateByPatientId(patientSocietyTask);
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
        PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
        List<PatientSocietyTask> list=patientSocietyTaskMapper.selectPatientSocietyTaskList(patientSocietyTask);
        for (PatientSocietyTask p:list) {
            if(!"3".equals(p.getTaskStatus())){
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
