package com.qx.ipa.mapper;

import com.qx.ipa.domain.IpaScaleQuestions;
import java.util.List;

/**
 * 身心调节-量-所有题目Mapper接口
 * 
 * @author meng
 * @date 2021-07-05
 */
public interface IpaScaleQuestionsMapper 
{
    /**
     * 查询身心调节-量-所有题目
     * 
     * @param id 身心调节-量-所有题目ID
     * @return 身心调节-量-所有题目
     */
    public IpaScaleQuestions selectIpaScaleQuestionsById(Long id);

    /**
     * 查询身心调节-量-所有题目列表
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 身心调节-量-所有题目集合
     */
    public List<IpaScaleQuestions> selectIpaScaleQuestionsList(IpaScaleQuestions ipaScaleQuestions);

    /**
     * 新增身心调节-量-所有题目
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 结果
     */
    public int insertIpaScaleQuestions(IpaScaleQuestions ipaScaleQuestions);

    /**
     * 修改身心调节-量-所有题目
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 结果
     */
    public int updateIpaScaleQuestions(IpaScaleQuestions ipaScaleQuestions);

    /**
     * 删除身心调节-量-所有题目
     * 
     * @param id 身心调节-量-所有题目ID
     * @return 结果
     */
    public int deleteIpaScaleQuestionsById(Long id);

    /**
     * 批量删除身心调节-量-所有题目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIpaScaleQuestionsByIds(Long[] ids);
}
