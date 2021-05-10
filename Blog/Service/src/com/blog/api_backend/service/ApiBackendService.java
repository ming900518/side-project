package com.blog.api_backend.service;

import java.util.List;

import com.blog.api_backend.model.login.LoginOutput;
import com.blog.api_backend.model.request.DeleteAdmin;
import com.blog.api_backend.model.request.EditAdminRequest;
import com.blog.api_backend.model.request.InsertAdminRequest;
import com.blog.api_backend.model.request.LogRequest;
import com.blog.api_backend.model.request.LoginBackendRequest;
import com.blog.api_backend.model.request.UpdataPwRequest;
import com.blog.api_backend.model.request.VendorRequest;
import com.blog.api_backend.model.response.OperatingLogView;
import com.blog.api_backend.model.response.VendorResponse;
import com.blog.db.admininfo.model.AdminInfo;
import com.blog.db.vendor.model.Vendor;

public interface ApiBackendService {

	public LoginOutput loginBackend(LoginBackendRequest loginBackendRequest);

	public List<AdminInfo> queryAdminList();

	public AdminInfo queryAdmin(AdminInfo adminInfo);

	public int insertAdminInfo(InsertAdminRequest insertAdminRequest, Integer adminId);

	public int updateAdminInfo(EditAdminRequest editAdminRequest, Integer adminId);

	public int removeAdminInfo(DeleteAdmin deleteAdmin, Integer adminId);

	public int updataPassWord(UpdataPwRequest updataPwRequest, Integer adminId);

	public List<OperatingLogView> queryLogList(LogRequest logRequest);

	// --- Vendor ----
	public List<VendorResponse> queryVendorList(VendorRequest vendorRequest);

	public Vendor queryVendor(Vendor vendor);

	public int saveVendor(Vendor vendor, Integer adminId);

	public int deleteVendor(Vendor vendor, Integer adminId);

}
