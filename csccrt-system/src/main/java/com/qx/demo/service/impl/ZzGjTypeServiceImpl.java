package com.qx.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzGjTypeMapper;
import com.qx.demo.entity.ZzGjType;
import com.qx.demo.service.IZzGjTypeService;

/**
 * 高级任务类型Service业务层处理
 * 
 * @author patient
 * @date 2021-02-03
 */
@Service
public class ZzGjTypeServiceImpl implements IZzGjTypeService 
{
    @Autowired
    private ZzGjTypeMapper zzGjTypeMapper;

    /**
     * 查询高级任务类型
     * 
     * @param id 高级任务类型ID
     * @return 高级任务类型
     */
    @Override
    public ZzGjType selectZzGjTypeById(Long id)
    {
        return zzGjTypeMapper.selectZzGjTypeById(id);
    }

    /**
     * 查询高级任务类型列表
     * 
     * @param zzGjType 高级任务类型
     * @return 高级任务类型
     */
    @Override
    public List<ZzGjType> selectZzGjTypeList(ZzGjType zzGjType)
    {
        return zzGjTypeMapper.selectZzGjTypeList(zzGjType);
    }

    /**
     * 新增高级任务类型
     * 
     * @param zzGjType 高级任务类型
     * @return 结果
     */
    @Override
    public int insertZzGjType(ZzGjType zzGjType)
    {
        return zzGjTypeMapper.insertZzGjType(zzGjType);
    }

    /**
     * 修改高级任务类型
     * 
     * @param zzGjType 高级任务类型
     * @return 结果
     */
    @Override
    public int updateZzGjType(ZzGjType zzGjType)
    {
        return zzGjTypeMapper.updateZzGjType(zzGjType);
    }

    /**
     * 批量删除高级任务类型
     * 
     * @param ids 需要删除的高级任务类型ID
     * @return 结果
     */
    @Override
    public int deleteZzGjTypeByIds(Long[] ids)
    {
        return zzGjTypeMapper.deleteZzGjTypeByIds(ids);
    }

    /**
     * 删除高级任务类型信息
     * 
     * @param id 高级任务类型ID
     * @return 结果
     */
    @Override
    public int deleteZzGjTypeById(Long id)
    {
        return zzGjTypeMapper.deleteZzGjTypeById(id);
    }
}
