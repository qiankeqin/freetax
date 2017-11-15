package com.freetax.facade.boss;

import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.adminUser.service.AdminUserService;
import com.freetax.utils.MD5Util;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:55
 */
@Service
public class AdminUserFacade {

    @Autowired
    private AdminUserService adminUserService;

    public Map insertAdminUser(String email, String phone, String remark, String nickname, String password, String isAdmin){
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
            if (StringUtils.isNotEmpty(isAdmin)){
                adminUser.setIsadmin(Integer.parseInt(isAdmin));
            }
            adminUser.setIsdel(0);
            adminUser.setIntime(new Date());
            adminUserService.insertAdminUser(adminUser);
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
     * @param phone
     * @param remark
     * @param nickname
     * @param password
     * @param isAdmin
     */
    public void updateAdminUserById(String id,String email, String phone, String remark, String nickname, String password, String isAdmin){
        AdminUser adminUser = new AdminUser();
        adminUser.setId(Integer.parseInt(id));
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
        if (StringUtils.isNotEmpty(isAdmin)){
            adminUser.setIsadmin(Integer.parseInt(isAdmin));
        }
        adminUserService.updateAdminUserById(adminUser);
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    public AdminUser queryAdminUserById(String id){
        return adminUserService.queryAdminUserById(Integer.parseInt(id));
    }

    /**
     * 查询用户列表
     * @param nickname
     * @param phone
     * @param pag
     * @return
     */
    public List<AdminUser> queryAdminUserByList(String nickname,String phone,Paging<AdminUser> pag){
        AdminUser adminUser = new AdminUser();
        if (StringUtils.isNotEmpty(nickname)){
            adminUser.setNickname(nickname);
        }
        if (StringUtils.isNotEmpty(phone)){
            adminUser.setPhone(phone);
        }
        return adminUserService.queryAdminUserByList(adminUser,pag);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAdminUserById(String id){
        adminUserService.deleteAdminUserById(Integer.parseInt(id));
    }

}
