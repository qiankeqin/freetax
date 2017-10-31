package com.freetax.mybatis.role.mapper;

import com.freetax.mybatis.role.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;


public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int delRoles(int[] ids);

    List<Role> findAllRole(RowBounds rowBounds, Map<String, Object> map);

    List<Role> queryNotSuperAdminRoleComboList();

    List<Role> selectByMenuid(@Param("menuid") Integer menuid);

    int isExistSameName(Role role);

    List<Map<String, Object>> selectCommonAdmin();

    List<Map<String, Object>> select4StaticRole(@Param("rolename") String rolename);


}