package com.freetax.facade.boss;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.adminMenu.service.AdminMenuService;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import com.freetax.mybatis.userMenuRelation.service.UserMenuRelationService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author zhurui
 * @Date 2017/11/15 10:44
 */
@Service
public class MenuFacade {

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private UserMenuRelationService userMenuRelationService;

    /**
     * 查询菜单列表
     * @return
     */
    public List<AdminMenuVo> queryDynamicMenuList(){
        //return adminMenuService.queryMenuList();
        AdminMenu menu = new AdminMenu();
        UserMenuRelation relation = new UserMenuRelation();
        List<AdminMenuVo> menus = new ArrayList<>();
        //获取当前登录用户
        AdminUser adminUser = (AdminUser)SecurityUtils.getSubject().getPrincipal();
        System.out.println("登录用户id:----------------------"+adminUser.getId());
        if (adminUser != null){
            if (adminUser.getIsadmin() == 1) {//超级管理员
                System.out.println("超级管理员。。。。。。。。。。。");
                //查询所有父菜单
                menus = adminMenuService.queryMenuListByVariation(relation);
                for (int i = 0; i < menus.size(); i++) {
                    relation.setMenuid(menus.get(i).getId());
                    //查询出子菜单
                    List<AdminMenu> child_menu = adminMenuService.queryMenuListBySon(relation);
                    menus.get(i).setSubmenu(child_menu);
                }
            } else {//普通用户
                System.out.println("普通用户。。。。。。。。。。。");
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

    /**
     * 查询菜单列表
     * @return
     */
    public List<AdminMenuVo> queryMenuList(){
        UserMenuRelation userMenuRelation = new UserMenuRelation();
        //查询所有父菜单
        List<AdminMenuVo> adminMenus =  adminMenuService.queryMenuList();
        for (int i = 0;i<adminMenus.size();i++){
            userMenuRelation.setMenuid(adminMenus.get(i).getId());
            //查询所有子菜单，赋值对应父菜单中
            List<AdminMenu> adminMenu = adminMenuService.queryMenuListBySon(userMenuRelation);
            adminMenus.get(i).setSubmenu(adminMenu);
        }
        return adminMenus;
    }

    /**
     * 修改用户下的菜单列表
     * @param userid
     * @param menuid
     */
    public void updateUserMenu(String userid,String menuid){
        UserMenuRelation relation = new UserMenuRelation();
        if (StringUtils.isNotEmpty(userid)){
            relation.setUserid(Integer.parseInt(userid));
        }
        if (StringUtils.isNotEmpty(menuid)){
            //删除用户下所有菜单关系
            userMenuRelationService.deleteUserMenu(relation);
            //逗号分隔菜单id
            String[] menus = menuid.split(",");
            for (int i = 0;i<menus.length;i++) {
                relation.setMenuid(Integer.parseInt(menuid));
                //分步插入用户菜单关系
                userMenuRelationService.insertUserMenu(relation);
            }
        }
    }


    /**
     * 新增菜单
     * @param name
     * @param url
     * @param orderid
     * @param pid
     */
    public void insertMenu(String name, String url, String orderid, String pid, String ico) {
        AdminMenu adminMenus = new AdminMenu();
        if (StringUtils.isNotEmpty(name)){
            adminMenus.setName(name);
        }
        if (StringUtils.isNotEmpty(url)){
            adminMenus.setUrl(url);
        }
        if (StringUtils.isNotEmpty(orderid)){
            adminMenus.setOrderid(Integer.parseInt(orderid));
        }
        if (StringUtils.isNotEmpty(pid)){
            adminMenus.setPid(Integer.parseInt(pid));
        }
        if (StringUtils.isNotEmpty(ico)){
            adminMenus.setIco(ico);
        }
        adminMenus.setIntime(new Date());
        adminMenus.setIsdel(0);
        adminMenuService.insertMenu(adminMenus);
    }

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    public AdminMenu queryMenuById(String id){
        return adminMenuService.queryMenuById(Integer.parseInt(id));
    }

    /**
     * 修改菜单
     * @param id
     * @param name
     * @param url
     * @param orderid
     * @param pid
     */
    public void updateMenuById(String id, String name, String url, String orderid, String pid, String ico) {
        AdminMenu admiMenu = new AdminMenu();
        if (StringUtils.isNotEmpty(id)) {
            admiMenu.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(name)){
            admiMenu.setName(name);
        }
        if (StringUtils.isNotEmpty(url)){
            admiMenu.setUrl(url);
        }
        if (StringUtils.isNotEmpty(orderid)){
            admiMenu.setOrderid(Integer.parseInt(orderid));
        }
        if (StringUtils.isNotEmpty(pid)){
            admiMenu.setPid(Integer.parseInt(pid));
        }
        if (StringUtils.isNotEmpty(ico)){
            admiMenu.setIco(ico);
        }
        adminMenuService.updateMenuById(admiMenu);
    }

    /**
     * 删除菜单
     * @param id
     */
    public void deleteMenuById(String id){
        adminMenuService.deleteMenuById(Integer.parseInt(id));
    }
}
