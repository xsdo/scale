package com.qx.scale.service;

import com.qx.scale.domain.ScaleScore;
import java.util.List;

/**
 * 量表分数Service接口
 * 
 * @author patient
 * @date 2021-10-11
 */
public interface IScaleScoreService 
{
    /**
     * 查询量表分数
     * 
     * @param id 量表分数ID
     * @return 量表分数
     */
    public ScaleScore selectScaleScoreById(Long id);

    /**
     * 查询量表分数列表
     * 
     * @param scaleScore 量表分数
     * @return 量表分数集合
     */
    public List<ScaleScore> selectScaleScoreList(ScaleScore scaleScore);

    /**
     * 新增量表分数
     * 
     * @param scaleScore 量表分数
     * @return 结果
     */
    public int insertScaleScore(ScaleScore scaleScore);

    /**
     * 修改量表分数
     * 
     * @param scaleScore 量表分数
     * @return 结果
     */
    public int updateScaleScore(ScaleScore scaleScore);

    /**
     * 批量删除量表分数
     * 
     * @param ids 需要删除的量表分数ID
     * @return 结果
     */
    public int deleteScaleScoreByIds(Long[] ids);

    /**
     * 删除量表分数信息
     * 
     * @param id 量表分数ID
     * @return 结果
     */
    public int deleteScaleScoreById(Long id);
}
