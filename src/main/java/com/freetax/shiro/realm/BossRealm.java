package com.freetax.shiro.realm;

import com.freetax.common.constant.ImConstant;
import com.freetax.common.constant.SessionConstant;
import com.freetax.common.constant.UserConstants;
import com.freetax.facade.im.ImFacade;
import com.freetax.facade.user.BossUserFacade;
import com.freetax.facade.user.UserRoleRelationFacade;
import com.freetax.mybatis.bossUser.entity.BossUser;
import com.freetax.mybatis.imuser.entity.ImUser;
import com.freetax.utils.pagination.util.StringUtils;
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


/**
 * 验证boss主体的安全数据源
 * @author zhuangyuhao
 */
public class BossRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(BossRealm.class);
    @Autowired
    private UserRoleRelationFacade userRoleRelationFacade;
    @Autowired
    private BossUserFacade bossUserFacade;

    @Autowired
    private ImFacade imFacade;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        log.info("boss 登录认证");
        String userName = (String) token.getPrincipal();    //用户名
        BossUser bossUser = bossUserFacade.getByUsername(userName);
        if (bossUser != null) {
            log.info("该用户在数据库中存在");
            if (bossUser.getIsdel() == 1) {
                throw new LockedAccountException("该账号已经被删除");
            }
        }  else{
            throw new UnknownAccountException();
        }
        // 获取用户的角色
        int roleid = userRoleRelationFacade.getRoleidByUserid(bossUser.getId());
        ImUser imUser = imFacade.getImuser(bossUser.getId(), ImConstant.TYPE_BOSS);
        String accid = null == imUser ? null : imUser.getAccid();
        String imtoken = null == imUser ? null : imUser.getToken();

        //封装自定义principle对象
        ShiroBossUser shiroBossUser = new ShiroBossUser(bossUser.getId(), bossUser.getName(), bossUser.getPhone(), bossUser.getUsername(),
                bossUser.getPassword(), bossUser.getIssuper(), bossUser.getStatus(), bossUser.getIsdel(), bossUser.getCreatetime(),
                bossUser.getAfterlogintime(), bossUser.getBeforelogintime(), roleid, accid, imtoken,
                bossUser.getIscircle(), bossUser.getCirclemanagement(), bossUser.getContributing(), bossUser.getCommon());

        log.info("realm name = " + getName());
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                shiroBossUser, // 自定义principle对象
                bossUser.getPassword(), // 密码，这里密码是加密的
                getName() // realm name
        );

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return authenticationInfo;

    }

    /**
     * 授权，只有成功通过doGetAuthenticationInfo方法的认证后才会执行。
     * 查询回调函数, 进行鉴权， 当缓存中无用户的授权信息时调用，否则从缓存中调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        this.clearCachedAuthorizationInfo(principals);
        log.info("清除Boss用户授权信息缓存");
        //  获取当前登录principle
        Subject subject = SecurityUtils.getSubject();
        ShiroBossUser shiroBossUser = (ShiroBossUser) subject.getSession(false).getAttribute(SessionConstant.BOSS_USER);
        if (null != shiroBossUser) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 判断账号状态 : 0 正常 1 冻结
            String status = String.valueOf(shiroBossUser.getStatus());
            if (StringUtils.isEmpty(status) || UserConstants.USER_STATUS.disable.toString().equals(status)) {
                log.error("账号状态异常");
                return null;
            }
            int roleid = shiroBossUser.getRole();
            log.debug("当前用户的角色 ：role=" + roleid);
            // 添加用户角色到授权信息
            info.addRole(String.valueOf(roleid));
            return info;
        }
        return null;
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
        private String name;
        private String phone;
        private String username;
        private String password;
        private Integer issuper;
        private Integer status;
        private Integer isdel;
        private Date createtime;
        private Date afterlogintime;
        private Date beforelogintime;
        //对应的角色
        private Integer role;
        private String accid;
        private String imtoken;
        //是否是圈主 0否 1是
        private Integer iscircle;
        //是否是圈子管理员 0否 1是
        private Integer circlemanagement;
        //是否是特约嘉宾 0否 1是
        private Integer contributing;
        //是否是普通管理员 0否 1是
        private Integer common;

        public void setImtoken(String imtoken) {
            this.imtoken = imtoken;
        }

        public void setIscircle(Integer iscircle) {
            this.iscircle = iscircle;
        }

        public void setCirclemanagement(Integer circlemanagement) {
            this.circlemanagement = circlemanagement;
        }

        public void setContributing(Integer contributing) {
            this.contributing = contributing;
        }

        public void setCommon(Integer common) {
            this.common = common;
        }

        public Integer getIscircle() {

            return iscircle;
        }

        public Integer getCirclemanagement() {
            return circlemanagement;
        }

        public Integer getContributing() {
            return contributing;
        }

        public Integer getCommon() {
            return common;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public void setToken(String imtoken) {
            this.imtoken = imtoken;
        }

        public String getAccid() {

            return accid;
        }

        public String getImtoken() {
            return imtoken;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setIssuper(Integer issuper) {
            this.issuper = issuper;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setIsdel(Integer isdel) {
            this.isdel = isdel;
        }

        public void setCreatetime(Date createtime) {
            this.createtime = createtime;
        }

        public void setAfterlogintime(Date afterlogintime) {
            this.afterlogintime = afterlogintime;
        }

        public void setBeforelogintime(Date beforelogintime) {
            this.beforelogintime = beforelogintime;
        }

        public void setRole(Integer role) {
            this.role = role;
        }

        public static long getSerialVersionUID() {

            return serialVersionUID;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public Integer getIssuper() {
            return issuper;
        }

        public Integer getStatus() {
            return status;
        }

        public Integer getIsdel() {
            return isdel;
        }

        public Date getCreatetime() {
            return createtime;
        }

        public Date getAfterlogintime() {
            return afterlogintime;
        }

        public Date getBeforelogintime() {
            return beforelogintime;
        }

        public Integer getRole() {
            return role;
        }


        public ShiroBossUser(Integer id, String name, String phone, String username, String password, Integer issuper, Integer status, Integer isdel, Date createtime, Date afterlogintime, Date beforelogintime, Integer role, String accid, String imtoken, Integer iscircle, Integer circlemanagement, Integer contributing, Integer common) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.username = username;
            this.password = password;
            this.issuper = issuper;
            this.status = status;
            this.isdel = isdel;
            this.createtime = createtime;
            this.afterlogintime = afterlogintime;
            this.beforelogintime = beforelogintime;
            this.role = role;
            this.accid = accid;
            this.imtoken = imtoken;
            this.iscircle = iscircle;
            this.circlemanagement = circlemanagement;
            this.contributing = contributing;
            this.common = common;
        }

        @Override
        public String toString() {
            return "ShiroBossUser{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", issuper=" + issuper +
                    ", status=" + status +
                    ", isdel=" + isdel +
                    ", createtime=" + createtime +
                    ", afterlogintime=" + afterlogintime +
                    ", beforelogintime=" + beforelogintime +
                    ", role=" + role +
                    ", accid='" + accid + '\'' +
                    ", imtoken='" + imtoken + '\'' +
                    ", iscircle=" + iscircle +
                    ", circlemanagement=" + circlemanagement +
                    ", contributing=" + contributing +
                    ", common=" + common +
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
