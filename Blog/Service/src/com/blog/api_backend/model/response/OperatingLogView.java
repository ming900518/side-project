package com.blog.api_backend.model.response;

import com.blog.db.operatinglog.model.OperatingLog;

public class OperatingLogView extends OperatingLog {
	String dataTitle;
	String userName;

	public String getDataTitle() {
		return dataTitle;
	}

	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
