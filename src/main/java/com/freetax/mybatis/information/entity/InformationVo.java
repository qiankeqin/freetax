package com.freetax.mybatis.information.entity;

import java.util.Date;

public class InformationVo {
    private Integer id;

    private String title;

    private String subhead;

    private String coverimg;

    private Integer type;

    private Integer ishot;

    private String source;

    private Date intime;

    private Integer isdel;

    private String content;

    private Integer upid;//上一条id

    private String upTitle;//上一条标题

    private Integer belowid;//下一条id

    private String belowTitle;//下一条标题

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public String getUpTitle() {
        return upTitle;
    }

    public void setUpTitle(String upTitle) {
        this.upTitle = upTitle;
    }

    public Integer getBelowid() {
        return belowid;
    }

    public void setBelowid(Integer belowid) {
        this.belowid = belowid;
    }

    public String getBelowTitle() {
        return belowTitle;
    }

    public void setBelowTitle(String belowTitle) {
        this.belowTitle = belowTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead == null ? null : subhead.trim();
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg == null ? null : coverimg.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}