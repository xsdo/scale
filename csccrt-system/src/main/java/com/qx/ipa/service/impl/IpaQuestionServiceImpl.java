package com.qx.ipa.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaQuestionMapper;
import com.qx.ipa.domain.IpaQuestion;
import com.qx.ipa.service.IIpaQuestionService;

/**
 * ipa问题Service业务层处理
 * 
 * @author Meng
 * @date 2021-07-06
 */
@Service
public class IpaQuestionServiceImpl implements IIpaQuestionService 
{
    @Autowired
    private IpaQuestionMapper ipaQuestionMapper;

    /**
     * 查询ipa问题
     * 
     * @param queId ipa问题ID
     * @return ipa问题
     */
    @Override
    public IpaQuestion selectIpaQuestionById(Long queId)
    {
        return ipaQuestionMapper.selectIpaQuestionById(queId);
    }

    /**
     * 查询ipa问题列表
     * 
     * @param ipaQuestion ipa问题
     * @return ipa问题
     */
    @Override
    public List<IpaQuestion> selectIpaQuestionList(IpaQuestion ipaQuestion)
    {
        return ipaQuestionMapper.selectIpaQuestionList(ipaQuestion);
    }

    /**
     * 新增ipa问题
     * 
     * @param ipaQuestion ipa问题
     * @return 结果
     */
    @Override
    public int insertIpaQuestion(IpaQuestion ipaQuestion)
    {
        return ipaQuestionMapper.insertIpaQuestion(ipaQuestion);
    }

    /**
     * 修改ipa问题
     * 
     * @param ipaQuestion ipa问题
     * @return 结果
     */
    @Override
    public int updateIpaQuestion(IpaQuestion ipaQuestion)
    {
        return ipaQuestionMapper.updateIpaQuestion(ipaQuestion);
    }

    /**
     * 批量删除ipa问题
     * 
     * @param queIds 需要删除的ipa问题ID
     * @return 结果
     */
    @Override
    public int deleteIpaQuestionByIds(Long[] queIds)
    {
        return ipaQuestionMapper.deleteIpaQuestionByIds(queIds);
    }

    /**
     * 删除ipa问题信息
     * 
     * @param queId ipa问题ID
     * @return 结果
     */
    @Override
    public int deleteIpaQuestionById(Long queId)
    {
        return ipaQuestionMapper.deleteIpaQuestionById(queId);
    }
}
