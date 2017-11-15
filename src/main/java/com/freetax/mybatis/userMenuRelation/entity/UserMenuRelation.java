package com.freetax.mybatis.userMenuRelation.entity;

public class UserMenuRelation {
    private Integer userid;

    private Integer menuid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
}