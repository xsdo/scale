package com.qx.demo.mapper;

import com.qx.demo.entity.ZzDld;
import java.util.List;

/**
 * 多伦多述情障碍量Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzDldMapper 
{
    /**
     * 查询多伦多述情障碍量
     * 
     * @param id 多伦多述情障碍量ID
     * @return 多伦多述情障碍量
     */
    public ZzDld selectZzDldById(Long id);

    /**
     * 查询多伦多述情障碍量列表
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 多伦多述情障碍量集合
     */
    public List<ZzDld> selectZzDldList(ZzDld zzDld);

    /**
     * 新增多伦多述情障碍量
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 结果
     */
    public int insertZzDld(ZzDld zzDld);

    /**
     * 修改多伦多述情障碍量
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 结果
     */
    public int updateZzDld(ZzDld zzDld);

    /**
     * 删除多伦多述情障碍量
     * 
     * @param id 多伦多述情障碍量ID
     * @return 结果
     */
    public int deleteZzDldById(Long id);

    /**
     * 批量删除多伦多述情障碍量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzDldByIds(Long[] ids);
    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public List<ZzDld> selectListByTaskId(Long taskId);
}
