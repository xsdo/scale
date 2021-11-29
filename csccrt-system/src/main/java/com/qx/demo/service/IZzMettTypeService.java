package com.qx.demo.service;

import com.qx.demo.entity.ZzMettType;
import java.util.List;

/**
 * METT微情识别范式类型Service接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface IZzMettTypeService 
{
    /**
     * 查询METT微情识别范式类型
     * 
     * @param id METT微情识别范式类型ID
     * @return METT微情识别范式类型
     */
    public ZzMettType selectZzMettTypeById(Long id);

    /**
     * 查询METT微情识别范式类型列表
     * 
     * @param zzMettType METT微情识别范式类型
     * @return METT微情识别范式类型集合
     */
    public List<ZzMettType> selectZzMettTypeList(ZzMettType zzMettType);

    /**
     * 新增METT微情识别范式类型
     * 
     * @param zzMettType METT微情识别范式类型
     * @return 结果
     */
    public int insertZzMettType(ZzMettType zzMettType);

    /**
     * 修改METT微情识别范式类型
     * 
     * @param zzMettType METT微情识别范式类型
     * @return 结果
     */
    public int updateZzMettType(ZzMettType zzMettType);

    /**
     * 批量删除METT微情识别范式类型
     * 
     * @param ids 需要删除的METT微情识别范式类型ID
     * @return 结果
     */
    public int deleteZzMettTypeByIds(Long[] ids);

    /**
     * 删除METT微情识别范式类型信息
     * 
     * @param id METT微情识别范式类型ID
     * @return 结果
     */
    public int deleteZzMettTypeById(Long id);
}
