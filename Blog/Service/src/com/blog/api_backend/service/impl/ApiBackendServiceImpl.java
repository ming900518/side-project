package com.blog.api_backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.blog.api_backend.service.ApiBackendService;
import com.blog.base.service.BaseService;
import com.blog.db.admininfo.dao.AdminInfoMapper;
import com.blog.db.admininfo.model.AdminInfo;
import com.blog.db.operatinglog.dao.OperatingLogMapper;
import com.blog.db.operatinglog.model.OperatingLog;
import com.blog.db.vendor.dao.VendorMapper;
import com.blog.db.vendor.model.Vendor;
import com.blog.util.EncryptUtil;
import com.blog.util.LogUtils;
import com.blog.util.TimerUtil;

@Service
public class ApiBackendServiceImpl extends BaseService implements ApiBackendService {

	@Autowired
	AdminInfoMapper adminInfoMapper;

	@Autowired
	OperatingLogMapper operatingLogMapper;

	@Autowired
	VendorMapper vendorMapper;

	@Override
	public LoginOutput loginBackend(LoginBackendRequest loginBackendRequest) {
		LoginOutput loginOutput = null;

		try {
			String md5Code = EncryptUtil.digest(loginBackendRequest.getPassword());
			try {
				loginBackendRequest.setPassword(md5Code);
				AdminInfo adminInfo = adminInfoMapper.inspectPwd(loginBackendRequest);
				if (adminInfo != null) {
					loginOutput = new LoginOutput();

					Date currentDate = TimerUtil.getNowDate();
					SimpleDateFormat sdf = TimerUtil.getSimpleDateFormat("yyyyMMdd");
					loginOutput.setSecurityKey(sdf.format(currentDate));
					loginOutput.setAdminInfo(adminInfo);
				}
			} catch (Exception e) {
				LogUtils.error(this.getClass(), "loginBackend has error :" + e);
			}
		} catch (Exception e) {
			LogUtils.error(getClass(), "loginBackend function encrypt md5Code has error : " + e);
		}
		return loginOutput;
	}

	@Override
	public List<AdminInfo> queryAdminList() {
		return adminInfoMapper.queryAdminList();
	}

	@Override
	public AdminInfo queryAdmin(AdminInfo adminInfo) {
		adminInfo = adminInfoMapper.selectByPrimaryKey(adminInfo.getAdminId());
		return adminInfo;
	}

	@Override
	public int insertAdminInfo(InsertAdminRequest insertAdminRequest, Integer adminId) {
		int ires = 0;
		AdminInfo existAdminInfo = adminInfoMapper.selectByUserCode(insertAdminRequest.getUserCode());
		if (existAdminInfo != null) {
			return ires;
		}

		try {
			String newMd5Code = EncryptUtil.digest(insertAdminRequest.getUserPw());
			insertAdminRequest.setUserPw(newMd5Code);
		} catch (Exception e) {
			LogUtils.error(getClass(), "SetPassWord has error : " + e);
		}
		insertAdminRequest.setCreatedBy(adminId);
		insertAdminRequest.setCreationDate(TimerUtil.getNowDate());
		insertAdminRequest.setUpdatedBy(adminId);
		insertAdminRequest.setUpdateDate(TimerUtil.getNowDate());
		ires = adminInfoMapper.insert(insertAdminRequest);
		if (ires == 1)
			logOperating(1, insertAdminRequest.getAdminId(), 1, 1, insertAdminRequest.getUserName(), adminId);

		return ires;
	}

	private int logOperating(int tableId, int tablePid, int operatingLogType, int operatingMode, String remark,
			int adminId) {
		int res = 0;
		OperatingLog operatingLog = new OperatingLog();
		operatingLog.setTableId(tableId);
		operatingLog.setTablePid(tablePid);
		operatingLog.setOperatingLogType(operatingLogType);
		operatingLog.setOperatingMode(operatingMode);
		operatingLog.setRemark(remark);
		operatingLog.setUserId(adminId);

		operatingLog.setCreationDate(TimerUtil.getNowDate());
		operatingLogMapper.insert(operatingLog);
		return res;
	}

	@Override
	public int updateAdminInfo(EditAdminRequest editAdminRequest, Integer adminId) {
		int success = 0;
		try {
			editAdminRequest.setUpdatedBy(0);
			editAdminRequest.setUpdateDate(TimerUtil.getNowDate());
			success = adminInfoMapper.updateByPrimaryKeySelective(editAdminRequest);

			if (success == 1)
				logOperating(1, editAdminRequest.getAdminId(), 3, 1, editAdminRequest.getUserName(), adminId);

		} catch (Exception e) {
			LogUtils.error(getClass(), "updateAdminInfo has error : " + e);
		}
		return success;
	}

	@Override
	public int removeAdminInfo(DeleteAdmin deleteAdmin, Integer adminId) {
		int res = 0;
		try {
			res = adminInfoMapper.deleteByPrimaryKey(deleteAdmin.getAdminId());
		} catch (Exception e) {
			LogUtils.error(getClass(), "removeAdminInfo has error : " + e);
		}
		if (res == 1)
			logOperating(1, deleteAdmin.getAdminId(), 2, 1, deleteAdmin.getUserName(), adminId);

		return res;
	}

	@Override
	public int updataPassWord(UpdataPwRequest updataPwRequest, Integer adminId) {
		int success = 0;
		AdminInfo adminInfo = new AdminInfo();
		try {
			String userPwMd5Code = EncryptUtil.digest(updataPwRequest.getUserPw());
			String newMd5Code = EncryptUtil.digest(updataPwRequest.getNewPw());
			updataPwRequest.setUserPw(userPwMd5Code);
			adminInfo = adminInfoMapper.inspectPwdForupdataPassWord(updataPwRequest);

			if (adminInfo != null) {
				try {
					adminInfo.setUserPw(newMd5Code);
					adminInfo.setUpdatedBy(0);
					adminInfo.setUpdateDate(TimerUtil.getNowDate());
					adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
					success = 1;
				} catch (Exception e) {
					LogUtils.error(getClass(), "updataPassWord has error : " + e);
				}
			} else {
				success = 2;
			}
		} catch (Exception e) {
			LogUtils.error(getClass(), "updataPassWord function encrypt md5Code has error : " + e);
		}
		return success;
	}

	public List<OperatingLogView> queryLogList(LogRequest logRequest) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (logRequest.getStartDate() != null && !logRequest.getStartDate().equals(""))
			paramMap.put("startDate", logRequest.getStartDate());
		if (logRequest.getEndDate() != null && !logRequest.getEndDate().equals(""))
			paramMap.put("endDate", logRequest.getEndDate());
		if (logRequest.getOperatingLogType() != null)
			paramMap.put("operatingLogType", logRequest.getOperatingLogType());
		if (logRequest.getTableId() != null)
			paramMap.put("tableId", logRequest.getTableId());
		if (logRequest.getOperatingMode() != null)
			paramMap.put("operatingMode", logRequest.getOperatingMode());

		List<OperatingLogView> logList = operatingLogMapper.queryLogList(paramMap);
		return logList;
	}

	// ----------------基本CRUD--------------
	// ----------------Vendor---------------
	
	@Override
	public List<VendorResponse> queryVendorList(VendorRequest vendorRequest) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (vendorRequest.getTaxId() != null && !vendorRequest.getTaxId().equals(""))
			paramMap.put("taxId", vendorRequest.getTaxId());

		List<VendorResponse> vendorList = vendorMapper.queryVendorList(paramMap);
		return vendorList;
	}

	@Override
	public Vendor queryVendor(Vendor vendor) {
		vendor = vendorMapper.selectByPrimaryKey(vendor.getVendorId());
		return vendor;
	}

	@Override
	public int deleteVendor(Vendor vendor, Integer adminId) {
		int res = 0;
		try {
			res = vendorMapper.deleteByPrimaryKey(vendor.getVendorId());
		} catch (Exception e) {
			LogUtils.error(getClass(), "deleteVendor has error : " + e);
		}
		return res;
	}

	@Override
	public int saveVendor(Vendor vendor, Integer adminId) {
		int res = 0;
		if (vendor == null)
			return res;
		try {
			if (vendor.getVendorId() != null && vendor.getVendorId() != 0) {
				res = vendorMapper.updateByPrimaryKeySelective(vendor);
			} else {
				res = vendorMapper.insert(vendor);

			}
		} catch (Exception e) {
			LogUtils.error(getClass(), "saveVendor has error : " + e);
		}
		return res;
	}

	// ----------------Vendor end---------------
	// ----------------基本CRUD end--------------
}