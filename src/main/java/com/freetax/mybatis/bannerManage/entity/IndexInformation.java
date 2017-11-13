package com.freetax.mybatis.bannerManage.entity;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 9:54
 */
public class IndexInformation {

    private String banner;

    private List carouselFigure;//轮播图

    private String incomeComparison;//收入对比图

    private List toPlan;//筹划

    private List information;//资讯

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List getCarouselFigure() {
        return carouselFigure;
    }

    public void setCarouselFigure(List carouselFigure) {
        this.carouselFigure = carouselFigure;
    }

    public String getIncomeComparison() {
        return incomeComparison;
    }

    public void setIncomeComparison(String incomeComparison) {
        this.incomeComparison = incomeComparison;
    }

    public List getToPlan() {
        return toPlan;
    }

    public void setToPlan(List toPlan) {
        this.toPlan = toPlan;
    }

    public List getInformation() {
        return information;
    }

    public void setInformation(List information) {
        this.information = information;
    }
}
