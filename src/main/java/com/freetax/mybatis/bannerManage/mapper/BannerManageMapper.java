package com.freetax.mybatis.bannerManage.mapper;

import com.freetax.mybatis.bannerManage.entity.BannerManage;
import com.freetax.mybatis.bannerManage.entity.BannerManageVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerManage record);

    int insertSelective(BannerManage record);

    BannerManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerManage record);

    int updateByPrimaryKey(BannerManage record);

    int updateBannerByKey(BannerManage bannerManage);

    List<BannerManage> queryIndexBannerOrInformation();

    List<BannerManage> queryBannerByType(Integer type);

    void insertAdvertisement(BannerManage bannerManage);

    void updateAdvertisement(BannerManage bannerManage);

    BannerManage queryAdvertisementById(Integer id);

    List<BannerManage> queryAdvertisementByList(BannerManageVo bannerManage);

    void deleteAdvertisement(BannerManage bannerManage);
}