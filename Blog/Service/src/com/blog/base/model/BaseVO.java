package com.blog.base.model;

public class BaseVO {
	private Integer loginPlatform;
	private String mask;
	private String ip;
	private String sessionId;

	public Integer getLoginPlatform() {
		return loginPlatform;
	}

	public void setLoginPlatform(Integer loginPlatform) {
		this.loginPlatform = loginPlatform;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
