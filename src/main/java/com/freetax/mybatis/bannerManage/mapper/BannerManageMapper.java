package com.freetax.mybatis.bannerManage.mapper;

import com.freetax.mybatis.bannerManage.entity.BannerManage;
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

    List<BannerManage> queryIndexBannerOrInformation();
}