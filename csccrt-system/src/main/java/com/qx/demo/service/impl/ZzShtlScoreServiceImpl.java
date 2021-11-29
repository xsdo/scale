package com.qx.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.ShtlDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzShtlScoreMapper;
import com.qx.demo.entity.ZzShtlScore;
import com.qx.demo.service.IZzShtlScoreService;

/**
 * 社会情境排列Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzShtlScoreServiceImpl implements IZzShtlScoreService 
{
    @Autowired
    private ZzShtlScoreMapper zzShtlScoreMapper;

    /**
     * 查询社会情境排列
     * 
     * @param id 社会情境排列ID
     * @return 社会情境排列
     */
    @Override
    public ZzShtlScore selectZzShtlScoreById(Long id)
    {
        return zzShtlScoreMapper.selectZzShtlScoreById(id);
    }

    /**
     * 查询社会情境排列列表
     * 
     * @param zzShtlScore 社会情境排列
     * @return 社会情境排列
     */
    @Override
    public List<ZzShtlScore> selectZzShtlScoreList(ZzShtlScore zzShtlScore)
    {
        return zzShtlScoreMapper.selectZzShtlScoreList(zzShtlScore);
    }

    /**
     * 新增社会情境排列
     * 
     * @param zzShtlScore 社会情境排列
     * @return 结果
     */
    @Override
    public int insertZzShtlScore(ZzShtlScore zzShtlScore)
    {
        return zzShtlScoreMapper.insertZzShtlScore(zzShtlScore);
    }

    /**
     * 修改社会情境排列
     * 
     * @param zzShtlScore 社会情境排列
     * @return 结果
     */
    @Override
    public int updateZzShtlScore(ZzShtlScore zzShtlScore)
    {
        return zzShtlScoreMapper.updateZzShtlScore(zzShtlScore);
    }

    /**
     * 批量删除社会情境排列
     * 
     * @param ids 需要删除的社会情境排列ID
     * @return 结果
     */
    @Override
    public int deleteZzShtlScoreByIds(Long[] ids)
    {
        return zzShtlScoreMapper.deleteZzShtlScoreByIds(ids);
    }

    /**
     * 删除社会情境排列信息
     * 
     * @param id 社会情境排列ID
     * @return 结果
     */
    @Override
    public int deleteZzShtlScoreById(Long id)
    {
        return zzShtlScoreMapper.deleteZzShtlScoreById(id);
    }

    @Override
    public ShtlDomain selectListByTaskId(Long taskId) {
        ShtlDomain shtlDomain=new ShtlDomain();
        List<ZzShtlScore> list=zzShtlScoreMapper.selectListByTaskId(taskId);
        int a=0;
        //Double b=0.0;
        for (ZzShtlScore z:list) {
            if("1".equals(z.getPoint())){
                a+=Integer.parseInt(z.getPoint());
            }
           // b+=z.getReactionTime();
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        shtlDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        shtlDomain.setCorrect(a*100/list.size());
        //shtlDomain.setAverage(b/list.size());
        return shtlDomain;
    }
}
