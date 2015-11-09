package com.jeiglobal.service.jisamanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeiglobal.domain.manage.*;
import com.jeiglobal.repository.jisamanage.JisaManageRepository;
import com.jeiglobal.service.auth.AuthoritiesService;
import com.jeiglobal.utils.CommonUtils;

/**
 * 클래스명 : JisaLoginService.java
 *
 * 작성일 : 2015. 9. 10.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 수정자 : 노윤희(IT지원팀)
 * 
 * 지사관리
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
	public Map<String, Object> getJisaView(String jisaCD, String deptCD) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);		
		return jisaManageRepository.findJisaView(param);
	}

	/**
	 * 지사 과목정보 가져오기
	 * @param jisaCD
	 * @param startRow 
	 * @param endRow 
	 * @return List<SubjInfo>
	 */
	public List<SubjInfo> getJisaSubjInfos(String jisaCD, int startRow, int endRow) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		return jisaManageRepository.findJisaSubjInfos(param);
	}

	/**
	 * @param jisaCD
	 * @return int
	 */
	public int getJisaSubjInfosCount(String jisaCD) {
		return jisaManageRepository.findJisaSubjInfosCount(jisaCD);
	}

	/**
	 * @param jisaCD
	 * @param subj
	 * @return SubjInfo
	 */
	public SubjInfo getSubjInfo(String jisaCD, String subj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("subj", subj);
		return jisaManageRepository.findSubjInfo(param);
	}

	/**
	 * @param subjInfo void
	 * @param workId 
	 */
	public void addSubjInfo(SubjInfo subjInfo, String workId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("workId", workId);
		jisaManageRepository.insertSubjInfo(param);
	}
	
	
}
