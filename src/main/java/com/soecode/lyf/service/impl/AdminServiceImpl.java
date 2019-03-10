package com.soecode.lyf.service.impl;

import com.alibaba.fastjson.JSON;
import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.*;
import com.soecode.lyf.service.AdminService;
import com.soecode.lyf.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    //    日志
    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Override
    public ResultModel selectByLogin(HttpServletRequest request, Admin admin) {
//        初始化序列化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
//        查询数据库，返回值为0的化就是没有登录失败，为1的化就是成功
        //添加管理员状态
        admin.setAdminStatus(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue());
//        admin.setAdminIphone(admin.getAdminName());
        logger.info("登录验证—>start");
        Admin adminInfluence = adminMapper.selectByLogin(admin);
        logger.info("查询当前管理员权限->start");
        //查询用户的角色id
        List<Map<String,Object>> adminParamsList = adminMapper.selectRoleAdminById(adminInfluence);
        //查询权限id
        Map<String, Object> adminParamsMap = new HashMap<String, Object>();
        adminParamsMap.put("adminParamsList", adminParamsList);
        List<Map<String,Object>> permissonIdList = rolePermissionMapper.selectByRoleid(adminParamsMap);
        List<Map<String,Object>> roleLevelList = roleMapper.selectRoleById(adminParamsMap);
        int minRole = (int)roleLevelList.get(0).get("roleLevel");
        for(int i=0;i<roleLevelList.size();i++){
            if(minRole>(int)roleLevelList.get(i).get("roleLevel")){
                minRole = (int)roleLevelList.get(i).get("roleLevel");
            }
        }
        //查询当前管理员权限信息
        Map<String, Object> permissionIdMap = new HashMap<String, Object>();
        permissionIdMap.put("permissonIdList",permissonIdList);
        List<Map<String,Object>> permissionList = permissionMapper.selectPermissionById(permissionIdMap);
        //查看全部权限信息
//        List<Map<String,Object>> permissionAllList = permissionMapper.selectALLPermission();
        logger.info("查询当前管理员权限->end");
        if(!adminInfluence.getId().equals(null)){
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("adminId",adminInfluence.getId());
            resultMap.put("adminName",adminInfluence.getAdminName());
            System.out.println(adminInfluence.getAdminName());
            resultMap.put("adminStatus",adminInfluence.getAdminStatus());
            //管理员等级
            resultMap.put("roleLevel",minRole);
            //管理员角色id
            String roleIdList = JSON.toJSONString(adminParamsList);
//            System.out.println(roleIdList);
            resultMap.put("adminParamsList",roleIdList);
            //当前管理员权限信息
            String permission = JSON.toJSONString(permissionList);
            System.out.println(permission);
            resultMap.put("permissionList",permission);
            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(resultMap);
            logger.info("登录成功->end");
        }else {
            logger.info("登录失败—>end");
        }
        return result;
    }

    @Override
    public ResultModel selectAllAdmin(List<Map<String,Object>> permissionList, String permissionName) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
 A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                List<Map<String,Object>> adminList = adminMapper.selectAllAdmin();
                if(adminList.size() > 0){
                    Map<String, Object> adminMap = new HashMap<String, Object>();
                    adminMap.put("adminList", adminList);
                    //查询角色id
                    List<Map<String,Object>> roleIdList = adminMapper.selectAllAdminAndRole(adminMap);
                    Map<String, Object> roleMap = new HashMap<String, Object>();
                    roleMap.put("roleIdList", roleIdList);
                    //查询角色的等级和角色名称
                    List<Map<String,Object>> roleLevelList = roleMapper.selectRoleLevelbyId(roleMap);
                    for (Map roleAndAdminMap: roleIdList) {
                                for (Map roleLevelMap: roleLevelList ) {
                                    if (roleAndAdminMap.get("roleId") == roleLevelMap.get("id")) {
                                        roleAndAdminMap.put("roleLevel", roleLevelMap.get("roleLevel"));
                                        roleAndAdminMap.put("roleName", roleLevelMap.get("roleName"));
                                }
                        }
                        if((int)roleAndAdminMap.get("adminStatus") == 10){
                            roleAndAdminMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_SUCCESS.getDesc());
                        }
                        else {
                            roleAndAdminMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_FAIL.getDesc());
                        }
                    }
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    result.setData(roleIdList);
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel deleteAdmin(List<Map<String,Object>> permissionList, String permissionName,Admin admin) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int deleteAdminInfluence = adminMapper.deleteAdmin(admin);
                if(deleteAdminInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel updateAdmin(List<Map<String, Object>> permissionList, String permissionName, AdminParams adminParams,List<Map<String, Object>> roleNameList) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                logger.info("修改管理员-->start");
                //修改管理员 信息
                int deleteAdminInfluence = adminMapper.updateAdmin(adminParams);
                //把要修改的管理员的id存入角色的集合
                for (Map rolemap:roleNameList) {
                    rolemap.put("adminId",adminParams.getId());
                }
                Map<String,Object> roleMap = new HashMap<String, Object>();
                roleMap.put("roleNameList",roleNameList);
                int updateRoleInfluence = adminRoleMapper.updateRoleAdmin(roleMap);
                if(deleteAdminInfluence > 0 && updateRoleInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    logger.info("修改管理员-->end");
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel insertAdmin(List<Map<String, Object>> permissionList, String permissionName, AdminParams adminParams,List<Map<String,Object>> roleNameList) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限-->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                logger.info("新增管理员-->start");
                //添加管理员信息
                int insertAdminInfluence = adminMapper.insertAdmin(adminParams);
                //通过管理员姓名查询管理员id
                Admin admin = new Admin();
                admin.setAdminName(adminParams.getAdminName());
                admin.setAdminNumber(adminParams.getAdminNumber());
                admin.setAdminStatus(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue());
                Admin adminInfluence = adminMapper.selectByLogin(admin);
                //把管理员的角色放入角色表
                for (Map roleNameMap:roleNameList) {
                    roleNameMap.put("adminId",adminInfluence.getId());
                }
                //添加角色id和管理员id到映射表
                Map<String ,Object> roleAdminMap = new HashMap<>();
                roleAdminMap.put("roleNameList",roleNameList);
                int insertRoleAdminInfluence = adminRoleMapper.insertRoleAdmin(roleAdminMap);
                if(insertAdminInfluence > 0 && insertRoleAdminInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    logger.info("新增管理员-->end");
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel selectRepeat(Admin admin) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("查询数据库-->start");
        int adminInfulence = adminMapper.selectRepeat(admin);
        if(adminInfulence == 0){
            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("查询数据库-->end");
        return result;
    }

    @Override
    public ResultModel selectOneById(Admin admin) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("查询数据库-->start");
        Admin adminInfulence = adminMapper.selectOneById(admin);
        if(adminInfulence.getId() > 0){
            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(adminInfulence);
        }
        logger.info("查询数据库-->end");
        return result;
    }

    @Override
    public ResultModel updateOneAdmin(Admin admin) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("查询数据库-->start");
        int adminInfulence = adminMapper.updateOneAdmin(admin);
        if(adminInfulence > 0){
            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("查询数据库-->end");
        return result;
    }

    @Override
    public void setToken(String token,Admin admin) {
        logger.info("把token存入数据库中——>start");
        AdminParams adminParams = new AdminParams();
        Admin adminInfluence = adminMapper.selectByLogin(admin);
        adminParams.setToken(token);
        adminParams.setId(adminInfluence.getId());
        int tokenInfluence = adminMapper.setToken(adminParams);
        logger.info("把token存入数据库中——>end");
    }
}
