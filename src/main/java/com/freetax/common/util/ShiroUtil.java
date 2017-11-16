package com.freetax.common.util;

import com.freetax.common.constant.MsgCodeConstant;
import com.freetax.common.constant.SessionConstant;
import com.freetax.exception.AuthException;
import com.freetax.facade.user.UserFacade;
import com.freetax.mybatis.user.entity.LoginUser;
import com.freetax.shiro.realm.BossRealm;
import com.freetax.shiro.realm.ShiroRealm;
import com.freetax.utils.DateUtils;
import com.freetax.utils.propertiesLoader.MsgPropertiesLoader;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro工具类
 * 用于获取当前登录人的信息，如id，手机号等
 *
 * @Author zhuangyuhao
 * @Date 2017/2/24 10:01
 */
public class ShiroUtil {
    private static Logger log = LoggerFactory.getLogger(ShiroUtil.class);

    @Autowired
    private UserFacade userFacade;

    /**
     * 获取APP当前登录人信息
     *
     * @return
     */
    public static ShiroRealm.ShiroUser getAppUser() {
        ShiroRealm.ShiroUser member = null;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            if (session != null) {
                member = (ShiroRealm.ShiroUser) session.getAttribute(SessionConstant.APP_USER);
            }
        } catch (Exception e) {
            log.error("从session中获取当前登录的app用户失败!", e);
            throw new AuthException(MsgCodeConstant.un_login, MsgPropertiesLoader.getValue(String.valueOf(MsgCodeConstant.un_login)));

        }
        return member;

    }

    /**
     * 获取BOSS当前登录人信息
     *
     * @return
     */
    public static BossRealm.ShiroBossUser getBossUser() {
        BossRealm.ShiroBossUser member = null;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            if (session != null) {
                member = (BossRealm.ShiroBossUser) session.getAttribute(SessionConstant.BOSS_USER);
            }
        } catch (Exception e) {
            log.error("从session中获取当前登录的Boss用户失败!", e);
            throw new AuthException(MsgCodeConstant.un_login, MsgPropertiesLoader.getValue(String.valueOf(MsgCodeConstant.un_login)));

        }
        return member;

    }

    /**
     * 获取APP当前登录人id
     * @return
     */
    public static int getAppUserID() {
        int createID = 0;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            if (session != null) {
                ShiroRealm.ShiroUser principal = (ShiroRealm.ShiroUser) session.getAttribute(SessionConstant.APP_USER);
                log.debug("session中的用户信息，ShiroUser：" + principal.toString());
                if (principal != null) {
                    createID = principal.getId();
                    log.debug("session中的用户id:" + createID);
                }
            } else {
                log.warn("不存在session");
            }
        } catch (Exception e) {
            log.error("get seesion user info error!", e);
            throw new AuthException(MsgCodeConstant.un_login, MsgPropertiesLoader.getValue(String.valueOf(MsgCodeConstant.un_login)));
        }
        return createID;
    }


    /**
     * 获取boss用户登陆ID
     *
     * @return
     */
    public static Integer getBossUserID() {
        Integer createID = null;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            if (session != null) {
                BossRealm.ShiroBossUser principal = (BossRealm.ShiroBossUser) session.getAttribute(SessionConstant.BOSS_USER);
                if (principal != null) {
                    createID = principal.getId();
                }
            }
        } catch (Exception e) {
            log.error("从session中获取当前登录人id失败!", e);
            throw new AuthException(MsgCodeConstant.un_login, MsgPropertiesLoader.getValue(String.valueOf(MsgCodeConstant.un_login)));
        }
        return createID;
    }


    /**
     * 修改session中的bossuser的pwd
     *
     * @param pwd
     */
    public static void updateBossuserPwd(String pwd) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session != null) {
            BossRealm.ShiroBossUser principal = (BossRealm.ShiroBossUser) session.getAttribute(SessionConstant.BOSS_USER);
            if (null != principal) {
                //此处可以扩张需要的字段
                principal.setPassword(pwd);
                session.setAttribute(SessionConstant.BOSS_USER, principal);
            }

        }
    }

    /**
     * 修改session中的app用户的积分
     * @param point
     */
    public static void updateAppuserPoint(int point) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session != null) {
            ShiroRealm.ShiroUser principal = (ShiroRealm.ShiroUser) session.getAttribute(SessionConstant.APP_USER);
            if (null != principal) {
                //此处可以扩展需要的字段
//                principal.setPoints(point);
                session.setAttribute(SessionConstant.APP_USER, principal);
            }
        }
    }

    /**
     * 修改session中的app用户的手机号（用于绑定手机号场景）
     *
     * @param phone
     */
    public static void updateAppuserPhone(String phone) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session != null) {
            ShiroRealm.ShiroUser principal = (ShiroRealm.ShiroUser) session.getAttribute(SessionConstant.APP_USER);
            if (null != principal) {
                //此处可以扩张需要的字段
                principal.setPhone(phone);
                session.setAttribute(SessionConstant.APP_USER, principal);
            }

        }
    }

    /**
     * 修改session中的积分和手机号（用于绑定手机号场景）
     *
     * @param phone
     * @param point
     */
    public static void updateAppuserPhoneAndPoints(String phone, Integer point) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session != null) {
            ShiroRealm.ShiroUser principal = (ShiroRealm.ShiroUser) session.getAttribute(SessionConstant.APP_USER);
            if (null != principal) {
                //此处可以扩张需要的字段
                principal.setPhone(phone);
//                principal.setPoints(point);
                principal.setAccount(phone);
                session.setAttribute(SessionConstant.APP_USER, principal);
            }
        }
    }

    /**
     * 更新缓存在session中的app用户信息
     *
     * @param loginUser
     */
    public static void updateAppuser(LoginUser loginUser) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session != null) {
            session.setAttribute(SessionConstant.APP_USER, getShiroUserFromLoginUser(loginUser));
        }
    }

    /**
     * 从 LoginUser 转换为 ShiroUser
     *
     * @param loginUser
     * @return
     */
    public static ShiroRealm.ShiroUser getShiroUserFromLoginUser(LoginUser loginUser) {
        return new ShiroRealm.ShiroUser(loginUser.getId(), loginUser.getPhone(), loginUser.getPasswd(), loginUser.getPhoto(),
                loginUser.getName(), loginUser.getCompany(), loginUser.getEmail(), loginUser.getIntime(), loginUser.getLogintime(),
                loginUser.getAdvicetime(), loginUser.getInfosource(), loginUser.getStatus(), loginUser.getMark()
        );
    }

}
