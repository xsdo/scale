package com.qx.patient.mapper;

import com.qx.patient.domain.SysHometown;

import java.util.List;

/**
 * 籍贯Mapper接口
 * 
 * @author qx
 * @date 2020-08-10
 */
public interface SysHometownMapper 
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
    public List<SysHometown> selectSysHometownList(SysHometown sysHometown);

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
     * 删除籍贯
     * 
     * @param id 籍贯ID
     * @return 结果
     */
    public int deleteSysHometownById(Long id);

    /**
     * 批量删除籍贯
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysHometownByIds(Long[] ids);
}
