package com.blog.api_backend.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.api_backend.model.response.ContentListResponse;
import com.blog.db.content.model.Content;
import com.blog.util.recaptchav2java.Http;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.blog.api_backend.model.login.LoginOutput;
import com.blog.api_backend.model.login.RespLogin;
import com.blog.api_backend.model.request.AdminListRequest;
import com.blog.api_backend.model.request.DeleteAdmin;
import com.blog.api_backend.model.request.EditAdminRequest;
import com.blog.api_backend.model.request.InsertAdminRequest;
import com.blog.api_backend.model.request.LoginBackendRequest;
import com.blog.api_backend.model.request.UpdatePwRequest;
import com.blog.api_backend.model.response.FileUploadResponse;
import com.blog.api_backend.service.ApiBackendService;
import com.blog.base.controller.BaseController;
import com.blog.base.model.BaseModel;
import com.blog.db.admininfo.model.AdminInfo;
import com.blog.util.FileUtil;
import com.blog.util.LogUtils;
import com.blog.util.TimerUtil;
import com.blog.util.ValidateUtil;

@Controller
@EnableAsync
@RequestMapping(value = "/api_backend")
public class ApiBackendController extends BaseController {

	@Autowired
	private ApiBackendService apiBackendService;

	@RequestMapping(value = "/files/**", method = RequestMethod.GET)
	public @ResponseBody HttpEntity<byte[]> loadFile(HttpServletRequest request, HttpServletResponse resp) {
		byte[] image = null;
		HttpHeaders headers = new HttpHeaders();
		String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		if (ValidateUtil.isBlank(fullPath))
			return null;
		String path = fullPath.replace("/api_backend", "");
		if (ValidateUtil.isBlank(path))
			return null;

		String extension = (path.substring(path.lastIndexOf(".") + 1)).replace("/", "");
		if ("png".equals(extension)) {
			headers.setContentType(MediaType.IMAGE_PNG);
		} else if ("bdw".equals(extension)) {
			headers.setContentType(new MediaType("application", "octet-stream"));
		} else if ("jpg".equals(extension) || "jpeg".equals(extension)) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		} else if ("mp4".equals(extension)) {
			headers.setContentType(new MediaType("video", "mp4"));
		} else if ("mp3".equals(extension)) {
			headers.setContentType(new MediaType("video", "mp3"));
		} else if ("pdf".equals(extension)) {
			headers.setContentType(MediaType.APPLICATION_PDF);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}
		String rootPath = System.getProperty("catalina.home");
		String filePath = rootPath + path;
		File dir = new File(filePath);
		try {

			image = FileUtils.readFileToByteArray(dir);

			String fileName = dir.getAbsolutePath().substring(dir.getAbsolutePath().lastIndexOf("\\") + 1);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
			headers.setContentLength(image.length);
			return new HttpEntity<byte[]>(image, headers);
		} catch (IOException e) {
			LogUtils.fileConteollerError(filePath + " loadFile has error : 輸出發生異常 =>" + e);
		} finally {
			System.gc();
		}
		return null;
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public @ResponseBody FileUploadResponse fileUpload(HttpServletRequest request, HttpServletResponse resp,
			@RequestParam("file") CommonsMultipartFile file) {
		FileUploadResponse respResult = new FileUploadResponse();
		int res = 0;
		String msg = "";

		try {
			Integer id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : null;
			Integer ftype = request.getParameter("ftype") != null ? Integer.valueOf(request.getParameter("ftype"))
					: null;

			if (file != null && !file.isEmpty()) {
				// set file path
				StringBuffer path = new StringBuffer();
				String timeStr = TimerUtil.getNowTimestamp();
				switch (ftype) {
				case 0:

					path.append("image/");
					respResult.setfName(timeStr);
					break;
				case 1:

					path.append("other/");
					respResult.setfName(timeStr);
					break;
				}
				Map<String, Object> result = FileUtil.uploadMultipartFile(file, path.toString());
				String filePath = result.get("path").toString();
				if (filePath != null && filePath.length() > 0)
					res = 1;

				respResult.setUrl(filePath);
			}
		} catch (NoSuchMessageException e) {
			LogUtils.fileConteollerError(e.toString());
			msg = "file service has error , check log file.";
		}

		respResult.setRes(res);
		if (res == 1)
			msg = "儲存成功!";
		else
			msg = "儲存失敗!";

		respResult.setMsg(msg);
		return respResult;
	}

	// admin登入
	@RequestMapping(value = "/login_backend", produces = "application/json")
	public @ResponseBody BaseModel loginBackend(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp, @RequestBody LoginBackendRequest loginBackendRequest) {

		resp.setHeader("Access-Control-Allow-Origin", "*");

		RespLogin respLogin = new RespLogin();
		LoginOutput result = apiBackendService.loginBackend(loginBackendRequest);

		if (result != null) {
			String sessionId = (String) session.getId();
			result.setSessionId(sessionId);
			respLogin.setResult(true);
			respLogin.setData(result);
			respLogin.setMessage("登入成功");

			AdminInfo adminInfo = result.getAdminInfo();
			String strtp = String.valueOf(TimerUtil.getNowDate().getTime());
			session.setAttribute("time_key", strtp);
			session.setAttribute("loginSuccess", "yes");
			session.setAttribute("pass_id", adminInfo.getAdminId());
			session.setAttribute("adminInfo", adminInfo);
			session.setAttribute("serviceKey", result.getSecurityKey());
		} else {
			respLogin.setResult(false);
			respLogin.setMessage("帳號密碼錯誤,請確認!");
		}
		return respLogin;
	}

	// 查詢後台用戶
	@RequestMapping(value = "/queryAdmin", produces = "application/json")
	public @ResponseBody BaseModel queryAdmin(HttpSession httpSession, HttpServletRequest request,
			HttpServletResponse resp, @RequestBody AdminInfo adminInfo) {
		BaseModel res = new BaseModel();

		adminInfo = apiBackendService.queryAdmin(adminInfo);
		res = basicOutput(adminInfo);
		return res;
	}

	// admin列表
	@RequestMapping(value = "/adminList", produces = "application/json")
	public @ResponseBody BaseModel adminList(HttpSession session, HttpServletRequest request, HttpServletResponse resp,
			@RequestBody AdminListRequest adminListRequest) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("unlogin");
			return res;
		}

		List<AdminInfo> adminList = apiBackendService.queryAdminList();
		res.setResult(true);
		res.setData(adminList);
		res.setMessage("成功");
		return res;
	}

	// 新增admin
	@RequestMapping(value = "/addAdmin", produces = "application/json")
	public @ResponseBody BaseModel addAdmin(HttpSession httpSession, HttpServletRequest request,
			HttpServletResponse resp, @RequestBody InsertAdminRequest insertAdminRequest) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) httpSession.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("unlogin");
			return res;
		}

		int result = apiBackendService.insertAdminInfo(insertAdminRequest, adminInfo.getAdminId());

		if (result == 1) {
			res.setResult(true);
			res.setMessage("新增成功");
		} else {
			res.setResult(false);
			res.setMessage("新增失敗");
		}
		return res;
	}

	// 修改admin
	@RequestMapping(value = "/editAdmin", produces = "application/json")
	public @ResponseBody BaseModel editAdmin(HttpSession session, HttpServletRequest request, HttpServletResponse resp,
			@RequestBody EditAdminRequest editAdminRequest) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("unlogin");
			return res;
		}

		int result = apiBackendService.updateAdminInfo(editAdminRequest, adminInfo.getAdminId());
		if (result == 1) {
			res.setResult(true);
			res.setMessage("修改成功");
		} else {
			res.setResult(false);
			res.setMessage("修改失敗");
		}
		return res;
	}

	// 刪除admin
	@RequestMapping(value = "/deleteAdmin", produces = "application/json")
	public @ResponseBody BaseModel deleteAdmin(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp, @RequestBody DeleteAdmin deleteAdmin) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("unlogin");
			return res;
		}
		int result = apiBackendService.removeAdminInfo(deleteAdmin, adminInfo.getAdminId());
		if (result == 1) {
			res.setResult(true);
			res.setMessage("刪除成功");
		} else {
			res.setResult(false);
			res.setMessage("刪除失敗");
		}
		return res;
	}

	// 修改密碼
	@RequestMapping(value = "/updatePw", produces = "application/json")
	public @ResponseBody BaseModel updatePw(HttpSession session, HttpServletRequest request, HttpServletResponse resp,
			@RequestBody UpdatePwRequest updatePwRequest) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("unlogin");
			return res;
		}
		int result = apiBackendService.updatePassword(updatePwRequest, adminInfo.getAdminId());
		if (result == 1) {
			res.setResult(true);
			res.setMessage("修改成功");
		} else {
			res.setResult(false);
			res.setMessage("修改失敗");
		}
		return res;
	}

	// 查詢文章列表
	@RequestMapping(value = "/queryContentList", produces = "application/json")
	public @ResponseBody
	BaseModel queryContentList(HttpSession httpSession, HttpServletRequest request,
									 HttpServletResponse resp, @RequestBody Content contentList) {
		BaseModel res = new BaseModel();
		List<ContentListResponse> ContentListResponseList = new ArrayList();
		ContentListResponseList = apiBackendService.queryContentList(contentList);
		res = basicOutput(ContentListResponseList);
		return res;
	}

	// 查詢文章內容
	@RequestMapping(value = "/queryContent", produces = "application/json")
	public @ResponseBody
	BaseModel queryContent(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, @RequestBody Content content) {
		BaseModel res = new BaseModel();
		Content ContentResponse = new Content();
		ContentResponse = apiBackendService.queryContent(content);
		res = basicOutput(ContentResponse);
		return res;
	}

	// 新增文章內容
	@RequestMapping(value = "/saveContent", produces = "application/json")
	public @ResponseBody BaseModel saveContent(HttpSession httpSession, HttpServletRequest request,
											HttpServletResponse resp, @RequestBody Content content) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) httpSession.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("Unauthorized");
			return res;
		}
		int result = 0;
		if (content.getContentId() == null)
			result = apiBackendService.addContent(content, adminInfo.getAdminId());
		else
			result = apiBackendService.updateContent(content, adminInfo.getAdminId());

		if (result == 1) {
			res.setResult(true);
			res.setMessage("Success");
		} else {
			res.setResult(false);
			res.setMessage("Fail");
		}
		return res;
	}

	// 刪除文章
	@RequestMapping(value = "/deleteContent", produces = "application/json")
	public @ResponseBody BaseModel deleteContent(HttpSession httpSession, HttpServletRequest request,
											   HttpServletResponse resp, @RequestBody Content content) {
		BaseModel res = new BaseModel();
		AdminInfo adminInfo = (AdminInfo) httpSession.getAttribute("adminInfo");
		if (adminInfo == null) {
			res.setResult(false);
			res.setMessage("Unauthorized");
			return res;
		}
		int result = 0;
			result = apiBackendService.deleteContent(content, adminInfo.getAdminId());

		if (result == 1) {
			res.setResult(true);
			res.setMessage("Success");
		} else {
			res.setResult(false);
			res.setMessage("Fail");
		}
		return res;
	}
}
