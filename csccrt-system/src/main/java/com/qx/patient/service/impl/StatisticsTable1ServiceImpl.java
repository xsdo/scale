package com.qx.patient.service.impl;

import java.text.ParseException;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qx.common.utils.DateAgeUtil;
import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.PatientUser;
import com.qx.patient.mapper.PatientUserMapper;
import com.qx.patient.service.IPatientUserService;
import com.qx.scale.domain.ScalePatient;
import com.qx.scale.mapper.ScalePatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.StatisticsTable1Mapper;
import com.qx.patient.domain.StatisticsTable1;
import com.qx.patient.service.IStatisticsTable1Service;

/**
 * 统计1Service业务层处理
 * 
 * @author patient
 * @date 2020-09-02
 */
@Service
public class StatisticsTable1ServiceImpl implements IStatisticsTable1Service 
{
    @Autowired
    private StatisticsTable1Mapper statisticsTable1Mapper;
    @Autowired
    private PatientUserMapper patientUserMapper;
    @Autowired
    private ScalePatientMapper scalePatientMapper;

    /**
     * 查询统计1
     * 
     * @param id 统计1ID
     * @return 统计1
     */
    @Override
    public StatisticsTable1 selectStatisticsTable1ById(Long id)
    {
        return statisticsTable1Mapper.selectStatisticsTable1ById(id);
    }

    /**
     * 查询统计1列表
     * 
     * @param statisticsTable1 统计1
     * @return 统计1
     */
    @Override
    public StatisticsTable1 selectStatisticsTable1List(StatisticsTable1 statisticsTable1)
    {
        StatisticsTable1 statisticsTable11=statisticsTable1Mapper.selectStatisticsTable1ByYear(statisticsTable1.getYear());
        if(statisticsTable11==null){
            StatisticsTable1 statisticsTable=new StatisticsTable1();
            statisticsTable.setYear(statisticsTable1.getYear());
            statisticsTable.setRegisteredCount(0L);
            statisticsTable.setCompleteCount(0L);
            statisticsTable.setCreateTime(DateUtils.getNowDate());
            statisticsTable1Mapper.insertStatisticsTable1(statisticsTable);
        }
        StatisticsTable1 statisticsTableA=statisticsTable1Mapper.selectStatisticsTable1ByYear(statisticsTable1.getYear());
        List<PatientUser> list=patientUserMapper.selectAllByCreateTime(statisticsTable1.getYear()+"-01-01",(statisticsTable1.getYear()+1)+"-01-01","");
        for (PatientUser p:list) {
            if("0".equals(p.getSex())){
               statisticsTableA.setManCount(statisticsTableA.getManCount()+1);
            }
            if("1".equals(p.getSex())){
                statisticsTableA.setWomanCount(statisticsTableA.getWomanCount()+1);
            }
            int age = 0;
            try {
                if(p.getBirthday()!=null) {
                    age = DateAgeUtil.getAgeByBirth(p.getBirthday());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(age<18){
                statisticsTableA.setLess18Count(statisticsTableA.getLess18Count()+1);
            }else if(18<=age && age<=25){
                statisticsTableA.setTo1825Count(statisticsTableA.getTo1825Count()+1);
            }else if(26<=age && age<=35){
                statisticsTableA.setTo2635Count(statisticsTableA.getTo2635Count()+1);
            }else if(36<=age && age<=45){
                  statisticsTableA.setTo3645Count(statisticsTableA.getTo3645Count()+1);
            }else if(46<=age && age<=55){
                statisticsTableA.setTo4655Count(statisticsTableA.getTo4655Count()+1);
            }else if(56<=age && age<=65){
                statisticsTableA.setTo5665Count(statisticsTableA.getTo5665Count()+1);
            }else{
                statisticsTableA.setMore65Count(statisticsTableA.getMore65Count()+1);
            }
        }
        return statisticsTableA;
    }

    @Override
    public StatisticsTable1 selectStatisticsTable1List1(StatisticsTable1 statisticsTable1)
    {
        StatisticsTable1 statisticsTable11=statisticsTable1Mapper.selectStatisticsTable1ByYear(statisticsTable1.getYear());
        if(statisticsTable11==null){
            StatisticsTable1 statisticsTable=new StatisticsTable1();
            statisticsTable.setYear(statisticsTable1.getYear());
            statisticsTable.setRegisteredCount(0L);
            statisticsTable.setCompleteCount(0L);
            statisticsTable.setCreateTime(DateUtils.getNowDate());
            statisticsTable1Mapper.insertStatisticsTable1(statisticsTable);
        }
        StatisticsTable1 statisticsTableA=statisticsTable1Mapper.selectStatisticsTable1ByYear(statisticsTable1.getYear());
        List<ScalePatient> list=scalePatientMapper.selectAllByCreateTime((statisticsTable1.getYear()+"-01-01"),(statisticsTable1.getYear()+1)+"-01-01","");
        for (ScalePatient p:list) {
            if("0".equals(p.getSex())){
                statisticsTableA.setManCount(statisticsTableA.getManCount()+1);
            }
            if("1".equals(p.getSex())){
                statisticsTableA.setWomanCount(statisticsTableA.getWomanCount()+1);
            }
            int age = 0;
            try {
                if(p.getBirthday()!=null) {
                    age = DateAgeUtil.getAgeByBirth(p.getBirthday());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(age<18){
                statisticsTableA.setLess18Count(statisticsTableA.getLess18Count()+1);
            }else if(18<=age && age<=25){
                statisticsTableA.setTo1825Count(statisticsTableA.getTo1825Count()+1);
            }else if(26<=age && age<=35){
                statisticsTableA.setTo2635Count(statisticsTableA.getTo2635Count()+1);
            }else if(36<=age && age<=45){
                statisticsTableA.setTo3645Count(statisticsTableA.getTo3645Count()+1);
            }else if(46<=age && age<=55){
                statisticsTableA.setTo4655Count(statisticsTableA.getTo4655Count()+1);
            }else if(56<=age && age<=65){
                statisticsTableA.setTo5665Count(statisticsTableA.getTo5665Count()+1);
            }else{
                statisticsTableA.setMore65Count(statisticsTableA.getMore65Count()+1);
            }
        }
        return statisticsTableA;
    }
    /**
     * 新增统计1
     * 
     * @param statisticsTable1 统计1
     * @return 结果
     */
    @Override
    public int insertStatisticsTable1(StatisticsTable1 statisticsTable1)
    {
        statisticsTable1.setCreateTime(DateUtils.getNowDate());
        return statisticsTable1Mapper.insertStatisticsTable1(statisticsTable1);
    }

    /**
     * 修改统计1
     * 
     * @param statisticsTable1 统计1
     * @return 结果
     */
    @Override
    public int updateStatisticsTable1(StatisticsTable1 statisticsTable1)
    {
        return statisticsTable1Mapper.updateStatisticsTable1(statisticsTable1);
    }

    /**
     * 批量删除统计1
     * 
     * @param ids 需要删除的统计1ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable1ByIds(Long[] ids)
    {
        return statisticsTable1Mapper.deleteStatisticsTable1ByIds(ids);
    }

    /**
     * 删除统计1信息
     * 
     * @param id 统计1ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable1ById(Long id)
    {
        return statisticsTable1Mapper.deleteStatisticsTable1ById(id);
    }
}
