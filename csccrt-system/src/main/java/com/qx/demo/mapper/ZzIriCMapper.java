package com.qx.demo.mapper;

import com.qx.demo.entity.ZzDld;
import com.qx.demo.entity.ZzIriC;
import java.util.List;

/**
 * 人际反应指针Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzIriCMapper 
{
    /**
     * 查询人际反应指针
     * 
     * @param id 人际反应指针ID
     * @return 人际反应指针
     */
    public ZzIriC selectZzIriCById(Long id);

    /**
     * 查询人际反应指针列表
     * 
     * @param zzIriC 人际反应指针
     * @return 人际反应指针集合
     */
    public List<ZzIriC> selectZzIriCList(ZzIriC zzIriC);

    /**
     * 新增人际反应指针
     * 
     * @param zzIriC 人际反应指针
     * @return 结果
     */
    public int insertZzIriC(ZzIriC zzIriC);

    /**
     * 修改人际反应指针
     * 
     * @param zzIriC 人际反应指针
     * @return 结果
     */
    public int updateZzIriC(ZzIriC zzIriC);

    /**
     * 删除人际反应指针
     * 
     * @param id 人际反应指针ID
     * @return 结果
     */
    public int deleteZzIriCById(Long id);

    /**
     * 批量删除人际反应指针
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzIriCByIds(Long[] ids);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public List<ZzIriC> selectListByTaskId(Long taskId);
}
