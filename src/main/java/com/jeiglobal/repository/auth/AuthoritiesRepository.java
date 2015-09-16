package com.jeiglobal.repository.auth;

import java.util.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.repository.*;

/**
 * 
 * 클래스명 : AuthoritiesRepository.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Mysql 사용 인증 관련 Repository
 * src/main/resource/mapper/auth/AuthoritiesRepository.xml
 */
@PrimaryRepositoryAnnoInterface
public interface AuthoritiesRepository {
	public LoginInfo findMemberById(String memberId);

	public List<Authority> findPermissionById(String memberId);
	
	public void updateEncodeCookieById(Map<String, Object> map);

	public long countMemberByIdAndEncodeCookie(Map<String, Object> map);

	public void updateLoginInfo(Map<String, Object> map);

	public void insertLoginHis(Map<String, Object> map);

}
