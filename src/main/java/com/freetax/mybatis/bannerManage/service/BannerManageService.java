package com.freetax.mybatis.bannerManage.service;

import com.freetax.mybatis.bannerManage.entity.BannerManage;
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
}
