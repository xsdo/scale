package com.qx.patient.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.patient.mapper.EvaluationTypeMapper;
import com.qx.patient.domain.EvaluationType;
import com.qx.patient.service.IEvaluationTypeService;

/**
 * 测评类型Service业务层处理
 * 
 * @author patient
 * @date 2020-07-09
 */
@Service
public class EvaluationTypeServiceImpl implements IEvaluationTypeService 
{
    @Autowired
    private EvaluationTypeMapper evaluationTypeMapper;

    /**
     * 查询测评类型
     * 
     * @param typeId 测评类型ID
     * @return 测评类型
     */
    @Override
    public EvaluationType selectEvaluationTypeById(Long typeId)
    {
        return evaluationTypeMapper.selectEvaluationTypeById(typeId);
    }

    /**
     * 查询测评类型列表
     * 
     * @param evaluationType 测评类型
     * @return 测评类型
     */
    @Override
    public List<EvaluationType> selectEvaluationTypeList(EvaluationType evaluationType)
    {
        return evaluationTypeMapper.selectEvaluationTypeList(evaluationType);
    }

    /**
     * 新增测评类型
     * 
     * @param evaluationType 测评类型
     * @return 结果
     */
    @Override
    public int insertEvaluationType(EvaluationType evaluationType)
    {
        return evaluationTypeMapper.insertEvaluationType(evaluationType);
    }

    /**
     * 修改测评类型
     * 
     * @param evaluationType 测评类型
     * @return 结果
     */
    @Override
    public int updateEvaluationType(EvaluationType evaluationType)
    {
        return evaluationTypeMapper.updateEvaluationType(evaluationType);
    }

    /**
     * 批量删除测评类型
     * 
     * @param typeIds 需要删除的测评类型ID
     * @return 结果
     */
    @Override
    public int deleteEvaluationTypeByIds(Long[] typeIds)
    {
        return evaluationTypeMapper.deleteEvaluationTypeByIds(typeIds);
    }

    /**
     * 删除测评类型信息
     * 
     * @param typeId 测评类型ID
     * @return 结果
     */
    @Override
    public int deleteEvaluationTypeById(Long typeId)
    {
        return evaluationTypeMapper.deleteEvaluationTypeById(typeId);
    }


    /**
     * 构建前端所需要树结构
     *
     * @return 树结构列表
     */
    @Override
    public List<EvaluationType> buildTypeTree(List<EvaluationType> typeList)
    {
        List<EvaluationType> returnList = new ArrayList<EvaluationType>();
        for (Iterator<EvaluationType> iterator = typeList.iterator(); iterator.hasNext();)
        {
            EvaluationType e = (EvaluationType) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (e.getParentId() == 0)
            {
                recursionFn(typeList, e);
                returnList.add(e);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = typeList;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *

     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildTypeTreeSelect(List<EvaluationType> typeList)
    {
        List<EvaluationType> typeList1 = buildTypeTree(typeList);
        return typeList1.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<EvaluationType> list, EvaluationType t)
    {
        // 得到子节点列表
        List<EvaluationType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (EvaluationType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<EvaluationType> it = childList.iterator();
                while (it.hasNext())
                {
                    EvaluationType n = (EvaluationType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<EvaluationType> list, EvaluationType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    /**
     * 得到子节点列表
     */
    private List<EvaluationType> getChildList(List<EvaluationType> list, EvaluationType t)
    {
        List<EvaluationType> tlist = new ArrayList<EvaluationType>();
        Iterator<EvaluationType> it = list.iterator();
        while (it.hasNext())
        {
            EvaluationType n = (EvaluationType) it.next();
            if (n.getParentId().longValue() == t.getTypeId().longValue())
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
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<EvaluationType> getChildPerms(List<EvaluationType> list, int parentId)
    {
        List<EvaluationType> returnList = new ArrayList<EvaluationType>();
        for (Iterator<EvaluationType> iterator = list.iterator(); iterator.hasNext();)
        {
           EvaluationType t = (EvaluationType) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

}
