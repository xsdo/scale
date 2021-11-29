package com.qx.scale.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.EvaluationType;
import com.qx.scale.domain.ScaleTask;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScaleMenuMapper;
import com.qx.scale.domain.ScaleMenu;
import com.qx.scale.service.IScaleMenuService;

/**
 * 量表目录Service业务层处理
 * 
 * @author patient
 * @date 2021-09-27
 */
@Service
public class ScaleMenuServiceImpl implements IScaleMenuService 
{
    @Autowired
    private ScaleMenuMapper scaleMenuMapper;

    /**
     * 查询量表目录
     * 
     * @param id 量表目录ID
     * @return 量表目录
     */
    @Override
    public ScaleMenu selectScaleMenuById(Long id)
    {
        return scaleMenuMapper.selectScaleMenuById(id);
    }

    /**
     * 查询量表目录列表
     * 
     * @param scaleMenu 量表目录
     * @return 量表目录
     */
    @Override
    public List<ScaleMenu> selectScaleMenuList(ScaleMenu scaleMenu)
    {
        return scaleMenuMapper.selectScaleMenuList(scaleMenu);
    }

    /*@Override
    public List<ScaleMenu> listByScaleName(String scaleName)
    {
        ScaleMenu scaleMenu =new ScaleMenu();
        scaleMenu.setScaleName(scaleName);
        return scaleMenuMapper.selectScaleMenuList(scaleMenu);
    }*/
    @Override
    public List<ScaleMenu> scaleMenuListByTask(List<ScaleTask> task){
        List<ScaleMenu> scaleMenuList =new ArrayList<ScaleMenu>();
        for (ScaleTask scaleTask:task){
            Long l=Long.parseLong(scaleTask.getScaleId());
            ScaleMenu scaleMenu =scaleMenuMapper.selectScaleMenuById(l);
            scaleMenuList.add(scaleMenu);
        }
        return scaleMenuList;
    }
    /**
     * 新增量表目录
     * 
     * @param scaleMenu 量表目录
     * @return 结果
     */
    @Override
    public int insertScaleMenu(ScaleMenu scaleMenu)
    {
        scaleMenu.setCreateTime(DateUtils.getNowDate());
        return scaleMenuMapper.insertScaleMenu(scaleMenu);
    }

    /**
     * 修改量表目录
     * 
     * @param scaleMenu 量表目录
     * @return 结果
     */
    @Override
    public int updateScaleMenu(ScaleMenu scaleMenu)
    {
        scaleMenu.setUpdateTime(DateUtils.getNowDate());
        return scaleMenuMapper.updateScaleMenu(scaleMenu);
    }


    @Override
    public boolean hasChildById(Long id) {
        int result = scaleMenuMapper.hasChildById(id);
        return result > 0 ? true : false;
    }
    /**
     * 批量删除量表目录
     * 
     * @param ids 需要删除的量表目录ID
     * @return 结果
     */
    @Override
    public int deleteScaleMenuByIds(Long[] ids)
    {
        return scaleMenuMapper.deleteScaleMenuByIds(ids);
    }

    /**
     * 删除量表目录信息
     * 
     * @param id 量表目录ID
     * @return 结果
     */
    @Override
    public int deleteScaleMenuById(Long id)
    {
        return scaleMenuMapper.deleteScaleMenuById(id);
    }
    /**
     * 构建前端所需要树结构
     *
     * @return 树结构列表
     */
    @Override
    public List<ScaleMenu> buildTypeTree(List<ScaleMenu> menuList)
    {
        List<ScaleMenu> returnList = new ArrayList<ScaleMenu>();
        for (Iterator<ScaleMenu> iterator = menuList.iterator(); iterator.hasNext();)
        {
            ScaleMenu s = (ScaleMenu) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (s.getPid() == 0)
            {
                recursionFn(menuList, s);
                returnList.add(s);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menuList;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *

     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildTypeTreeSelect(List<ScaleMenu> typeList)
    {
        List<ScaleMenu> typeList1 = buildTypeTree(typeList);
        return typeList1.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<ScaleMenu> list, ScaleMenu t)
    {
        // 得到子节点列表
        List<ScaleMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ScaleMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<ScaleMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    ScaleMenu n = (ScaleMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ScaleMenu> list, ScaleMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    /**
     * 得到子节点列表
     */
    private List<ScaleMenu> getChildList(List<ScaleMenu> list, ScaleMenu t)
    {
        List<ScaleMenu> tlist = new ArrayList<ScaleMenu>();
        Iterator<ScaleMenu> it = list.iterator();
        while (it.hasNext())
        {
            ScaleMenu n = (ScaleMenu) it.next();
            if (n.getPid().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param
     * @return String
     */
    public List<ScaleMenu> getChildPerms(List<ScaleMenu> list, int pid)
    {
        List<ScaleMenu> returnList = new ArrayList<ScaleMenu>();
        for (Iterator<ScaleMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            ScaleMenu t = (ScaleMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == pid)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }
}
