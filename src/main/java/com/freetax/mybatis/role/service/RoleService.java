package com.freetax.mybatis.role.service;


import com.freetax.mybatis.role.entity.Role;
import com.freetax.mybatis.role.mapper.RoleMapper;
import com.freetax.utils.pagination.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author zhuangyuhao
 * @Date 2017/1/22 9:44
 */
@Service
@Transactional
public class RoleService {

    private static Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleMapper roleMapper;

    public Boolean addUserRole(Role role) {
        try {
            log.info("新增角色，role =" + role.toString());
            int n = roleMapper.insertSelective(role);
            return n == 1;
        } catch (Exception e) {
            log.error("新增角色失败，role = " + role.toString());
            throw e;
        }
    }

    public Boolean updateRole(Role role) {
        try {
            log.info("修改角色信息");
            int n = roleMapper.updateByPrimaryKeySelective(role);
            return n == 1;
        } catch (Exception e) {
            log.error("修改角色信息失败");
            throw e;
        }
    }

    public void delRoles(int[] ids) {
        try {
            log.info("删除角色，ids =" + ids);
            roleMapper.delRoles(ids);
        } catch (Exception e) {
            log.error("删除角色失败，ids = " + ids);
            throw e;
        }
    }

    public List<Role> queryRoleList(Paging<Role> pager, Map<String, Object> map) {
        try {
            log.info("查询角色列表");
            return roleMapper.findAllRole(pager.getRowBounds(), map);
        } catch (Exception e) {
            log.error("查询角色列表异常", e);
            throw e;
        }
    }

    public List<Role> queryNotSuperAdminRoleComboList() {
        try {
            log.info("查询角色下拉列表");
            return roleMapper.queryNotSuperAdminRoleComboList();
        } catch (Exception e) {
            log.error("查询角色下拉列表异常", e);
            throw e;
        }
    }

    public Role selectByPrimaryKey(Integer id) {
        try {
            log.info("根据id查询角色信息, id=" + id);
            return roleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("根据id查询角色信息失败，id=" + id);
            throw e;
        }
    }

    public List<Role> queryRoleByMenuid(Integer menuid) {
        try {
            log.info("根据菜单id查询角色");
            return roleMapper.selectByMenuid(menuid);
        } catch (Exception e) {
            log.error("根据菜单id查询角色失败");
            throw e;
        }
    }

    public int isExistSameName(Role role) {
        try {
            log.info("判断是否存在相同的角色名称");
            return roleMapper.isExistSameName(role);
        } catch (Exception e) {
            log.error("判断是否存在相同的角色名称失败", e);
            throw e;
        }
    }

    public List<Map<String, Object>> selectCommonAdmin() {
        try {
            log.info("查询普通管理员身份下的所有角色");
            return roleMapper.selectCommonAdmin();
        } catch (Exception e) {
            log.error("查询普通管理员身份下的所有角色失败", e);
            throw e;
        }
    }

    public List<Map<String, Object>> select4StaticRole(String rolename) {
        try {
            log.info("查询4中固定角色的其中一种");
            return roleMapper.select4StaticRole(rolename);
        } catch (Exception e) {
            log.error("查询4中固定角色的其中一种失败", e);
            throw e;
        }
    }


}
