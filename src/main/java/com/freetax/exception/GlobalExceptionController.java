package com.freetax.exception;

import com.freetax.common.Response;
import com.freetax.facade.boss.BossLoginFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常控制器
 * 对应web.xml中error-page配置的异常页面路径
 */
@RestController
@RequestMapping("/rest/exception")
//@Api(value="HttpRequestExceptionHandler", description="400,403,404,405,500异常返回json数据")
public class GlobalExceptionController {

    @Autowired
    private BossLoginFacade bossLoginFacade;

    /**
     * 页面请求400错误
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_400", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求400错误", response = Response.class, notes = "400错误", httpMethod = "GET")
    public Response error_400() throws Exception {
        Response response = new Response();
        response.setMsgCode(400);
        response.setMessage("错误请求,服务器不理解请求的语法");
        return response;
    }

    /**
     * 请求无权限
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_401", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "请求401错误", response = Response.class, notes = "401错误", httpMethod = "GET")
    public void error_401(HttpServletResponse response) throws Exception {
        bossLoginFacade.handleNoPermission(response);
    }

    /**
     * 请求异常
     *
     * @return
     * @throws Exception String
     */

    @RequestMapping(value = "/error_403", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求403错误", response = Response.class, notes = "403错误", httpMethod = "GET")
    public Response error_403() throws Exception {
        Response response = new Response();
        response.setMsgCode(403);
        response.setMessage("（禁止） 服务器拒绝请求。");
        return response;
    }

    /**
     * 请求异常 服务器找不到请求的网页
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_404", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求404错误", response = Response.class, notes = "404错误", httpMethod = "GET")
    public Response error_404() throws Exception {
        Response response = new Response();
        response.setMsgCode(404);
        response.setMessage("服务器找不到请求的网页");
        return response;
    }

    /**
     * 禁用请求中指定的方法
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_405", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求405错误", response = Response.class, notes = "405错误", httpMethod = "GET")
    public Response error_405() throws Exception {
        Response response = new Response();
        response.setMsgCode(405);
        response.setMessage("禁用请求中指定的方法");
        return response;
    }

    /**
     * 禁用请求中指定的方法
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_413", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求413错误", response = Response.class, notes = "413错误", httpMethod = "GET")
    public Response error_413() throws Exception {
        Response response = new Response();
        response.setMsgCode(413);
        response.setMessage("（请求实体过大） 服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。 ");
        return response;
    }

    /**
     * 服务器异常
     *
     * @return String
     */
    @RequestMapping(value = "/error_500", produces = "text/html;charset=UTF-8")
    @ApiOperation(value = "页面请求500错误", response = Response.class, notes = "500错误", httpMethod = "GET")
    public Response error_500() throws Exception {
        Response response = new Response();
        response.setMsgCode(500);
        response.setMessage("服务器处理失败");
        return response;
    }

}
