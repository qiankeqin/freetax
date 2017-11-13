package com.freetax.mybatis.user.service;

import com.freetax.mybatis.user.entity.*;
import com.freetax.mybatis.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public LoginUser selectLoginUserByToken(String token) {
        try {
            log.info("根据token查询用户信息");
            return userMapper.selectLoginUserByToken(token);
        } catch (Exception e) {
            log.error("根据token查询用户信息失败", e);
            throw e;
        }
    }

    public LoginUser selectLoginuserByUserid(Integer userid) {
        try {
            log.info("根据用户id查询LoginUser");
            return userMapper.selectLoginuserByUserid(userid);
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
}
