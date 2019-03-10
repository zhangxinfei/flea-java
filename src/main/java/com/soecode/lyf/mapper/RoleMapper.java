package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.Role;
import com.soecode.lyf.entity.params.AdminParams;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    /**
     * 删除角色信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询全部角色信息
     */
    List<Map<String,Object>> selectAllRole(int roelLevel);

    /**
     * 查询单个角色信息
     */
    List<Map<String,Object>> selectRoleById(Map<String,Object> adminParamsMap);

    /**
     * 通过管理员集合中的roleId查询当前管理员的角色等级
     * @param adminListMap
     * @return
     */
    List<Map<String,Object>> selectRoleLevelbyId(Map<String,Object> adminListMap);

    /**
     * 通过名称查询角色id
     * @param roleName
     * @return
     */
    int selectRoleIdByName(String roleName);

    /**
     * 查询全部角色信息
     * @return
     */
    List<Map<String,Object>> selectRole();

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int updateRole(Role role);


    /**
     *新增角色信息
     * @param role
     * @return
     */
    int insertRole(Role role);
}