package com.freetax.controller.pc;

import com.freetax.common.Response;
import com.freetax.facade.user.PcRegisterFacade;
import com.freetax.facade.user.UserFacade;
import com.freetax.mybatis.user.entity.User;
import com.freetax.utils.JsonUtils;
import com.freetax.utils.ValidateUtils;
import com.freetax.utils.VerifyCodeUtils;
import com.taobao.api.ApiException;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    @Autowired
    UserFacade userFacade;

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
        log.info("获取到的request method>>>>" + request.getMethod());
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

    @ApiOperation(value = "用户注册接口（PC端用户注册接口）", notes = "用户注册接口（PC端用户注册接口）", response = Response.class)
    @RequestMapping(value = "pc_register", method = RequestMethod.POST)
    public Response pcRegister(@ApiParam(value = "验证的手机号") @RequestParam String mobile,
                               @ApiParam(value = "验证码（手机收到的验证码）") @RequestParam String phcode,
                               @ApiParam(value = "密码（用户设置的密码）") @RequestParam String passwd) {

        Response response = new Response();

        //根据手机号查询当前手机号是否注册
        int sum = pcRegisterFacade.queryIsRegister(mobile);

        if (sum == 0) {
            response = pcRegisterFacade.pcRegister(mobile, phcode, passwd);
        }else if (sum > 0){
            log.error("该手机号已注册！");
            response.setCode(201);
            response.setMessage("该手机号已注册！");
        }
        return response;
    }

    @ApiOperation(value = "用户登录接口（PC端用户登录接口）", notes = "用户登录接口（PC端用户登录接口）", response = Response.class)
    @RequestMapping(value = "pc_login", method = RequestMethod.POST)
    public Response pcLogin(@ApiParam(value = "验证的手机号") @RequestParam String mobile,
                            @ApiParam(value = "密码（用户设置的密码）") @RequestParam String passwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Response response = pcRegisterFacade.pcLogin(mobile, passwd);

        return response;
    }

    @ApiOperation(value = "用户修改密码接口（PC端）", notes = "用户修改密码接口（PC端）", response = Response.class)
    @RequestMapping(value = "pc_update_passwd", method = RequestMethod.POST)
    public Response pcUpdatePasswd(@ApiParam(value = "验证的手机号") @RequestParam String mobile,
                                   @ApiParam(value = "验证码（手机收到的验证码）") @RequestParam String phcode,
                                   @ApiParam(value = "新密码（用户设置的密码）") @RequestParam String passwd){

        Response response = pcRegisterFacade.pcUpdatePasswd(mobile, phcode, passwd);

        return response;
    }

    @ApiOperation(value = "用户退出接口（PC端）", notes = "用户退出接口（PC端）", response = Response.class)
    @RequestMapping(value = "pc_login_out", method = RequestMethod.POST)
    public void pcLoginOut(HttpServletResponse response) throws IOException {
        SecurityUtils.getSubject().logout();
        Response jsonResult = new Response();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.getJsonStringFromObj(jsonResult));
    }

    @ApiOperation(value = "修改个人资料（PC端）", notes = "修改个人资料（PC端）", response = Response.class)
    @RequestMapping(value = "update_person_info", method = RequestMethod.POST)
    public Response updatePersonInfo(@ApiParam(value = "用户id") @RequestParam String id,
                                     @ApiParam(value = "联系人") @RequestParam(required = false) String name,
                                     @ApiParam(value = "公司名称") @RequestParam(required = false) String company,
                                     @ApiParam(value = "邮箱") @RequestParam(required = false) String email){

        Response response = userFacade.updatePersonInfo(id, name, company, email);

        return response;
    }

    @ApiOperation(value = "上传用户头像图片（PC端）", notes = "上传用户头像图片（PC端）", response = Response.class)
    @RequestMapping(value = "upload_photo_img", method = RequestMethod.POST)
    public Response uploadPhotoImg(HttpServletRequest request,
                                   @ApiParam(value = "头像图片file") @RequestParam MultipartFile file) throws IOException {
        Response response = userFacade.uploadPhotoImg(request, file);

        return response;
    }
}
