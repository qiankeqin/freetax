package com.freetax.facade.user;

import com.freetax.common.Response;
import com.freetax.common.constant.MsgCodeConstant;
import com.freetax.exception.AuthException;
import com.freetax.mybatis.user.entity.*;
import com.freetax.mybatis.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    /**
     * 修改用户个人资料
     * @param id
     * @param name
     * @param company
     * @param email
     * @return
     */
    public Response updatePersonInfo(String id, String name, String company, String email){
        Response response = new Response();

        try {
            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setCompany(company);
            user.setEmail(email);
            userService.updatePersonInfo(user);
            response.setCode(200);
            response.setMessage("修改成功");
        }catch (Exception e){
            response.setCode(300);
            response.setMessage("修改失败");
            throw e;
        }

        return response;
    }
}