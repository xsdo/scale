package com.qx.demo.mapper;

import com.qx.demo.entity.ZzSysbScore;
import java.util.List;

/**
 * 失言任务范式Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzSysbScoreMapper 
{
    /**
     * 查询失言任务范式
     * 
     * @param id 失言任务范式ID
     * @return 失言任务范式
     */
    public ZzSysbScore selectZzSysbScoreById(Long id);

    /**
     * 查询失言任务范式列表
     * 
     * @param zzSysbScore 失言任务范式
     * @return 失言任务范式集合
     */
    public List<ZzSysbScore> selectZzSysbScoreList(ZzSysbScore zzSysbScore);

    /**
     * 新增失言任务范式
     * 
     * @param zzSysbScore 失言任务范式
     * @return 结果
     */
    public int insertZzSysbScore(ZzSysbScore zzSysbScore);

    /**
     * 修改失言任务范式
     * 
     * @param zzSysbScore 失言任务范式
     * @return 结果
     */
    public int updateZzSysbScore(ZzSysbScore zzSysbScore);

    /**
     * 删除失言任务范式
     * 
     * @param id 失言任务范式ID
     * @return 结果
     */
    public int deleteZzSysbScoreById(Long id);

    /**
     * 批量删除失言任务范式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzSysbScoreByIds(Long[] ids);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public List<ZzSysbScore> selectListByTaskId(Long taskId);
}
