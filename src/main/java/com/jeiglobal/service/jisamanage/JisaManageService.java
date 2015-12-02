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
	 * @param subjInfo
	 * @param workId 
	 */
	public void addSubjInfo(SubjInfo subjInfo, String workId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("workId", workId);
		jisaManageRepository.insertSubjInfo(param);
	}

	/**
	 * @param subjInfo
	 * @param workId void
	 */
	public void addCodeDtl(SubjInfo subjInfo, String workId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("workId", workId);
		jisaManageRepository.insertSubjInfoToCodeDtl(param);
	}
	
	/**
	 * 유지회원 0, 퇴회회원 0인 경우 : 한번도 판매되지 않은 상품 전체 수정
	 * @param subjInfo
	 * @param workId
	 * @param beforeSubj 
	 */
	public void setSubjInfo(SubjInfo subjInfo, String workId, String beforeSubj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("workId", workId);
		param.put("beforeSubj", beforeSubj);
		jisaManageRepository.updateSubjInfo(param);
	}
	
	/**
	 * @param subjInfo
	 * @param workId
	 * @param beforeSubj void
	 */
	public void setSubjInfoToCodeDtl(SubjInfo subjInfo, String workId,
			String beforeSubj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("workId", workId);
		param.put("beforeSubj", beforeSubj);
		jisaManageRepository.updateSubjInfoToCodeDtl(param);
	}

	/**
	 * 유지회원 0, 퇴회회원 0이상인 경우 판매중지일 수정
	 * @param subj
	 * @param jisaCD
	 * @param stopDate
	 * @param workId
	 */
	public void setSubjInfoStopDate(String subj, String jisaCD,
			String stopDate, String workId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subj", subj);
		param.put("jisaCD", jisaCD);
		param.put("stopDate", stopDate);		
		param.put("workId", workId);		
		jisaManageRepository.updateSubjInfoStopDate(param);
	}

	/**
	 * 유지회원 0, 퇴회회원 0인 경우 globalbiz.subjInfo 삭제
	 * @param subj
	 * @param jisaCD
	 * @param workId
	 */
	public void removeSubjInfo(String subj, String jisaCD) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subj", subj);
		param.put("jisaCD", jisaCD);
		jisaManageRepository.deleteSubjInfo(param);
	}

	/**
	 * 유지회원 0, 퇴회회원 0인 경우 globalbiz.CodeDtl 삭제
	 * @param subj
	 * @param jisaCD
	 */
	public void removeSubjInfoToCodeDtl(String subj, String jisaCD) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subj", subj);
		param.put("jisaCD", jisaCD);
		jisaManageRepository.deleteSubjInfoToCodeDtl(param);
	}

	/**
	 * @param jisaCD
	 * @return List<SubjTuitionInfo>
	 */
	public List<SubjTuitionInfo> getJisaTuitionInfos(String jisaCD) {
		return jisaManageRepository.findJisaTuitionInfos(jisaCD);
	}

	/**
	 * @param jisaCD
	 * @param deptType
	 * @param feeType
	 * @return SubjTuitionInfo
	 */
	public SubjTuitionInfo getJisaTuitionInfo(String jisaCD, String deptType,
			String feeType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptType", deptType);
		param.put("feeType", feeType);
		return jisaManageRepository.findJisaTuitionInfo(param);
	}

	/**
	 * @param tuition
	 * @param workId
	 */
	public void setJisaTuitionInfo(SubjTuitionInfo tuition, String workId) {
		// TODO Auto-generated method stub
		tuition.setUpdID(workId);
		jisaManageRepository.updateJisaTuitionInfo(tuition);
	}

	/**
	 * 유지인 회원은 없는 경우 판매중지일 입력할 경우 DeptSubjInfo useYN 수정
	 * @param subj
	 * @param jisaCD
	 */
	public void setDeptSubjInfo(String subj, String jisaCD) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("subj", subj);
		jisaManageRepository.updateDeptSubjInfoSaleStop(param);
		
	}

	/**
	 * DeptSubjInfo 추가
	 * @param subjInfo void
	 */
	public void addDeptSubjInfo(SubjInfo subjInfo) {
		jisaManageRepository.insertDeptSubjInfo(subjInfo);
	}

	/**
	 * @param subjInfo
	 * @param beforeSubj void
	 */
	public void setDeptSubjInfo(SubjInfo subjInfo, String beforeSubj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjInfo", subjInfo);
		param.put("beforeSubj", beforeSubj);
		jisaManageRepository.updateDeptSubjInfo(param);
	}

	/**
	 * @param subj
	 * @param jisaCD void
	 */
	public void removeDeptSubjInfo(String subj, String jisaCD) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("subj", subj);
		jisaManageRepository.deleteDeptSubjInfo(param);
	}


	
}
