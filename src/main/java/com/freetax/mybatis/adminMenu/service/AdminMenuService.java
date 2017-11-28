package com.freetax.mybatis.adminMenu.service;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.adminMenu.mapper.AdminMenuMapper;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:09
 */
@Service
public class AdminMenuService {

    private static Logger log = LoggerFactory.getLogger(AdminMenuService.class);

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    /**
     * 查询父级菜单列表
     * @return
     */
    public List<AdminMenuVo> queryMenuListByVariation(UserMenuRelation relation){
        try {
            log.info("查询父级菜单列表");
            return adminMenuMapper.queryMenuListByVariation(relation);
        } catch (Exception e) {
            log.error("查询父级菜单列表异常",e);
            throw e;
        }
    }

    /**
     * 查询子菜单列表
     * @param relation
     * @return
     */
    public List<AdminMenu> queryMenuListBySon(UserMenuRelation relation){
        try {
            log.info("查询子菜单列表");
            return adminMenuMapper.queryMenuListBySon(relation);
        } catch (Exception e) {
            log.error("查询子菜单列表异常",e);
            throw e;
        }
    }

    /**
     * 查询菜单列表
     * @return
     */
    public List<AdminMenuVo> queryMenuList(){
        try {
            log.info("查询菜单列表");
            return adminMenuMapper.queryMenuList();
        } catch (Exception e) {
            log.error("查询菜单列表异常",e);
            throw e;
        }
    }

    /**
     * 新增菜单
     * @param adminMenus
     */
    public void insertMenu(AdminMenu adminMenus) {
        try {
            log.info("新增菜单");
            adminMenuMapper.insertMenu(adminMenus);
        } catch (Exception e){
            log.error("新增菜单异常",e);
            throw e;
        }
    }

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    public AdminMenu queryMenuById(Integer id){
        try {
            log.info("根据id查询菜单");
            return adminMenuMapper.queryMenuById(id);
        } catch (Exception e){
            log.error("根据id查询菜单异常",e);
            throw e;
        }
    }

    /**
     * 修改菜单
     * @param adminMenu
     */
    public void updateMenuById(AdminMenu adminMenu){
        try {
            log.info("修改菜单");
            adminMenuMapper.updateMenuById(adminMenu);
        } catch (Exception e){
            log.error("修改菜单异常",e);
            throw e;
        }
    }

    /**
     * 删除菜单
     * @param id
     */
    public void deleteMenuById(Integer id) {
        try {
            log.info("删除菜单");
            adminMenuMapper.deleteMenuById(id);
        } catch (Exception e){
            log.error("删除菜单异常",e);
            throw e;
        }
    }
}
