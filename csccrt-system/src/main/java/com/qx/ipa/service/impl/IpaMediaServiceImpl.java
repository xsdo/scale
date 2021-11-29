package com.qx.ipa.service.impl;

import java.util.List;

import com.qx.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaMediaMapper;
import com.qx.ipa.domain.IpaMedia;
import com.qx.ipa.service.IIpaMediaService;

/**
 * 视频地址Service业务层处理
 *
 * @author Meng
 * @date 2021-07-08
 */
@Service
public class IpaMediaServiceImpl implements IIpaMediaService {
    @Autowired
    private IpaMediaMapper ipaMediaMapper;

    /**
     * 查询视频地址
     *
     * @param id 视频地址ID
     * @return 视频地址
     */
    @Override
    public IpaMedia selectIpaMediaById(Long id) {
        return ipaMediaMapper.selectIpaMediaById(id);
    }

    @Override
    public IpaMedia selectIpaMediaByMark(String mark) {
        return ipaMediaMapper.selectIpaMediaByMark(mark);
    }

    @Override
    public IpaMedia selectIpaMediaRelax() {

        IpaMedia ipaMedia = new IpaMedia();
        ipaMedia.setMark("0");
        List<IpaMedia> ipaMediaList = ipaMediaMapper.selectIpaMediaList(ipaMedia);
        int i = ipaMediaList.size();
        //随机返回一个放松视频
        return ipaMediaList.get((int)(Math.random()*i));
    }

    /**
     * 查询视频地址列表
     *
     * @param ipaMedia 视频地址
     * @return 视频地址
     */
    @Override
    public List<IpaMedia> selectIpaMediaList(IpaMedia ipaMedia) {
        return ipaMediaMapper.selectIpaMediaList(ipaMedia);
    }

    /**
     * 新增视频地址
     *
     * @param ipaMedia 视频地址
     * @return 结果
     */
    @Override
    public int insertIpaMedia(IpaMedia ipaMedia) {
        return ipaMediaMapper.insertIpaMedia(ipaMedia);
    }

    /**
     * 修改视频地址
     *
     * @param ipaMedia 视频地址
     * @return 结果
     */
    @Override
    public int updateIpaMedia(IpaMedia ipaMedia) {
        return ipaMediaMapper.updateIpaMedia(ipaMedia);
    }

    /**
     * 批量删除视频地址
     *
     * @param ids 需要删除的视频地址ID
     * @return 结果
     */
    @Override
    public int deleteIpaMediaByIds(Long[] ids) {
        return ipaMediaMapper.deleteIpaMediaByIds(ids);
    }

    /**
     * 删除视频地址信息
     *
     * @param id 视频地址ID
     * @return 结果
     */
    @Override
    public int deleteIpaMediaById(Long id) {
        return ipaMediaMapper.deleteIpaMediaById(id);
    }
}
