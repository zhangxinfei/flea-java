package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.AdminRole;
import com.soecode.lyf.entity.params.AdminParams;

import java.util.List;
import java.util.Map;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);

    /**
     * 查询多个管理员的多个角色ID
     * @return
     */
    List<Map<String ,Object>> selectManyIdByadminId(Map<String ,Object> adminMap);

    /**
     * 通过所有管理员
     * @param adminAllMap
     * @return
     */
    List<Map<String,Object>> selectAllAdminAndRole(Map<String ,Object> adminAllMap);

    /**
     * 新增管理员id和角色id的映射
     * @param roleAdminMap
     * @return
     */
    int insertRoleAdmin(Map<String,Object> roleAdminMap);

    /**
     * 批量修改
     * @param roleAdminMap
     * @return
     */
    int updateRoleAdmin(Map<String,Object> roleAdminMap);
}