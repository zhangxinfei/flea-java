package com.soecode.lyf.controller;

import com.soecode.lyf.entity.Role;
import com.soecode.lyf.entity.params.Layui;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.service.RoleService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Role")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
public class RoleController {
    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    @Autowired
    RoleService roleService;

    /**
     * 查询角色分类
     * @return
     */
    @RequestMapping(value = "/selectAllRole", method = RequestMethod.POST, produces = "application/json")
    public ResultModel selectAllRole(HttpServletRequest request, @RequestBody String responseData){
        logger.info("查询数据库中全部角色信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        List<Map<String,Object>> adminParamsList = (List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("adminParamsList")), Map.class);
        ResultModel result = roleService.selectAllRole(permissionList,permissionName,adminParamsList);
        logger.info("查询数据库中全部角色信息->end");
        return result;
    }

    //查询角色信息
    @RequestMapping(value = "/selectRole",method = RequestMethod.POST,produces="application/json")
    public Layui selectRole(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中角色信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        ResultModel result = roleService.selectRole(permissionList,permissionName);
        logger.info("查询数据库中角色信息->end");
        if(result.getData() == ""){
            List layUiData = new ArrayList<>();
            return Layui.data(10,layUiData);
        } else{
            return Layui.data(10, (List<?>)result.getData());
        }
    }

    //删除角色
    @RequestMapping(value = "/deleteByPrimaryKey",method = RequestMethod.POST,produces="application/json")
    public ResultModel deleteByPrimaryKey(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中角色信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        int roleId = (int)responseList.get("roleId");
        ResultModel result = roleService.deleteByPrimaryKey(permissionList,permissionName,roleId);
        logger.info("查询数据库中角色信息->end");
        return result;
    }

    //新增角色
    @RequestMapping(value = "/insertRole",method = RequestMethod.POST,produces="application/json")
    public ResultModel insertRole(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("新增数据库中角色信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        Role role = new Role();
        role.setRoleName((String) responseList.get("roleName"));
        role.setCreateBy((String) responseList.get("createBy"));
        role.setCreateData((String) responseList.get("createData"));
        role.setRoleLevel((int) responseList.get("roleLevel"));
        role.setRoleNnfo((String) responseList.get("roleNnfo"));
        List<Map<String,Object>> permissionRoleList = (List<Map<String,Object>>)responseList.get("permissionRoleList");
        ResultModel result = roleService.insertRole(permissionList,permissionName,role,permissionRoleList);
        logger.info("新增数据库中角色信息->end");
        return result;
    }


    //修改角色
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST,produces="application/json")
    public ResultModel updateRole(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("修改数据库中角色信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        Role role = new Role();
        role.setId((int)responseList.get("id"));
        role.setRoleName((String) responseList.get("roleName"));
        role.setUpdateBate((String) responseList.get("updateBate"));
        role.setUpdateBy((String) responseList.get("updateBy"));
        role.setRoleLevel((int) responseList.get("roleLevel"));
        role.setRoleNnfo((String) responseList.get("roleNnfo"));
        List<Map<String,Object>> permissionRoleList = (List<Map<String,Object>>)responseList.get("permissionRoleList");
        ResultModel result = roleService.updateRole(permissionList,permissionName,role,permissionRoleList);
        logger.info("修改数据库中角色信息->end");
        return result;
    }


}
