package com.freetax.shiro.realm;

import com.freetax.common.constant.ImConstant;
import com.freetax.common.constant.SessionConstant;
import com.freetax.common.constant.UserConstants;
import com.freetax.facade.im.ImFacade;
import com.freetax.facade.user.BossUserFacade;
import com.freetax.facade.user.UserRoleRelationFacade;
import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.adminUser.service.AdminUserService;
import com.freetax.mybatis.bossUser.entity.BossUser;
import com.freetax.mybatis.imuser.entity.ImUser;
import com.freetax.utils.pagination.util.StringUtils;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * 验证boss主体的安全数据源
 * @author zhuangyuhao
 */
public class BossRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(BossRealm.class);

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("——认证方法。。。。。。。。。。");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据用户名查询用户密码
        String name = token.getUsername();
        Pattern pattern = Pattern.compile("[0-9]*");
        AdminUser logginUser;
        if (pattern.matcher(name).matches() && name.length() == 11) {
            logginUser = adminUserService.queryUserPasswordByPhone(name);
        } else {
            logginUser = adminUserService.queryUserPasswordByName(name);
        }
        if (logginUser == null) {
            throw new UnknownAccountException();
        } else {
            //用户存在
            log.info("该用户在数据库中存在");
            if (logginUser.getIsdel() == 1) {
                throw new LockedAccountException("该账号已经被删除");
            }
        }
        System.out.println(logginUser + "登录密码--" + logginUser.getPassword() + "当前登录密码--" + JSONArray.fromObject(token.getPassword()).toString());
        return new SimpleAuthenticationInfo(logginUser, logginUser.getPassword(), getName());
    }

    /**
     * 授权，只有成功通过doGetAuthenticationInfo方法的认证后才会执行。
     * 查询回调函数, 进行鉴权， 当缓存中无用户的授权信息时调用，否则从缓存中调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
/*
        this.clearCachedAuthorizationInfo(principals);
        log.info("清除Boss用户授权信息缓存");
        //  获取当前登录principle
        Subject subject = SecurityUtils.getSubject();
        ShiroBossUser shiroBossUser = (ShiroBossUser) subject.getSession(false).getAttribute(SessionConstant.BOSS_USER);
        if (null != shiroBossUser) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 判断账号状态 : 0 正常 1 冻结
            String isAdmin = String.valueOf(shiroBossUser.getIsAdmin());
            if (StringUtils.isEmpty(isAdmin) || UserConstants.USER_STATUS.disable.toString().equals(isAdmin)) {
                log.error("账号状态异常");
                return null;
            }
            int roleid = shiroBossUser.getIsAdmin();
            log.debug("当前用户的角色 ：role=" + roleid);
            // 添加用户角色到授权信息
            info.addRole(String.valueOf(roleid));
            return info;
        }*/
        log.info("——授权方法。。。。。。。。。。");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    // 登陆成功后强制加载shiro权限缓存 避免懒加载 先清除
    public void forceShiroToReloadUserAuthorityCache() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipal());
        // this.isPermitted(SecurityUtils.getSubject().getPrincipals(),
        // "强制加载缓存，避免懒加载" + System.currentTimeMillis());
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroBossUser implements Serializable {

        private static final long serialVersionUID = -1373760761780840081L;
        private Integer id;
        private String email;
        private String phone;
        private Date intime;
        private Integer isdel;
        private Integer remark;
        private String nickname;
        private String password;
        private Integer isAdmin;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Date getIntime() {
            return intime;
        }

        public void setIntime(Date intime) {
            this.intime = intime;
        }

        public Integer getIsdel() {
            return isdel;
        }

        public void setIsdel(Integer isdel) {
            this.isdel = isdel;
        }

        public Integer getRemark() {
            return remark;
        }

        public void setRemark(Integer remark) {
            this.remark = remark;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(Integer isAdmin) {
            this.isAdmin = isAdmin;
        }

        public ShiroBossUser(Integer id, String email, String phone, Date intime, Integer isdel, Integer remark, String nickname, String password, Integer isAdmin) {
            this.id = id;
            this.email = email;
            this.phone = phone;
            this.intime = intime;
            this.isdel = isdel;
            this.remark = remark;
            this.nickname = nickname;
            this.password = password;
            this.isAdmin = isAdmin;
        }

        @Override
        public String toString() {
            return "ShiroBossUser{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", intime='" + intime + '\'' +
                    ", isdel='" + isdel + '\'' +
                    ", remark=" + remark +
                    ", nickname=" + nickname +
                    ", password=" + password +
                    ", isAdmin=" + isAdmin +
                    '}';
        }

        /**
         * 根据手机号+id 判断是否是同一个boss用户
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ShiroBossUser other = (ShiroBossUser) obj;

            if (id == null || phone == null) {
                return false;
            } else if (id.equals(other.id)
                    && phone.equals(other.phone))
                return true;
            return false;
        }

    }

}
