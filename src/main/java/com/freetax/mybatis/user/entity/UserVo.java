package com.freetax.mybatis.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shuxf
 * @Date 2017/2/6 16:54
 */
public class UserVo implements Serializable {
    private Integer id;

    private String openid;

    private String qq;

    private String sina;

    private String email;

    private String phone;

    private String token;

    private String invitecode;

    private String referrals;

    private String nickname;

    private String sign;

    private Integer attention;

    private Integer fans;

    private Integer points;

    private String photo;

    private Integer sex;

    private Date birthday;

    private String province;

    private String city;

    private Date intime;

    private Integer growth;

    private Integer level;

    private Integer isdv;

    private Integer status;

    private Integer livesum;//直播个数

    private Integer becollectsum;//被收藏次数

    private Integer besharesum;//被分享次数

    private Integer followsum;//关注别人的个数（关注数）

    private Integer befollowsum;//被关注的次数（粉丝数）

    private Integer postsum;//帖子数量

    public Integer activecount;//参加活动数

    public Integer collectioncount;//收藏帖子数

    private Integer heatValue;//作者热度

    private Integer isrecommend;//是否作为发现页必推作者：0 否 1 是'

    private Integer isessencesum;//精贴数量

    private Integer exceptional;//被打赏积分

    private Date applydate;//申请时间

    private Integer commentsum;//被评论数

    private Integer zansum;//被点赞数

    private Integer authstatus;//实名认证状态

    private Date addVtime;//加V时间

    private Integer appyid;//申请id

    private String reason;//驳回原因

    private Integer auditStatus;//审核状态

    private Date audittime;//审核时间

    private Integer isfollow;//是否被关注 0 未关注 1 已关注（当前作者有没有被APP当前登录的用户关注过）

    private String ipcity;//登录城市

    private Integer badgenum;//当前用户获取到的徽章总数

    public String getIpcity() {
        return ipcity;
    }

    public void setIpcity(String ipcity) {
        this.ipcity = ipcity;
    }

    public Integer getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(Integer heatValue) {
        this.heatValue = heatValue;
    }

    public Integer getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Integer isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Integer getActivecount() {
        return activecount;
    }

    public void setActivecount(Integer activecount) {
        this.activecount = activecount;
    }

    public Integer getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(Integer collectioncount) {
        this.collectioncount = collectioncount;
    }



    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getAppyid() {
        return appyid;
    }

    public void setAppyid(Integer appyid) {
        this.appyid = appyid;
    }

    public Date getAddVtime() {
        return addVtime;
    }

    public void setAddVtime(Date addVtime) {
        this.addVtime = addVtime;
    }

    public Integer getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(Integer authstatus) {
        this.authstatus = authstatus;
    }

    public Integer getZansum() {
        return zansum;
    }

    public void setZansum(Integer zansum) {
        this.zansum = zansum;
    }

    public Integer getCommentsum() {
        return commentsum;
    }

    public void setCommentsum(Integer commentsum) {
        this.commentsum = commentsum;
    }

    public Integer getExceptional() {
        return exceptional;
    }

    public void setExceptional(Integer exceptional) {
        this.exceptional = exceptional;
    }

    public Integer getIsessencesum() {
        return isessencesum;
    }

    public void setIsessencesum(Integer isessencesum) {
        this.isessencesum = isessencesum;
    }

    public Date getApplydate() {
        return applydate;
    }

    public Integer getPostsum() {
        return postsum;
    }

    public void setPostsum(Integer postsum) {
        this.postsum = postsum;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSina() {
        return sina;
    }

    public void setSina(String sina) {
        this.sina = sina;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getReferrals() {
        return referrals;
    }

    public void setReferrals(String referrals) {
        this.referrals = referrals;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsdv() {
        return isdv;
    }

    public void setIsdv(Integer isdv) {
        this.isdv = isdv;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLivesum() {
        return livesum;
    }

    public void setLivesum(Integer livesum) {
        this.livesum = livesum;
    }

    public Integer getBecollectsum() {
        return becollectsum;
    }

    public void setBecollectsum(Integer becollectsum) {
        this.becollectsum = becollectsum;
    }

    public Integer getBesharesum() {
        return besharesum;
    }

    public void setBesharesum(Integer besharesum) {
        this.besharesum = besharesum;
    }

    public Integer getFollowsum() {
        return followsum;
    }

    public void setFollowsum(Integer followsum) {
        this.followsum = followsum;
    }

    public Integer getBefollowsum() {
        return befollowsum;
    }

    public void setBefollowsum(Integer befollowsum) {
        this.befollowsum = befollowsum;
    }

    public Integer getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(Integer isfollow) {
        this.isfollow = isfollow;
    }

    public Integer getBadgenum() {
        return badgenum;
    }

    public void setBadgenum(Integer badgenum) {
        this.badgenum = badgenum;
    }
}
