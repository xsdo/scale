package com.qx.scale.mapper;

import com.qx.scale.domain.ScaleQuestions;
import java.util.List;

/**
 * 量表题目及分数Mapper接口
 * 
 * @author patient
 * @date 2021-09-30
 */
public interface ScaleQuestionsMapper 
{
    /**
     * 查询量表题目及分数
     * 
     * @param id 量表题目及分数ID
     * @return 量表题目及分数
     */
    public ScaleQuestions selectScaleQuestionsById(Long id);

    /**
     * 查询量表题目及分数列表
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 量表题目及分数集合
     */
    public List<ScaleQuestions> selectScaleQuestionsList(ScaleQuestions scaleQuestions);

    /**
     * 新增量表题目及分数
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 结果
     */
    public int insertScaleQuestions(ScaleQuestions scaleQuestions);

    /**
     * 修改量表题目及分数
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 结果
     */
    public int updateScaleQuestions(ScaleQuestions scaleQuestions);

    /**
     * 删除量表题目及分数
     * 
     * @param id 量表题目及分数ID
     * @return 结果
     */
    public int deleteScaleQuestionsById(Long id);

    /**
     * 批量删除量表题目及分数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScaleQuestionsByIds(Long[] ids);
}
