package com.jeiglobal.service.jisaLogin;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.jeiglobal.service.common.auth.*;
import com.jeiglobal.utils.*;

/**
 * 클래스명 : JisaLoginService.java
 *
 * 작성일 : 2015. 9. 10.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Service
public class JisaLoginService {

	@Value("${cookieShare.domain}")
	private String cookieDomain;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	/** 쿠키 백업
	 * @param authId
	 * @param authKey
	 * @param response void
	 */
	public void addBackupCookies(String authId, String authKey,
			HttpServletResponse response) {
		CommonUtils.addCookie("BmsAUTHKey", authKey, cookieDomain, response);
		CommonUtils.addCookie("BmsAUTHId", authId, cookieDomain, response);		
	}

	/** 쿠키 값 변경
	 * @param memberId
	 * @param response void
	 */
	public void addJACookies(String memberId, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		String authKey = standrdPasswordEncoder.encode(memberId);
		authoritiesService.updateEncodeCookieById(memberId, authKey);
		CommonUtils.addCookie("AUTHKey", authKey, cookieDomain, response);
		CommonUtils.addCookie("AUTHId", memberId, cookieDomain, response);
	}

}
