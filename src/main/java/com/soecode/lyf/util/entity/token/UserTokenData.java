package com.soecode.lyf.util.entity.token;

import java.io.Serializable;

/**
 * Created by wxl on 2018/6/19.
 */
public class UserTokenData implements Serializable {
    private static final long serialVersionUID = 2724888087391664167L;
    private String iphone;
    private String date;

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
