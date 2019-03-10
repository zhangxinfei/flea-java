package com.soecode.lyf.util.entity.token;

public class AdminTokenData {
    private static final long serialVersionUID = 2724888087391664167L;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminIphone() {
        return adminIphone;
    }

    public void setAdminIphone(String adminIphone) {
        this.adminIphone = adminIphone;
    }

    private String adminName;//管理员姓名
    private String adminIphone;//管理员电话


}
