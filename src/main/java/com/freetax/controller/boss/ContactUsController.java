package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.ContactUsFacade;
import com.freetax.mybatis.contactUs.entity.ContactUs;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhurui
 * @Date 2017/11/14 10:07
 */
@RestController
@RequestMapping("/boss/contactUs")
public class ContactUsController {

    @Autowired
    private ContactUsFacade contactUsFacade;


    /**
     * 增加公司信息
     * @param name
     * @param subsidiary
     * @param phone
     * @param site
     * @param banner
     * @return
     */
    @ApiOperation(value = "新增公司信息",notes = "用于新增联系我们的公司信息",response = Response.class)
    @RequestMapping(value = "/insertContactUs",method = RequestMethod.POST)
    public Response insertContactUs(@ApiParam(value = "公司名称")@RequestParam String name,
                                    @ApiParam(value = "所属公司名称")@RequestParam String subsidiary,
                                    @ApiParam(value = "电话")@RequestParam String phone,
                                    @ApiParam(value = "地址")@RequestParam String site,
                                    @ApiParam(value = "地理位置图片")@RequestParam String banner){
        Response response = new Response();
        contactUsFacade.insertContactUs(name,subsidiary,phone,site,banner);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


    /**
     * 根据id查询公司信息详情
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询公司信息详情",notes = "用户查询公司详情",response = Response.class)
    @RequestMapping(value = "queryContactUsDetailsById",method = RequestMethod.POST)
    public Response queryContactUsDetailsById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        ContactUs contactUs = contactUsFacade.queryContactUsDetailsById(id);
        response.setMessage("查询成功");
        response.setData(contactUs);
        return response;
    }

    /**
     * 修改公司信息
     * @param id
     * @param name
     * @param subsidiary
     * @param phone
     * @param site
     * @param banner
     * @return
     */
    @ApiOperation(value = "修改公司信息",notes = "用于修改公司信息",response = Response.class)
    @RequestMapping(value = "updateContactUsDetails",method = RequestMethod.POST)
    public Response updateContactUsDetails(@ApiParam(value = "id")@RequestParam String id,
                                           @ApiParam(value = "公司名称")@RequestParam(required = false) String name,
                                           @ApiParam(value = "所属公司名称")@RequestParam(required = false) String subsidiary,
                                           @ApiParam(value = "电话")@RequestParam(required = false) String phone,
                                           @ApiParam(value = "地址")@RequestParam(required = false) String site,
                                           @ApiParam(value = "地理位置图片")@RequestParam(required = false) String banner){
        Response response = new Response();
        contactUsFacade.updateContactUsDetails(id,name,subsidiary,phone,site,banner);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 删除公司信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除公司信息",notes = "用于删除公司信息",response = Response.class)
    @RequestMapping(value = "deleteContacUs",method = RequestMethod.POST)
    public Response deleteContactUs(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        contactUsFacade.deleteContactUs(id);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }


}
