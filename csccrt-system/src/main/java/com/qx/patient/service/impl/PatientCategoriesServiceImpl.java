package com.qx.patient.service.impl;

import com.qx.patient.domain.PatientCategories;
import com.qx.patient.mapper.PatientCategoriesMapper;
import com.qx.patient.service.IPatientCategoriesService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分类目录Service业务层处理
 * 
 * @author qx
 * @date 2020-08-10
 */
@Service
public class PatientCategoriesServiceImpl implements IPatientCategoriesService
{
    @Autowired
    private PatientCategoriesMapper patientCategoriesMapper;

    /**
     * 查询分类目录
     * 
     * @param id 分类目录ID
     * @return 分类目录
     */
    @Override
    public PatientCategories selectPatientCategoriesById(Long id)
    {
        return patientCategoriesMapper.selectPatientCategoriesById(id);
    }

    /**
     * 查询分类目录列表
     * 
     * @param patientCategories 分类目录
     * @return 分类目录
     */
    @Override
    public List<Map<String,String>> selectPatientCategoriesList(PatientCategories patientCategories)
    {
        List<PatientCategories> list= patientCategoriesMapper.selectPatientCategoriesList(patientCategories);
        List<Map<String,String>> mapList=new ArrayList<>();
        for (PatientCategories s:list) {
            Map<String,String> map=new LinkedMap<>();
            map.put("dictLabel",s.getClassificationCode());
            map.put("dictValue",s.getId()+"");
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 新增分类目录
     * 
     * @param patientCategories 分类目录
     * @return 结果
     */
    @Override
    public int insertPatientCategories(PatientCategories patientCategories)
    {
        return patientCategoriesMapper.insertPatientCategories(patientCategories);
    }

    /**
     * 修改分类目录
     * 
     * @param patientCategories 分类目录
     * @return 结果
     */
    @Override
    public int updatePatientCategories(PatientCategories patientCategories)
    {
        return patientCategoriesMapper.updatePatientCategories(patientCategories);
    }

    /**
     * 批量删除分类目录
     * 
     * @param ids 需要删除的分类目录ID
     * @return 结果
     */
    @Override
    public int deletePatientCategoriesByIds(Long[] ids)
    {
        return patientCategoriesMapper.deletePatientCategoriesByIds(ids);
    }

    /**
     * 删除分类目录信息
     * 
     * @param id 分类目录ID
     * @return 结果
     */
    @Override
    public int deletePatientCategoriesById(Long id)
    {
        return patientCategoriesMapper.deletePatientCategoriesById(id);
    }
}
