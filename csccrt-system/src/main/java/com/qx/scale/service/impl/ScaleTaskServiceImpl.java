package com.qx.scale.service.impl;

import java.util.Date;
import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.domain.IpaTaskScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScaleTaskMapper;
import com.qx.scale.domain.ScaleTask;
import com.qx.scale.service.IScaleTaskService;

/**
 * 量表系统任务Service业务层处理
 * 
 * @author patient
 * @date 2021-10-11
 */
@Service
public class ScaleTaskServiceImpl implements IScaleTaskService 
{
    @Autowired
    private ScaleTaskMapper scaleTaskMapper;

    /**
     * 查询量表系统任务
     * 
     * @param taskId 量表系统任务ID
     * @return 量表系统任务
     */
    @Override
    public ScaleTask selectScaleTaskById(Long taskId)
    {
        return scaleTaskMapper.selectScaleTaskById(taskId);
    }

    /**
     * 查询量表系统任务列表
     * 
     * @param scaleTask 量表系统任务
     * @return 量表系统任务
     */
    @Override
    public List<ScaleTask> selectScaleTaskList(ScaleTask scaleTask)
    {
        return scaleTaskMapper.selectScaleTaskList(scaleTask);
    }

    @Override
    public List<ScaleTask> getTask(String workstation){
        ScaleTask scaleTask =new ScaleTask();
        scaleTask.setWorkstation(workstation);
        List<ScaleTask> task = scaleTaskMapper.selectScaleTaskLists(scaleTask);
        return task;
    }

    @Override
    public int updateTaskStatus(ScaleTask scaleTask) {
        scaleTask.setDelFlag("1");
        scaleTask.setTaskStatus("3");
        scaleTask.setUpdateTime(new Date());
        return scaleTaskMapper.updateScaleTask(scaleTask);
    }
    /**
     * 根据PatientID修改任务
     * @param scaleTask
     * @return
     */
    @Override
    public int updateByPatientId(ScaleTask scaleTask) {
        scaleTask.setDelFlag("0");
        return scaleTaskMapper.updateScaleTask(scaleTask);

    }
    @Override
    public Boolean getWebpackVersion(String workstation, String patientId) {
        ScaleTask task =new ScaleTask();
        //获取所有任务，判断未完成的任务中，如果该工作站存在并且该条记录患者不同于请求患者，则返回true表示已被占用
        List<ScaleTask>list=scaleTaskMapper.selectScaleTaskList(task);
        for (ScaleTask p:list) {
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

    /**
     * 新增量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    @Override
    public int insertScaleTask(ScaleTask scaleTask)
    {
        scaleTask.setCreateTime(DateUtils.getNowDate());
        return scaleTaskMapper.insertScaleTask(scaleTask);
    }

    @Override
    public int insertScaleTaskByScaleId(ScaleTask scaleTask)
    {
        ScaleTask scaleTask1 =new ScaleTask();
        scaleTask1.setCreateTime(new Date());
        scaleTask1.setUpdateTime(new Date());
        scaleTask1.setTaskStatus("1");
        scaleTask1.setDelFlag("0");
        scaleTask1.setTypeids("1");
        scaleTask1.setCreateBy(scaleTask.getCreateBy());
        scaleTask1.setUpdateBy(scaleTask.getUpdateBy());
        if (scaleTask!=null&&!scaleTask1.equals("")){
            scaleTask1.setUserId(scaleTask.getUserId());
            scaleTask1.setUserName(scaleTask.getUserName());
            scaleTask1.setPatientId(scaleTask.getPatientId());
            scaleTask1.setPatientName(scaleTask.getPatientName());
            scaleTask1.setWorkstation(scaleTask.getWorkstation());
            scaleTask1.setTestCoding(scaleTask.getTestCoding());
            String[] scaleIds=scaleTask.getScaleId().split(",");
            for (String scaleid : scaleIds){
                scaleTask1.setScaleId(scaleid);
                scaleTaskMapper.insertScaleTask(scaleTask1);
            }
        }
        return 1;
    }
    /**
     * 修改量表系统任务
     * 
     * @param scaleTask 量表系统任务
     * @return 结果
     */
    @Override
    public int updateScaleTask(ScaleTask scaleTask)
    {
        scaleTask.setUpdateTime(DateUtils.getNowDate());
        return scaleTaskMapper.updateScaleTask(scaleTask);
    }

    /**
     * 批量删除量表系统任务
     * 
     * @param taskIds 需要删除的量表系统任务ID
     * @return 结果
     */
    @Override
    public int deleteScaleTaskByIds(Long[] taskIds)
    {
        return scaleTaskMapper.deleteScaleTaskByIds(taskIds);
    }

    /**
     * 删除量表系统任务信息
     * 
     * @param taskId 量表系统任务ID
     * @return 结果
     */
    @Override
    public int deleteScaleTaskById(Long taskId)
    {
        return scaleTaskMapper.deleteScaleTaskById(taskId);
    }
}
