package com.jeiglobal.common.auth;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;

import com.jeiglobal.service.common.auth.*;
import com.jeiglobal.utils.*;
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
	
	@Value("${serverurl.globalbms}")
	private String globalbmsUrl;
	
	@Value("${cookieShare.domain}")
	private String cookieDomain;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(request.getCookies() != null && request.getCookies().length > 0){
			List<Cookie> cookies = Arrays.asList(request.getCookies());
			for (Cookie cookie : cookies) {
				CommonUtils.removeCookie(cookie.getName(), cookieDomain, response);
			}
		}

		response.sendRedirect(globalbmsUrl);
		
	}

}
