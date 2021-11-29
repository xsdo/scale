package com.qx.ipa.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.mapper.IpaTaskMapper;
import com.qx.patient.domain.PatientSocietyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaPatientUserMapper;
import com.qx.ipa.domain.IpaPatientUser;
import com.qx.ipa.service.IIpaPatientUserService;

import javax.annotation.Resource;

/**
 * 心身调节系统患者Service业务层处理
 * 
 * @author q
 * @date 2021-07-02
 */
@Service
public class IpaPatientUserServiceImpl implements IIpaPatientUserService 
{
    @Resource
    private IpaPatientUserMapper ipaPatientUserMapper;

    @Autowired
    private IpaTaskMapper ipaTaskMapper;
    /**
     * 查询心身调节系统患者
     * 
     * @param patientId 心身调节系统患者ID
     * @return 心身调节系统患者
     */
    @Override
    public IpaPatientUser selectIpaPatientUserById(Long patientId)
    {
        return ipaPatientUserMapper.selectIpaPatientUserById(patientId);
    }

    /**
     * 查询心身调节系统患者列表
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 心身调节系统患者
     */
    @Override
    public List<IpaPatientUser> selectIpaPatientUserList(IpaPatientUser ipaPatientUser)
    {
        List<IpaPatientUser> list = ipaPatientUserMapper.selectIpaPatientUserList(ipaPatientUser);
        if(ipaPatientUser.getFlag()==null ||"".equals(ipaPatientUser.getFlag())){
            return list;
        }else{
            for (IpaPatientUser p:list) {
                IpaTask task = new IpaTask();
                task.setPatientId(p.getPatientId());
                task.setDelFlag("0");
                task.setTypeFlag(ipaPatientUser.getFlag());
                List<IpaTask> ipaTasks = ipaTaskMapper.selectIpaTaskList(task);
                if (ipaTasks.size()==1){
                    p.setTaskStatus(ipaTasks.get(0).getTaskStatus());
                }
            }
        }

        return list;
    }

    /**
     * 新增心身调节系统患者
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 结果
     */
    @Override
    public int insertIpaPatientUser(IpaPatientUser ipaPatientUser)
    {
        ipaPatientUser.setCreateTime(DateUtils.getNowDate());
        return ipaPatientUserMapper.insertIpaPatientUser(ipaPatientUser);
    }

    /**
     * 修改心身调节系统患者
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 结果
     */
    @Override
    public int updateIpaPatientUser(IpaPatientUser ipaPatientUser)
    {
        ipaPatientUser.setUpdateTime(DateUtils.getNowDate());
        return ipaPatientUserMapper.updateIpaPatientUser(ipaPatientUser);
    }

    /**
     * 批量删除心身调节系统患者
     * 
     * @param patientIds 需要删除的心身调节系统患者ID
     * @return 结果
     */
    @Override
    public int deleteIpaPatientUserByIds(Long[] patientIds)
    {
        return ipaPatientUserMapper.deleteIpaPatientUserByIds(patientIds);
    }

    /**
     * 删除心身调节系统患者信息
     * 
     * @param patientId 心身调节系统患者ID
     * @return 结果
     */
    @Override
    public int deleteIpaPatientUserById(Long patientId)
    {
        return ipaPatientUserMapper.deleteIpaPatientUserById(patientId);
    }

    @Override
    public int delAllTask(Long patientId) {

        int sum=1;
        IpaTask ipaTask = new IpaTask();
        ipaTask.setPatientId(patientId);
        List<IpaTask> ipaTasks = ipaTaskMapper.selectIpaTaskLists(ipaTask);
        if (ipaTasks.size()>0){
            for (IpaTask task : ipaTasks) {
                sum+=ipaTaskMapper.deleteIpaTaskById(task.getTaskId());
            }
        }
        return sum;
    }
}
