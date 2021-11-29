package com.qx.eis.service;

import com.qx.eis.domain.EisUser;


import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 10:10
 */

public interface IEisUserService {

    List<EisUser> selectEisUsers(Long id);


    int insertUserRegister(EisUser eisUser);


    EisUser userLogin(String telNumber,String password);

    EisUser selectEisUserByTel(String telNumber);

}
