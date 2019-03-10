package com.soecode.lyf.entity.params;

import com.soecode.lyf.entity.Admin;

public class AdminParams {
    private Integer id;
    //管理员姓名
    private String adminName;
    //管理员电话
    private String adminNumber;
    //管理员电话
    private String adminIphone;
    //管理员状态
    private Integer adminStatus;

    private String createData;

    private String createBy;
    //权限编码
    private String permissionName;
    //角色ID
    private int roleId;
    //角色等级
    private int roleLevel;
    //角色名称
    private String roleName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    private String updateData;

    private String updateBy;

    public String getUpdateData() {
        return updateData;
    }

    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    //token的秘钥
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public String getAdminIphone() {
        return adminIphone;
    }

    public void setAdminIphone(String adminIphone) {
        this.adminIphone = adminIphone;
    }

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

}
