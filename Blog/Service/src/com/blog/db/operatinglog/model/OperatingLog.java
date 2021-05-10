package com.blog.db.operatinglog.model;

import java.util.Date;

public class OperatingLog {
    private Integer operatingLogId;

    private Integer operatingLogType;

    private Integer tableId;

    private Integer tablePid;

    private Integer operatingMode;

    private Integer userId;

    private String remark;

    private Date creationDate;

    public Integer getOperatingLogId() {
        return operatingLogId;
    }

    public void setOperatingLogId(Integer operatingLogId) {
        this.operatingLogId = operatingLogId;
    }

    public Integer getOperatingLogType() {
        return operatingLogType;
    }

    public void setOperatingLogType(Integer operatingLogType) {
        this.operatingLogType = operatingLogType;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTablePid() {
        return tablePid;
    }

    public void setTablePid(Integer tablePid) {
        this.tablePid = tablePid;
    }

    public Integer getOperatingMode() {
        return operatingMode;
    }

    public void setOperatingMode(Integer operatingMode) {
        this.operatingMode = operatingMode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}