package com.freetax.mybatis.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserExcel implements Serializable {

    private Integer id;

    private String email;

    private String phone;

    private String photo;

    private Date intime;

    private Integer infosource;//信息来源：0 业务咨询 1 招商加盟

    private String name;//联系人姓名

    private String company;//公司名称

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Integer getInfosource() {
        return infosource;
    }

    public void setInfosource(Integer infosource) {
        this.infosource = infosource;
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
}