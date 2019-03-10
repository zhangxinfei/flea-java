package com.soecode.lyf.service;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.Permission;
import com.soecode.lyf.entity.Role;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.result.ResultModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 查看商品分类的全部分类
     *  @Request
     *  @Parms admin
     */
    ResultModel selectAllRole( List<Map<String,Object>> permissionList, String permissionName,  List<Map<String,Object>> adminParamsList);

    /**
     * 查询角色信息
     * @param permissionList
     * @param permissionName
     * @return
     */
    ResultModel selectRole( List<Map<String,Object>> permissionList, String permissionName);

    /**
     * 通过id删除角色
     * @param permissionList
     * @param permissionName
     * @param roleId
     * @return
     */
    ResultModel deleteByPrimaryKey(List<Map<String,Object>> permissionList, String permissionName,int roleId);

    /**
     * 新增角色信息
     * @param permissionList
     * @param permissionName
     * @param role
     * @return
     */
    ResultModel insertRole(List<Map<String,Object>> permissionList, String permissionName,Role role,List<Map<String,Object>> permissionRoleList);

    /**
     * 修改角色信息
     * @param permissionList
     * @param permissionName
     * @param role
     * @return
     */
    ResultModel updateRole(List<Map<String,Object>> permissionList, String permissionName,Role role,List<Map<String,Object>> permissionRoleList);



}
