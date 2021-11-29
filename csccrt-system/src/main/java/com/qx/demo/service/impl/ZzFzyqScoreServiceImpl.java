package com.qx.demo.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.FzyqDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzFzyqScoreMapper;
import com.qx.demo.entity.ZzFzyqScore;
import com.qx.demo.service.IZzFzyqScoreService;

/**
 * 复杂眼区识别范式Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzFzyqScoreServiceImpl implements IZzFzyqScoreService 
{
    @Autowired
    private ZzFzyqScoreMapper zzFzyqScoreMapper;

    /**
     * 查询复杂眼区识别范式
     * 
     * @param id 复杂眼区识别范式ID
     * @return 复杂眼区识别范式
     */
    @Override
    public ZzFzyqScore selectZzFzyqScoreById(Long id)
    {
        return zzFzyqScoreMapper.selectZzFzyqScoreById(id);
    }

    /**
     * 查询复杂眼区识别范式列表
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 复杂眼区识别范式
     */
    @Override
    public List<ZzFzyqScore> selectZzFzyqScoreList(ZzFzyqScore zzFzyqScore)
    {
        return zzFzyqScoreMapper.selectZzFzyqScoreList(zzFzyqScore);
    }

    /**
     * 新增复杂眼区识别范式
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 结果
     */
    @Override
    public int insertZzFzyqScore(ZzFzyqScore zzFzyqScore)
    {
        return zzFzyqScoreMapper.insertZzFzyqScore(zzFzyqScore);
    }

    /**
     * 修改复杂眼区识别范式
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 结果
     */
    @Override
    public int updateZzFzyqScore(ZzFzyqScore zzFzyqScore)
    {
        return zzFzyqScoreMapper.updateZzFzyqScore(zzFzyqScore);
    }

    /**
     * 批量删除复杂眼区识别范式
     * 
     * @param ids 需要删除的复杂眼区识别范式ID
     * @return 结果
     */
    @Override
    public int deleteZzFzyqScoreByIds(Long[] ids)
    {
        return zzFzyqScoreMapper.deleteZzFzyqScoreByIds(ids);
    }

    /**
     * 删除复杂眼区识别范式信息
     * 
     * @param id 复杂眼区识别范式ID
     * @return 结果
     */
    @Override
    public int deleteZzFzyqScoreById(Long id)
    {
        return zzFzyqScoreMapper.deleteZzFzyqScoreById(id);
    }

    @Override
    public FzyqDomain selectListByTaskId(Long taskId) {
        FzyqDomain fzyqDomain=new FzyqDomain();
       List<ZzFzyqScore> list=zzFzyqScoreMapper.selectListByTaskId(taskId);
       int a=0;
       Double b=0.0;
        for (ZzFzyqScore z:list) {
          if("1".equals(z.getPoint())){
              a+=Integer.parseInt(z.getPoint());
          }
          b+=z.getReactionTime();
        }
        BigDecimal g1= new  BigDecimal(b/list.size());
        fzyqDomain.setCorrect(a*100/list.size());
        fzyqDomain.setAverage(g1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        fzyqDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        return fzyqDomain;
    }
}
