package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzShtlTypeMapper;
import com.qx.demo.entity.ZzShtlType;
import com.qx.demo.service.IZzShtlTypeService;

/**
 * 社会情境排列Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzShtlTypeServiceImpl implements IZzShtlTypeService 
{
    @Autowired
    private ZzShtlTypeMapper zzShtlTypeMapper;

    /**
     * 查询社会情境排列
     * 
     * @param id 社会情境排列ID
     * @return 社会情境排列
     */
    @Override
    public ZzShtlType selectZzShtlTypeById(Long id)
    {
        return zzShtlTypeMapper.selectZzShtlTypeById(id);
    }

    /**
     * 查询社会情境排列列表
     * 
     * @param zzShtlType 社会情境排列
     * @return 社会情境排列
     */
    @Override
    public List<ZzShtlType> selectZzShtlTypeList(ZzShtlType zzShtlType)
    {
        return zzShtlTypeMapper.selectZzShtlTypeList(zzShtlType);
    }

    /**
     * 新增社会情境排列
     * 
     * @param zzShtlType 社会情境排列
     * @return 结果
     */
    @Override
    public int insertZzShtlType(ZzShtlType zzShtlType)
    {
        return zzShtlTypeMapper.insertZzShtlType(zzShtlType);
    }

    /**
     * 修改社会情境排列
     * 
     * @param zzShtlType 社会情境排列
     * @return 结果
     */
    @Override
    public int updateZzShtlType(ZzShtlType zzShtlType)
    {
        return zzShtlTypeMapper.updateZzShtlType(zzShtlType);
    }

    /**
     * 批量删除社会情境排列
     * 
     * @param ids 需要删除的社会情境排列ID
     * @return 结果
     */
    @Override
    public int deleteZzShtlTypeByIds(Long[] ids)
    {
        return zzShtlTypeMapper.deleteZzShtlTypeByIds(ids);
    }

    /**
     * 删除社会情境排列信息
     * 
     * @param id 社会情境排列ID
     * @return 结果
     */
    @Override
    public int deleteZzShtlTypeById(Long id)
    {
        return zzShtlTypeMapper.deleteZzShtlTypeById(id);
    }
}
