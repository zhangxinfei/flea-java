package com.soecode.lyf.entity.params;

import java.util.List;
import java.util.Map;

public class ResponseParams {
    int page;
    int limit;
    List<Map<String,Object>> permissionList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Map<String, Object>> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Map<String, Object>> permissionList) {
        this.permissionList = permissionList;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }

    String PermissionName;
}
