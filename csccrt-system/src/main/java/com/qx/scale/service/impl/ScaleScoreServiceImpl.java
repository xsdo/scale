package com.qx.scale.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScaleScoreMapper;
import com.qx.scale.domain.ScaleScore;
import com.qx.scale.service.IScaleScoreService;

/**
 * 量表分数Service业务层处理
 * 
 * @author patient
 * @date 2021-10-11
 */
@Service
public class ScaleScoreServiceImpl implements IScaleScoreService 
{
    @Autowired
    private ScaleScoreMapper scaleScoreMapper;

    /**
     * 查询量表分数
     * 
     * @param id 量表分数ID
     * @return 量表分数
     */
    @Override
    public ScaleScore selectScaleScoreById(Long id)
    {
        return scaleScoreMapper.selectScaleScoreById(id);
    }

    /**
     * 查询量表分数列表
     * 
     * @param scaleScore 量表分数
     * @return 量表分数
     */
    @Override
    public List<ScaleScore> selectScaleScoreList(ScaleScore scaleScore)
    {
        return scaleScoreMapper.selectScaleScoreList(scaleScore);
    }

    /**
     * 新增量表分数
     * 
     * @param scaleScore 量表分数
     * @return 结果
     */
    @Override
    public int insertScaleScore(ScaleScore scaleScore)
    {
        return scaleScoreMapper.insertScaleScore(scaleScore);
    }

    /**
     * 修改量表分数
     * 
     * @param scaleScore 量表分数
     * @return 结果
     */
    @Override
    public int updateScaleScore(ScaleScore scaleScore)
    {
        return scaleScoreMapper.updateScaleScore(scaleScore);
    }

    /**
     * 批量删除量表分数
     * 
     * @param ids 需要删除的量表分数ID
     * @return 结果
     */
    @Override
    public int deleteScaleScoreByIds(Long[] ids)
    {
        return scaleScoreMapper.deleteScaleScoreByIds(ids);
    }

    /**
     * 删除量表分数信息
     * 
     * @param id 量表分数ID
     * @return 结果
     */
    @Override
    public int deleteScaleScoreById(Long id)
    {
        return scaleScoreMapper.deleteScaleScoreById(id);
    }
}
