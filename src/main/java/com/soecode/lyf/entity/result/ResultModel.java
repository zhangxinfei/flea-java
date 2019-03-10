package com.soecode.lyf.entity.result;

import java.io.Serializable;

/**
 * 返回数据状态，封装实体类
 */
public class ResultModel implements Serializable {
    private static final long serialVersionUID = 1L;
    //状态
    private boolean success;

    //枚举中的键
    private Integer code;
    //枚举中的数据
    private Object data;
    //枚举中的值
    private String message;

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel() {
        this.success = true;
        this.code = 0;
        this.message = "";
    }

    public ResultModel(Integer resultCode){
        this.code = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
