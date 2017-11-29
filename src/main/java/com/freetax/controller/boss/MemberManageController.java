package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.user.UserFacade;
import com.freetax.mybatis.user.entity.User;
import com.freetax.utils.pagination.model.Paging;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/16 9:53
 */
@RestController
@RequestMapping("/boss/user")
public class MemberManageController {

    @Autowired
    private UserFacade userFacade;

    /**
     * 编辑会员信息
     *
     * @param phone
     * @param photo
     * @param name
     * @param company
     * @param email
     * @param infosource
     * @return
     */
    @ApiOperation(value = "编辑会员用户信息", notes = "用于编辑修改会员用户信息", response = Response.class)
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public Response updateUser(@ApiParam(value = "id") @RequestParam String id,
                               @ApiParam(value = "手机号") @RequestParam(required = false) String phone,
                               @ApiParam(value = "密码")@RequestParam(required = false) String password,
                               @ApiParam(value = "头像") @RequestParam(required = false) String photo,
                               @ApiParam(value = "姓名") @RequestParam(required = false) String name,
                               @ApiParam(value = "公司名称") @RequestParam(required = false) String company,
                               @ApiParam(value = "邮箱") @RequestParam(required = false) String email,
                               @ApiParam(value = "信息来源：0 业务咨询 1 招商加盟") @RequestParam(required = false) String infosource) {
        Response response = new Response();
        userFacade.updateUser(id, phone, photo, password, name, company, email, infosource);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 根据id查询会员用户详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询会员用户详情", notes = "根据id查询会员用户详情", response = Response.class)
    @RequestMapping(value = "queryUserById", method = RequestMethod.POST)
    public Response queryUserById(@ApiParam(value = "id") @RequestParam String id) {
        Response response = new Response();
        User user = userFacade.queryUserById(id);
        response.setMessage("查询成功");
        response.setData(user);
        return response;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户删除", notes = "用于删除用户操作", response = Response.class)
    @RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
    public Response deleteUserById(@ApiParam(value = "id") @RequestParam String id) {
        Response response = new Response();
        userFacade.deleteUserById(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


    /**
     * 更新用户联系状态
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "更改用户联系状态", notes = "用于更改用户联系状态", response = Response.class)
    @RequestMapping(value = "updateUserByMark", method = RequestMethod.POST)
    public Response updateUserByMark(@ApiParam(value = "联系状态 0 未联系 1已联系") @RequestParam String mark,
                                     @ApiParam(value = "用户id") @RequestParam String id) {
        Response response = new Response();
        userFacade.updateUserByMark(id, mark);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 条件查询用户列表
     *
     * @param phone
     * @param name
     * @param company
     * @param infosource
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "条件查询用户列表", notes = "用于条件查询用户列表", response = Response.class)
    @RequestMapping(value = "queryUserByList", method = RequestMethod.POST)
    public Response queryUserByList(@ApiParam(value = "手机号") @RequestParam(required = false) String phone,
                                    @ApiParam(value = "姓名") @RequestParam(required = false) String name,
                                    @ApiParam(value = "公司名称") @RequestParam(required = false) String company,
                                    @ApiParam(value = "信息来源：0 业务咨询 1 招商加盟") @RequestParam(required = false) String infosource,
                                    @ApiParam(value = "是否联系 0 否 1是") @RequestParam(required = false) String mark,
                                    @ApiParam(value = "最后登录开始时间")@RequestParam(required = false) String logbegin,
                                    @ApiParam(value = "最后登录结束时间")@RequestParam(required = false) String logend,
                                    @ApiParam(value = "最后开始询问时间")@RequestParam(required = false) String advicebiegin,
                                    @ApiParam(value = "最后结束询问时间")@RequestParam(required = false) String adviceend,
                                    @ApiParam(value = "注册开始时间")@RequestParam(required = false) String registerin,
                                    @ApiParam(value = "注册结束时间")@RequestParam(required = false) String registerend,
                                    @ApiParam(value = "当前页") @RequestParam(defaultValue = "1") String pageNo,
                                    @ApiParam(value = "每页几条") @RequestParam(defaultValue = "10") String pageSize) {
        Response response = new Response();
        Paging<User> pag = new Paging<User>(Integer.valueOf(pageNo), Integer.parseInt(pageSize));
        List<User> users = userFacade.queryUserByList(phone, name, company, infosource, mark,logbegin,logend, advicebiegin,adviceend, pag,registerend,registerin);
        pag.result(users);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }
}
