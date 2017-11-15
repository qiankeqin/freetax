package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.boss.MenuFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 新增菜单
     * @param name
     * @param url
     * @param orderid
     * @param pid
     * @return
     */
    @ApiOperation(value = "新增菜单",notes = "新增菜单",response = Response.class)
    @RequestMapping(value = "insertMenu",method = RequestMethod.POST)
    public Response insertMenu(@ApiParam(value = "名称")@RequestParam String name,
                               @ApiParam(value = "url")@RequestParam String url,
                               @ApiParam(value = "排序")@RequestParam String orderid,
                               @ApiParam(value = "父菜单id")@RequestParam String pid){
        Response response = new Response();
        menuFacade.insertMenu(name,url,orderid,pid);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
