package com.freetax.controller;

import com.freetax.common.Response;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shuxf
 * @Date 2017/10/27 14:12
 * 新项目测试控制器
 */
@RestController
@RequestMapping("/pc/freetax/")
public class TestFreetaxController {

    /**
     * 测试少税派接口调用
     * 测试提交
     * @return
     */
    @ApiOperation(value = "测试少税派接口调用", notes = "测试少税派接口调用", response = Response.class)
    @RequestMapping(value = "getFreeTax", method = RequestMethod.POST)
    public Response getFreeTax(){
        Response response = new Response();

        response.setCode(200);
        response.setMessage("测试呀");
        response.setData("数据数据123");

        return response;
    }
}
