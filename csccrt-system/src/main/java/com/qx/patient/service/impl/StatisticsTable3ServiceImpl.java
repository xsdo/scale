package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.PatientUser;
import com.qx.patient.domain.StatisticsTable2;
import com.qx.patient.mapper.PatientUserMapper;
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.mapper.ScalePatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.StatisticsTable3Mapper;
import com.qx.patient.domain.StatisticsTable3;
import com.qx.patient.service.IStatisticsTable3Service;

/**
 * 统计3Service业务层处理
 * 
 * @author patient
 * @date 2020-09-02
 */
@Service
public class StatisticsTable3ServiceImpl implements IStatisticsTable3Service 
{
    @Autowired
    private StatisticsTable3Mapper statisticsTable3Mapper;
    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private ScalePatientMapper scalePatientMapper;

    /**
     * 查询统计3
     * 
     * @param id 统计3ID
     * @return 统计3
     */
    @Override
    public StatisticsTable3 selectStatisticsTable3ById(Long id)
    {
        return statisticsTable3Mapper.selectStatisticsTable3ById(id);
    }

    /**
     * 查询统计3列表
     * 
     * @param statisticsTable3 统计3
     * @return 统计3
     */
    @Override
    public StatisticsTable3 selectStatisticsTable3List(StatisticsTable3 statisticsTable3)
    {
        StatisticsTable3 statisticsTable33=new StatisticsTable3();
        List<PatientUser> list=patientUserMapper.selectAllByCreateTime(statisticsTable3.getYear()+"-01-01",(statisticsTable3.getYear()+1)+"-01-01","");
        for (PatientUser p:list) {
            if("焦虑症".equals(p.getDiagnosis())){
                statisticsTable33.setJlzCount(statisticsTable33.getJlzCount()+1L);
            }else if("抑郁症".equals(p.getDiagnosis())){
                statisticsTable33.setYyzCount(statisticsTable33.getYyzCount()+1L);
            }else if("强迫症".equals(p.getDiagnosis())){
                statisticsTable33.setQpzCount(statisticsTable33.getQpzCount()+1L);
            }else if("精神分裂症".equals(p.getDiagnosis())){
                statisticsTable33.setJsflzCount(statisticsTable33.getJsflzCount()+1L);
            }else if("失眠障碍".equals(p.getDiagnosis())){
                statisticsTable33.setSmzaCount(statisticsTable33.getSmzaCount()+1L);
            }else if("双向情感障碍".equals(p.getDiagnosis())){
                statisticsTable33.setSxqgzaCount(statisticsTable33.getSxqgzaCount()+1L);
            }else{
                statisticsTable33.setQtCount(statisticsTable33.getQtCount()+1L);
            }
        }
        return statisticsTable33;
    }

    @Override
    public StatisticsTable3 selectStatisticsTable3List1(StatisticsTable3 statisticsTable3)
    {
        StatisticsTable3 statisticsTable33=new StatisticsTable3();
        List<ScalePatient> list=scalePatientMapper.selectAllByCreateTime((statisticsTable3.getYear()+"-01-01"),(statisticsTable3.getYear()+1)+"-01-01","");
        for (ScalePatient p:list) {
            if("焦虑症".equals(p.getDiagnosis())){
                statisticsTable33.setJlzCount(statisticsTable33.getJlzCount()+1L);
            }else if("抑郁症".equals(p.getDiagnosis())){
                statisticsTable33.setYyzCount(statisticsTable33.getYyzCount()+1L);
            }else if("强迫症".equals(p.getDiagnosis())){
                statisticsTable33.setQpzCount(statisticsTable33.getQpzCount()+1L);
            }else if("精神分裂症".equals(p.getDiagnosis())){
                statisticsTable33.setJsflzCount(statisticsTable33.getJsflzCount()+1L);
            }else if("失眠障碍".equals(p.getDiagnosis())){
                statisticsTable33.setSmzaCount(statisticsTable33.getSmzaCount()+1L);
            }else if("双向情感障碍".equals(p.getDiagnosis())){
                statisticsTable33.setSxqgzaCount(statisticsTable33.getSxqgzaCount()+1L);
            }else{
                statisticsTable33.setQtCount(statisticsTable33.getQtCount()+1L);
            }
        }
        return statisticsTable33;
    }

    /**
     * 新增统计3
     * 
     * @param statisticsTable3 统计3
     * @return 结果
     */
    @Override
    public int insertStatisticsTable3(StatisticsTable3 statisticsTable3)
    {
        statisticsTable3.setCreateTime(DateUtils.getNowDate());
        return statisticsTable3Mapper.insertStatisticsTable3(statisticsTable3);
    }

    /**
     * 修改统计3
     * 
     * @param statisticsTable3 统计3
     * @return 结果
     */
    @Override
    public int updateStatisticsTable3(StatisticsTable3 statisticsTable3)
    {
        return statisticsTable3Mapper.updateStatisticsTable3(statisticsTable3);
    }

    /**
     * 批量删除统计3
     * 
     * @param ids 需要删除的统计3ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable3ByIds(Long[] ids)
    {
        return statisticsTable3Mapper.deleteStatisticsTable3ByIds(ids);
    }

    /**
     * 删除统计3信息
     * 
     * @param id 统计3ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable3ById(Long id)
    {
        return statisticsTable3Mapper.deleteStatisticsTable3ById(id);
    }
}
