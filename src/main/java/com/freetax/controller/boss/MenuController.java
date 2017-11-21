package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.boss.MenuFacade;
import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/15 10:21
 */
@RestController
@RequestMapping("/boss/menu")
public class MenuController {

    @Autowired
    private MenuFacade menuFacade;

    /**
     * 查询菜单列表
     * @return
     */
    @ApiOperation(value = "查询动态菜单列表",notes = "用于动态查询菜单列表",response = Response.class)
    @RequestMapping(value = "queryDynamicMenuList",method = RequestMethod.POST)
    public Response queryDynamicMenuList(){
        Response response = new Response();
        List<AdminMenuVo> adminMenus = menuFacade.queryDynamicMenuList();
        if (adminMenus != null) {
            response.setMessage("查询成功");
            response.setData(adminMenus);
        }else {
            response.setMessage("请登录");
            response.setData(-1);
        }
        return response;
    }

    /**
     * 查询菜单列表
     * @return
     */
    @ApiOperation(value = "查询菜单列表",response = Response.class)
    @RequestMapping(value = "queryMenuList",method = RequestMethod.POST)
    public Response queryMenuList(){
        Response response = new Response();
        List<AdminMenuVo> adminMenu = menuFacade.queryMenuList();
        response.setMessage("查询成功");
        response.setData(adminMenu);
        return response;
    }
}
