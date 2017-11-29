package com.freetax.mybatis.bannerManage.entity;

import java.util.Date;

public class BannerManageVo {
    private Integer id;

    private String banner;

    private String des;

    private Integer orderid;

    private String title;

    private Integer location;

    private Integer isdel;

    private Date intime;

    private Date intimein;

    private Date intimeend;

    public Date getIntimein() {
        return intimein;
    }

    public void setIntimein(Date intimein) {
        this.intimein = intimein;
    }

    public Date getIntimeend() {
        return intimeend;
    }

    public void setIntimeend(Date intimeend) {
        this.intimeend = intimeend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
}