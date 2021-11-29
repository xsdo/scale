package com.qx.patient.service;

import com.qx.patient.domain.PatientCategories;

import java.util.List;
import java.util.Map;

/**
 * 分类目录Service接口
 * 
 * @author qx
 * @date 2020-08-10
 */
public interface IPatientCategoriesService 
{
    /**
     * 查询分类目录
     * 
     * @param id 分类目录ID
     * @return 分类目录
     */
    public PatientCategories selectPatientCategoriesById(Long id);

    /**
     * 查询分类目录列表
     * 
     * @param patientCategories 分类目录
     * @return 分类目录集合
     */
    public List<Map<String,String>>  selectPatientCategoriesList(PatientCategories patientCategories);

    /**
     * 新增分类目录
     * 
     * @param patientCategories 分类目录
     * @return 结果
     */
    public int insertPatientCategories(PatientCategories patientCategories);

    /**
     * 修改分类目录
     * 
     * @param patientCategories 分类目录
     * @return 结果
     */
    public int updatePatientCategories(PatientCategories patientCategories);

    /**
     * 批量删除分类目录
     * 
     * @param ids 需要删除的分类目录ID
     * @return 结果
     */
    public int deletePatientCategoriesByIds(Long[] ids);

    /**
     * 删除分类目录信息
     * 
     * @param id 分类目录ID
     * @return 结果
     */
    public int deletePatientCategoriesById(Long id);
}
