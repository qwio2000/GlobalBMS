package com.jeiglobal.service.jisamanage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeiglobal.repository.jisamanage.JisaManageRepository;
import com.jeiglobal.service.auth.AuthoritiesService;
import com.jeiglobal.utils.CommonUtils;

/**
 * 클래스명 : JisaLoginService.java
 *
 * 작성일 : 2015. 9. 10.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 본사 -> 지사 로그인을 처리할 때 사용하는 서비스
 */
@Service
public class JisaManageService {

	@Value("${cookieShare.domain}")
	private String cookieDomain;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private JisaManageRepository jisaManageRepository;
	
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
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		String authKey = standrdPasswordEncoder.encode(memberId);
		CommonUtils.addCookie("AUTHKey", authKey, cookieDomain, response);
		CommonUtils.addCookie("AUTHId", memberId, cookieDomain, response);
	}

	/**
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getJisaList() {
		return jisaManageRepository.findJisaList();
	}
	/*
	public JisaView getJisaView() {
		return jisaManageRepository.findJisaView();
	}
	*/	
	
	//

}
