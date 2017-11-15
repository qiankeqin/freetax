package com.freetax.facade.boss;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.service.AdminMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2017/11/15 10:44
 */
@Service
public class MenuFacade {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 新增菜单
     * @param name
     * @param url
     * @param orderid
     * @param pid
     */
    public void insertMenu(String name,String url,String orderid,String pid){
        AdminMenu menu = new AdminMenu();
        if (StringUtils.isNotEmpty(name)){
            menu.setName(name);
        }
        if (StringUtils.isNotEmpty(url)){
            menu.setUrl(url);
        }
        if (StringUtils.isNotEmpty(orderid)){
            menu.setOrderid(Integer.parseInt(orderid));
        }
        if (StringUtils.isNotEmpty(pid)){
            menu.setPid(Integer.parseInt(pid));
        }
        adminMenuService.insertMenu(menu);
    }
}
