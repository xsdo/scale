package com.qx.ipa.mapper;

import com.qx.ipa.domain.IpaTaskScore;
import java.util.List;

/**
 * ipa任务得分保存Mapper接口
 * 
 * @author Meng
 * @date 2021-07-09
 */
public interface IpaTaskScoreMapper 
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
     * 修改ipa任务得分保存
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return 结果
     */
    public int updateIpaTaskScore(IpaTaskScore ipaTaskScore);

    /**
     * 删除ipa任务得分保存
     * 
     * @param id ipa任务得分保存ID
     * @return 结果
     */
    public int deleteIpaTaskScoreById(Long id);

    /**
     * 批量删除ipa任务得分保存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIpaTaskScoreByIds(Long[] ids);
}
