package com.jeiglobal.hk.common.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
/**
 * 
 * 클래스명 : AuthenticationFailureHandlerImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그인 실패 시 후 처리
 * 
 * 1. loginFormPath에 error 파라미터를 붙여서 연결(페이지에서 error 파라미터로 처리)
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	private String loginFormPath;
		
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if(loginFormPath == null || loginFormPath.isEmpty()){
			setLoginFormPath("/login?error=true");
		}
		request.getRequestDispatcher(loginFormPath).forward(request,response);
	}

	public void setLoginFormPath(String loginFormPath) {
		this.loginFormPath = loginFormPath;
	}
}
