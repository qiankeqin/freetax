package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.boss.MenuFacade;
import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 菜单和用户绑定
     *
     * @return
     */
    @ApiOperation(value = "菜单和用户绑定",notes = "用于菜单和用户绑定",response = Response.class)
    @RequestMapping(value = "setMenuAndUserBinding",method = RequestMethod.POST)
    public Response insertMenuAndUserBinding(@ApiParam(value = "菜单id")@RequestParam String menuid,
                                          @ApiParam(value = "用户id")@RequestParam String userid){
        Response response = new Response();
        menuFacade.setMenuAndUserBinding(menuid,userid);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


    /**
     * 修改用户菜单列表
     * @param userid
     * @return
     */
    @RequestMapping(value = "updateUserMenu" ,method = RequestMethod.POST)
    @ApiOperation(value = "修改用户菜单列表",notes = "用于修改用户菜单列表",response = Response.class)
    public Response updateUserMenu(@ApiParam(value = "用户id")@RequestParam String  userid,
                                   @ApiParam(value = "菜单id")@RequestParam String menuid){
        Response response = new Response();
        menuFacade.updateUserMenu(userid,menuid);
        response.setMessage("查询成功");
        response.setData(1);
        return response;
    }
}
