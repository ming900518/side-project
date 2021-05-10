package com.blog.util;

public final class EmailContent {
	//localhost
	// public static final String LINK =  "http://localhost:8080/kinmencoupon_service/api_app/";
	
	// release
	public static final String LINK = "https://trip8899.tainan.gov.tw/kinmencoupon_service/api_app/";

	//coloring
	// public static final String LINK = "https://www.bonniecoloring.com/kinmencoupon_service/api_app/";
	
	public static final String SUBJECT_SIGNUP = "kinmencoupon 註冊驗證通知";
	public static String getBodySignup(String code){
			return "您好！下方連結是您的註冊驗證連結，如本人未申請註冊相關項目，請忽略此信件<br>"+
					"<a href=\" "+ LINK + "verification?token=" + code  + " \">註冊驗證連結</a>";
	}

	
	public static final String SUBJECT_FORGETPWD = "kinmencoupon 忘記密碼";
	public static String getBodyForgetPwd(String userPwd ){
		return	"您好！您的新密碼為 " + userPwd + "，如本人未申請註冊相關項目，請忽略此信件"; 
	}
}
