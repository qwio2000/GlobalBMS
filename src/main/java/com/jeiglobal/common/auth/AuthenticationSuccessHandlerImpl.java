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
 * 1. 로그인 시 선택한 언어 값을 받아온다.
 * 2. Auth 정보를 쿠키를 생성한다.
 * 3. 사용자가 이전에 요청한 Url이 있었으면 그 Url로 연결, 없었으면 DEFAULT_INDEX_URL로 연결
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
		String retUrl = request.getParameter("returl");
		String hostUrl = ("08".equals(member.getJisaCD())) ? hongkongUrl : 
			("08".equals(member.getJisaCD())) ? request.getContextPath() : "" ;
		if(retUrl == null || retUrl.isEmpty()){
			response.sendRedirect(hostUrl+"/"+member.getUserType().toLowerCase());
		}else{
			response.sendRedirect(hostUrl+retUrl);
		}
	}
	
	
	private void addAuthCookie(HttpServletResponse response,Authentication authentication){
		LoginInfo member = (LoginInfo)authentication.getPrincipal();
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		
		String authId = member.getUserId();
		String authKey = standrdPasswordEncoder.encode(authId);
		
		authoritiesService.updateEncodeCookieById(authId,authKey);
		CommonUtils.addCookie("AUTHKey", authKey, cookieDomain, response);
		CommonUtils.addCookie("AUTHId", authId, cookieDomain, response);
	}
}
