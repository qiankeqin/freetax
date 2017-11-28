package com.freetax.mybatis.adminMenu.entity;

import java.util.Date;
import java.util.List;

public class AdminMenuVo {
    private Integer id;

    private String name;

    private String url;

    private Integer orderid;

    private Integer pid;

    private Date intime;

    private Integer isdel;

    private List<AdminMenu> submenu;

    private Boolean isUse;

    private String ico;

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    public List<AdminMenu> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<AdminMenu> submenu) {
        this.submenu = submenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
}