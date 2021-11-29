package com.qx.patient.service.impl;

import java.util.List;
import com.qx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.StatisticsTable2Mapper;
import com.qx.patient.domain.StatisticsTable2;
import com.qx.patient.service.IStatisticsTable2Service;

/**
 * 统计2Service业务层处理
 * 
 * @author patient
 * @date 2020-09-02
 */
@Service
public class StatisticsTable2ServiceImpl implements IStatisticsTable2Service 
{
    @Autowired
    private StatisticsTable2Mapper statisticsTable2Mapper;

    /**
     * 查询统计2
     * 
     * @param id 统计2ID
     * @return 统计2
     */
    @Override
    public StatisticsTable2 selectStatisticsTable2ById(Long id)
    {
        return statisticsTable2Mapper.selectStatisticsTable2ById(id);
    }

    /**
     * 查询统计2列表
     * 
     * @param statisticsTable2 统计2
     * @return 统计2
     */
    @Override
    public List<StatisticsTable2> selectStatisticsTable2List(StatisticsTable2 statisticsTable2)
    {
        StatisticsTable2 statisticsTable22=statisticsTable2Mapper.selectStatisticsTable2ByYear(statisticsTable2.getYear());
        if(statisticsTable22==null){
            StatisticsTable2 statisticsTable=new StatisticsTable2();
            statisticsTable.setYear(statisticsTable2.getYear());
            statisticsTable.setJanuaryCount(0L);
            statisticsTable.setFebruaryCount(0L);
            statisticsTable.setMarch(0L);
            statisticsTable.setApril(0L);
            statisticsTable.setMay(0L);
            statisticsTable.setJune(0L);
            statisticsTable.setJuly(0L);
            statisticsTable.setAugust(0L);
            statisticsTable.setSeptember(0L);
            statisticsTable.setOctober(0L);
            statisticsTable.setNovember(0L);
            statisticsTable.setDecember(0L);
            statisticsTable.setCreateTime(DateUtils.getNowDate());
            statisticsTable2Mapper.insertStatisticsTable2(statisticsTable);
        }
        return statisticsTable2Mapper.selectStatisticsTable2List(statisticsTable2);
    }

    /**
     * 新增统计2
     * 
     * @param statisticsTable2 统计2
     * @return 结果
     */
    @Override
    public int insertStatisticsTable2(StatisticsTable2 statisticsTable2)
    {
        statisticsTable2.setCreateTime(DateUtils.getNowDate());
        return statisticsTable2Mapper.insertStatisticsTable2(statisticsTable2);
    }

    /**
     * 修改统计2
     * 
     * @param statisticsTable2 统计2
     * @return 结果
     */
    @Override
    public int updateStatisticsTable2(StatisticsTable2 statisticsTable2)
    {
        return statisticsTable2Mapper.updateStatisticsTable2(statisticsTable2);
    }

    /**
     * 批量删除统计2
     * 
     * @param ids 需要删除的统计2ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable2ByIds(Long[] ids)
    {
        return statisticsTable2Mapper.deleteStatisticsTable2ByIds(ids);
    }

    /**
     * 删除统计2信息
     * 
     * @param id 统计2ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsTable2ById(Long id)
    {
        return statisticsTable2Mapper.deleteStatisticsTable2ById(id);
    }
}
