package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.AdvisoryFacade;
import com.freetax.mybatis.advisory.entity.Advisory;
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
 * @Date 2017/11/17 10:06
 */
@RestController
@RequestMapping("/boss/advisory")
public class AdvisoryController {

    @Autowired
    private AdvisoryFacade advisoryFacade;


    /**
     * 根据id查询咨询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "query_advisory_details",method = RequestMethod.POST)
    @ApiOperation(value = "查询咨询详情",notes = "查询咨询详情",response = Response.class)
    public Response queryAdvisoryById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        Advisory advisory = advisoryFacade.queryAdvisoryById(id);
        response.setMessage("查询成功");
        response.setData(advisory);
        return response;
    }


    /**
     * 查询咨询列表
     * @param phone
     * @return
     */
    @ApiOperation(value = "条件查询咨询列表",notes = "条件查询咨询列表",response = Response.class)
    @RequestMapping(value = "query_advisory_list",method = RequestMethod.POST)
    public Response queryAdvisoryByList(@ApiParam(value = "电话号")@RequestParam(required = false) String phone,
                                        @ApiParam(value = "visit")@RequestParam(required = false) String visit,
                                        @ApiParam(value = "当前页")@RequestParam String pageNo,
                                        @ApiParam(value = "每页几条")@RequestParam String pageSize){
        Response response = new Response();
        Paging<Advisory> pag =  new Paging<Advisory>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<Advisory> advisories = advisoryFacade.queryAdvisoryByList(phone,visit,pag);
        pag.result(advisories);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }


    /**
     * 操作咨询回访
     * @param id
     * @param visit
     * @return
     */
    @ApiOperation(value = "操作咨询回访",response = Response.class)
    @RequestMapping(value = "uodate_advisory_visit",method = RequestMethod.POST)
    public Response updateAdvisoryByVisit(@ApiParam(value = "id")@RequestParam String id,
                                          @ApiParam(value = "是否回访 0 否 1 是")@RequestParam String visit){
        Response response = new Response();
        advisoryFacade.updateAdvisoryVisit(id,visit);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
