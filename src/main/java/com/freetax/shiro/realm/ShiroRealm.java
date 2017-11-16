package com.freetax.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.freetax.common.constant.SessionConstant;
import com.freetax.common.constant.UserConstants;
import com.freetax.common.util.ShiroUtil;
import com.freetax.facade.user.UserFacade;
import com.freetax.mybatis.user.entity.LoginUser;
import com.freetax.utils.pagination.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * App用户安全数据源
 * @author zhuangyuhao
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserFacade userFacade;

    /**
     * 表示获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("PC前台登录认证");

        // 获取token中的username。这里不固定，可能是phone,qq,wx,wb
        Gson gson = new Gson();
        String tokenJson = gson.toJson(token);

        //解析json提取需要验证的信息
        JSONObject job = JSONObject.parseObject(tokenJson);
        String phone = job.get("username").toString();
        String passwd = job.get("password").toString();

        Map<String, Object> parammap = new HashMap<>();
        parammap.put("phone", phone);
        parammap.put("passwd", passwd);
        LoginUser loginUser = userFacade.getLoginUserByToken(parammap);

        log.debug("当前登录PC的用户信息，LoginUser = " + loginUser.toString());

        if (loginUser != null) {
            if (1 == loginUser.getStatus()) {
                throw new LockedAccountException(); // 帐号异常封号
            }
        } else {
            throw new UnknownAccountException();// 用户手机号不存在
        }

        //登录的pwd
        String loginPwd = String.valueOf(((UsernamePasswordToken) token).getPassword());
        log.debug("登录的pwd，" + loginPwd);

        //获取服务端的密码，并MD5二次加密
        String mytokenStr = loginUser.getPasswd();
        String password = mytokenStr;
        String pwd = new Md5Hash(password, null, 2).toString();
        log.debug("服务端的pwd," + password);

        // 2 根据登录用户信息生成ShiroUser用户，
        // PS:这个ShiroUser就是session中存储的app用户对象
        ShiroUser shiroUser = ShiroUtil.getShiroUserFromLoginUser(loginUser);

        // 3 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(shiroUser, // 用户
                pwd,    //md5加密后
                getName() // realm name
        );

    }

    /**
     * 表示根据用户身份获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("清除App用户授权信息缓存");
        this.clearCachedAuthorizationInfo(principals);
        //  获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        ShiroUser member = (ShiroUser) subject.getSession(false).getAttribute(SessionConstant.APP_USER);
        if (null != member) {

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            String status = String.valueOf(member.getStatus());
            if (StringUtils.isEmpty(status) || UserConstants.USER_STATUS.disable.toString().equals(status)) {
                return null;
            }

            return info;
        }
        return null;
    }


    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    // 登陆成功后强制加载shiro权限缓存 避免懒加载 先清除
    public void forceShiroToReloadUserAuthorityCache() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipal());
        // this.isPermitted(SecurityUtils.getSubject().getPrincipals(),
        // "强制加载缓存，避免懒加载" + System.currentTimeMillis());
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    /*public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }*/

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        private Integer id;
        private String account; //账号(使用手机号)
        private String passwd;  //密码
        private String photo;   //头像url
        private String name;    //联系人名称
        private String company; //公司名称
        private String email;   //邮箱
        private Date intime;    //注册时间
        private Date logintime; //最后一次登录时间
        private Date advicetime;//最后咨询时间
        private Integer infosource;//信息来源：0 业务咨询 1 招商加盟
        private Integer status; //账号状态：默认 0 正常 1 封号
        private Integer mark;   //联系状态：0 未联系 1已联系
        private String phone;   //手机号

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getIntime() {
            return intime;
        }

        public void setIntime(Date intime) {
            this.intime = intime;
        }

        public Date getLogintime() {
            return logintime;
        }

        public void setLogintime(Date logintime) {
            this.logintime = logintime;
        }

        public Date getAdvicetime() {
            return advicetime;
        }

        public void setAdvicetime(Date advicetime) {
            this.advicetime = advicetime;
        }

        public Integer getInfosource() {
            return infosource;
        }

        public void setInfosource(Integer infosource) {
            this.infosource = infosource;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
            this.mark = mark;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * 重载equals,只计算id+account;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ShiroUser other = (ShiroUser) obj;

            if (id == 0 || phone == null) {
                return false;
            } else if (id == other.id && phone.equals(other.phone))
                return true;
            return false;
        }

        public ShiroUser(Integer id, String phone, String passwd, String photo, String name, String company, String email, Date intime, Date logintime,
                         Date advicetime, Integer infosource, Integer status, Integer mark) {
            this.id = id;
            this.passwd = passwd;

            this.photo = photo;    //头像url
            this.name = name;      //联系人名称
            this.company = company;//公司名称
            this.email = email;    //邮箱
            this.intime = intime;  //注册时间
            this.logintime = logintime;  //最后一次登录时间
            this.advicetime = advicetime;//最后咨询时间
            this.infosource = infosource;//信息来源：0 业务咨询 1 招商加盟
            this.status = status;        //账号状态：默认 0 正常 1 封号
            this.mark = mark;            //联系状态：0 未联系 1已联系
            this.phone = phone;          //手机号
        }

        @Override
        public String toString() {
            return "ShiroUser{" +
                    "id=" + id +
                    ", account='" + account + '\'' +
                    ", status=" + status +
                    ", passwd=" + passwd +
                    ", photo='" + photo + '\'' +
                    ", name='" + name + '\'' +
                    ", company=" + company +
                    ", email='" + email + '\'' +
                    ", intime='" + intime + '\'' +
                    ", logintime=" + logintime +
                    ", advicetime=" + advicetime +
                    ", infosource='" + infosource + '\'' +
                    ", status='" + status + '\'' +
                    ", mark='" + mark + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

}
