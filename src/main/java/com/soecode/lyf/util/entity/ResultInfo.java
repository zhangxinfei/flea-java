package com.soecode.lyf.util.entity;

import java.io.Serializable;


/**
 *@Description:
 *@Author:WXL
 *@Since:2017年12月29日
 */
public class ResultInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092903303385153617L;

	private boolean success;

	private int code;

	private Object data;

	private String message;

	public ResultInfo(Object data,int code, String message,boolean success) {
		this.data = data;
		this.code = code;
		this.message = message;
		this.success = success;
	}
	public ResultInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultInfo() {
		
		this.success = true;
		this.code = 0;
		this.message = "";
	}
	
	public ResultInfo(Integer resultCode){
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
