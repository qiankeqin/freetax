package com.freetax.facade.user;

import com.aliyuncs.exceptions.ClientException;
import com.freetax.common.Response;
import com.freetax.common.util.ShiroUtil;
import com.freetax.mybatis.user.entity.LoginUser;
import com.freetax.mybatis.user.entity.User;
import com.freetax.mybatis.user.entity.Validateinfo;
import com.freetax.mybatis.user.service.UserService;
import com.freetax.shiro.realm.ShiroRealm;
import com.freetax.utils.DateUtils;
import com.freetax.utils.MD5Util;
import com.freetax.utils.UUIDGenerator;
import com.freetax.utils.propertiesLoader.PropertiesLoader;
import com.freetax.utils.sms.SDKSendSms;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private UserFacade userFacade;

    /**
     * 查询当前手机号是否已注册
     * @param mobile
     * @return
     */
    public Integer queryIsRegister(String mobile){
        return userService.queryIsRegister(mobile);
    }

    /**
     * 发送短信
     *
     * @param mobile
     * @param verifyCode
     */
    public void sendSms(String mobile, String verifyCode) throws ClientException {
        //发送短信服务
        SDKSendSms.sendSMS(mobile, verifyCode, PropertiesLoader.getValue("login_app_sms_template_code"));
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

    /**
     * 前台PC端用户注册接口
     * @param mobile
     * @param phcode
     * @param passwd
     * @return
     */
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
                user.setPhoto("http://120.77.214.187:8100/ssp/images/grzx/bg-gr.png");
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

    /**
     * PC前端用户登录接口
     * @param mobile
     * @param passwd
     * @return
     */
    public Response pcLogin(String mobile, String passwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Response response = new Response();

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {

            //5 验证通过则在session中缓存登录用户信息
            Session session = currentUser.getSession();
            //6 清除session中的boss用户信息
            session.removeAttribute("bossuser");
//            ShiroRealm.ShiroUser appuser = (ShiroRealm.ShiroUser) currentUser.getPrincipal();
//            log.debug("当前登录的LoginUser信息=" + appuser.toString());
//            session.setAttribute("appuser", appuser);
            //用户id
//            int appuserid = appuser.getId();
//            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile, MD5Util.MD5EncodeByUTF8(passwd));
//            upToken.setRememberMe(true);
            //查询数据库中的用户实体
            Map<String, Object> parammap = new HashMap<>();
            parammap.put("phone", mobile);
            parammap.put("passwd", MD5Util.MD5EncodeByUTF8(passwd));
            LoginUser user = userFacade.getLoginUserByToken(parammap);

            if (null == user){
                response.setCode(203);
                response.setMessage("该用户不存在");
            }else {
                if (user.getStatus() == 1){
                    response.setCode(202);
                    response.setMessage("账户已被冻结！");
                }else {
                    if (MD5Util.MD5EncodeByUTF8(passwd) == user.getPasswd()){
                        response.setCode(201);
                        response.setMessage("手机号/密码不匹配！");
                    }else {
                        //7 返回用户是否是第一次登录（根据登录时间和注册时间的间隔判断，若间隔小于10秒，则认为是第一次登录，否则不是）
                        Date loginTime = new Date();
                        //8 登录验证成功后，更新用户信息
                        updateLoginUserInfo(mobile, loginTime);
                        //9 返回登录人的信息
                        LoginUser loginuser = userFacade.getLoginuserByUserid(mobile);//根据phone查询当前用户的实体对象
                        ShiroRealm.ShiroUser shiroUser = ShiroUtil.getShiroUserFromLoginUser(loginuser);//将LoginUser实体转化为ShiroUser对象
                        session.setAttribute("appuser", shiroUser);

                        response.setCode(200);
                        response.setMessage("登录成功");
                        response.setData(shiroUser);
                    }
                }
            }

//            try {
//                currentUser.login(upToken);
//                response.setCode(200);
//                response.setMessage("登录成功");
//                response.setData(shiroUser);
//
//            } catch (IncorrectCredentialsException ice) {
//                response.setCode(201);
//                response.setMessage("手机号/密码不匹配！");
//
//            } catch (LockedAccountException lae) {
//
//                response.setCode(202);
//                response.setMessage("账户已被冻结！");
//
//            } catch (AuthenticationException ae) {
//                System.out.println(ae.getMessage());
//                throw ae;
//            }

        }
        return response;
    }

    /**
     * PC用户修改密码
     * @param mobile
     * @param phcode
     * @param passwd
     * @return
     */
    public Response pcUpdatePasswd(String mobile, String phcode, String passwd){
        Response response = new Response();

        LoginUser loginuser = userFacade.getLoginuserByUserid(mobile);

        if (null == loginuser){
            log.info("该手机号未注册");
            response.setCode(401);
            response.setMessage("该手机号未注册");
        }else {
            try {
                //从缓存中获取本手机号的验证码进行比对
                Subject currentUser = SecurityUtils.getSubject();
                Session session = currentUser.getSession(true);
                Validateinfo info = (Validateinfo) session.getAttribute("r" + mobile);

                if (phcode.equals(info.getCheckCode())) {
                    Map<String, Object> parammap = new HashMap<>();
                    parammap.put("phone", mobile);
                    parammap.put("passwd", MD5Util.MD5EncodeByUTF8(passwd));
                    userService.updatePasswd(parammap);

                    log.info("密码修改成功");
                    response.setCode(200);
                    response.setMessage("密码修改成功");
                }else {
                    log.info("验证码错误");
                    response.setCode(300);
                    response.setMessage("验证码错误");
                }

            }catch (Exception e){
                log.error("服务器繁忙，密码修改失败");
                response.setCode(301);
                response.setMessage("服务器繁忙，修改失败");
            }
        }

        return response;
    }

    /**
     * 登录成功后，更新用户的部分信息：
     * 最后一次登录时间
     *
     * @param mobile
     * @param loginTime
     */
    private void updateLoginUserInfo(String mobile, Date loginTime)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User u = new User();
        u.setPhone(mobile);
        u.setLogintime(loginTime);
        userService.updateLoginappuserInfo(u);
    }
}
