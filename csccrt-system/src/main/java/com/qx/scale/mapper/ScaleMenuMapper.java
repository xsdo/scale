package com.qx.scale.mapper;

import com.qx.scale.domain.ScaleMenu;
import java.util.List;

/**
 * 量表目录Mapper接口
 * 
 * @author patient
 * @date 2021-09-27
 */
public interface ScaleMenuMapper 
{
    /**
     * 查询量表目录
     * 
     * @param id 量表目录ID
     * @return 量表目录
     */
    public ScaleMenu selectScaleMenuById(Long id);

    /**
     * 查询量表目录列表
     * 
     * @param scaleMenu 量表目录
     * @return 量表目录集合
     */
    public List<ScaleMenu> selectScaleMenuList(ScaleMenu scaleMenu);



    /**
     * 新增量表目录
     * 
     * @param scaleMenu 量表目录
     * @return 结果
     */
    public int insertScaleMenu(ScaleMenu scaleMenu);

    /**
     * 修改量表目录
     * 
     * @param scaleMenu 量表目录
     * @return 结果
     */
    public int updateScaleMenu(ScaleMenu scaleMenu);


    int hasChildById(Long id);
    /**
     * 删除量表目录
     * 
     * @param id 量表目录ID
     * @return 结果
     */
    public int deleteScaleMenuById(Long id);

    /**
     * 批量删除量表目录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScaleMenuByIds(Long[] ids);
}
