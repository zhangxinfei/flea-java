package com.soecode.lyf.util.entity;

/**
 * 用于接收跳转到三预平台，用户信息及业务精灵信息
 */
public class UserSession {
    private String userName;//用户名
    private String year;//年份
    private String wizard;//业务精灵
    private String userid;//用户id
    private String tabletype;//用户对于业务精灵的权限

    private String businessWizard;//业务精灵权限
    private String businessWizardTab;//业务精灵页签

    private String customer_txt;
    private String version;

    private String auth;
    private String authTab;

    private String cpmo_cop;

    private String cpmo_cop_txt;

    private String authCpmo_cop;

    private String authWizard;

    public String getAuthCpmo_cop() {
        return authCpmo_cop;
    }

    public String getCpmo_cop_txt() {
        return cpmo_cop_txt;
    }

    public void setCpmo_cop_txt(String cpmo_cop_txt) {
        this.cpmo_cop_txt = cpmo_cop_txt;
    }

    public void setAuthCpmo_cop(String authCpmo_cop) {
        this.authCpmo_cop = authCpmo_cop;
    }

    public String getAuthWizard() {
        return authWizard;
    }

    public void setAuthWizard(String authWizard) {
        this.authWizard = authWizard;
    }

    public String getCpmo_cop() {
        return cpmo_cop;
    }

    public void setCpmo_cop(String cpmo_cop) {
        this.cpmo_cop = cpmo_cop;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuthTab() {
        return authTab;
    }

    public void setAuthTab(String authTab) {
        this.authTab = authTab;
    }

    public String getBusinessWizardTab() {
        return businessWizardTab;
    }
    public void setBusinessWizardTab(String businessWizardTab) {
        this.businessWizardTab = businessWizardTab;
    }
    public String getBusinessWizard() {
        return businessWizard;
    }

    public void setBusinessWizard(String businessWizard) {
        this.businessWizard = businessWizard;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getCustomer_txt() {
		return customer_txt;
	}

	public void setCustomer_txt(String customer_txt) {
		this.customer_txt = customer_txt;
	}

	public String getTabletype() {
        return tabletype;
    }

    public void setTabletype(String tabletype) {
        this.tabletype = tabletype;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWizard() {
        return wizard;
    }

    public void setWizard(String wizard) {
        this.wizard = wizard;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
