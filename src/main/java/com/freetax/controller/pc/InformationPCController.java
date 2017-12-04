package com.freetax.controller.pc;

import com.freetax.common.Response;
import com.freetax.facade.BannerManageFacade;
import com.freetax.facade.InformationFacade;
import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.entity.InformationVo;
import com.freetax.utils.pagination.model.Paging;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 11:03
 */
@RestController
@RequestMapping("/pc/information")
public class InformationPCController {

    @Autowired
    private InformationFacade informationFacade;

    @Autowired
    private BannerManageFacade bannerManageFacade;


    /**
     * 条件查询资讯文章列表
     *
     * @param type
     * @param ishot
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "条件查询资讯文章列表(pc)", notes = "用于根据多条件查询资讯文章列表", response = Response.class)
    @RequestMapping(value = "/query_information_list", method = RequestMethod.POST)
    public Response queryInformationByList(@ApiParam(value = "资讯类型0:全部 1：行业资讯 2：财税知识 3：政策案例") @RequestParam(required = false) String type,
                                           @ApiParam(value = "是否热门") @RequestParam(required = false) String ishot,
                                           @ApiParam(value = "当前页") @RequestParam(defaultValue = "1") String pageNo,
                                           @ApiParam(value = "每页几条") @RequestParam(defaultValue = "10") String pageSize) {
        Response response = new Response();
        Paging<Information> pag;
        if (type.equals(3) || type.equals("3")) {
            pag = new Paging<Information>(Integer.valueOf(pageNo), 5);
        } if (ishot.equals("1") || ishot.equals(1)){
            pag = new Paging<Information>(Integer.valueOf(pageNo), 4);
        }else {
            pag = new Paging<Information>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        }
        List<Information> list = informationFacade.queryInformationByListPc(type, ishot, pag);
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
    @ApiOperation(value = "查询资讯详情(pc)", notes = "根据咨询文章id查询资讯详情", response = Response.class)
    public Response queryInformationById(@ApiParam(value = "id") @RequestParam String id) {
        Response response = new Response();
        InformationVo information = informationFacade.queryInformationToPc(id);
        response.setMessage("查询成功");
        response.setData(information);
        return response;
    }

}
