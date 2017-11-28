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

    /**
     * 新增菜单
     * @param name
     * @param url
     * @param orderid
     * @param pid
     * @return
     */
    @RequestMapping(value = "insert_menu",method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单",notes = "新增菜单",response = Response.class)
    public Response insertMenu(@ApiParam(value = "名称")@RequestParam String name,
                               @ApiParam(value = "路径url")@RequestParam String url,
                               @ApiParam(value = "排序号")@RequestParam String orderid,
                               @ApiParam(value = "图标")@RequestParam String ico,
                               @ApiParam(value = "父菜单id")@RequestParam(required = false) String pid){
        Response response = new Response();
        menuFacade.insertMenu(name,url,orderid,pid,ico);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "query_menu_id",method = RequestMethod.POST)
    @ApiOperation(value = "根据id查询菜单",notes = "根据id查询菜单",response = Response.class)
    public Response queryMenuById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        AdminMenu menu = menuFacade.queryMenuById(id);
        response.setMessage("查询成功");
        response.setData(menu);
        return response;
    }

    /**
     * 编辑菜单
     * @param id
     * @param name
     * @param url
     * @param orderid
     * @param pid
     * @return
     */
    @RequestMapping(value = "update_menu_id",method = RequestMethod.POST)
    @ApiOperation(value = "修改菜单",notes = "修改菜单",response = Response.class)
    public Response updateMenuById(@ApiParam(value = "id")@RequestParam String id,
                                   @ApiParam(value = "名称")@RequestParam(required = false) String name,
                                   @ApiParam(value = "路径url")@RequestParam(required = false) String url,
                                   @ApiParam(value = "排序号")@RequestParam(required = false) String orderid,
                                   @ApiParam(value = "图标")@RequestParam(required = false) String ico,
                                   @ApiParam(value = "父菜单id")@RequestParam(required = false) String pid){
        Response response = new Response();
        menuFacade.updateMenuById(id,name,url,orderid,pid,ico);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_menu_id",method = RequestMethod.POST)
    @ApiOperation(value = "删除菜单",notes = "删除菜单",response = Response.class)
    public Response deleteMenuById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        menuFacade.deleteMenuById(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


}
