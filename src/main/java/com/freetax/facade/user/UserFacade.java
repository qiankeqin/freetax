package com.freetax.facade.user;

import com.freetax.common.constant.MsgCodeConstant;
import com.freetax.exception.AuthException;
import com.freetax.mybatis.user.entity.*;
import com.freetax.mybatis.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * APP用户 facade
 * @Author shuxf
 * @Date 2017/10/30 20:20
 */
@Service
public class UserFacade {

    private static Logger log = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    private UserService userService;

    /**
     * 根据token获取app登录用户信息
     * @param parammap
     * @return
     */
    public LoginUser getLoginUserByToken(Map<String, Object> parammap) {

        LoginUser loginUser = userService.selectLoginUserByToken(parammap);
        if (null == loginUser) {
            throw new AuthException(MsgCodeConstant.app_user_not_exist_with_this_token, "该token的app用户不存在");
        }
        return loginUser;
    }

    /**
     * 根据用户id获取Loginuser
     *
     * @param mobile
     * @return
     */
    public LoginUser getLoginuserByUserid(String mobile) {
        return userService.selectLoginuserByUserid(mobile);
    }


}