package com.qx.ipa.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaDomain;
import com.qx.ipa.domain.IpaTaskScore;
import com.qx.ipa.mapper.IpaTaskScoreMapper;
import com.qx.patient.domain.EvaluationType;
import com.qx.patient.domain.PatientBasisTask;
import com.qx.patient.mapper.EvaluationTypeMapper;
import com.qx.patient.service.IEvaluationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaTaskMapper;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.service.IIpaTaskService;

/**
 * 智能化心身调节任务Service业务层处理
 * 
 * @author qx
 * @date 2021-07-05
 */
@Service
public class IpaTaskServiceImpl implements IIpaTaskService 
{
    @Autowired
    private IpaTaskMapper ipaTaskMapper;

    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;

    @Autowired
    private IpaTaskScoreMapper ipaTaskScoreMapper;
    /**
     * 查询智能化心身调节任务
     * 
     * @param taskId 智能化心身调节任务ID
     * @return 智能化心身调节任务
     */
    @Override
    public IpaTask selectIpaTaskById(Long taskId)
    {
        return ipaTaskMapper.selectIpaTaskById(taskId);
    }

    /**
     * 查询智能化心身调节任务列表
     * 
     * @param ipaTask 智能化心身调节任务
     * @return 智能化心身调节任务
     */
    @Override
    public List<IpaTask> selectIpaTaskList(IpaTask ipaTask)
    {

        List<IpaTask> list = ipaTaskMapper.selectIpaTaskList(ipaTask);
        //获取任务的名称
        for (IpaTask task:list){
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
     * 新增智能化心身调节任务
     * 
     * @param ipaTask 智能化心身调节任务
     * @return 结果
     */
    @Override
    public int insertIpaTask(IpaTask ipaTask)
    {
        ipaTask.setCreateTime(DateUtils.getNowDate());
        return ipaTaskMapper.insertIpaTask(ipaTask);
    }

    /**
     * 修改智能化心身调节任务
     * 
     * @param ipaTask 智能化心身调节任务
     * @return 结果
     */
    @Override
    public int updateIpaTask(IpaTask ipaTask)
    {
        ipaTask.setUpdateTime(DateUtils.getNowDate());
        return ipaTaskMapper.updateIpaTask(ipaTask);
    }

    /**
     * 批量删除智能化心身调节任务
     * 
     * @param taskIds 需要删除的智能化心身调节任务ID
     * @return 结果
     */
    @Override
    public int deleteIpaTaskByIds(Long[] taskIds)
    {
        return ipaTaskMapper.deleteIpaTaskByIds(taskIds);
    }

    /**
     * 删除智能化心身调节任务信息
     * 
     * @param taskId 智能化心身调节任务ID
     * @return 结果
     */
    @Override
    public int deleteIpaTaskById(Long taskId)
    {
        return ipaTaskMapper.deleteIpaTaskById(taskId);
    }

    /**
     * 根据PatientID修改任务
     * @param ipaTask
     * @return
     */
    @Override
    public int updateByPatientId(IpaTask ipaTask) {
        ipaTask.setDelFlag("0");
        return ipaTaskMapper.updateByPatientId(ipaTask);

    }

    @Override
    public Boolean getWebpackVersion(String workstation, String patientId) {
        IpaTask task=new IpaTask();
        //获取所有任务，判断未完成的任务中，如果该工作站存在并且该条记录患者不同于请求患者，则返回true表示已被占用
        List<IpaTask> list=ipaTaskMapper.selectIpaTaskList(task);
        for (IpaTask p:list) {
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
    public List<IpaTask> selectIpaTaskByworkstation(String workstation) {
        return ipaTaskMapper.selectIpaTaskByworkstation(workstation);
    }

    /***
     * 患者端任务做完时，改变任务表中完成状态
     * @param ipaTask
     * @return
     */
    @Override
    public int updateIpaTaskStatus(IpaTask ipaTask) {
        String typeFlag = ipaTask.getTypeFlag();
        //新冠时，可直接更新任务表中状态
        if(typeFlag!=null && typeFlag!="" && "0".equals(typeFlag)){
            ipaTask.setTaskStatus("3");
            ipaTask.setDelFlag("1");
            return ipaTaskMapper.updateIpaTask(ipaTask);
        //量表时，需要判断，仅当最后一张量表时，才做更新
        }else if (typeFlag!=null && typeFlag!="" && "1".equals(typeFlag)){
            IpaTaskScore ipaTaskScore = new IpaTaskScore();
            ipaTaskScore.setTaskId(ipaTask.getTaskId());
            List<IpaTaskScore> ipaTaskScores = ipaTaskScoreMapper.selectIpaTaskScoreList(ipaTaskScore);
            //当size为3时，即 做完第三张表时，才做更新
            if (ipaTaskScores.size()==3){
                ipaTask.setTaskStatus("3");
                ipaTask.setDelFlag("1");
                return ipaTaskMapper.updateIpaTask(ipaTask);
            }
        }
        return 0;
    }

}
