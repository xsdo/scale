package com.qx.demo.service;

import com.qx.demo.entity.ZzFzyqType;
import java.util.List;

/**
 * 复杂眼区类型Service接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface IZzFzyqTypeService 
{
    /**
     * 查询复杂眼区类型
     * 
     * @param id 复杂眼区类型ID
     * @return 复杂眼区类型
     */
    public ZzFzyqType selectZzFzyqTypeById(Long id);

    /**
     * 查询复杂眼区类型列表
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 复杂眼区类型集合
     */
    public List<ZzFzyqType> selectZzFzyqTypeList(ZzFzyqType zzFzyqType);

    /**
     * 新增复杂眼区类型
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 结果
     */
    public int insertZzFzyqType(ZzFzyqType zzFzyqType);

    /**
     * 修改复杂眼区类型
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 结果
     */
    public int updateZzFzyqType(ZzFzyqType zzFzyqType);

    /**
     * 批量删除复杂眼区类型
     * 
     * @param ids 需要删除的复杂眼区类型ID
     * @return 结果
     */
    public int deleteZzFzyqTypeByIds(Long[] ids);

    /**
     * 删除复杂眼区类型信息
     * 
     * @param id 复杂眼区类型ID
     * @return 结果
     */
    public int deleteZzFzyqTypeById(Long id);
}
