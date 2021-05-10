package com.blog.api_backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api_backend.model.login.LoginOutput;
import com.blog.api_backend.model.request.DeleteAdmin;
import com.blog.api_backend.model.request.EditAdminRequest;
import com.blog.api_backend.model.request.InsertAdminRequest;
import com.blog.api_backend.model.request.LoginBackendRequest;
import com.blog.api_backend.model.request.UpdataPwRequest;
import com.blog.api_backend.service.ApiBackendService;
import com.blog.base.service.BaseService;
import com.blog.db.admininfo.dao.AdminInfoMapper;
import com.blog.db.admininfo.model.AdminInfo;
import com.blog.util.EncryptUtil;
import com.blog.util.LogUtils;
import com.blog.util.TimerUtil;

@Service
public class ApiBackendServiceImpl extends BaseService implements ApiBackendService {

	@Autowired
	AdminInfoMapper adminInfoMapper;

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

		return ires;
	}

	@Override
	public int updateAdminInfo(EditAdminRequest editAdminRequest, Integer adminId) {
		int success = 0;
		try {
			editAdminRequest.setUpdatedBy(0);
			editAdminRequest.setUpdateDate(TimerUtil.getNowDate());
			success = adminInfoMapper.updateByPrimaryKeySelective(editAdminRequest);

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
}