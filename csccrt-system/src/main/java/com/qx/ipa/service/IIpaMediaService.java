package com.qx.ipa.service;

import com.qx.ipa.domain.IpaMedia;
import java.util.List;

/**
 * 视频地址Service接口
 * 
 * @author Meng
 * @date 2021-07-08
 */
public interface IIpaMediaService 
{
    /**
     * 查询视频地址
     * 
     * @param id 视频地址ID
     * @return 视频地址
     */
    public IpaMedia selectIpaMediaById(Long id);

    /**
     * 查询视频地址
     *
     * @param mark 视频标志
     * @return 视频地址
     */
    public IpaMedia selectIpaMediaByMark(String mark);

    /**
     * 查询视频地址
     *
     *  随机放松视频
     * @return 视频地址
     */
    public IpaMedia selectIpaMediaRelax();

    /**
     * 查询视频地址列表
     * 
     * @param ipaMedia 视频地址
     * @return 视频地址集合
     */
    public List<IpaMedia> selectIpaMediaList(IpaMedia ipaMedia);

    /**
     * 新增视频地址
     * 
     * @param ipaMedia 视频地址
     * @return 结果
     */
    public int insertIpaMedia(IpaMedia ipaMedia);

    /**
     * 修改视频地址
     * 
     * @param ipaMedia 视频地址
     * @return 结果
     */
    public int updateIpaMedia(IpaMedia ipaMedia);

    /**
     * 批量删除视频地址
     * 
     * @param ids 需要删除的视频地址ID
     * @return 结果
     */
    public int deleteIpaMediaByIds(Long[] ids);

    /**
     * 删除视频地址信息
     * 
     * @param id 视频地址ID
     * @return 结果
     */
    public int deleteIpaMediaById(Long id);
}
