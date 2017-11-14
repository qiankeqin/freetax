package com.freetax.controller.pc;

import com.freetax.common.Response;
import com.freetax.facade.BannerManageFacade;
import com.freetax.mybatis.bannerManage.entity.BannerManage;
import com.freetax.mybatis.bannerManage.entity.IndexInformation;
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
 * @Date 2017/11/13 9:42
 */
@RestController()
@RequestMapping("/pc/index/")
public class indexController {

    @Autowired
    private BannerManageFacade bannerManageFacade;

    /**
     * 查询首页信息
     *
     * @return
     */
    @RequestMapping(value = "/index_banner_information", method = RequestMethod.POST)
    @ApiOperation(value = "查询首页信息", notes = "用于查询首页信息banner和底部资讯", response = Response.class)
    public Response queryIndexBannerOrInformation() {
        Response response = new Response();
        IndexInformation indexInformation = bannerManageFacade.queryIndexBannerOrInformation();
        response.setMessage("查询成功");
        response.setData(indexInformation);
        return response;
    }

    /**
     * 查询各页面的banner
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "查询pc中banner", notes = "用于查询pc中所有banner信息", response = Response.class)
    @RequestMapping(value = "queryBannerByType", method = RequestMethod.POST)
    public Response queryBannerByType(@ApiParam(value = "类型 3:税务筹划背景图片 4:税务筹划顶部banner 5:资讯顶部banner") @RequestParam String type) {
        Response response = new Response();
        List<BannerManage> bannerManages = bannerManageFacade.queryBannerByType(type);
        response.setMessage("查询成功");
        response.setData(bannerManages);
        return response;
    }
}
