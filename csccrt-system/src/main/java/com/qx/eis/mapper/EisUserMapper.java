package com.qx.eis.mapper;

import com.qx.eis.domain.EisUser;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 9:32
 */
public interface EisUserMapper {

  public List<EisUser> selectEisUsers (Long id);

  public EisUser selectEisUserById(long userId);

  public EisUser selectEisUserByTel(String telNumber);

  public int insertEisUser(EisUser eisUser);

}
