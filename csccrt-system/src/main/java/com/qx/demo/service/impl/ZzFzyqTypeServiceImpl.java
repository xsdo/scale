package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzFzyqTypeMapper;
import com.qx.demo.entity.ZzFzyqType;
import com.qx.demo.service.IZzFzyqTypeService;

/**
 * 复杂眼区类型Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzFzyqTypeServiceImpl implements IZzFzyqTypeService 
{
    @Autowired
    private ZzFzyqTypeMapper zzFzyqTypeMapper;

    /**
     * 查询复杂眼区类型
     * 
     * @param id 复杂眼区类型ID
     * @return 复杂眼区类型
     */
    @Override
    public ZzFzyqType selectZzFzyqTypeById(Long id)
    {
        return zzFzyqTypeMapper.selectZzFzyqTypeById(id);
    }

    /**
     * 查询复杂眼区类型列表
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 复杂眼区类型
     */
    @Override
    public List<ZzFzyqType> selectZzFzyqTypeList(ZzFzyqType zzFzyqType)
    {
        return zzFzyqTypeMapper.selectZzFzyqTypeList(zzFzyqType);
    }

    /**
     * 新增复杂眼区类型
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 结果
     */
    @Override
    public int insertZzFzyqType(ZzFzyqType zzFzyqType)
    {
        return zzFzyqTypeMapper.insertZzFzyqType(zzFzyqType);
    }

    /**
     * 修改复杂眼区类型
     * 
     * @param zzFzyqType 复杂眼区类型
     * @return 结果
     */
    @Override
    public int updateZzFzyqType(ZzFzyqType zzFzyqType)
    {
        return zzFzyqTypeMapper.updateZzFzyqType(zzFzyqType);
    }

    /**
     * 批量删除复杂眼区类型
     * 
     * @param ids 需要删除的复杂眼区类型ID
     * @return 结果
     */
    @Override
    public int deleteZzFzyqTypeByIds(Long[] ids)
    {
        return zzFzyqTypeMapper.deleteZzFzyqTypeByIds(ids);
    }

    /**
     * 删除复杂眼区类型信息
     * 
     * @param id 复杂眼区类型ID
     * @return 结果
     */
    @Override
    public int deleteZzFzyqTypeById(Long id)
    {
        return zzFzyqTypeMapper.deleteZzFzyqTypeById(id);
    }
}
