package com.qx.ipa.mapper;

import com.qx.ipa.domain.IpaQuestion;
import java.util.List;

/**
 * ipa问题Mapper接口
 * 
 * @author Meng
 * @date 2021-07-06
 */
public interface IpaQuestionMapper 
{
    /**
     * 查询ipa问题
     * 
     * @param queId ipa问题ID
     * @return ipa问题
     */
    public IpaQuestion selectIpaQuestionById(Long queId);

    /**
     * 查询ipa问题列表
     * 
     * @param ipaQuestion ipa问题
     * @return ipa问题集合
     */
    public List<IpaQuestion> selectIpaQuestionList(IpaQuestion ipaQuestion);

    /**
     * 新增ipa问题
     * 
     * @param ipaQuestion ipa问题
     * @return 结果
     */
    public int insertIpaQuestion(IpaQuestion ipaQuestion);

    /**
     * 修改ipa问题
     * 
     * @param ipaQuestion ipa问题
     * @return 结果
     */
    public int updateIpaQuestion(IpaQuestion ipaQuestion);

    /**
     * 删除ipa问题
     * 
     * @param queId ipa问题ID
     * @return 结果
     */
    public int deleteIpaQuestionById(Long queId);

    /**
     * 批量删除ipa问题
     * 
     * @param queIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIpaQuestionByIds(Long[] queIds);
}
