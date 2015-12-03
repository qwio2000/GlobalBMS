package com.jeiglobal.common.auth;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.context.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.service.auth.*;
import com.jeiglobal.service.menu.*;
import com.jeiglobal.utils.*;

/**
 * 
 * 클래스명 : SecurityContextRepositoryImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 시큐리티 Context를 만들고 Context에 Authentication 정보 주입
 * 
 * 1. 컨텍스트 객체를 로드
 * 2. Cookie 값 로드
 * 3. Cookie 가 있으면 4, 없으면 Authentication 정보 null로 세팅 후 반환
 * 4. Cookie가 가지고 있는 AUTHKey와 DB에 저장된 encodeCookie가 동일한 지 검사
 * 4-1. 동일할 경우 Authentication이 null인지 판단
 * 4-1-1. Authentication이 null인 경우 globalbiz.Users 테이블에서 회원 정보 가져온 후 인증 정보에 셋팅
 * 4-2. 쿠키 정보 삭제 후 Authentication에 null로 세팅 후 반환 
 */
@Component
public class SecurityContextRepositoryImpl implements SecurityContextRepository{
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	private SecurityContext ctx;
	
	private HttpSessionSecurityContextRepository contextRep;
	
	@Autowired
	private MenuService menuService;
	
	@Value("${cookieShare.domain}")
	private String cookieDomain;
	
	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		contextRep = new HttpSessionSecurityContextRepository();
		ctx = contextRep.loadContext(requestResponseHolder);
		Map<String,Object> map = new HashMap<String, Object>();
		HttpServletRequest request = requestResponseHolder.getRequest();
		HttpServletResponse response = requestResponseHolder.getResponse();
		map = getAuthCookieValue(request);
		if(map != null && !map.isEmpty() && map.containsKey("AUTHId") && map.containsKey("AUTHKey")){
			String userName = map.get("AUTHId").toString();
			String encodeCookie = map.get("AUTHKey").toString();
			long cnt = authoritiesService.countMemberByIdAndEncodeCookie(userName, encodeCookie);
			if(cnt == 1){
				if(ctx.getAuthentication() == null){
					LoginInfo member = authoritiesService.findMemberById(userName);
					member.setUserPasswd("");
					member.setEncodeCookie("");
					
					Authentication authentication = new UsernamePasswordAuthenticationToken(member,"",null);
					ctx.setAuthentication(authentication);
				}
			}else{
				if(request.getCookies() != null && request.getCookies().length > 0){
					List<Cookie> cookies = Arrays.asList(request.getCookies());
					for (Cookie cookie : cookies) {
						CommonUtils.removeCookie(cookie.getName(), cookieDomain, response);
					}
				}
				ctx.setAuthentication(null);
			}
		}else{
			ctx.setAuthentication(null);
		}
		return ctx;
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		return contextRep.containsContext(request);
	}
	
	
	@Override
	public void saveContext(SecurityContext context,
			HttpServletRequest request, HttpServletResponse response) {
		contextRep.saveContext(ctx, request, response);
	}
	
	private Map<String,Object> getAuthCookieValue(HttpServletRequest request){
		Cookie[] cookies =  request.getCookies();
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(cookies == null){
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if("AUTHId".equals(cookie.getName())){
				try {
					map.put("AUTHId",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}else if("AUTHKey".equals(cookie.getName())){
				try {
					map.put("AUTHKey",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}
		}
		
		return map;
	}
}
