package com.freetax.mybatis.user.service;

import com.freetax.mybatis.user.entity.*;
import com.freetax.mybatis.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author shuxf
 * @Date 2017/10/30 19:43
 */
@Service
@Transactional
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public Integer queryIsRegister(String mobile){
        try {
            log.info("查询当前手机号是否已注册");
            return userMapper.queryIsRegister(mobile);
        }catch (Exception e){
            log.error("查询当前手机号是否已注册失败", e);
            throw e;
        }
    }

    public LoginUser selectLoginUserByToken(Map<String, Object> parammap) {
        try {
            log.info("根据token查询用户信息");
            return userMapper.selectLoginUserByToken(parammap);
        } catch (Exception e) {
            log.error("根据token查询用户信息失败", e);
            throw e;
        }
    }

    public LoginUser selectLoginuserByUserid(String mobile) {
        try {
            log.info("根据用户id查询LoginUser");
            return userMapper.selectLoginuserByUserid(mobile);
        } catch (Exception e) {
            log.error("根据用户id查询LoginUser失败", e);
            throw e;
        }
    }

    public void pcRegister(User user){
        try {
            log.info("注册插入用户信息");
            userMapper.insertSelective(user);
        }catch (Exception e){
            log.error("注册插入用户信息失败", e);
            throw e;
        }
    }

    public void updateLoginappuserInfo(User user){
        try {
            log.info("更新用户登录时间");
            userMapper.updateLoginappuserInfo(user);
        }catch (Exception e){
            log.error("更新用户登录时间错误");
            throw e;
        }
    }
}
