package com.qx.patient.mapper;

import com.qx.patient.domain.StatisticsTable2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计2Mapper接口
 * 
 * @author patient
 * @date 2020-09-02
 */
public interface StatisticsTable2Mapper 
{
    /**
     * 查询统计2
     * 
     * @param id 统计2ID
     * @return 统计2
     */
    public StatisticsTable2 selectStatisticsTable2ById(Long id);

    /**
     * 查询统计2列表
     * 
     * @param statisticsTable2 统计2
     * @return 统计2集合
     */
    public List<StatisticsTable2> selectStatisticsTable2List(StatisticsTable2 statisticsTable2);

    /**
     * 新增统计2
     * 
     * @param statisticsTable2 统计2
     * @return 结果
     */
    public int insertStatisticsTable2(StatisticsTable2 statisticsTable2);

    /**
     * 修改统计2
     * 
     * @param statisticsTable2 统计2
     * @return 结果
     */
    public int updateStatisticsTable2(StatisticsTable2 statisticsTable2);

    /**
     * 删除统计2
     * 
     * @param id 统计2ID
     * @return 结果
     */
    public int deleteStatisticsTable2ById(Long id);

    /**
     * 批量删除统计2
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStatisticsTable2ByIds(Long[] ids);

    /**
     * 查询统计2
     *
     * @param year 年限
     * @return 统计2
     */
    public StatisticsTable2 selectStatisticsTable2ByYear(@Param("year") Long year);
}
