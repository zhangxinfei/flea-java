package com.soecode.lyf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.params.Layui;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.service.AdminService;
import com.soecode.lyf.util.JwtUtils;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.RsaJsUtils;
import com.soecode.lyf.util.entity.token.AdminTokenData;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
//@CrossOrigin(origins = "http://127.0.0.1:80") //允许这个域名就行跨域访问
public class AdministratorsController {
    @Autowired
    AdminService adminService;

    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    /**
     * 用户登录判断
     *
     * @return
     */
    @RequestMapping(value = "/selectByLogin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel selectByLogin(HttpServletRequest request, @RequestBody String admin_login) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        try {
            logger.info("==========================解密=========================");
            String admin_loginRsa = admin_login.substring(12);
            String jsonStr = RsaJsUtils.decryptByPrivateKeyData(admin_loginRsa);
            Admin admin = JSON.parseObject(jsonStr, Admin.class);
            logger.info("======================验证本地库是否存在数据=========================");
            result = adminService.selectByLogin(request, admin);
            if (result.getCode() == 20) {
                logger.info("======================未查询到用户数据=========================");
                return result;
            }
            logger.info("======================用户登录成功封装用户数据返回token=========================");
            //***********************组织token中存储的用户信息start*******************************
            long ttlMillis = 1000 * 60 * 60;//设定token时间，设定一个3600秒token过期
            //***********************组织token中存储的用户信息end*******************************
            AdminTokenData adminTokenData = new AdminTokenData();
            adminTokenData.setAdminIphone(admin.getAdminIphone());
            adminTokenData.setAdminName(admin.getAdminName());
            String subject = JSONObject.toJSONString(adminTokenData, SerializerFeature.WriteMapNullValue);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("admin", result.getData());
            //用户code                   用户ip地址
//          resultMap = result.getData();
            String token = JwtUtils.createJWT(String.valueOf(admin.getAdminIphone()), request.getRemoteAddr(), subject, ttlMillis, admin.getAdminName());
            AdminParams adminReturn = new AdminParams();
//            adminService.setToken(token,admin);
            //把token存入token中
            adminReturn.setToken(token);
            resultMap.put("adminReturn", adminReturn);
            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(resultMap);
        } catch (Exception e) {
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
            e.printStackTrace();
        }
        return result;
    }


    //    查询管理员信息
//    @AuthToken(type="disticntApp")
    @RequestMapping(value = "/selectAllAdmin", method = RequestMethod.POST, produces = "application/json")
    public Layui selectAllAdmin(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中全部管理员信息->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        ResultModel result = adminService.selectAllAdmin(permissionList, (String) responseList.get("permissionName"));
        logger.info("查询数据库中全部管理员信息->end");
        if (result.getData() == "") {
            List layUiData = new ArrayList<>();
            return Layui.data(10, layUiData);
        } else {
            return Layui.data(10, (List<?>) result.getData());
        }
    }

    //    删除管理员接口
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel deleteAdmin(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("删除管理员->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        int adminId = (int) responseList.get("adminId");
        String permissionName = (String) responseList.get("permissionName");
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setAdminStatus(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue());
        ResultModel result = adminService.deleteAdmin(permissionList, permissionName, admin);
        logger.info("删除管理员->end");
        return result;
    }

    //修改其他管理员信息
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel updateAdmin(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("修改其他管理员信息-->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String) responseList.get("permissionName");
        //获取前端传递的管理员信息
        AdminParams adminParams = new AdminParams();
        adminParams.setId((int)responseList.get("id"));
        adminParams.setAdminIphone((String) responseList.get("adminIphone"));
        adminParams.setAdminName((String) responseList.get("adminName"));
        adminParams.setCreateBy((String) responseList.get("updateBy"));
        adminParams.setCreateData((String) responseList.get("updateData"));
        adminParams.setAdminStatus((int) responseList.get("adminStatus"));
        //获取修改的角色信息
        List<Map<String, Object>> roleNameList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("roleId")), Map.class);
        ResultModel result = adminService.updateAdmin(permissionList, permissionName, adminParams,roleNameList);
        logger.info("修改其他管理员信息-->end");
        return result;
    }


    //管理员表查重
    @RequestMapping(value = "/selectRepeat", method = RequestMethod.POST, produces = "application/json")
    public ResultModel selectRepeat(HttpServletRequest request, @RequestBody Admin admin) {
        logger.info("查询管理员是否重复->start");
        ResultModel result = adminService.selectRepeat(admin);
        logger.info("查询管理员是否重复->end");
        return result;
    }

    //新增管理员信息
    @RequestMapping(value = "/insertAdmin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel insertAdmin(@RequestBody String responseData) {
        logger.info("新增管理员-->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String) responseList.get("permissionName");
        AdminParams adminParams = new AdminParams();
        adminParams.setAdminIphone((String) responseList.get("adminIphone"));
        adminParams.setAdminNumber((String) responseList.get("adminNumber"));
        adminParams.setAdminName((String) responseList.get("adminName"));
        adminParams.setCreateBy((String) responseList.get("createBy"));
        adminParams.setCreateData((String) responseList.get("createData"));
        adminParams.setAdminStatus(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue());
        List<Map<String, Object>> roleNameList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("id")), Map.class);
        ResultModel result = adminService.insertAdmin(permissionList, permissionName, adminParams, roleNameList);
        logger.info("新增管理员-->end");
        return result;
    }

    @CrossOrigin(origins = {"http://localhost:8080", "null"})
    @RequestMapping(value = "/selectOneById", method = RequestMethod.POST, produces = "application/json")
    public ResultModel selectOneById(@RequestBody Admin admin) {
        logger.info("查询单个管理员信息-->start");
        ResultModel result = adminService.selectOneById(admin);
        logger.info("查询单个管理员信息-->end");
        return result;
    }

    @RequestMapping(value = "/updateOneAdmin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel updateOneAdmin(@RequestBody Admin admin) {
        logger.info("修改当前管理员信息-->start");
        ResultModel result = adminService.updateOneAdmin(admin);
        logger.info("修改当前管理员信息-->end");
        return result;
    }


}
