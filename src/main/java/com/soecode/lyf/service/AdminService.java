package com.soecode.lyf.service;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.CommodityType;
import com.soecode.lyf.entity.Permission;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.result.ResultModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AdminService {
    /**
     *管理员登录
     * @Request
     * @Parms admin
     */
    ResultModel selectByLogin(HttpServletRequest request, Admin admin);

    /**
     *根据管理员权限查询所有管理员信息
     * @Parms admin
     */
    ResultModel selectAllAdmin(List<Map<String,Object>> permissionList, String permissionName);

    /**
     * 删除管理员
     * @Parms admin
     */
    ResultModel deleteAdmin(List<Map<String,Object>> permissionList, String permissionName,Admin admin);

    /**
     * 修改管理员
     * @param permissionList
     * @param permissionName
     * @param adminParams
     * @return
     */
    ResultModel updateAdmin(List<Map<String,Object>> permissionList, String permissionName, AdminParams adminParams,List<Map<String, Object>> roleNameList);

    /**
     * 新增管理员
     * @param permissionList
     * @param permissionName
     * @param adminParams
     * @return
     */
    ResultModel insertAdmin(List<Map<String,Object>> permissionList, String permissionName,AdminParams adminParams,List<Map<String,Object>> roleNameList);

    /**
     * 查询管理员是否重复
     * @param admin
     * @return
     */
    ResultModel selectRepeat(Admin admin);

    /**
     * 查询当前管理员信息
     * @param admin
     * @return
     */
    ResultModel selectOneById(Admin admin);

    /**
     * 修改单个管理员信息
     * @param admin
     * @return
     */
    ResultModel updateOneAdmin(Admin admin);

    /**
     * 把token存入数据库
     * @param token
     * @param admin
     * @return
     */
    void setToken(String token,Admin admin);

}


