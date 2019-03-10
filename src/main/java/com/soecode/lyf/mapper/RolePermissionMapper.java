package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.AdminRole;
import com.soecode.lyf.entity.Role;
import com.soecode.lyf.entity.RolePermission;

import java.util.List;
import java.util.Map;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
    /**
     * 通过角色id查询权限
     * @param adminParamsMap
     * @return
     */
    List<Map<String,Object>> selectByRoleid(Map<String, Object> adminParamsMap);

    /**
     * 添加角色和权限的映射
     * @param permissionRoleList
     * @return
     */
    int insertPermissionRole(Map<String,Object> permissionRoleList);

    /**
     * 修改角色和权限的映射
     * @param permissionRoleList
     * @return
     */
    int updatePermissionRole(Map<String,Object> permissionRoleList);

}