package com.freetax.mybatis.adminMenu.service;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.mapper.AdminMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:09
 */
@Service
public class AdminMenuService {

    private static Logger log = LoggerFactory.getLogger(AdminMenuService.class);

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    public void insertMenu(AdminMenu adminMenu){
        try {
            log.info("新增菜单");
            adminMenuMapper.insertSelective(adminMenu);
        } catch (Exception e) {
            log.error("新增菜单异常",e);
            throw e;
        }
    }
}
