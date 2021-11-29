package com.qx.demo.mapper;

import com.qx.demo.entity.ScaleTitle;
import java.util.List;

/**
 * 量题目Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ScaleTitleMapper 
{
    /**
     * 查询量题目
     * 
     * @param id 量题目ID
     * @return 量题目
     */
    public ScaleTitle selectScaleTitleById(Long id);

    /*
    *
    * 根据scaleID查题目
    *
    * */




     public List<ScaleTitle> selectScaleTitleListByScaleId(Long scaleId);

    /**
     * 查询量题目列表
     * 
     * @param scaleTitle 量题目
     * @return 量题目集合
     */
    public List<ScaleTitle> selectScaleTitleList(ScaleTitle scaleTitle);

    /**
     * 新增量题目
     * 
     * @param scaleTitle 量题目
     * @return 结果
     */
    public int insertScaleTitle(ScaleTitle scaleTitle);

    /**
     * 修改量题目
     * 
     * @param scaleTitle 量题目
     * @return 结果
     */
    public int updateScaleTitle(ScaleTitle scaleTitle);

    /**
     * 删除量题目
     * 
     * @param id 量题目ID
     * @return 结果
     */
    public int deleteScaleTitleById(Long id);

    /**
     * 批量删除量题目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScaleTitleByIds(Long[] ids);


}
