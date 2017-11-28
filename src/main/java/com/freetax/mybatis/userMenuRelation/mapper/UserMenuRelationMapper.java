package com.freetax.mybatis.userMenuRelation.mapper;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuRelationMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserMenuRelation record);

    int insertSelective(UserMenuRelation record);

    UserMenuRelation selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserMenuRelation record);

    int updateByPrimaryKey(UserMenuRelation record);

    void deleteUserMenu(UserMenuRelation relation);

    void insertUserMenu(UserMenuRelation relation);

    List<AdminMenuVo> queryUserByMenuToList(Integer id);
}