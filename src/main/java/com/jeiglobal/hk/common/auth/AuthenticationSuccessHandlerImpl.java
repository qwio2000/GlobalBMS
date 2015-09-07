package com.jeiglobal.hk.common.auth;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.service.common.auth.*;
/**
 * 
 * 클래스명 : AuthenticationSuccessHandlerImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 1. 로그인 시 선택한 언어 값을 받아온다.
 * 2. Auth 정보를 쿠키를 생성한다.
 * 3. 사용자가 이전에 요청한 Url이 있었으면 그 Url로 연결, 없었으면 DEFAULT_INDEX_URL로 연결
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoginInfo member = (LoginInfo)authentication.getPrincipal();
				
		addAuthCookie(response, authentication);
		String retUrl = request.getParameter("returl");
		if(retUrl == null || retUrl.isEmpty()){
			if("JA".equalsIgnoreCase(member.getEmpKeyLvCD())){
				response.sendRedirect(request.getContextPath()+"/ja/centers");
			}else if("FA".equalsIgnoreCase(member.getEmpKeyLvCD())){
				response.sendRedirect(request.getContextPath()+"/fa/members");
			}else if("MA".equalsIgnoreCase(member.getEmpKeyLvCD())){
				response.sendRedirect(request.getContextPath()+"/fa/members");
			}
			return;
		}
		response.sendRedirect(retUrl);
	}
	
	
	private void addAuthCookie(HttpServletResponse response,Authentication authentication){
		LoginInfo member = (LoginInfo)authentication.getPrincipal();
		
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		
		String authId = member.getMemberId();
		String authKey = standrdPasswordEncoder.encode(authId);
		authoritiesService.updateEncodeCookieById(authId,authKey);
		
		try {
			Cookie cookie = new Cookie("AUTHKey",URLEncoder.encode(authKey,"utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie("AUTHId",URLEncoder.encode(authId,"utf-8"));
			cookie1.setPath("/");
			response.addCookie(cookie1);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
