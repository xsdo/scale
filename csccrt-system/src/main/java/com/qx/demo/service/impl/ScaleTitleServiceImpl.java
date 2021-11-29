package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ScaleTitleMapper;
import com.qx.demo.entity.ScaleTitle;
import com.qx.demo.service.IScaleTitleService;

/**
 * 量题目Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ScaleTitleServiceImpl implements IScaleTitleService 
{
    @Autowired
    private ScaleTitleMapper scaleTitleMapper;

    /**
     * 查询量题目
     * 
     * @param id 量题目ID
     * @return 量题目
     */
    @Override
    public ScaleTitle selectScaleTitleById(Long id)
    {
        return scaleTitleMapper.selectScaleTitleById(id);
    }

    @Override
    public List<ScaleTitle> selectScaleTitleListByScaleId(Long scaleId)
    {
        return scaleTitleMapper.selectScaleTitleListByScaleId(scaleId);
    };


    /**
     * 查询量题目列表
     * 
     * @param scaleTitle 量题目
     * @return 量题目
     */
    @Override
    public List<ScaleTitle> selectScaleTitleList(ScaleTitle scaleTitle)
    {
        return scaleTitleMapper.selectScaleTitleList(scaleTitle);
    }

    /**
     * 新增量题目
     * 
     * @param scaleTitle 量题目
     * @return 结果
     */
    @Override
    public int insertScaleTitle(ScaleTitle scaleTitle)
    {
        return scaleTitleMapper.insertScaleTitle(scaleTitle);
    }

    /**
     * 修改量题目
     * 
     * @param scaleTitle 量题目
     * @return 结果
     */
    @Override
    public int updateScaleTitle(ScaleTitle scaleTitle)
    {
        return scaleTitleMapper.updateScaleTitle(scaleTitle);
    }

    /**
     * 批量删除量题目
     * 
     * @param ids 需要删除的量题目ID
     * @return 结果
     */
    @Override
    public int deleteScaleTitleByIds(Long[] ids)
    {
        return scaleTitleMapper.deleteScaleTitleByIds(ids);
    }

    /**
     * 删除量题目信息
     * 
     * @param id 量题目ID
     * @return 结果
     */
    @Override
    public int deleteScaleTitleById(Long id)
    {
        return scaleTitleMapper.deleteScaleTitleById(id);
    }
}
