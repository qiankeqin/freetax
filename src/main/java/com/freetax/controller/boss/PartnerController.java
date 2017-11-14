package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.boss.PartnerFacade;
import com.freetax.mybatis.partner.entity.Partner;
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
 * @Date 2017/11/14 16:50
 */
@RestController
@RequestMapping("/boss/partner")
public class PartnerController {

    @Autowired
    private PartnerFacade partnerFacade;

    /**
     * 新增合作伙伴
     * @param banner
     * @param link
     * @return
     */
    @ApiOperation(value = "新增合作伙伴",notes = "用于新增合作伙伴",response = Response.class)
    @RequestMapping(value = "insertPartner",method = RequestMethod.POST)
    public Response insertPartner(@ApiParam(value = "banner")@RequestParam String banner,
                                  @ApiParam(value = "链接")@RequestParam String link){
        Response response = new Response();
        partnerFacade.insertPartner(banner,link);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 根据id查询合作伙伴详情
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询合作伙伴",notes = "用于根据id查询合作伙伴详情",response = Response.class)
    @RequestMapping(value = "queryPartnerById",method = RequestMethod.POST)
    public Response queryPartnerById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        Partner partner = partnerFacade.queryPartnerById(id);
        response.setMessage("查询成功");
        response.setData(partner);
        return response;
    }

    /**
     * 条件查询合作伙伴列表
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询合作伙伴列表",notes = "用于条件查询合作伙伴列表",response = Response.class)
    @RequestMapping(value = "queryPartnerByList",method = RequestMethod.POST)
    public Response queryPartnerByList(@ApiParam(value = "名称")@RequestParam String name,
                                       @ApiParam(value = "当前页")@RequestParam(defaultValue = "1") String pageNo,
                                       @ApiParam(value = "每页几条")@RequestParam(defaultValue = "10") String pageSize){
        Response response = new Response();
        Paging<Partner> pag = new Paging<Partner>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<Partner> partners = partnerFacade.queryPartnerByList(name,pag);
        pag.result(partners);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }

    /**
     * 修改合作伙伴
     * @param id
     * @param name
     * @param banner
     * @param link
     * @return
     */
    @ApiOperation(value = "修改合作伙伴",notes = "修改合作伙伴",response = Response.class)
    @RequestMapping(value = "updatePartnerById",method = RequestMethod.POST)
    public Response updatePartnerById(@ApiParam(value = "id")@RequestParam String id,
                                      @ApiParam(value = "名称")@RequestParam(required = false) String name,
                                      @ApiParam(value = "banner")@RequestParam(required = false) String banner,
                                      @ApiParam(value = "链接")@RequestParam(required = false) String link){
        Response response = new Response();
        partnerFacade.updatePartnerById(id,name,banner,link);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 删除合作伙伴
     * @param id
     * @return
     */
    @ApiOperation(value = "删除合作伙伴",notes = "用于根据id删除合作伙伴",response = Response.class)
    @RequestMapping(value = "deletePartner",method = RequestMethod.POST)
    public Response deletePartner(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        partnerFacade.deletePartner(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;

    }
}
