package com.qx.peri.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaPatientUser;
import com.qx.ipa.domain.IpaTask;
import com.qx.peri.domain.PeriTask;
import com.qx.peri.mapper.PeriTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.peri.mapper.PeriPatientUserMapper;
import com.qx.peri.domain.PeriPatientUser;
import com.qx.peri.service.IPeriPatientUserService;

/**
 * 围手术期系统患者Service业务层处理
 * 
 * @author Meng
 * @date 2021-07-13
 */
@Service
public class PeriPatientUserServiceImpl implements IPeriPatientUserService 
{
    @Autowired
    private PeriPatientUserMapper periPatientUserMapper;

    @Autowired
    private PeriTaskMapper periTaskMapper;

    /**
     * 查询围手术期系统患者
     * 
     * @param patientId 围手术期系统患者ID
     * @return 围手术期系统患者
     */
    @Override
    public PeriPatientUser selectPeriPatientUserById(Long patientId)
    {
        return periPatientUserMapper.selectPeriPatientUserById(patientId);
    }

    /**
     * 查询围手术期系统患者列表
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 围手术期系统患者
     */
    @Override
    public List<PeriPatientUser> selectPeriPatientUserList(PeriPatientUser periPatientUser)
    {
        List<PeriPatientUser> list = periPatientUserMapper.selectPeriPatientUserList(periPatientUser);
        if(periPatientUser.getFlag()==null ||"".equals(periPatientUser.getFlag())){
            return list;
        }else{
            for (PeriPatientUser p:list) {
                PeriTask task = new PeriTask();
                task.setPatientId(p.getPatientId());
                task.setDelFlag("0");
                List<PeriTask> tasks = periTaskMapper.selectPeriTaskList(task);
                if (tasks.size()==1){
                    p.setTaskStatus(tasks.get(0).getTaskStatus());
                }
            }
        }
        return list;
    }

    /**
     * 新增围手术期系统患者
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 结果
     */
    @Override
    public int insertPeriPatientUser(PeriPatientUser periPatientUser)
    {
        periPatientUser.setCreateTime(DateUtils.getNowDate());
        return periPatientUserMapper.insertPeriPatientUser(periPatientUser);
    }

    /**
     * 修改围手术期系统患者
     * 
     * @param periPatientUser 围手术期系统患者
     * @return 结果
     */
    @Override
    public int updatePeriPatientUser(PeriPatientUser periPatientUser)
    {
        periPatientUser.setUpdateTime(DateUtils.getNowDate());
        return periPatientUserMapper.updatePeriPatientUser(periPatientUser);
    }

    /**
     * 批量删除围手术期系统患者
     * 
     * @param patientIds 需要删除的围手术期系统患者ID
     * @return 结果
     */
    @Override
    public int deletePeriPatientUserByIds(Long[] patientIds)
    {
        return periPatientUserMapper.deletePeriPatientUserByIds(patientIds);
    }

    /**
     * 删除围手术期系统患者信息
     * 
     * @param patientId 围手术期系统患者ID
     * @return 结果
     */
    @Override
    public int deletePeriPatientUserById(Long patientId)
    {
        return periPatientUserMapper.deletePeriPatientUserById(patientId);
    }

    @Override
    public int delAllTask(Long patientId) {
        int sum=1;
        PeriTask perTask = new PeriTask();
        perTask.setPatientId(patientId);
        List<PeriTask> perTasks = periTaskMapper.selectPeriTaskList(perTask);
        if (perTasks.size()>0){
            for (PeriTask task : perTasks) {
                if(!"3".equals(task.getTaskStatus())) {
                    sum += periTaskMapper.deletePeriTaskById(task.getTaskId());
                }
            }
        }
        return sum;
    }
}
