package com.freetax.mybatis.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于shiroRealm，包含登录app的用户的所有信息
 * 比User多出  role, accid, imtoken
 *
 * @Author zhuangyuhao
 * @Date 2017/1/18 16:26
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 3748849614356147652L;

    private Integer id;

    private String phone;

    private String passwd;

    private String photo;

    private String name;

    private String company;

    private String email;

    private Date intime;

    private Date logintime;

    private Date advicetime;

    private Integer infosource;

    private Integer status;

    private Integer mark;

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
        this.phone = phone;
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

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", intime='" + intime + '\'' +
                ", logintime='" + logintime + '\'' +
                ", advicetime='" + advicetime + '\'' +
                ", infosource='" + infosource + '\'' +
                ", status=" + status +
                ", mark=" + mark +
                '}';
    }
}
