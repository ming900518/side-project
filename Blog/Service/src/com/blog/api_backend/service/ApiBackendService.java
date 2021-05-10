package com.blog.api_backend.service;

import java.util.List;

import com.blog.api_backend.model.login.LoginOutput;
import com.blog.api_backend.model.request.DeleteAdmin;
import com.blog.api_backend.model.request.EditAdminRequest;
import com.blog.api_backend.model.request.InsertAdminRequest;
import com.blog.api_backend.model.request.LoginBackendRequest;
import com.blog.api_backend.model.request.UpdataPwRequest;
import com.blog.db.admininfo.model.AdminInfo;

public interface ApiBackendService {

	public LoginOutput loginBackend(LoginBackendRequest loginBackendRequest);

	public List<AdminInfo> queryAdminList();

	public AdminInfo queryAdmin(AdminInfo adminInfo);

	public int insertAdminInfo(InsertAdminRequest insertAdminRequest, Integer adminId);

	public int updateAdminInfo(EditAdminRequest editAdminRequest, Integer adminId);

	public int removeAdminInfo(DeleteAdmin deleteAdmin, Integer adminId);

	public int updataPassWord(UpdataPwRequest updataPwRequest, Integer adminId);
}
