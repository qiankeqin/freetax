package com.freetax.mybatis.adminMenu.mapper;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import com.freetax.mybatis.adminMenu.entity.AdminMenuVo;
import com.freetax.mybatis.userMenuRelation.entity.UserMenuRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenu record);

    int insertSelective(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMenu record);

    int updateByPrimaryKey(AdminMenu record);

    List<AdminMenuVo> queryMenuListByVariation(UserMenuRelation relation);

    List<AdminMenu> queryMenuListBySon(UserMenuRelation relation);

    List<AdminMenuVo> queryMenuList();

    void insertMenu(AdminMenu adminMenus);

    AdminMenu queryMenuById(Integer id);

    void updateMenuById(AdminMenu adminMenu);

    void deleteMenuById(Integer id);
}