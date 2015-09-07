package com.jeiglobal.hk.common.auth;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.context.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.service.common.auth.*;

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
 * 1. 컨텍스트 객체를 로드한 후 Authentication이 없는 경우 2~5를 진행, 있으면 그대로 반환
 * 2. 쿠키 값에 AUTHId라는 이름으로 된 값과 AUTHKey로 된 값을 load
 * 3. 읽어온 값으로 globalbiz.ComLoginInfo 테이블에 일치하는 계정이 있는지 확인
 * 4. 있으면 해당 계정의 정보와 권한을 얻어온다.
 * 5. 계정의 정보와 권한으로 Authentication 객체를 생성 후 SecurityContext에 저장 
 */
@Component
public class SecurityContextRepositoryImpl implements SecurityContextRepository{
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	private SecurityContext ctx;
	
	private HttpSessionSecurityContextRepository contextRep;
	
	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		contextRep = new HttpSessionSecurityContextRepository();
		ctx = contextRep.loadContext(requestResponseHolder);
		if(ctx.getAuthentication() == null){
			
			Map<String,Object> map = new HashMap<String, Object>();
			
			map = getAuthCookieValue(requestResponseHolder.getRequest());
			
			if(map != null && !map.isEmpty() && map.containsKey("AUTHId") && map.containsKey("AUTHKey")){
				String userName = map.get("AUTHId").toString();
				String encodeCookie = map.get("AUTHKey").toString();
				
				long cnt = authoritiesService.countMemberByIdAndEncodeCookie(userName, encodeCookie);
				
				if(cnt == 1){
					List<GrantedAuthority> authorities = new ArrayList<>();
					
					List<Authority> memberAuthories = authoritiesService.findPermissionById(userName);
					
					for(int i = 0; i < memberAuthories.size(); i++){
						authorities.add(new SimpleGrantedAuthority(memberAuthories.get(i).getAuthority()));
					}
					
					LoginInfo member = authoritiesService.findMemberById(userName);
					member.setMemberPassword("");
					member.setEncodeCookie("");
					
					Authentication authentication = new UsernamePasswordAuthenticationToken(member,"",authorities);
					ctx.setAuthentication(authentication);
				}
			}
		}
		return ctx;
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return contextRep.containsContext(request);
	}
	
	
	@Override
	public void saveContext(SecurityContext context,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
