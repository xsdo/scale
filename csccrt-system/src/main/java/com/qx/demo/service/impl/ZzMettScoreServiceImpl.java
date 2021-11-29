package com.qx.demo.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.qx.demo.entity.MettDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzMettScoreMapper;
import com.qx.demo.entity.ZzMettScore;
import com.qx.demo.service.IZzMettScoreService;

/**
 * METT微情识别范式Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzMettScoreServiceImpl implements IZzMettScoreService 
{
    @Autowired
    private ZzMettScoreMapper zzMettScoreMapper;

    /**
     * 查询METT微情识别范式
     * 
     * @param id METT微情识别范式ID
     * @return METT微情识别范式
     */
    @Override
    public ZzMettScore selectZzMettScoreById(Long id)
    {
        return zzMettScoreMapper.selectZzMettScoreById(id);
    }

    /**
     * 查询METT微情识别范式列表
     * 
     * @param zzMettScore METT微情识别范式
     * @return METT微情识别范式
     */
    @Override
    public List<ZzMettScore> selectZzMettScoreList(ZzMettScore zzMettScore)
    {
        return zzMettScoreMapper.selectZzMettScoreList(zzMettScore);
    }

    /**
     * 新增METT微情识别范式
     * 
     * @param zzMettScore METT微情识别范式
     * @return 结果
     */
    @Override
    public int insertZzMettScore(ZzMettScore zzMettScore)
    {
        return zzMettScoreMapper.insertZzMettScore(zzMettScore);
    }

    /**
     * 修改METT微情识别范式
     * 
     * @param zzMettScore METT微情识别范式
     * @return 结果
     */
    @Override
    public int updateZzMettScore(ZzMettScore zzMettScore)
    {
        return zzMettScoreMapper.updateZzMettScore(zzMettScore);
    }

    /**
     * 批量删除METT微情识别范式
     * 
     * @param ids 需要删除的METT微情识别范式ID
     * @return 结果
     */
    @Override
    public int deleteZzMettScoreByIds(Long[] ids)
    {
        return zzMettScoreMapper.deleteZzMettScoreByIds(ids);
    }

    /**
     * 删除METT微情识别范式信息
     * 
     * @param id METT微情识别范式ID
     * @return 结果
     */
    @Override
    public int deleteZzMettScoreById(Long id)
    {
        return zzMettScoreMapper.deleteZzMettScoreById(id);
    }

    @Override
    public MettDomain selectListByTaskId(Long taskId) {
        MettDomain mettDomain=new MettDomain();
       List<ZzMettScore> list=zzMettScoreMapper.selectListByTaskId(taskId);
        Map<String, List<ZzMettScore>> map= list.stream().collect(Collectors.groupingBy(ZzMettScore::getExId));
        Map<String,Double> map1=new HashMap<>();
        Map<String,Double> map2=new HashMap<>();
        List<Map<String,Double>> mapList=new ArrayList<>();
        for (String s:map.keySet()) {
            Double a=0.0;
            Double b=0.0;
            for (ZzMettScore z:map.get(s)) {
               if("1".equals(z.getPoint())){
                   a+=Double.parseDouble(z.getPoint());
               }
               b+=z.getReactionTime();
            }
            BigDecimal g1= new  BigDecimal(a/4);
            BigDecimal g2= new  BigDecimal(b/4);
            map1.put(s,g1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            map2.put(s,g2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        mettDomain.setMap1(map1);
        mettDomain.setMap2(map2);
        mapList.add(map1);
        mapList.add(map2);
        mettDomain.setList(mapList);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        mettDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        return mettDomain;
    }
}
