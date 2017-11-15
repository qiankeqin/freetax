package com.freetax.mybatis.adminMenu.mapper;

import com.freetax.mybatis.adminMenu.entity.AdminMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenu record);

    int insertSelective(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMenu record);

    int updateByPrimaryKey(AdminMenu record);
}