package com.qx.ipa.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaScaleQuestionsMapper;
import com.qx.ipa.domain.IpaScaleQuestions;
import com.qx.ipa.service.IIpaScaleQuestionsService;

/**
 * 身心调节-量-所有题目Service业务层处理
 * 
 * @author meng
 * @date 2021-07-05
 */
@Service
public class IpaScaleQuestionsServiceImpl implements IIpaScaleQuestionsService 
{
    @Autowired
    private IpaScaleQuestionsMapper ipaScaleQuestionsMapper;

    /**
     * 查询身心调节-量-
     * 
     * @param id 身心调节-量-所有题目ID
     * @return 身心调节-量-所有题目
     */
    @Override
    public IpaScaleQuestions selectIpaScaleQuestionsById(Long id)
    {
        return ipaScaleQuestionsMapper.selectIpaScaleQuestionsById(id);
    }

    /**
     * 查询身心调节-量-所有题目列表
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 身心调节-量-所有题目
     */
    @Override
    public List<IpaScaleQuestions> selectIpaScaleQuestionsList(IpaScaleQuestions ipaScaleQuestions)
    {
        return ipaScaleQuestionsMapper.selectIpaScaleQuestionsList(ipaScaleQuestions);
    }

    /**
     * 新增身心调节-量-所有题目
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 结果
     */
    @Override
    public int insertIpaScaleQuestions(IpaScaleQuestions ipaScaleQuestions)
    {
        return ipaScaleQuestionsMapper.insertIpaScaleQuestions(ipaScaleQuestions);
    }

    /**
     * 修改身心调节-量-所有题目
     * 
     * @param ipaScaleQuestions 身心调节-量-所有题目
     * @return 结果
     */
    @Override
    public int updateIpaScaleQuestions(IpaScaleQuestions ipaScaleQuestions)
    {
        return ipaScaleQuestionsMapper.updateIpaScaleQuestions(ipaScaleQuestions);
    }

    /**
     * 批量删除身心调节-量-所有题目
     * 
     * @param ids 需要删除的身心调节-量-所有题目ID
     * @return 结果
     */
    @Override
    public int deleteIpaScaleQuestionsByIds(Long[] ids)
    {
        return ipaScaleQuestionsMapper.deleteIpaScaleQuestionsByIds(ids);
    }

    /**
     * 删除身心调节-量-所有题目信息
     * 
     * @param id 身心调节-量-所有题目ID
     * @return 结果
     */
    @Override
    public int deleteIpaScaleQuestionsById(Long id)
    {
        return ipaScaleQuestionsMapper.deleteIpaScaleQuestionsById(id);
    }
}
