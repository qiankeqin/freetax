package com.freetax.facade;

import com.freetax.mybatis.bannerManage.entity.BannerManage;
import com.freetax.mybatis.bannerManage.entity.IndexInformation;
import com.freetax.mybatis.bannerManage.service.BannerManageService;
import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 9:48
 */
@Service
public class BannerManageFacade {

    @Autowired
    private BannerManageService bannerManageService;

    @Autowired
    private InformationService informationService;

    public IndexInformation queryIndexBannerOrInformation(){
        IndexInformation indexInformation = new IndexInformation();
        List<BannerManage> blist =  bannerManageService.queryIndexBannerOrInformation();
        List<BannerManage> carouselFigure = new ArrayList();//轮播图
        List toPlan = new ArrayList();//筹划背景
        List information = new ArrayList();//资讯
        if (blist != null) {
            int k = 0;
            for (int i = 0; i < blist.size(); i++) {
                switch (blist.get(i).getLocation()) {
                    //上方banner
                    case 1:
                        indexInformation.setBanner(blist.get(i).getBanner());
                        continue;
                    //主页滚动banner
                    case 2:
                        carouselFigure.set(k,blist.get(i));
                        k++;
                        continue;
                    //税务筹划背景图片
                    case 3:
                        if (blist.get(i).getOrderid() == 0) {
                            toPlan.set(0, blist.get(i));
                        }else if (blist.get(i).getOrderid() == 1){
                            toPlan.set(1,blist.get(i));
                        }
                        continue;
                    //收入对比图
                    case 6:
                        indexInformation.setIncomeComparison(blist.get(i).getBanner());
                        continue;
                }
            }
            indexInformation.setCarouselFigure(carouselFigure);
            indexInformation.setToPlan(toPlan);
            //查询精选资讯
            List<Information> informations = informationService.queryInformationIsHot();
            indexInformation.setInformation(informations);
        }else {
            return null;
        }
        return indexInformation;
    }
}
