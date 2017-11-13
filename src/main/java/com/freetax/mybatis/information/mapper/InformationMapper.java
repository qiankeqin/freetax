package com.freetax.mybatis.information.mapper;

import com.freetax.mybatis.information.entity.Information;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);

    List<Information> queryInformationIsHot();

    void deleteInformation(Integer id);

    List<Information> findAllQueryInformationByList(Information information, RowBounds rowBounds);
}