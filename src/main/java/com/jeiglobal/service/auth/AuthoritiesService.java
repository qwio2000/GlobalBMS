package com.jeiglobal.service.auth;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.repository.auth.*;

import eu.bitwalker.useragentutils.*;
/**
 * 
 * 클래스명 : AuthoritiesService.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그인에 관련된 비즈니스 로직을 처리하는 서비스
 */
@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public LoginInfo findMemberById(String memberId){
		return authoritiesRepository.findMemberById(memberId);
	}

	public List<Authority> findPermissionById(String memberId){
		return authoritiesRepository.findPermissionById(memberId);
	}
	
	/**
	 * 로그인 시 로그인한 회원 고유의 키 값을 디비에 업데이트 : 중복 로그인 방지
	 * @param memberId
	 * @param encodeCookie void
	 */
	public void updateEncodeCookieById(String memberId,String encodeCookie){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("encodeCookie", encodeCookie);
		authoritiesRepository.updateEncodeCookieById(map);
	}
	
	public long countMemberByIdAndEncodeCookie(String memberId,String encodeCookie){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("encodeCookie", encodeCookie);
		return authoritiesRepository.countMemberByIdAndEncodeCookie(map);
	}

	/**
	 * @param userId void
	 * @param request 
	 */
	public void updateLoginInfo(String userId, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("ip", ip);
		authoritiesRepository.updateLoginInfo(map);
	}

	/**
	 * @param member
	 * @param request void
	 */
	public void insertLoginHis(LoginInfo member, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		String browserInfo = userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginInfo", member);
		map.put("ip", ip);
		map.put("browserInfo", browserInfo);
		authoritiesRepository.insertLoginHis(map);
	}

}
