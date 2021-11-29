package com.qx.demo.service;

import com.qx.demo.entity.ZzShtlType;
import java.util.List;

/**
 * 社会情境排列Service接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface IZzShtlTypeService
{
    /**
     * 查询社会情境排列
     * 
     * @param id 社会情境排列ID
     * @return 社会情境排列
     */
    public ZzShtlType selectZzShtlTypeById(Long id);

    /**
     * 查询社会情境排列列表
     * 
     * @param zzShtlType 社会情境排列
     * @return 社会情境排列集合
     */
    public List<ZzShtlType> selectZzShtlTypeList(ZzShtlType zzShtlType);

    /**
     * 新增社会情境排列
     * 
     * @param zzShtlType 社会情境排列
     * @return 结果
     */
    public int insertZzShtlType(ZzShtlType zzShtlType);

    /**
     * 修改社会情境排列
     * 
     * @param zzShtlType 社会情境排列
     * @return 结果
     */
    public int updateZzShtlType(ZzShtlType zzShtlType);

    /**
     * 批量删除社会情境排列
     * 
     * @param ids 需要删除的社会情境排列ID
     * @return 结果
     */
    public int deleteZzShtlTypeByIds(Long[] ids);

    /**
     * 删除社会情境排列信息
     * 
     * @param id 社会情境排列ID
     * @return 结果
     */
    public int deleteZzShtlTypeById(Long id);
}
