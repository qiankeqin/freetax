package com.freetax.mybatis.user.service;

import com.freetax.mybatis.user.entity.*;
import com.freetax.mybatis.user.mapper.UserMapper;
import com.freetax.utils.pagination.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public void updatePersonInfo(User user){
        try {
            log.info("个人资料修改成功");
            userMapper.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            log.error("个人资料修改失败");
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

    /**
     * 修改会员用户信息
     * @param user
     */
    public void updateUser(User user){
        try {
            log.info("修改会员用户信息");
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            log.error("修改会员用户信息异常",e);
            throw e;
        }
    }

    public void updatePasswd(Map<String, Object> parammap){
        try {
            log.info("PC用户修改密码");
            userMapper.updatePasswd(parammap);
        }catch (Exception e){
            log.error("PC用户密码修改失败", e);
            throw e;
        }
    }

    /**
     *  根据id查询用户信息
     * @param id
     * @return
     */
    public User queryUserById(Integer id){
        try {
            log.info("根据id查询用户信息");
            return userMapper.queryUserById(id);
        } catch (Exception e) {
            log.error("根据id查询用户信息异常",e);
            throw e;
        }
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUserById(Integer id){
        try {
            log.info("删除用户");
            userMapper.deleteUserById(id);
        } catch (Exception e) {
            log.error("删除用户异常",e);
            throw e;
        }
    }

    /**
     * 更新用户联系状态
     * @param user
     */
    public void updateUserByMark(User user){
        try {
            log.info("更新用户联系状态");
            userMapper.updateUserByMark(user);
        } catch (Exception e) {
            log.error("更新用户联系状态异常",e);
            throw e;
        }
    }

    /**
     * 查询用户列表
     * @param userVo
     * @return
     */
    public List<User> queryUserByList(UserVo userVo, Paging<User> pag){
        try {
            log.info("查询用户列表");
            return userMapper.findAllQueryUserByList(userVo,pag.getRowBounds());
        } catch (Exception e) {
            log.error("查询用户列表异常",e);
            throw e;
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<UserExcel> queryUserByAll(){
        try {
            log.info("查询所有用户");
            return userMapper.queryUserByAll();
        } catch (Exception e){
            log.error("查询所有用户异常",e);
            throw e;
        }
    }
}
