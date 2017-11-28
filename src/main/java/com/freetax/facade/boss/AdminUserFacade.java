package com.freetax.facade.boss;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.adminMenu.service.AdminMenuService;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.adminUser.entity.AdminUserVo;
import com.freetax.mybatis.adminUser.service.AdminUserService;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import com.freetax.mybatis.userMenuRelation.service.UserMenuRelationService;
import com.freetax.utils.MD5Util;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:55
 */
@Service
public class AdminUserFacade {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private UserMenuRelationService userMenuRelationService;

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 新增boss用户
     * @param email
     * @param phone
     * @param remark
     * @param nickname
     * @param password
     * @param menus
     * @return
     */
    public Map insertAdminUser(String email, String phone, String remark, String nickname, String password, String menus){
        Map resault = new HashMap();
        try {
            AdminUser adminUser = new AdminUser();
            if (StringUtils.isNotEmpty(email)){
                adminUser.setEmail(email);
            }
            if (StringUtils.isNotEmpty(phone)){
                adminUser.setPhone(phone);
            }
            if (StringUtils.isNotEmpty(remark)){
                adminUser.setRemark(remark);
            }
            if (StringUtils.isNotEmpty(nickname)){
                adminUser.setNickname(nickname);
            }
            if (StringUtils.isNotEmpty(password)){
                adminUser.setPassword(MD5Util.MD5Encode(password, "UTF-8"));
            }
            adminUser.setIsdel(0);
            adminUser.setIntime(new Date());
            adminUserService.insertAdminUser(adminUser);
            //设置用户和菜单关系
            if (StringUtils.isNotEmpty(menus)) {
                setUserByMenu(adminUser.getId().toString(), menus);
            }
            resault.put("code",200);
        } catch (NumberFormatException e) {
            resault.put("code",300);
            e.printStackTrace();
        }
        return resault;
    }

    /**
     * 修改用户信息
     * @param id
     * @param email
     * @param remark
     * @param nickname
     * @param password
     * @param menus
     */
    public void updateAdminUserById(String id,String email, String remark, String nickname, String password, String menus){
        AdminUser adminUser = new AdminUser();
        adminUser.setId(Integer.parseInt(id));
        if (StringUtils.isNotEmpty(email)){
            adminUser.setEmail(email);
        }
        if (StringUtils.isNotEmpty(remark)){
            adminUser.setRemark(remark);
        }
        if (StringUtils.isNotEmpty(nickname)){
            adminUser.setNickname(nickname);
        }
        if (StringUtils.isNotEmpty(password)){
            adminUser.setPassword(MD5Util.MD5Encode(password, "UTF-8"));
        }
        if (StringUtils.isNotEmpty(menus)){
            setUserByMenu(id, menus);
        }
        adminUserService.updateAdminUserById(adminUser);
    }

    /**
     * 维护用户和菜单关系
     * @param id
     * @param menus
     */
    private void setUserByMenu(String id, String menus) {
        UserMenuRelation menuRelation = new UserMenuRelation();
        menuRelation.setUserid(Integer.parseInt(id));
        //清除该用户的菜单绑定
        userMenuRelationService.deleteUserMenu(menuRelation);
        String[] menuList = menus.split(",");
        for (int i = 0 ;i<menuList.length;i++){
            menuRelation.setMenuid(Integer.parseInt(menuList[i]));
            userMenuRelationService.insertUserMenu(menuRelation);
        }
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    public AdminUserVo queryAdminUserById(String id){
        AdminUserVo adminUserVo = null;
        //查询用户的菜单
        List<AdminMenuVo> menus = userMenuRelationService.queryUserByMenuToList(Integer.parseInt(id));
        //查询所有菜单
        List<AdminMenuVo> adminMenus = adminMenuService.queryMenuList();
        //循环标记哪些菜单是已有，哪些是没有绑定的，以true和false代表
        for (int k = 0 ;k<adminMenus.size();k++){
            if (menus != null) {
                for (int i = 0; i < menus.size(); i++) {
                    if (menus.get(i).getId() == adminMenus.get(k).getId()) {
                        adminMenus.get(k).setUse(true);
                        break;
                    } else {
                        adminMenus.get(k).setUse(false);
                    }
                }
            }else {
                adminMenus.get(k).setUse(false);
            }
        }
        adminUserVo = adminUserService.queryAdminUserById(Integer.parseInt(id));
        adminUserVo.setMenus(adminMenus);
        return adminUserVo;
    }

    /**
     * 查询用户列表
     * @param nickname
     * @param phone
     * @param pag
     * @return
     */
    public List<AdminUserVo> queryAdminUserByList(String nickname,String phone,Paging<AdminUserVo> pag){
        AdminUser adminUser = new AdminUser();
        List<AdminUserVo> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(nickname)){
            adminUser.setNickname(nickname);
        }
        if (StringUtils.isNotEmpty(phone)){
            adminUser.setPhone(phone);
        }
        list =  adminUserService.queryAdminUserByList(adminUser,pag);
        for (int i = 0;i<list.size();i++){
            List<AdminMenuVo> menus = userMenuRelationService.queryUserByMenuToList(list.get(i).getId());
            list.get(i).setMenus(menus);
        }
        return list;
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAdminUserById(String id){
        adminUserService.deleteAdminUserById(Integer.parseInt(id));
    }

}
