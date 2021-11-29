package com.qx.ipa.mapper;

import com.qx.ipa.domain.IpaPatientUser;
import org.mapstruct.MapperConfig;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 心身调节系统患者Mapper接口
 * 
 * @author q
 * @date 2021-07-02
 */

public interface IpaPatientUserMapper 
{
    /**
     * 查询心身调节系统患者
     * 
     * @param patientId 心身调节系统患者ID
     * @return 心身调节系统患者
     */
    public IpaPatientUser selectIpaPatientUserById(Long patientId);

    /**
     * 查询心身调节系统患者列表
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 心身调节系统患者集合
     */
    public List<IpaPatientUser> selectIpaPatientUserList(IpaPatientUser ipaPatientUser);

    /**
     * 新增心身调节系统患者
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 结果
     */
    public int insertIpaPatientUser(IpaPatientUser ipaPatientUser);

    /**
     * 修改心身调节系统患者
     * 
     * @param ipaPatientUser 心身调节系统患者
     * @return 结果
     */
    public int updateIpaPatientUser(IpaPatientUser ipaPatientUser);

    /**
     * 删除心身调节系统患者
     * 
     * @param patientId 心身调节系统患者ID
     * @return 结果
     */
    public int deleteIpaPatientUserById(Long patientId);

    /**
     * 批量删除心身调节系统患者
     * 
     * @param patientIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIpaPatientUserByIds(Long[] patientIds);
}
