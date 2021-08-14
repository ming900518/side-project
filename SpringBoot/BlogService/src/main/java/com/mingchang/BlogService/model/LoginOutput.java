package com.mingchang.BlogService.model;

import com.mingchang.BlogService.model.database.AdminInfo;

public class LoginOutput {

    private Integer loginPlatform;
    private String mask;
    private String ip;
    private String sessionId;
    private Integer status;
    private String securityKey;
    private AdminInfo adminInfo;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }
}
