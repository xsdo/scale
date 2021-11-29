package com.qx.peri.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.EvaluationType;
import com.qx.patient.mapper.EvaluationTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.peri.mapper.PeriTaskMapper;
import com.qx.peri.domain.PeriTask;
import com.qx.peri.service.IPeriTaskService;

/**
 * 围手术期任务Service业务层处理
 * 
 * @author Meng
 * @date 2021-07-13
 */
@Service
public class PeriTaskServiceImpl implements IPeriTaskService 
{
    @Autowired
    private PeriTaskMapper periTaskMapper;

    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;

    /**
     * 查询围手术期任务
     * 
     * @param taskId 围手术期任务ID
     * @return 围手术期任务
     */
    @Override
    public PeriTask selectPeriTaskById(Long taskId)
    {
        return periTaskMapper.selectPeriTaskById(taskId);
    }

    /**
     * 查询围手术期任务列表
     * 
     * @param periTask 围手术期任务
     * @return 围手术期任务
     */
    @Override
    public List<PeriTask> selectPeriTaskList(PeriTask periTask)
    {
        List<PeriTask> list = periTaskMapper.selectPeriTaskList(periTask);
        //获取任务的名称
        for (PeriTask task:list){
            String typeids = task.getTypeids();
            String[] arr = typeids.split(",");
            String string = "";
            for (String s : arr) {
                EvaluationType evaluationType = evaluationTypeMapper.selectEvaluationTypeById(Long.valueOf(s));
                string += evaluationType.getTypeName() + ",";
            }
            task.setTypeNames(string.substring(0, string.length() - 1));
        }
        return list;
    }

    /**
     * 新增围手术期任务
     * 
     * @param periTask 围手术期任务
     * @return 结果
     */
    @Override
    public int insertPeriTask(PeriTask periTask)
    {
        periTask.setCreateTime(DateUtils.getNowDate());
        periTask.setTaskStatus("0");
        periTask.setDelFlag("0");
        periTask.setTypeFlag("");
        return periTaskMapper.insertPeriTask(periTask);
    }

    /**
     * 修改围手术期任务
     * 
     * @param periTask 围手术期任务
     * @return 结果
     */
    @Override
    public int updatePeriTask(PeriTask periTask)
    {
        periTask.setUpdateTime(DateUtils.getNowDate());
        return periTaskMapper.updatePeriTask(periTask);
    }

    @Override
    public int updateByPatientId(PeriTask periTask) {
        periTask.setDelFlag("0");
        return periTaskMapper.updateByPatientId(periTask);
    }

    /**
     * 批量删除围手术期任务
     * 
     * @param taskIds 需要删除的围手术期任务ID
     * @return 结果
     */
    @Override
    public int deletePeriTaskByIds(Long[] taskIds)
    {
        return periTaskMapper.deletePeriTaskByIds(taskIds);
    }

    /**
     * 删除围手术期任务信息
     * 
     * @param taskId 围手术期任务ID
     * @return 结果
     */
    @Override
    public int deletePeriTaskById(Long taskId)
    {
        return periTaskMapper.deletePeriTaskById(taskId);
    }

    @Override
    public Boolean getWebpackVersion(String workstation, String patientId) {
        PeriTask task=new PeriTask();
        //获取所有任务，判断未完成的任务中，如果该工作站存在并且该条记录患者不同于请求患者，则返回true表示已被占用
        List<PeriTask> list=periTaskMapper.selectPeriTaskList(task);
        for (PeriTask p:list) {
            if(!"3".equals(p.getTaskStatus())){
                if(workstation.equals(p.getWorkstation())){
                    if(Long.parseLong(patientId)!=p.getPatientId()){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public PeriTask selectPeriTaskByworkstation(String workstation) {
        return periTaskMapper.selectPeriTaskByworkstation(workstation);
    }
}
