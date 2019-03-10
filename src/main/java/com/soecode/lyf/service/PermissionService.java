package com.soecode.lyf.service;

import com.soecode.lyf.entity.result.ResultModel;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    /**
     * 查看所有权限信息
     * @return
     */
    ResultModel selectALLPermission(List<Map<String,Object>> permissionList, String permissionName);
}
