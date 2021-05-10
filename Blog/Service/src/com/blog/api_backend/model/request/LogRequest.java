package com.blog.api_backend.model.request;

public class LogRequest {

    private Integer operatingLogType;

    private Integer tableId;

    private Integer tablePid;

    private Integer operatingMode;

	private String startDate;
	
	private String endDate;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
		
}
