package com.freetax.mybatis.adminUser.mapper;

import com.freetax.mybatis.adminUser.entity.AdminUser;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    List<AdminUser> findAllQueryAdminUserByList(AdminUser adminUser , RowBounds rowBounds);

    void deleteAdminUserById(Integer id);

    AdminUser queryUserPasswordByName(String phone);
}