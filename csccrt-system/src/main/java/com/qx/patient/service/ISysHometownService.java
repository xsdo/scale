package com.qx.patient.service;

import com.qx.patient.domain.SysHometown;

import java.util.List;
import java.util.Map;

/**
 * 籍贯Service接口
 * 
 * @author qx
 * @date 2020-08-10
 */
public interface ISysHometownService 
{
    /**
     * 查询籍贯
     * 
     * @param id 籍贯ID
     * @return 籍贯
     */
    public SysHometown selectSysHometownById(Long id);

    /**
     * 查询籍贯列表
     * 
     * @param sysHometown 籍贯
     * @return 籍贯集合
     */
    public List<Map<String,String>> selectSysHometownList(SysHometown sysHometown);

    /**
     * 新增籍贯
     * 
     * @param sysHometown 籍贯
     * @return 结果
     */
    public int insertSysHometown(SysHometown sysHometown);

    /**
     * 修改籍贯
     * 
     * @param sysHometown 籍贯
     * @return 结果
     */
    public int updateSysHometown(SysHometown sysHometown);

    /**
     * 批量删除籍贯
     * 
     * @param ids 需要删除的籍贯ID
     * @return 结果
     */
    public int deleteSysHometownByIds(Long[] ids);

    /**
     * 删除籍贯信息
     * 
     * @param id 籍贯ID
     * @return 结果
     */
    public int deleteSysHometownById(Long id);
}
