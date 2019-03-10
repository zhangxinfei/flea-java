package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.AdminRole;
import com.soecode.lyf.entity.params.AdminParams;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    /**
     * 登录查询
     * @param admin
     * @return
     */
    Admin selectByLogin(Admin admin);
    /**
     * 根据权限，查询全部管理员信息
     * @return
     */
    List<Map<String,Object>> selectAllAdmin();
    /**
     * 查询单个管理员信息，根据id和状态
     * @param admin
     * @return
     */
    List<Map<String,Object>> selectRoleAdminById(Admin admin);

    /**
     * 删除管理员，改变状态
     * @param admin
     * @return
     */
    int deleteAdmin(Admin admin);

    /**
     * 修改管理员信息
     * @param adminParams
     * @return
     */
    int updateAdmin(AdminParams adminParams);

    /**
     * 新增管理员信息
     * @param adminParams
     * @return
     */
    int insertAdmin(AdminParams adminParams);

    /**
     * 查询所有管员角色id和管理员的信息
     * @param adminMap
     * @return
     */
    List<Map<String ,Object>> selectAllAdminAndRole(Map<String,Object> adminMap);

    /**
     * 查询管理员姓名在数据库是否重复
     * @param admin
     * @return
     */
    int selectRepeat(Admin admin);

    /**
     * 查询单个管理员信息
     * @param admin
     * @return
     */
    Admin selectOneById(Admin admin);

    /**
     * 修改单个管理员信息
     * @param admin
     * @return
     */
    int updateOneAdmin(Admin admin);

    /**
     * 把token存入数据库
     * @param adminParams
     * @return
     */
    int setToken(AdminParams adminParams);

    /**
     * 通过token查询用户
     * @param token
     * @return
     */
    AdminParams selectAdminByToken(String token);
}