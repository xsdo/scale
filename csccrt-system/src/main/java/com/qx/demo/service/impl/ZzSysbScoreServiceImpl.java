package com.qx.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.SysbDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzSysbScoreMapper;
import com.qx.demo.entity.ZzSysbScore;
import com.qx.demo.service.IZzSysbScoreService;

/**
 * 失言任务范式Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzSysbScoreServiceImpl implements IZzSysbScoreService 
{
    @Autowired
    private ZzSysbScoreMapper zzSysbScoreMapper;

    /**
     * 查询失言任务范式
     * 
     * @param id 失言任务范式ID
     * @return 失言任务范式
     */
    @Override
    public ZzSysbScore selectZzSysbScoreById(Long id)
    {
        return zzSysbScoreMapper.selectZzSysbScoreById(id);
    }

    /**
     * 查询失言任务范式列表
     * 
     * @param zzSysbScore 失言任务范式
     * @return 失言任务范式
     */
    @Override
    public List<ZzSysbScore> selectZzSysbScoreList(ZzSysbScore zzSysbScore)
    {
        return zzSysbScoreMapper.selectZzSysbScoreList(zzSysbScore);
    }

    /**
     * 新增失言任务范式
     * 
     * @param zzSysbScore 失言任务范式
     * @return 结果
     */
    @Override
    public int insertZzSysbScore(ZzSysbScore zzSysbScore)
    {
        return zzSysbScoreMapper.insertZzSysbScore(zzSysbScore);
    }

    /**
     * 修改失言任务范式
     * 
     * @param zzSysbScore 失言任务范式
     * @return 结果
     */
    @Override
    public int updateZzSysbScore(ZzSysbScore zzSysbScore)
    {
        return zzSysbScoreMapper.updateZzSysbScore(zzSysbScore);
    }

    /**
     * 批量删除失言任务范式
     * 
     * @param ids 需要删除的失言任务范式ID
     * @return 结果
     */
    @Override
    public int deleteZzSysbScoreByIds(Long[] ids)
    {
        return zzSysbScoreMapper.deleteZzSysbScoreByIds(ids);
    }

    /**
     * 删除失言任务范式信息
     * 
     * @param id 失言任务范式ID
     * @return 结果
     */
    @Override
    public int deleteZzSysbScoreById(Long id)
    {
        return zzSysbScoreMapper.deleteZzSysbScoreById(id);
    }

    @Override
    public SysbDomain selectListByTaskId(Long taskId) {
        SysbDomain sysbDomain = new SysbDomain();
        List<ZzSysbScore> list = zzSysbScoreMapper.selectListByTaskId(taskId);
        for (ZzSysbScore z : list) {
            if ("1".equals(z.getTitle()) || "2".equals(z.getTitle())) {
                sysbDomain.setKzwt(sysbDomain.getKzwt() + Integer.parseInt(z.getPoint()));
            } else if ("3".equals(z.getTitle()) || "4".equals(z.getTitle()) || "5".equals(z.getTitle()) || "6".equals(z.getTitle()) || "7".equals(z.getTitle()) || "8".equals(z.getTitle())) {
                sysbDomain.setSywt(sysbDomain.getSywt() + Integer.parseInt(z.getPoint()));
            }
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        sysbDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        sysbDomain.setSum(sysbDomain.getKzwt()+sysbDomain.getSywt());
        return sysbDomain;
    }
}
