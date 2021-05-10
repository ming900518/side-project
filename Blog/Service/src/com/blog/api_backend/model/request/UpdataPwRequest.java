package com.blog.api_backend.model.request;

import com.blog.db.admininfo.model.AdminInfo;

public class UpdataPwRequest extends AdminInfo{
	
	private String newPw;

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	
}
