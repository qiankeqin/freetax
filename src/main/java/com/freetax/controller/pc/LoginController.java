package com.freetax.controller.pc;

import com.freetax.common.Response;
import com.freetax.facade.user.PcRegisterFacade;
import com.freetax.utils.ValidateUtils;
import com.freetax.utils.VerifyCodeUtils;
import com.taobao.api.ApiException;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author shuxf
 * @Date 2017/11/10 17:26
 */
@RestController
@RequestMapping("/pc/login/")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    PcRegisterFacade pcRegisterFacade;

    /**
     * 手机注册账号时发送的验证码
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws ApiException
     */
    @ApiOperation(value = "手机注册账号时发送的验证码", notes = "手机注册账号时发送的验证码", response = Response.class)
    @RequestMapping(value = {"/get_mobile_code"}, method = RequestMethod.GET)
    public Response getMobileCode(@ApiParam(value = "验证的手机号") @RequestParam String mobile,
                                  HttpServletRequest request) throws IOException, ApiException {
        log.info("获得手机验证码,  手机号mobile==" + mobile);
        log.info("获取到的request method>>>>"+request.getMethod());
        Response response = new Response();
        if (ValidateUtils.isMobile(mobile)) {
            // 生成随机字串（验证码长度6）
            String verifyCode = VerifyCodeUtils.generateVerifyCode(6, "0123456789");
            log.info("verifyCode == " + verifyCode);

            pcRegisterFacade.sendSms(mobile, verifyCode);

            pcRegisterFacade.putValidationInfoToSession(mobile, verifyCode, "r");

            response.setCode(200);
            response.setData(verifyCode);
        } else {
            log.error("手机号不正确！");
            response.setCode(300);
            response.setMessage("请输入正确的手机号！");
        }

        return response;
    }
}
