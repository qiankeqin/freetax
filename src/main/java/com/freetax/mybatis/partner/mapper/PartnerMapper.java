package com.freetax.mybatis.partner.mapper;

import com.freetax.mybatis.partner.entity.Partner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Partner record);

    int insertSelective(Partner record);

    Partner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Partner record);

    int updateByPrimaryKey(Partner record);

    List<Partner> queryPartnerList();

    List<Partner> findAllQueryPartnerByList(Partner partner, RowBounds rowBounds);

    void deletePartner(Partner partner);

    void updateByid(Partner partner);
}