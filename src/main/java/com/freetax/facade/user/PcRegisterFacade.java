package com.freetax.facade.user;

import com.freetax.mybatis.user.entity.Validateinfo;
import com.freetax.utils.DateUtils;
import com.freetax.utils.propertiesLoader.PropertiesLoader;
import com.freetax.utils.sms.SDKSendSms;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
}
