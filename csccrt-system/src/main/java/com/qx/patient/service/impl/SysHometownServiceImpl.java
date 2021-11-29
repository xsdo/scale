package com.qx.patient.service.impl;

import com.qx.patient.domain.SysHometown;
import com.qx.patient.mapper.SysHometownMapper;
import com.qx.patient.service.ISysHometownService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 籍贯Service业务层处理
 * 
 * @author qx
 * @date 2020-08-10
 */
@Service
public class SysHometownServiceImpl implements ISysHometownService
{
    @Autowired
    private SysHometownMapper sysHometownMapper;

    /**
     * 查询籍贯
     * 
     * @param id 籍贯ID
     * @return 籍贯
     */
    @Override
    public SysHometown selectSysHometownById(Long id)
    {
        return sysHometownMapper.selectSysHometownById(id);
    }

    /**
     * 查询籍贯列表
     * 
     * @param sysHometown 籍贯
     * @return 籍贯
     */
    @Override
    public List<Map<String,String>> selectSysHometownList(SysHometown sysHometown)
    {
        List<SysHometown> list= sysHometownMapper.selectSysHometownList(sysHometown);
        List<Map<String,String>> mapList=new ArrayList<>();
        for (SysHometown s:list) {
            Map<String,String> map=new LinkedMap<>();
            map.put("dictLabel",s.getHometown());
            map.put("dictValue",s.getId()+"");
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 新增籍贯
     * 
     * @param sysHometown 籍贯
     * @return 结果
     */
    @Override
    public int insertSysHometown(SysHometown sysHometown)
    {
        return sysHometownMapper.insertSysHometown(sysHometown);
    }

    /**
     * 修改籍贯
     * 
     * @param sysHometown 籍贯
     * @return 结果
     */
    @Override
    public int updateSysHometown(SysHometown sysHometown)
    {
        return sysHometownMapper.updateSysHometown(sysHometown);
    }

    /**
     * 批量删除籍贯
     * 
     * @param ids 需要删除的籍贯ID
     * @return 结果
     */
    @Override
    public int deleteSysHometownByIds(Long[] ids)
    {
        return sysHometownMapper.deleteSysHometownByIds(ids);
    }

    /**
     * 删除籍贯信息
     * 
     * @param id 籍贯ID
     * @return 结果
     */
    @Override
    public int deleteSysHometownById(Long id)
    {
        return sysHometownMapper.deleteSysHometownById(id);
    }
}
