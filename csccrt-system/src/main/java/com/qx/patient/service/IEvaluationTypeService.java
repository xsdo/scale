package com.qx.patient.service;

import com.qx.patient.domain.EvaluationType;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 测评类型Service接口
 * 
 * @author patient
 * @date 2020-07-09
 */
public interface IEvaluationTypeService 
{
    /**
     * 查询测评类型
     * 
     * @param typeId 测评类型ID
     * @return 测评类型
     */
    public EvaluationType selectEvaluationTypeById(Long typeId);

    /**
     * 查询测评类型列表
     * 
     * @param evaluationType 测评类型
     * @return 测评类型集合
     */
    public List<EvaluationType> selectEvaluationTypeList(EvaluationType evaluationType);

    /**
     * 新增测评类型
     * 
     * @param evaluationType 测评类型
     * @return 结果
     */
    public int insertEvaluationType(EvaluationType evaluationType);

    /**
     * 修改测评类型
     * 
     * @param evaluationType 测评类型
     * @return 结果
     */
    public int updateEvaluationType(EvaluationType evaluationType);

    /**
     * 批量删除测评类型
     * 
     * @param typeIds 需要删除的测评类型ID
     * @return 结果
     */
    public int deleteEvaluationTypeByIds(Long[] typeIds);

    /**
     * 删除测评类型信息
     * 
     * @param typeId 测评类型ID
     * @return 结果
     */
    public int deleteEvaluationTypeById(Long typeId);

    public List<EvaluationType> buildTypeTree(List<EvaluationType> typeList);

    public List<TreeSelect> buildTypeTreeSelect(List<EvaluationType> typeList);

    public List<EvaluationType> getChildPerms(List<EvaluationType> list, int parentId);
}
