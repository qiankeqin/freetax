package com.freetax.mybatis.userMenuRelation.mapper;

import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;

public interface UserMenuRelationMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserMenuRelation record);

    int insertSelective(UserMenuRelation record);

    UserMenuRelation selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserMenuRelation record);

    int updateByPrimaryKey(UserMenuRelation record);
}