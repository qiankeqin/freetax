package com.freetax.controller.boss;

import com.freetax.common.Response;
import com.freetax.facade.ConsultlLeadingOutFacade;
import com.freetax.facade.ExcelLeadingOutFacade;
import com.freetax.facade.InformationLeadingOutFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/29 9:58
 */
@RestController()
@RequestMapping("/boss/leadingout/")
public class ExcelLeadingOutController {

    @Autowired
    private ExcelLeadingOutFacade excelLeadingOutFacade;

    @Autowired
    private ConsultlLeadingOutFacade consultlLeadingOutFacade;

    @Autowired
    private InformationLeadingOutFacade informationLeadingOutFacade;

    /**
     * 会员用户导出
     *
     * @return
     */
    @ApiOperation(value = "用户导出Excel", notes = "用于帖子导出", response = Response.class)
    @RequestMapping(value = "/excelLeadingOutByUser", method = RequestMethod.POST)
    public Response excelLeadingOutByUser() {
        Response response = new Response();
        Map map = excelLeadingOutFacade.excelLeadingOutByUser();
        response.setMessage("操作成功");
        response.setData(map);
        return response;
    }

    /**
     * 咨询用户导出
     *
     * @return
     */
    @ApiOperation(value = "用户导出Excel", notes = "用于帖子导出", response = Response.class)
    @RequestMapping(value = "/excelLeadingOutByConsultl", method = RequestMethod.POST)
    public Response excelLeadingOutByConsultl() {
        Response response = new Response();
        Map map = consultlLeadingOutFacade.excelLeadingOutByConsultl();
        response.setMessage("操作成功");
        response.setData(map);
        return response;
    }

    /**
     * 咨询用户导出
     *
     * @return
     */
    @ApiOperation(value = "用户导出Excel", notes = "用于帖子导出", response = Response.class)
    @RequestMapping(value = "/excelLeadingOutByInformation", method = RequestMethod.POST)
    public Response excelLeadingOutByInformation() {
        Response response = new Response();
        Map map = informationLeadingOutFacade.excelLeadingOutByInformation();
        response.setMessage("操作成功");
        response.setData(map);
        return response;
    }
}
