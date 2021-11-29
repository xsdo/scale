package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzMettTypeMapper;
import com.qx.demo.entity.ZzMettType;
import com.qx.demo.service.IZzMettTypeService;

/**
 * METT微情识别范式类型Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzMettTypeServiceImpl implements IZzMettTypeService 
{
    @Autowired
    private ZzMettTypeMapper zzMettTypeMapper;

    /**
     * 查询METT微情识别范式类型
     * 
     * @param id METT微情识别范式类型ID
     * @return METT微情识别范式类型
     */
    @Override
    public ZzMettType selectZzMettTypeById(Long id)
    {
        return zzMettTypeMapper.selectZzMettTypeById(id);
    }

    /**
     * 查询METT微情识别范式类型列表
     * 
     * @param zzMettType METT微情识别范式类型
     * @return METT微情识别范式类型
     */
    @Override
    public List<ZzMettType> selectZzMettTypeList(ZzMettType zzMettType)
    {
        return zzMettTypeMapper.selectZzMettTypeList(zzMettType);
    }

    /**
     * 新增METT微情识别范式类型
     * 
     * @param zzMettType METT微情识别范式类型
     * @return 结果
     */
    @Override
    public int insertZzMettType(ZzMettType zzMettType)
    {
        return zzMettTypeMapper.insertZzMettType(zzMettType);
    }

    /**
     * 修改METT微情识别范式类型
     * 
     * @param zzMettType METT微情识别范式类型
     * @return 结果
     */
    @Override
    public int updateZzMettType(ZzMettType zzMettType)
    {
        return zzMettTypeMapper.updateZzMettType(zzMettType);
    }

    /**
     * 批量删除METT微情识别范式类型
     * 
     * @param ids 需要删除的METT微情识别范式类型ID
     * @return 结果
     */
    @Override
    public int deleteZzMettTypeByIds(Long[] ids)
    {
        return zzMettTypeMapper.deleteZzMettTypeByIds(ids);
    }

    /**
     * 删除METT微情识别范式类型信息
     * 
     * @param id METT微情识别范式类型ID
     * @return 结果
     */
    @Override
    public int deleteZzMettTypeById(Long id)
    {
        return zzMettTypeMapper.deleteZzMettTypeById(id);
    }
}
