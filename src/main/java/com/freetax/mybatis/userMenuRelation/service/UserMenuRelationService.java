package com.freetax.mybatis.userMenuRelation.service;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import com.freetax.mybatis.userMenuRelation.mapper.UserMenuRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/15 14:14
 */
@Service
public class UserMenuRelationService {

    private static Logger log = LoggerFactory.getLogger(UserMenuRelationService.class);

    @Autowired
    private UserMenuRelationMapper userMenuRelationMapper;

    /**
     * 删除用户下所有菜单关系
     * @param relation
     */
    public void deleteUserMenu(UserMenuRelation relation){
        try {
            log.info("删除用户下所有菜单关系");
            userMenuRelationMapper.deleteUserMenu(relation);
        } catch (Exception e) {
            log.error("删除用户下所有菜单关系异常",e);
            throw e;
        }
    }

    /**
     * 新增用户菜单
     * @param relation
     */
    public void insertUserMenu(UserMenuRelation relation){
        try {
            log.info("增加用户和菜单关系");
            userMenuRelationMapper.insertUserMenu(relation);
        } catch (Exception e) {
            log.error("增加用户和菜单关系异常",e);
            throw e;
        }
    }

    /**
     * 查询用户菜单
     * @param id
     * @return
     */
    public List<AdminMenuVo> queryUserByMenuToList(Integer id){
        try {
            log.info("查询用户菜单");
            return userMenuRelationMapper.queryUserByMenuToList(id);
        } catch (Exception e){
            log.error("查询用户菜单异常",e);
            throw e;
        }
    }
}
