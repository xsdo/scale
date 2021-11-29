package com.qx.eis.service.impl;

import com.qx.common.exception.user.UserPasswordNotMatchException;
import com.qx.eis.domain.EisUser;
import com.qx.eis.mapper.EisUserMapper;
import com.qx.eis.service.IEisUserService;
import com.qx.patient.util.ExportExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 10:11
 */
@Service
public class EisUserServiceImpl implements IEisUserService {

    private static final Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);
    @Autowired
    private EisUserMapper eisUserMapper;

    @Override
    public List<EisUser> selectEisUsers(Long id){
        return  eisUserMapper.selectEisUsers(id);
    }

    @Override
    public int insertUserRegister(EisUser eisUser){
        //查询用户是否存在
        String telNumber =eisUser.getTelNumber();
        //EisUser users= eisUserMapper.selectEisUserById(userId);
        EisUser user =eisUserMapper.selectEisUserByTel(telNumber);
        if (user==null){
            eisUser.setUserType("0");
            eisUser.setStatus("0");
            eisUser.setDelFlag("0");
            eisUser.setCreateDate(new Date());
            eisUser.setLoginDate(new Date());
            return (eisUserMapper.insertEisUser(eisUser));
        }else {
            return 0;
        }

    }




    @Override
    public EisUser userLogin (String telNumber,String password){
        EisUser eisUser=new EisUser();
        //查询用户是否存在
        EisUser user =eisUserMapper.selectEisUserByTel(telNumber);

        if (user!=null){
            //log.error("用户不为空");
            //log.error(user.getPassword());
            //log.error(password);
            if (user.getPassword()==password||user.getPassword().equals(password)){
                return user;
            }
        }else {
            log.error("用户为空");
            return eisUser;
        }
        return eisUser;
    }

    @Override
    public EisUser selectEisUserByTel(String telNumber){
        //查询用户是否存在
        EisUser user =eisUserMapper.selectEisUserByTel(telNumber);
        if (user!=null){
            log.info("用户信息查询");
            return user;
        }else {
            return null;
        }
    }
}
