package com.qx.eis.mapper;

import com.qx.eis.domain.EisTaskScore;


import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 14:41
 */
public interface EisTaskScareMapper {
    /**
     * 查询eis任务得分保存eis
     *
     * @param id eis任务得分保存ID
     * @return eis任务得分保存
     */
    public EisTaskScore selectEisTaskScoreById(Long id);

  /*  *//**
     * 查询eis任务得分保存列表
     *
     * @param eisTaskScore eis任务得分保存
     * @return eis任务得分保存集合
     *//*
    public List<EisTaskScore> selectEisTaskScoreList(EisTaskScore eisTaskScore);

    *//**
     * 新增eis任务得分保存
     *
     * @param eisTaskScore eis任务得分保存
     * @return 结果
     *//*
    public int insertEisTaskScore(EisTaskScore eisTaskScore);

    *//**
     * 修改eis任务得分保存
     *
     * @param eisTaskScore eis任务得分保存
     * @return 结果
     *//*
    public int updateEisTaskScore(EisTaskScore eisTaskScore);

    *//**
     * 删除eis任务得分保存
     *
     * @param id eis任务得分保存ID
     * @return 结果
     *//*
    public int deleteEisTaskScoreById(Long id);

    *//**
     * 批量删除eis任务得分保存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     *//*
    public int deleteEisTaskScoreByIds(Long[] ids);*/
}
