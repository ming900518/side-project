package com.blog.util.auth;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HttpSession session = request.getSession();
			String loginFailedUrl = request.getContextPath() + "/loginFailed";

			Object passId = session.getAttribute("pass_id");
			if (passId != null){
				String login_id = passId.toString();
				String time_key = (String) session.getAttribute("time_key");
				
			}else{
				response.sendRedirect(loginFailedUrl);
				return false;
			}

			String login_result = (String) session.getAttribute("loginSuccess");
			if(login_result != null && login_result.equals("yes")){
				return true;
			}else{
				response.sendRedirect(loginFailedUrl);
				return false;
			}
		}
		return false;
	}	
	
}