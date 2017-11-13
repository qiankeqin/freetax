package com.freetax.facade.user;

import com.freetax.common.Response;
import com.freetax.mybatis.user.entity.User;
import com.freetax.mybatis.user.entity.Validateinfo;
import com.freetax.mybatis.user.service.UserService;
import com.freetax.utils.DateUtils;
import com.freetax.utils.MD5Util;
import com.freetax.utils.UUIDGenerator;
import com.freetax.utils.propertiesLoader.PropertiesLoader;
import com.freetax.utils.sms.SDKSendSms;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author shuxf
 * @Date 2017/11/10 17:35
 */
@Service
public class PcRegisterFacade {

    private static Logger log = LoggerFactory.getLogger(PcRegisterFacade.class);

    @Autowired
    private UserService userService;

    /**
     * 发送短信
     *
     * @param mobile
     * @param verifyCode
     */
    public void sendSms(String mobile, String verifyCode) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code", verifyCode);
        map.put("min", "10");//短信验证时间10分钟
        Gson gson = new Gson();
        String json = gson.toJson(map);

        //发送短信服务
        SDKSendSms.sendSMS(mobile, json, PropertiesLoader.getValue("login_app_sms_template_code"));
    }

    /**
     * 把短信验证的信息放入缓存
     *
     * @param mobile
     * @param verifyCode
     * @param sessionPrefix
     */
    public void putValidationInfoToSession(String mobile, String verifyCode, String sessionPrefix) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(true);
        Validateinfo info = new Validateinfo();
        info.setCreateTime(DateUtils.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
        info.setCheckCode(verifyCode);
        info.setAccount(mobile);
        session.setAttribute(sessionPrefix + mobile, info); //缓存短信验证信息
        session.setAttribute("phone", mobile); //缓存接收短信验证码的手机号
    }

    public Response pcRegister(String mobile, String phcode, String passwd){

        Response response = new Response();
        User user = new User();//初始化

        try {
            //从缓存中获取本手机号的验证码进行比对
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Validateinfo info = (Validateinfo) session.getAttribute("r" + mobile);

            if (phcode.equals(info.getCheckCode())) {
                log.info("验证码正确开始注册");
                user.setPhone(mobile);
                user.setPasswd(MD5Util.MD5EncodeByUTF8(passwd));
                user.setPhoto("http://pic.mofo.shop/upload/person/img/logo%403xy.png");
                user.setName("ft_" + UUIDGenerator.genUUIDRemoveSep());
                user.setCompany("");
                user.setEmail("");
                user.setIntime(new Date());
                user.setStatus(0);
                user.setMark(0);

                userService.pcRegister(user);

                response.setCode(200);
                response.setMessage("注册成功");

            } else {
                response.setCode(300);
                response.setMessage("用户输入的验证码不正确");
            }
        }catch (Exception e){
            response.setCode(301);
            response.setMessage("服务器繁忙，注册失败");
        }

        return response;
    }
}
