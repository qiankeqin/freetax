package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.BannerManageFacade;
import com.freetax.mybatis.bannerManage.entity.BannerManage;
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

    /**
     * 编辑广告
     * @param banner
     * @param describe
     * @param orderid
     * @param title
     * @param location
     * @return
     */
    @ApiOperation(value = "编辑广告",notes = "编辑广告",response = Response.class)
    @RequestMapping(value = "updateAdvertisement",method = RequestMethod.POST)
    public Response updateAdvertisement(@ApiParam(value = "banner图片url")@RequestParam(required = false) String banner,
                                        @ApiParam(value = "对于banner的描述")@RequestParam(required = false) String describe,
                                        @ApiParam(value = "排序")@RequestParam(required = false) String orderid,
                                        @ApiParam(value = "标题")@RequestParam(required = false) String title,
                                        @ApiParam(value = "banner位置 1:主页顶部banner 2:主页滚动banner 3:税务筹划背景图片 4:税务筹划顶部banner 5:资讯顶部banner " +
                                                "6:收入对比图 7：问答banner 8：招商banner 9：联系我们banner")@RequestParam(required = false) String location){
        Response response = new Response();
        bannerManageFacade.updateAdvertisement(banner,describe,orderid,title,location);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 根据id查询广告
     * @param id
     * @return
     */
    @RequestMapping(value = "query_advertisement_id",method = RequestMethod.POST)
    @ApiOperation(value = "根据id查询广告",notes = "根据id查询广告",response = Response.class)
    public Response queryAdvertisementById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        BannerManage bannerManage = bannerManageFacade.queryAdvertisementById(id);
        response.setMessage("查询成功");
        response.setData(bannerManage);
        return response;
    }


    /**
     * 查询广告列表
     * @param title
     * @param location
     * @return
     */
    @RequestMapping(value = "query_advertisement_list",method = RequestMethod.POST)
    @ApiOperation(value = "查询广告列表",notes = "查询广告列表",response = Response.class)
    public Response queryAdvertisementByList(@ApiParam(value = "标题")@RequestParam(required = false) String title,
                                             @ApiParam(value = "banner位置 1:主页顶部banner 2:主页滚动banner 3:税务筹划背景图片 4:税务筹划顶部banner 5:资讯顶部banner " +
                                                     "6:收入对比图 7：问答banner 8：招商banner 9：联系我们banner")@RequestParam(required = false) String location){
        Response response = new Response();
        List<BannerManage> list = bannerManageFacade.queryAdvertisementByList(title,location);
        response.setMessage("查询成功");
        response.setData(list);
        return response;
    }


    /**
     * 删除广告
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_advertisement",method = RequestMethod.POST)
    @ApiOperation(value = "删除广告",notes = "删除广告",response = Response.class)
    public Response deleteAdvertisement(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        bannerManageFacade.deleteAdvertisement(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

}
