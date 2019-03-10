package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.Permission;
import com.soecode.lyf.entity.RolePermission;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    /**
     * 通过权限id查询是否有权限
     * @param permissionIdMap
     * @return
     */
    List<Map<String, Object>> selectPermissionById(Map<String, Object> permissionIdMap);

    /**
     * 查看所有权限信息
     * @return
     */
    List<Map<String, Object>> selectALLPermission();

    /**
     * 通过权限编码查询id
     * @param permissionNameMap
     * @return
     */
    List<Map<String, Object>> selectPermissionByName(Map<String, Object> permissionNameMap);

}