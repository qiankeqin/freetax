package com.freetax.facade.boss;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.adminMenu.service.AdminMenuService;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/15 10:44
 */
@Service
public class MenuFacade {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 查询菜单列表
     * @return
     */
    public List<AdminMenuVo> queryMenuList(){
        //return adminMenuService.queryMenuList();
        AdminMenu menu = new AdminMenu();
        UserMenuRelation relation = new UserMenuRelation();
        List<AdminMenuVo> menus = new ArrayList<>();
        //获取当前登录用户
        AdminUser adminUser = (AdminUser)SecurityUtils.getSubject().getPrincipal();
        if (adminUser != null){
            if (adminUser.getIsadmin() == 1) {//超级管理员
                //查询所有父菜单
                menus = adminMenuService.queryMenuListByVariation(relation);
                for (int i = 0; i < menus.size(); i++) {
                    relation.setMenuid(menus.get(i).getId());
                    //查询出子菜单
                    List<AdminMenu> child_menu = adminMenuService.queryMenuListBySon(relation);
                    menus.get(i).setSubmenu(child_menu);
                }
            } else {//普通用户
                relation.setUserid(adminUser.getId());
                //查询所有父菜单
                menus = adminMenuService.queryMenuListByVariation(relation);
                for (int i = 0; i < menus.size(); i++) {
                    relation.setMenuid(menus.get(i).getId());
                    List<AdminMenu> child_menu = adminMenuService.queryMenuListBySon(relation);
                   menus.get(i).setSubmenu(child_menu);
                }
            }
        }
        return menus;
    }
}
