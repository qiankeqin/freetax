package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.BannerManageFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhurui
 * @Date 2017/11/27 9:31
 */
@RestController
@RequestMapping("/boss/advertisement/")
public class AdvertisementManageController {

    @Autowired
    private BannerManageFacade bannerManageFacade;

    /**
     * 新增广告
     * @param banner
     * @param describe
     * @param orderid
     * @param title
     * @param location
     * @return
     */
    @ApiOperation(value = "新增广告",notes = "新增广告",response = Response.class)
    @RequestMapping(value = "insert_advertisement",method = RequestMethod.POST)
    public Response insertAdvertisement(@ApiParam(value = "banner图片url")@RequestParam String banner,
                                        @ApiParam(value = "对于banner的描述")@RequestParam String describe,
                                        @ApiParam(value = "排序")@RequestParam String orderid,
                                        @ApiParam(value = "标题")@RequestParam String title,
                                        @ApiParam(value = "banner位置 1:主页顶部banner 2:主页滚动banner 3:税务筹划背景图片 4:税务筹划顶部banner 5:资讯顶部banner " +
                                                "6:收入对比图 7：问答banner 8：招商banner 9：联系我们banner")@RequestParam String location){
        Response response = new Response();
        bannerManageFacade.insertAdvertisement(banner,describe,orderid,title,location);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
