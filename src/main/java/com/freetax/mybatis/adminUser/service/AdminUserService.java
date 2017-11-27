package com.freetax.mybatis.adminUser.service;

import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.adminUser.entity.AdminUserVo;
import com.freetax.mybatis.adminUser.mapper.AdminUserMapper;
import com.freetax.utils.pagination.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:48
 */
@Service
public class AdminUserService {

    private static Logger log = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 新增boss后台用户
     * @param adminUser
     */
    public void insertAdminUser(AdminUser adminUser){
        try {
            log.info("新增boss后台用户");
            adminUserMapper.insertUserByMenu(adminUser);
        } catch (Exception e) {
            log.error("新增boss后台用户异常",e);
            throw e;
        }
    }

    /**
     * 修改用户信息
     * @param adminUser
     */
    public void updateAdminUserById(AdminUser adminUser){
        try {
            log.info("修改用户信息");
            adminUserMapper.updateByPrimaryKeySelective(adminUser);
        } catch (Exception e) {
            log.error("修改用户信息异常",e);
            throw e;
        }
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    public AdminUserVo queryAdminUserById(Integer id){
        try {
            log.info("查询用户详情");
            return adminUserMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("查询用户详情异常",e);
            throw e;
        }
    }

    /**
     * 查询用户列表
     * @param adminUser
     * @param pag
     * @return
     */
    public List<AdminUser> queryAdminUserByList(AdminUser adminUser, Paging<AdminUser> pag){
        try {
            log.info("查询用户列表");
            return adminUserMapper.findAllQueryAdminUserByList(adminUser,pag.getRowBounds());
        } catch (Exception e) {
            log.error("查询用户列表异常",e);
            throw e;
        }
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAdminUserById(Integer id){
        try {
            log.info("删除用户");
            adminUserMapper.deleteAdminUserById(id);
        } catch (Exception e) {
            log.error("删除用户异常",e);
            throw e;
        }
    }

    public AdminUser queryUserPasswordByName(String name){
        return adminUserMapper.queryUserPasswordByName(name);
    }

    public AdminUser queryUserPasswordByPhone(String phone){
        return adminUserMapper.queryUserPasswordByPhone(phone);
    }

}
