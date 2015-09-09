package com.jeiglobal.common.auth;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.*;
import org.springframework.stereotype.*;

/**
 * 
 * 클래스명 : AuthenticationEntryPointImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 1. loginForm 경로 설정
 * 2. 사용자가 요청한 Url 경로를 encoding
 * 3. 요청한 경로가 "/" 이면 returl을 붙이지 않고 그 외에는 returl이라는 파라미터를 붙인 후 Redirect를 진행
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);
	
	private String loginFormPath;
	
	@Value("${serverurl.globalbms}")
	private String globalbmsUrl;
	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		if(loginFormPath == null || loginFormPath.isEmpty()){
			setLoginFormPath("/login");
		}
		String redirectUrl = UrlUtils.buildRequestUrl(request);
		LOGGER.debug("redirectUrl : {}", redirectUrl);
		String encodedUrl = response.encodeRedirectURL(redirectUrl);
		LOGGER.debug("encodedUrl : {}", encodedUrl);
		if("/".equals(encodedUrl)){
			LOGGER.debug("URL : {}", globalbmsUrl+loginFormPath);
			response.sendRedirect(globalbmsUrl+loginFormPath);
		}else{
			LOGGER.debug("URL : {}", globalbmsUrl+loginFormPath+"?returl="+encodedUrl);
			response.sendRedirect(globalbmsUrl+loginFormPath+"?returl="+encodedUrl);
		}
	}

	public void setLoginFormPath(String loginFormPath) {
		this.loginFormPath = loginFormPath;
	}
	
}
