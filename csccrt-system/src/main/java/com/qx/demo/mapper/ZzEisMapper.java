package com.qx.demo.mapper;

import com.qx.demo.entity.ZzEis;
import java.util.List;

/**
 * 情绪智力量(EIS)Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzEisMapper 
{
    /**
     * 查询情绪智力量(EIS)
     * 
     * @param id 情绪智力量(EIS)ID
     * @return 情绪智力量(EIS)
     */
    public ZzEis selectZzEisById(Long id);

    /**
     * 查询情绪智力量(EIS)列表
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 情绪智力量(EIS)集合
     */
    public List<ZzEis> selectZzEisList(ZzEis zzEis);

    /**
     * 新增情绪智力量(EIS)
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 结果
     */
    public int insertZzEis(ZzEis zzEis);

    /**
     * 修改情绪智力量(EIS)
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 结果
     */
    public int updateZzEis(ZzEis zzEis);

    /**
     * 删除情绪智力量(EIS)
     * 
     * @param id 情绪智力量(EIS)ID
     * @return 结果
     */
    public int deleteZzEisById(Long id);

    /**
     * 批量删除情绪智力量(EIS)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzEisByIds(Long[] ids);
    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public List<ZzEis> selectListByTaskId(Long taskId);
}
