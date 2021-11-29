package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzSysbTypeMapper;
import com.qx.demo.entity.ZzSysbType;
import com.qx.demo.service.IZzSysbTypeService;

/**
 * 失言识别类型Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzSysbTypeServiceImpl implements IZzSysbTypeService 
{
    @Autowired
    private ZzSysbTypeMapper zzSysbTypeMapper;

    /**
     * 查询失言识别类型
     * 
     * @param id 失言识别类型ID
     * @return 失言识别类型
     */
    @Override
    public ZzSysbType selectZzSysbTypeById(Long id)
    {
        return zzSysbTypeMapper.selectZzSysbTypeById(id);
    }

    /**
     * 查询失言识别类型列表
     * 
     * @param zzSysbType 失言识别类型
     * @return 失言识别类型
     */
    @Override
    public List<ZzSysbType> selectZzSysbTypeList(ZzSysbType zzSysbType)
    {
        return zzSysbTypeMapper.selectZzSysbTypeList(zzSysbType);
    }

    /**
     * 新增失言识别类型
     * 
     * @param zzSysbType 失言识别类型
     * @return 结果
     */
    @Override
    public int insertZzSysbType(ZzSysbType zzSysbType)
    {
        return zzSysbTypeMapper.insertZzSysbType(zzSysbType);
    }

    /**
     * 修改失言识别类型
     * 
     * @param zzSysbType 失言识别类型
     * @return 结果
     */
    @Override
    public int updateZzSysbType(ZzSysbType zzSysbType)
    {
        return zzSysbTypeMapper.updateZzSysbType(zzSysbType);
    }

    /**
     * 批量删除失言识别类型
     * 
     * @param ids 需要删除的失言识别类型ID
     * @return 结果
     */
    @Override
    public int deleteZzSysbTypeByIds(Long[] ids)
    {
        return zzSysbTypeMapper.deleteZzSysbTypeByIds(ids);
    }

    /**
     * 删除失言识别类型信息
     * 
     * @param id 失言识别类型ID
     * @return 结果
     */
    @Override
    public int deleteZzSysbTypeById(Long id)
    {
        return zzSysbTypeMapper.deleteZzSysbTypeById(id);
    }
}
