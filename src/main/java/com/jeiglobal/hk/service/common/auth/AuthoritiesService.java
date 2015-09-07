package com.jeiglobal.hk.service.common.auth;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.repository.common.auth.*;
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
	private MssqlRepository mssqlRepository;
	
	public LoginInfo findMemberById(String memberId){
		return authoritiesRepository.findMemberById(memberId);
	}

	public List<Authority> findPermissionById(String memberId){
		return authoritiesRepository.findPermissionById(memberId);
	}
	
	/**
	 * 디비에 MSSQL SHA256으로 암호화된 값으로 저장 되어 있기 때문에 비교를 위해서 
	 * 사용자가 입력한 비밀번호를 MSSQL에서 암호화 한 값으로 변환
	 * @param password
	 * @return String
	 */
	public String selectEncryptPassWord(String password){
		return mssqlRepository.selectEncryptPassWord(password);
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
	
}
