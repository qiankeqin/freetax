package com.freetax.mybatis.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shuxf
 * @Date 2017/2/6 16:54
 */
public class UserVo implements Serializable {
    private Integer id;

    private String email;

    private String phone;

    private String photo;

    private Date intime;

    private Integer status;//账号状态：0 正常 1 封号

    private Integer mark;//联系状态：0 未联系 1已联系

    private Date logintime;//最后一次登录时间

    private Date logBegin;//登录开始时间

    private Date logEnd;//登录结束时间

    private Date advicetime;//最后一次咨询时间

    private Date adviceBegin;//咨询开始时间

    private Date adviceEnd;//咨询结束时间

    private Date registerin;//注册开始时间

    private Date registerend;//注册结束时间

    private Integer infosource;//信息来源：0 业务咨询 1 招商加盟

    private String passwd;//用户密码

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

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogBegin() {
        return logBegin;
    }

    public void setLogBegin(Date logBegin) {
        this.logBegin = logBegin;
    }

    public Date getLogEnd() {
        return logEnd;
    }

    public void setLogEnd(Date logEnd) {
        this.logEnd = logEnd;
    }

    public Date getAdvicetime() {
        return advicetime;
    }

    public void setAdvicetime(Date advicetime) {
        this.advicetime = advicetime;
    }

    public Date getAdviceBegin() {
        return adviceBegin;
    }

    public void setAdviceBegin(Date adviceBegin) {
        this.adviceBegin = adviceBegin;
    }

    public Date getAdviceEnd() {
        return adviceEnd;
    }

    public void setAdviceEnd(Date adviceEnd) {
        this.adviceEnd = adviceEnd;
    }

    public Date getRegisterin() {
        return registerin;
    }

    public void setRegisterin(Date registerin) {
        this.registerin = registerin;
    }

    public Date getRegisterend() {
        return registerend;
    }

    public void setRegisterend(Date registerend) {
        this.registerend = registerend;
    }

    public Integer getInfosource() {
        return infosource;
    }

    public void setInfosource(Integer infosource) {
        this.infosource = infosource;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
