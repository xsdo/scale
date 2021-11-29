package com.qx.patient.service;

import com.qx.patient.domain.StatisticsTable3;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计3Service接口
 * 
 * @author patient
 * @date 2020-09-02
 */
public interface IStatisticsTable3Service 
{
    /**
     * 查询统计3
     * 
     * @param id 统计3ID
     * @return 统计3
     */
    public StatisticsTable3 selectStatisticsTable3ById(Long id);

    /**
     * 查询统计3列表
     * 
     * @param statisticsTable3 统计3
     * @return 统计3集合
     */
    public StatisticsTable3 selectStatisticsTable3List(StatisticsTable3 statisticsTable3);

    StatisticsTable3 selectStatisticsTable3List1(StatisticsTable3 statisticsTable3);

    /**
     * 新增统计3
     * 
     * @param statisticsTable3 统计3
     * @return 结果
     */
    public int insertStatisticsTable3(StatisticsTable3 statisticsTable3);

    /**
     * 修改统计3
     * 
     * @param statisticsTable3 统计3
     * @return 结果
     */
    public int updateStatisticsTable3(StatisticsTable3 statisticsTable3);

    /**
     * 批量删除统计3
     * 
     * @param ids 需要删除的统计3ID
     * @return 结果
     */
    public int deleteStatisticsTable3ByIds(Long[] ids);

    /**
     * 删除统计3信息
     * 
     * @param id 统计3ID
     * @return 结果
     */
    public int deleteStatisticsTable3ById(Long id);


}
