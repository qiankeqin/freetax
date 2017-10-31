package com.freetax.shiro.realm;

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
import java.util.Date;

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
        log.info("APP登录认证");

        // 获取token中的username。这里不固定，可能是phone,qq,wx,wb
        Gson gson = new Gson();
        String tokenJson = gson.toJson(token);
        LoginUser loginUser = userFacade.getLoginUserByToken(tokenJson);

        log.debug("当前登录APP的用户信息，LoginUser = " + loginUser.toString());

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
        String mytokenStr = loginUser.getToken();
        UsernamePasswordToken mytoken = gson.fromJson(mytokenStr, UsernamePasswordToken.class);
        String password = String.valueOf(mytoken.getPassword());
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
            //获取用户的角色
            LoginUser loginUser = userFacade.getLoginuserByUserid(member.getId());
            String role = loginUser.getRole();
            log.debug("当前登录对象的角色,role=" + role);
            info.addRole(role);

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
        private Integer status; //账号状态：默认 0 正常  1 异常封号
        private String role;       //角色
        private Date registerTime;    //注册时间
        private String photo;    //头像url
        private String nickname;    //昵称
        private Integer level;   //用户等级：0 普通用户  1 青铜  2 白银 3 黄金 4 白金 5 钻石 6 金钻石 7皇冠 8金皇冠
        private String phone;   //手机号
        private String token;
        private Integer points; //用户积分
        private Integer sex;    //性别：1男 0女
        private String accid;   //云信id
        private String imToken; //云信token
        private String sign;    //个性签名
        private String birthday;  //生日
        private String qq;  //qq
        private String sina;    //微博
        private String openid;  //微信
        private Integer heatValue;  //作者热度
        private String ipCity; //根据用户登录的ip获取的所在的城市代码,对应yw_city的code
        private String latitude;    //纬度
        private String longitude;   //经度

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {

            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setHeatValue(Integer heatValue) {
            this.heatValue = heatValue;
        }

        public void setIpCity(String ipCity) {
            this.ipCity = ipCity;
        }

        public Integer getHeatValue() {

            return heatValue;
        }

        public String getIpCity() {
            return ipCity;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setSina(String sina) {
            this.sina = sina;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getQq() {
            return qq;
        }

        public String getSina() {
            return sina;
        }

        public String getOpenid() {
            return openid;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthday() {

            return birthday;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign() {

            return sign;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public void setImToken(String imToken) {
            this.imToken = imToken;
        }

        public String getAccid() {

            return accid;
        }

        public String getImToken() {
            return imToken;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getSex() {

            return sex;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public Integer getPoints() {

            return points;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setRegisterTime(Date registerTime) {
            this.registerTime = registerTime;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Integer getId() {
            return id;
        }

        public String getAccount() {
            return account;
        }

        public Integer getStatus() {
            return status;
        }

        public String getRole() {
            return role;
        }

        public Date getRegisterTime() {
            return registerTime;
        }

        public String getPhoto() {
            return photo;
        }

        public String getNickname() {
            return nickname;
        }

        public Integer getLevel() {
            return level;
        }

        public String getPhone() {
            return phone;
        }

        public String getToken() {
            return token;
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

        public ShiroUser(Integer id, String account, Integer status, String role, Date registerTime, String photo, String nickname, Integer level, String phone, String token, Integer points, Integer sex, String accid, String imToken, String sign, String birthday, String qq, String sina, String openid, Integer heatValue, String ipCity, String latitude, String longitude) {
            this.id = id;
            this.account = account;
            this.status = status;
            this.role = role;
            this.registerTime = registerTime;
            this.photo = photo;
            this.nickname = nickname;
            this.level = level;
            this.phone = phone;
            this.token = token;
            this.points = points;
            this.sex = sex;
            this.accid = accid;
            this.imToken = imToken;
            this.sign = sign;
            this.birthday = birthday;
            this.qq = qq;
            this.sina = sina;
            this.openid = openid;
            this.heatValue = heatValue;
            this.ipCity = ipCity;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "ShiroUser{" +
                    "id=" + id +
                    ", account='" + account + '\'' +
                    ", status=" + status +
                    ", role='" + role + '\'' +
                    ", registerTime=" + registerTime +
                    ", photo='" + photo + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", level=" + level +
                    ", phone='" + phone + '\'' +
                    ", token='" + token + '\'' +
                    ", points=" + points +
                    ", sex=" + sex +
                    ", accid='" + accid + '\'' +
                    ", imToken='" + imToken + '\'' +
                    ", sign='" + sign + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", qq='" + qq + '\'' +
                    ", sina='" + sina + '\'' +
                    ", openid='" + openid + '\'' +
                    ", heatValue=" + heatValue +
                    ", ipCity='" + ipCity + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }
    }

}
