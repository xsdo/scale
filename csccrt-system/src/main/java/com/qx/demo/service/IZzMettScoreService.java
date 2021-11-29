package com.qx.demo.service;

import com.qx.demo.entity.MettDomain;
import com.qx.demo.entity.ZzMettScore;
import java.util.List;

/**
 * METT微情识别范式Service接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface IZzMettScoreService 
{
    /**
     * 查询METT微情识别范式
     * 
     * @param id METT微情识别范式ID
     * @return METT微情识别范式
     */
    public ZzMettScore selectZzMettScoreById(Long id);

    /**
     * 查询METT微情识别范式列表
     * 
     * @param zzMettScore METT微情识别范式
     * @return METT微情识别范式集合
     */
    public List<ZzMettScore> selectZzMettScoreList(ZzMettScore zzMettScore);

    /**
     * 新增METT微情识别范式
     * 
     * @param zzMettScore METT微情识别范式
     * @return 结果
     */
    public int insertZzMettScore(ZzMettScore zzMettScore);

    /**
     * 修改METT微情识别范式
     * 
     * @param zzMettScore METT微情识别范式
     * @return 结果
     */
    public int updateZzMettScore(ZzMettScore zzMettScore);

    /**
     * 批量删除METT微情识别范式
     * 
     * @param ids 需要删除的METT微情识别范式ID
     * @return 结果
     */
    public int deleteZzMettScoreByIds(Long[] ids);

    /**
     * 删除METT微情识别范式信息
     * 
     * @param id METT微情识别范式ID
     * @return 结果
     */
    public int deleteZzMettScoreById(Long id);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public MettDomain selectListByTaskId(Long taskId);
}
