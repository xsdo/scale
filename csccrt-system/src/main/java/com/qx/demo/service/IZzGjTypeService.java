package com.qx.demo.service;

import com.qx.demo.entity.ZzGjType;
import java.util.List;

/**
 * 高级任务类型Service接口
 * 
 * @author patient
 * @date 2021-02-03
 */
public interface IZzGjTypeService 
{
    /**
     * 查询高级任务类型
     * 
     * @param id 高级任务类型ID
     * @return 高级任务类型
     */
    public ZzGjType selectZzGjTypeById(Long id);

    /**
     * 查询高级任务类型列表
     * 
     * @param zzGjType 高级任务类型
     * @return 高级任务类型集合
     */
    public List<ZzGjType> selectZzGjTypeList(ZzGjType zzGjType);

    /**
     * 新增高级任务类型
     * 
     * @param zzGjType 高级任务类型
     * @return 结果
     */
    public int insertZzGjType(ZzGjType zzGjType);

    /**
     * 修改高级任务类型
     * 
     * @param zzGjType 高级任务类型
     * @return 结果
     */
    public int updateZzGjType(ZzGjType zzGjType);

    /**
     * 批量删除高级任务类型
     * 
     * @param ids 需要删除的高级任务类型ID
     * @return 结果
     */
    public int deleteZzGjTypeByIds(Long[] ids);

    /**
     * 删除高级任务类型信息
     * 
     * @param id 高级任务类型ID
     * @return 结果
     */
    public int deleteZzGjTypeById(Long id);
}
