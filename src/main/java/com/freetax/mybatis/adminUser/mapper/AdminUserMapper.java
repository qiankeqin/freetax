package com.freetax.mybatis.adminUser.mapper;

import com.freetax.mybatis.adminUser.entity.AdminUser;
import com.freetax.mybatis.adminUser.entity.AdminUserVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUserVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    List<AdminUserVo> findAllQueryAdminUserByList(AdminUser adminUser , RowBounds rowBounds);

    void deleteAdminUserById(Integer id);

    int insertUserByMenu(AdminUser adminUser);

    AdminUser queryUserPasswordByName(String name);

    AdminUser queryUserPasswordByPhone(String phone);
}