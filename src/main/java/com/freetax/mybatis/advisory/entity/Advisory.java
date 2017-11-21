package com.freetax.mybatis.advisory.entity;

import java.util.Date;

public class Advisory {
    private Integer id;

    private String name;

    private String phone;

    private Integer visit;

    private Date intime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
}