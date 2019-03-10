package com.soecode.lyf.entity;

public class Role {
    private Integer id;

    private String roleName;

    private String roleNnfo;

    private Integer roleLevel;

    private String createData;

    private String createBy;

    private String updateBate;

    private String updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleNnfo() {
        return roleNnfo;
    }

    public void setRoleNnfo(String roleNnfo) {
        this.roleNnfo = roleNnfo == null ? null : roleNnfo.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData == null ? null : createData.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBate() {
        return updateBate;
    }

    public void setUpdateBate(String updateBate) {
        this.updateBate = updateBate == null ? null : updateBate.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}