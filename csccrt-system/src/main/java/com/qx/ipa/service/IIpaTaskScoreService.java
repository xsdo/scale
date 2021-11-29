package com.qx.ipa.service;

import com.qx.ipa.domain.IpaDomain;
import com.qx.ipa.domain.IpaTask;
import com.qx.ipa.domain.IpaTaskScore;
import java.util.List;

/**
 * ipa任务得分保存Service接口
 * 
 * @author Meng
 * @date 2021-07-09
 */
public interface IIpaTaskScoreService 
{
    /**
     * 查询ipa任务得分保存
     * 
     * @param id ipa任务得分保存ID
     * @return ipa任务得分保存
     */
    public IpaTaskScore selectIpaTaskScoreById(Long id);

    /**
     * 查询ipa任务得分保存列表
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return ipa任务得分保存集合
     */
    public List<IpaTaskScore> selectIpaTaskScoreList(IpaTaskScore ipaTaskScore);

    /**
     * 新增ipa任务得分保存
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return 结果
     */
    public int insertIpaTaskScore(IpaTaskScore ipaTaskScore);

    /**
     * 新增ipa任务得分保存
     *
     * @param
     * @return 结果
     */
    public int insertIpaTaskScore(IpaTask ipaTask,String scaleId,int score);

    /**
     * 修改ipa任务得分保存
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return 结果
     */
    public int updateIpaTaskScore(IpaTaskScore ipaTaskScore);

    /**
     * 批量删除ipa任务得分保存
     * 
     * @param ids 需要删除的ipa任务得分保存ID
     * @return 结果
     */
    public int deleteIpaTaskScoreByIds(Long[] ids);

    /**
     * 删除ipa任务得分保存信息
     * 
     * @param id ipa任务得分保存ID
     * @return 结果
     */
    public int deleteIpaTaskScoreById(Long id);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public IpaDomain selectListByTaskId(Long taskId);
}
