package com.qx.scale.service;

import com.qx.scale.domain.ScaleQuestions;
import com.qx.scale.domain.vo.ScaleQuestionsVo;

import java.util.List;

/**
 * 量表题目及分数Service接口
 * 
 * @author patient
 * @date 2021-09-30
 */
public interface IScaleQuestionsService 
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

    List<ScaleQuestionsVo> selectScaleQuestionsVoList(List<ScaleQuestions> scaleQuestionsList, Long scaleId);

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
     * 批量删除量表题目及分数
     * 
     * @param ids 需要删除的量表题目及分数ID
     * @return 结果
     */
    public int deleteScaleQuestionsByIds(Long[] ids);

    /**
     * 删除量表题目及分数信息
     * 
     * @param id 量表题目及分数ID
     * @return 结果
     */
    public int deleteScaleQuestionsById(Long id);
}
