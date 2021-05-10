package com.blog.api_app.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BaseAppResponse {
	private int res;
	private int resCode;
	private String msg;
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public int getResCode() {
		return resCode;
	}
	public void setResCode(int resCode) {
		this.resCode = resCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
