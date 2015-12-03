package com.jeiglobal.common.auth;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.service.auth.*;
import com.jeiglobal.utils.*;
/**
 * 
 * 클래스명 : AuthenticationSuccessHandlerImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 1. globalbiz.Users 테이블에 로그인 내역을 업데이트 후 globalbiz.UsersLoginHis에 Insert
 * 2. Auth 정보 쿠키를 생성한다.
 * 3. 로그인 요청한 사용자 계층에 맞는 메인페이지로 연결
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Value("${serverurl.hongkong}")
	private String hongkongUrl;

	@Value("${cookieShare.domain}")
	private String cookieDomain;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoginInfo member = (LoginInfo)authentication.getPrincipal();
		authoritiesService.updateLoginInfo(member.getUserId(), request);
		authoritiesService.insertLoginHis(member, request);
		addAuthCookie(response, authentication);
		String hostUrl = ("08".equals(member.getJisaCD())) ? hongkongUrl : ("00".equals(member.getJisaCD())) ? request.getContextPath() : "" ;
		String retUrl = "";//request.getParameter("returl").replaceAll("&amp;", "&");
//		//retUrl이 다른 계층 Url인 경우
//		if(!retUrl.startsWith("/"+member.getUserType().toLowerCase())){
//			retUrl = "/"+member.getUserType().toLowerCase();
//		}
		response.sendRedirect(hostUrl + "/" + member.getUserType().toLowerCase() + retUrl);
	}
	
	
	private void addAuthCookie(HttpServletResponse response, Authentication authentication){
		LoginInfo member = (LoginInfo)authentication.getPrincipal();
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		
		String authId = member.getUserId();
		String authKey = standrdPasswordEncoder.encode(authId);
		
		authoritiesService.updateEncodeCookieById(authId,authKey);
		CommonUtils.addCookie("AUTHKey", authKey, cookieDomain, response);
		CommonUtils.addCookie("AUTHId", authId, cookieDomain, response);
	}
}
