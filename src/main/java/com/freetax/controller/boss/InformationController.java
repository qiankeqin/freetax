package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.common.ResponseVo;
import com.freetax.facade.BannerManageFacade;
import com.freetax.facade.InformationFacade;
import com.freetax.mybatis.information.entity.Information;
import com.freetax.utils.pagination.model.Paging;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/13 11:03
 */
@RestController
@RequestMapping("/boss/information")
public class InformationController {

    @Autowired
    private InformationFacade informationFacade;

    @Autowired
    private BannerManageFacade bannerManageFacade;

    /**
     * 新增资讯文章
     *
     * @param title
     * @param subhead
     * @param content
     * @param coverimg
     * @param type
     * @param ishot
     * @param source
     * @return
     */
    @ApiOperation(value = "新增资讯文章", notes = "用于新增资讯接口", response = Response.class)
    @RequestMapping(value = "insert_information", method = RequestMethod.POST)
    public Response insertInformation(@ApiParam(value = "标题") @RequestParam String title,
                                      @ApiParam(value = "副标题") @RequestParam(required = false) String subhead,
                                      @ApiParam(value = "内容") @RequestParam String content,
                                      @ApiParam(value = "资讯封面图") @RequestParam String coverimg,
                                      @ApiParam(value = "资讯类型 1：行业资讯 2：财税知识 3：政策案例") @RequestParam(required = false) String type,
                                      @ApiParam(value = "是否为热门") @RequestParam(required = false) String ishot,
                                      @ApiParam(value = "资讯来源") @RequestParam(required = false) String source) {
        Response response = new Response();
        informationFacade.insertInformation(title, subhead, content, coverimg, type, ishot, source);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 修改资讯文章
     *
     * @param title
     * @param subhead
     * @param content
     * @param coverimg
     * @param type
     * @param ishot
     * @param source
     * @return
     */
    @ApiOperation(value = "修改资讯文章", notes = "用于修改资讯文章接口", response = Response.class)
    @RequestMapping(value = "update_information", method = RequestMethod.POST)
    public Response updateInformation(@ApiParam(value = "id") @RequestParam String id,
                                      @ApiParam(value = "标题") @RequestParam(required = false) String title,
                                      @ApiParam(value = "副标题") @RequestParam(required = false) String subhead,
                                      @ApiParam(value = "内容") @RequestParam(required = false) String content,
                                      @ApiParam(value = "资讯封面图") @RequestParam(required = false) String coverimg,
                                      @ApiParam(value = "资讯类型 1：行业资讯 2：财税知识 3：政策案例") @RequestParam(required = false) String type,
                                      @ApiParam(value = "是否为热门") @RequestParam(required = false) String ishot,
                                      @ApiParam(value = "资讯来源") @RequestParam(required = false) String source) {
        Response response = new Response();
        informationFacade.updateInformation(id, title, subhead, content, coverimg, type, ishot, source);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 删除资讯文章
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除资讯文章", notes = "删除资讯文章", response = Response.class)
    @RequestMapping(value = "delete_information", method = RequestMethod.POST)
    public Response deleteInformation(@ApiParam(value = "id") @RequestParam String id) {
        Response response = new Response();
        informationFacade.deleteInformation(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 条件查询资讯文章列表
     *
     * @param title
     * @param type
     * @param ishot
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "条件查询资讯文章列表", notes = "用于根据多条件查询资讯文章列表", response = Response.class)
    @RequestMapping(value = "query_information_list", method = RequestMethod.POST)
    public Response queryInformationByList(@ApiParam(value = "标题") @RequestParam(required = false) String title,
                                           @ApiParam(value = "资讯类型") @RequestParam(required = false) String type,
                                           @ApiParam(value = "是否热门") @RequestParam(required = false) String ishot,
                                           @ApiParam(value = "资讯来源") @RequestParam(required = false) String source,
                                           @ApiParam(value = "当前页") @RequestParam(defaultValue = "1") String pageNo,
                                           @ApiParam(value = "每页几条") @RequestParam(defaultValue = "10") String pageSize) {
        Response response = new Response();
        Paging<Information> pag = new Paging<Information>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        List<Information> list = informationFacade.queryInformationByList(title, type, ishot, source, pag);
        pag.result(list);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }

    /**
     * 根据咨询文章id查询资讯详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/query_information_id", method = RequestMethod.POST)
    @ApiOperation(value = "查询资讯详情", notes = "根据咨询文章id查询资讯详情", response = Response.class)
    public Response queryInformationById(@ApiParam(value = "id") @RequestParam String id) {
        Response response = new Response();
        Information information = informationFacade.queryInformationById(id);
        response.setMessage("查询成功");
        response.setData(information);
        return response;
    }

    /**
     * 切割banner图
     *
     * @param file
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    @ApiOperation(value = "切割banner图", notes = "用于切割所有banner图片", response = Response.class)
    @RequestMapping(value = "bannerImgIncision", method = RequestMethod.POST)
    public ResponseVo bannerImgIncision(@ApiParam(value = "文件") @RequestParam MultipartFile file,
                                        @ApiParam(value = "x坐标") @RequestParam(required = false) String x,
                                        @ApiParam(value = "y坐标") @RequestParam(required = false) String y,
                                        @ApiParam(value = "w宽") @RequestParam(required = false) String w,
                                        @ApiParam(value = "h高") @RequestParam(required = false) String h) {
        ResponseVo response = new ResponseVo();
        String url = bannerManageFacade.bannerImgIncision(file, x, y, w, h);
        Map map = new HashMap();
        map.put("src",url);
        //截取url中的文件名
        String title = url.substring(url.lastIndexOf("/")+1,url.length());
        map.put("title",title);
        response.setMsg("操作成功");
        response.setData(map);
        return response;
    }

    /**
     * 设置资讯文章热门/取消热门
     *
     * @param id
     * @param ishot
     * @return
     */
    @ApiOperation(value = "设置热门/取消热门", notes = "设置热门/取消热门", response = Response.class)
    @RequestMapping(value = "insertInformationByIsHot", method = RequestMethod.POST)
    public Response insertInformationByIsHot(@ApiParam(value = "id") @RequestParam String id,
                                             @ApiParam(value = "是否设为热门 0 否 1是") @RequestParam String ishot) {
        Response response = new Response();
        informationFacade.insertInformationByIsHot(id, ishot);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
