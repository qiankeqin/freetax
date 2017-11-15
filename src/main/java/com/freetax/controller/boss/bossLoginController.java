package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.utils.MD5Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:26
 */
@RestController
@RequestMapping("/boss/login")
public class bossLoginController {

    @ApiOperation(value = "登录", notes = "用于登录", response = Response.class)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Response Login(@ApiParam(value = "用户名") @RequestParam String name,
                          @ApiParam(value = "密码") @RequestParam String password) {
        Response response = new Response();
        UsernamePasswordToken token;
        Subject subject = SecurityUtils.getSubject();
        try {
            token = new UsernamePasswordToken(name, MD5Util.MD5Encode(password, "UTF-8"));//对密码加密比对
            subject.login(token);
            response.setMessage("登录成功");
            Subject ject = SecurityUtils.getSubject();
            AdminUser user = (AdminUser) ject.getPrincipal();
            response.setData(user);
        } catch (UnknownAccountException e) {
            response.setCode(400);
            response.setMessage("用户名不存在");
            response.setData(-1);
            return response;
        } catch (LockedAccountException e) {
            response.setCode(400);
            response.setMessage("账号状态异常");
            response.setData(-1);
            return response;
        } catch (AuthenticationException e) {
            response.setCode(400);
            response.setMessage("用户名或密码错误");
            response.setData(-1);
            return response;
        }

        return response;
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", response = Response.class)
    @RequestMapping(value = "logOut", method = RequestMethod.POST)
    public Response logOut() {
        Response response = new Response();
        SecurityUtils.getSubject().logout();
        return response;
    }
}
