package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.boss.AdminUserFacade;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.utils.pagination.model.Paging;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/15 11:43
 */
@RestController
@RequestMapping("/boss/adminuser")
public class AdminUserController {

    @Autowired
    private AdminUserFacade adminUserFacade;

    /**
     * 新增后台用户
     * @param email
     * @param phone
     * @param remark
     * @param nickname
     * @param password
     * @param isAdmin
     * @return
     */
    @ApiOperation(value = "新增后台用户",notes = "用于新增管理员用户",response = Response.class)
    @RequestMapping(value = "insertAdminUser",method = RequestMethod.POST)
    public Response insertAdminUser(@ApiParam(value = "邮箱")@RequestParam(required = false) String email,
                                    @ApiParam(value = "电话")@RequestParam String phone,
                                    @ApiParam(value = "备注")@RequestParam(required = false) String remark,
                                    @ApiParam(value = "昵称")@RequestParam(required = false) String nickname,
                                    @ApiParam(value = "密码")@RequestParam String password,
                                    @ApiParam(value = "是否是超级管理员")@RequestParam String isAdmin){
        Response response = new Response();
        Map resault = adminUserFacade.insertAdminUser(email,phone,remark,nickname,password,isAdmin);
        if ((Integer)resault.get("code") == 200) {
            response.setMessage("操作成功");
            response.setData(1);
        } else {
            response.setMessage("新增失败");
            response.setData(-1);
        }
        return response;
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
     * @return
     */
    @ApiOperation(value = "修改boss用户信息",notes = "用于修改用户资料",response = Response.class)
    @RequestMapping(value = "updateAdminUserById",method = RequestMethod.POST)
    public Response updateAdminUser(@ApiParam(value = "id")@RequestParam String id,
                                    @ApiParam(value = "邮箱")@RequestParam(required = false) String email,
                                    @ApiParam(value = "电话")@RequestParam(required = false) String phone,
                                    @ApiParam(value = "备注")@RequestParam(required = false) String remark,
                                    @ApiParam(value = "昵称")@RequestParam(required = false) String nickname,
                                    @ApiParam(value = "密码")@RequestParam(required = false) String password,
                                    @ApiParam(value = "是否是超级管理员")@RequestParam(required = false) String isAdmin){
        Response response = new Response();
        adminUserFacade.updateAdminUserById(id,email,phone,remark,nickname,password,isAdmin);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询用户信息",notes = "用于根据id查询用户信息",response = Response.class)
    @RequestMapping(value = "query_AdminUserById",method = RequestMethod.POST)
    public Response queryAdminUserById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        AdminUser adminUser = adminUserFacade.queryAdminUserById(id);
        response.setMessage("查询成功");
        response.setData(adminUser);
        return response;
    }

    /**
     * 查询用户列表
     * @param nickname
     * @param phone
     * @return
     */
    @ApiOperation(value = "查询boss用户列表",notes = "用于查询boss用户列表",response = Response.class)
    @RequestMapping(value = "queryAdminUserByList",method = RequestMethod.POST)
    public Response queryAdminUserByList(@ApiParam(value = "昵称")@RequestParam(required = false) String nickname,
                                         @ApiParam(value = "电话")@RequestParam(required = false) String phone,
                                         @ApiParam(value = "当前页")@RequestParam(defaultValue = "1") String pageNo,
                                         @ApiParam(value = "每页几条")@RequestParam(defaultValue = "10") String pageSize){
        Response response = new Response();
        Paging<AdminUser> pag = new Paging<AdminUser>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<AdminUser> adminUsers = adminUserFacade.queryAdminUserByList(nickname,phone,pag);
        pag.result(adminUsers);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除boss用户",notes = "用于删除用户",response = Response.class)
    @RequestMapping(value = "deleteAdminUserById",method = RequestMethod.POST)
    public Response deleteAdminUserById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        adminUserFacade.deleteAdminUserById(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
