package com.qx.demo.service;

import com.qx.demo.entity.ShtlDomain;
import com.qx.demo.entity.ZzShtlScore;
import java.util.List;

/**
 * 社会情境排列Service接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface IZzShtlScoreService 
{
    /**
     * 查询社会情境排列
     * 
     * @param id 社会情境排列ID
     * @return 社会情境排列
     */
    public ZzShtlScore selectZzShtlScoreById(Long id);

    /**
     * 查询社会情境排列列表
     * 
     * @param zzShtlScore 社会情境排列
     * @return 社会情境排列集合
     */
    public List<ZzShtlScore> selectZzShtlScoreList(ZzShtlScore zzShtlScore);

    /**
     * 新增社会情境排列
     * 
     * @param zzShtlScore 社会情境排列
     * @return 结果
     */
    public int insertZzShtlScore(ZzShtlScore zzShtlScore);

    /**
     * 修改社会情境排列
     * 
     * @param zzShtlScore 社会情境排列
     * @return 结果
     */
    public int updateZzShtlScore(ZzShtlScore zzShtlScore);

    /**
     * 批量删除社会情境排列
     * 
     * @param ids 需要删除的社会情境排列ID
     * @return 结果
     */
    public int deleteZzShtlScoreByIds(Long[] ids);

    /**
     * 删除社会情境排列信息
     * 
     * @param id 社会情境排列ID
     * @return 结果
     */
    public int deleteZzShtlScoreById(Long id);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public ShtlDomain selectListByTaskId(Long taskId);
}
