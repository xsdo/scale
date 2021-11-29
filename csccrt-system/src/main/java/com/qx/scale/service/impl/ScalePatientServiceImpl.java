package com.qx.scale.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaTask;
import com.qx.scale.domain.ScaleTask;
import com.qx.scale.mapper.ScaleTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScalePatientMapper;
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.service.IScalePatientService;

/**
 * 量表系统患者Service业务层处理
 * 
 * @author patient
 * @date 2021-10-11
 */
@Service
public class ScalePatientServiceImpl implements IScalePatientService 
{
    @Autowired
    private ScalePatientMapper scalePatientMapper;

    @Autowired
    private ScaleTaskMapper scaleTaskMapper;

    /**
     * 查询量表系统患者
     * 
     * @param patientId 量表系统患者ID
     * @return 量表系统患者
     */
    @Override
    public ScalePatient selectScalePatientById(Long patientId)
    {
        return scalePatientMapper.selectScalePatientById(patientId);
    }

    /**
     * 查询量表系统患者列表
     * 
     * @param scalePatient 量表系统患者
     * @return 量表系统患者
     */
    @Override
    public List<ScalePatient> selectScalePatientList(ScalePatient scalePatient)
    {
        return scalePatientMapper.selectScalePatientList(scalePatient);
    }

    /**
     * 新增量表系统患者
     * 
     * @param scalePatient 量表系统患者
     * @return 结果
     */
    @Override
    public int insertScalePatient(ScalePatient scalePatient)
    {
        scalePatient.setCreateTime(DateUtils.getNowDate());
        return scalePatientMapper.insertScalePatient(scalePatient);
    }

    /**
     * 修改量表系统患者
     * 
     * @param scalePatient 量表系统患者
     * @return 结果
     */
    @Override
    public int updateScalePatient(ScalePatient scalePatient)
    {
        scalePatient.setUpdateTime(DateUtils.getNowDate());
        return scalePatientMapper.updateScalePatient(scalePatient);
    }

    /**
     * 批量删除量表系统患者
     * 
     * @param patientIds 需要删除的量表系统患者ID
     * @return 结果
     */
    @Override
    public int deleteScalePatientByIds(Long[] patientIds)
    {
        return scalePatientMapper.deleteScalePatientByIds(patientIds);
    }

    @Override
    public int delAllTask(Long patientId) {

        int sum=1;
        ScaleTask scaleTask =new ScaleTask();
        scaleTask.setPatientId(patientId);
        List<ScaleTask>scaleTasks=scaleTaskMapper.selectScaleTaskLists(scaleTask);
        if (scaleTasks.size()>0){
            for (ScaleTask task:scaleTasks){
                sum+=scaleTaskMapper.deleteScaleTaskById(task.getTaskId());
            }
        }
        return sum;
    }

    /**
     * 删除量表系统患者信息
     * 
     * @param patientId 量表系统患者ID
     * @return 结果
     */
    @Override
    public int deleteScalePatientById(Long patientId)
    {
        return scalePatientMapper.deleteScalePatientById(patientId);
    }
}
