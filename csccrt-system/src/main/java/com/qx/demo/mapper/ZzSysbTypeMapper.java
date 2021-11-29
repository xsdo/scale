package com.qx.demo.mapper;

import com.qx.demo.entity.ZzSysbType;
import java.util.List;

/**
 * 失言识别类型Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzSysbTypeMapper 
{
    /**
     * 查询失言识别类型
     * 
     * @param id 失言识别类型ID
     * @return 失言识别类型
     */
    public ZzSysbType selectZzSysbTypeById(Long id);

    /**
     * 查询失言识别类型列表
     * 
     * @param zzSysbType 失言识别类型
     * @return 失言识别类型集合
     */
    public List<ZzSysbType> selectZzSysbTypeList(ZzSysbType zzSysbType);

    /**
     * 新增失言识别类型
     * 
     * @param zzSysbType 失言识别类型
     * @return 结果
     */
    public int insertZzSysbType(ZzSysbType zzSysbType);

    /**
     * 修改失言识别类型
     * 
     * @param zzSysbType 失言识别类型
     * @return 结果
     */
    public int updateZzSysbType(ZzSysbType zzSysbType);

    /**
     * 删除失言识别类型
     * 
     * @param id 失言识别类型ID
     * @return 结果
     */
    public int deleteZzSysbTypeById(Long id);

    /**
     * 批量删除失言识别类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzSysbTypeByIds(Long[] ids);
}
