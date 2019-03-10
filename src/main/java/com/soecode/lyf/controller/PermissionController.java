package com.soecode.lyf.controller;

import com.soecode.lyf.entity.params.Layui;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.service.PermissionService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Permission")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
public class PermissionController {
    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    @Autowired
    PermissionService permissionService;

    //查询权限信息
    @RequestMapping(value = "/selectALLPermission",method = RequestMethod.POST,produces="application/json")
    public ResultModel selectALLPermission(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中全部权限信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String)responseList.get("permissionName");
        ResultModel result = permissionService.selectALLPermission(permissionList,permissionName);
        logger.info("查询数据库中全部权限信息->end");
        return result;
    }

}
