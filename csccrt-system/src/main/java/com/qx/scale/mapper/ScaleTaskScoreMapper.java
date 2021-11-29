package com.qx.scale.mapper;

import com.qx.scale.domain.ScaleTaskScore;
import java.util.List;

/**
 * 量表得分保存Mapper接口
 * 
 * @author patient
 * @date 2021-10-11
 */
public interface ScaleTaskScoreMapper 
{
    /**
     * 查询量表得分保存
     * 
     * @param id 量表得分保存ID
     * @return 量表得分保存
     */
    public ScaleTaskScore selectScaleTaskScoreById(Long id);

    /**
     * 查询量表得分保存列表
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 量表得分保存集合
     */
    public List<ScaleTaskScore> selectScaleTaskScoreList(ScaleTaskScore scaleTaskScore);

    /**
     * 新增量表得分保存
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 结果
     */
    public int insertScaleTaskScore(ScaleTaskScore scaleTaskScore);

    /**
     * 修改量表得分保存
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 结果
     */
    public int updateScaleTaskScore(ScaleTaskScore scaleTaskScore);

    /**
     * 删除量表得分保存
     * 
     * @param id 量表得分保存ID
     * @return 结果
     */
    public int deleteScaleTaskScoreById(Long id);

    /**
     * 批量删除量表得分保存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScaleTaskScoreByIds(Long[] ids);
}
