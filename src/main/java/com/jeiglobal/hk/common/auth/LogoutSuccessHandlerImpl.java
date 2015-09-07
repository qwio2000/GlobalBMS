package com.jeiglobal.hk.common.auth;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.service.common.auth.*;
/**
 * 
 * 클래스명 : LogoutSuccessHandlerImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그 아웃 시 후 처리
 * 
 * 1. 쿠키 값을 초기화한 후 loginForm으로 Redirect
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Cookie cookie = new Cookie("AUTHId","");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		Cookie cookie1 = new Cookie("AUTHKey","");
		cookie1.setPath("/");
		cookie1.setMaxAge(0);
		response.addCookie(cookie1);
		
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
