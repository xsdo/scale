package com.qx.scale.service;

import com.qx.scale.domain.ScaleMenu;
import com.qx.scale.domain.ScaleTask;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 量表目录Service接口
 * 
 * @author patient
 * @date 2021-09-27
 */
public interface IScaleMenuService 
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

//    List<ScaleMenu> listByScaleName(String scaleName);

    List<ScaleMenu> scaleMenuListByTask(List<ScaleTask> task);

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

    boolean hasChildById(Long id);

    /**
     * 批量删除量表目录
     * 
     * @param ids 需要删除的量表目录ID
     * @return 结果
     */
    public int deleteScaleMenuByIds(Long[] ids);

    /**
     * 删除量表目录信息
     * 
     * @param id 量表目录ID
     * @return 结果
     */
    public int deleteScaleMenuById(Long id);

    List<ScaleMenu> buildTypeTree(List<ScaleMenu> menuList);

    List<TreeSelect> buildTypeTreeSelect(List<ScaleMenu> typeList);
}
