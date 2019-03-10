package com.soecode.lyf.service.impl;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.Permission;
import com.soecode.lyf.entity.Role;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.*;
import com.soecode.lyf.service.RoleService;
import com.soecode.lyf.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    //    日志
    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    CommodityTypeMapper commodityTypeMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public ResultModel selectAllRole( List<Map<String,Object>> permissionList, String permissionName, List<Map<String,Object>> adminParamsList) {
        //初始化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                Map<String, Object> adminParamsMap = new HashMap<String, Object>();
                adminParamsMap.put("adminParamsList", adminParamsList);
                List<Map<String,Object>> roleLevelList = roleMapper.selectRoleById(adminParamsMap);
                int minRole = (int)roleLevelList.get(0).get("roleLevel");
                for(int i=0;i<roleLevelList.size();i++){
                    if(minRole>(int)roleLevelList.get(i).get("roleLevel")){
                        minRole = (int)roleLevelList.get(i).get("roleLevel");
                    }
                }
                    List<Map<String,Object>> roleList = roleMapper.selectAllRole(minRole);
                if(roleList.size() > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    result.setData(roleList);
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel selectRole(List<Map<String, Object>> permissionList, String permissionName) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                List<Map<String,Object>> roleInfluence = roleMapper.selectRole();
                if(roleInfluence.size() > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    result.setData(roleInfluence);
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel deleteByPrimaryKey(List<Map<String, Object>> permissionList, String permissionName, int roleId) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int roleInfluence = roleMapper.deleteByPrimaryKey(roleId);
                if(roleInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    /**
     * 新增角色
     * @param permissionList
     * @param permissionName
     * @param role
     * @param permissionRoleList
     * @return
     */
    @Override
    public ResultModel insertRole(List<Map<String, Object>> permissionList, String permissionName, Role role,List<Map<String,Object>> permissionRoleList) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                Map<String, Object> permissionRoleMap = new HashMap<String, Object>();
                permissionRoleMap.put("permissionRoleList", permissionRoleList);
               //增加角色
                int roleInfluence = roleMapper.insertRole(role);
                //获取新增的角色的id
                int roleId = roleMapper.selectRoleIdByName(role.getRoleName());
                for (Map permissionRolemap:permissionRoleList) {
                    permissionRolemap.put("roleId",roleId);
                }
                //增加角色和权限的映射
                int permissionRoleInfluence = rolePermissionMapper.insertPermissionRole(permissionRoleMap);
                if(roleInfluence > 0 && permissionRoleInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    /**
     * 修改角色
     * @param permissionList
     * @param permissionName
     * @param role
     * @param permissionRoleList
     * @return
     */
    @Override
    public ResultModel updateRole(List<Map<String, Object>> permissionList, String permissionName, Role role,List<Map<String,Object>> permissionRoleList) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int roleInfluence = roleMapper.updateRole(role);
                for (Map permissionRolemap:permissionRoleList) {
                    permissionRolemap.put("roleId",role.getId());
                }
                Map<String,Object> permissionRoleMap = new HashMap<String,Object>();
                permissionRoleMap.put("permissionRoleList",permissionRoleList);
                int rolePermissionInfluence = rolePermissionMapper.updatePermissionRole(permissionRoleMap);
                if(roleInfluence > 0 && rolePermissionInfluence>0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }


}
