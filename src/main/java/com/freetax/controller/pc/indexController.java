package com.freetax.controller.pc;

import com.freetax.common.Response;
import com.freetax.facade.BannerManageFacade;
import com.freetax.mybatis.bannerManage.entity.IndexInformation;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhurui
 * @Date 2017/11/13 9:42
 */
@RestController()
@RequestMapping("/pc/index/")
public class indexController {

    @Autowired
    private BannerManageFacade bannerManageFacade;

    /**
     * 查询首页信息
     * @return
     */
    @RequestMapping(value = "/index_banner_information",method = RequestMethod.POST)
    @ApiOperation(value = "查询首页信息",notes = "用于查询首页信息banner和底部资讯",response = Response.class)
    public Response queryIndexBannerOrInformation(){
        Response response = new Response();
        IndexInformation indexInformation = bannerManageFacade.queryIndexBannerOrInformation();
        response.setMessage("查询成功");
        response.setData(indexInformation);
        return response;
    }
}
