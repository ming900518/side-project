package com.blog.api_backend.model.request;

public class LoginBackendRequest {
	private String userCode;
	private String password;


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
