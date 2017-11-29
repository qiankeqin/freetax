package com.freetax.mybatis.bannerManage.service;

import com.freetax.mybatis.bannerManage.entity.BannerManage;
import com.freetax.mybatis.bannerManage.entity.BannerManageVo;
import com.freetax.mybatis.bannerManage.entity.IndexInformation;
import com.freetax.mybatis.bannerManage.mapper.BannerManageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 9:45
 */
@Service
public class BannerManageService {

    private static Logger log = LoggerFactory.getLogger(BannerManageService.class);

    @Autowired
    private BannerManageMapper bannerManageMapper;

    /**
     * 查询首页信息
     * @return
     */
    public List<BannerManage> queryIndexBannerOrInformation(){
        try {
            log.info("查询首页banner信息");
            return bannerManageMapper.queryIndexBannerOrInformation();
        } catch (Exception e) {
            log.error("查询首页banner信息异常",e);
            throw e;
        }
    }

    /**
     * pc中根据类型查询banner
     * @param type
     * @return
     */
    public List<BannerManage> queryBannerByType(Integer type){
        try {
            log.info("pc中根据类型查询banner");
            return bannerManageMapper.queryBannerByType(type);
        } catch (Exception e) {
            log.error("pc中根据类型查询banner异常",e);
            throw e;
        }
    }

    /**
     * 新增广告
     * @param bannerManage
     */
    public void insertAdvertisement(BannerManage bannerManage){
        try {
            log.info("新增广告banner");
            bannerManageMapper.insertAdvertisement(bannerManage);
        } catch (Exception e){
            log.error("新增广告banner异常",e);
            throw e;
        }
    }


    /**
     * 修改广告
     * @param bannerManage
     */
    public void updateAdvertisement(BannerManage bannerManage){
        try {
            log.info("修改广告");
            bannerManageMapper.updateAdvertisement(bannerManage);
        } catch (Exception e){
            log.error("广告修改失败",e);
            throw e;
        }
    }


    /**
     * 根据id查询广告
     * @param id
     * @return
     */
    public BannerManage queryAdvertisementById(Integer id){
        try {
            log.info("根据id查询广告");
            return bannerManageMapper.queryAdvertisementById(id);
        } catch (Exception e){
            log.error("根据id查询广告异常",e);
            throw e;
        }
    }

    /**
     * 条件查询广告列表
     * @param bannerManage
     * @return
     */
    public List<BannerManage> queryAdvertisementByList(BannerManageVo bannerManage){
        try {
            log.info("条件查询广告列表");
            return bannerManageMapper.queryAdvertisementByList(bannerManage);
        } catch (Exception e){
            log.error("条件查询广告列表异常",e);
            throw e;
        }
    }

    /**
     * 删除广告
     * @param bannerManage
     */
    public void deleteAdvertisement(BannerManage bannerManage){
        try {
            log.info("删除广告");
            bannerManageMapper.deleteAdvertisement(bannerManage);
        } catch (Exception e){
            log.error("删除广告异常",e);
            throw e;
        }
    }

}
