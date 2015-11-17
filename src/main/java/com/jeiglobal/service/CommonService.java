package com.jeiglobal.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.*;
import com.jeiglobal.repository.*;

/**
 * 클래스명 : CommonService.java
 *
 * 작성일 : 2015. 9. 15.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 공통 서비스
 */
@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	Map<String, Object> param = new HashMap<String, Object>();
	
	/**
	 * CodeDtl를 가져오는 메서드
	 * @param mstCD
	 * @param jisaCD
	 * @param dtlCD
	 * @param useYN : Y, N, 공백(전체)
	 * @return CodeDtl
	 */
	public CodeDtl getCodeDtl(String mstCD, String jisaCD, String dtlCD, String useYN){
		param.clear();
		param.put("mstCD", mstCD);
		param.put("jisaCD", jisaCD);
		param.put("dtlCD", dtlCD);
		param.put("useYN", useYN);
		return commonRepository.findCodeDtl(param);
	}
	
	/**
	 * CodeDtl 리스트를 가져오는 메서드
	 * @param mstCD
	 * @param jisaCD
	 * @return List<CodeDtl>
	 */
	public List<CodeDtl> getCodeDtls(String mstCD, String jisaCD, int sortVal, String useVal){
		param.clear();
		param.put("mstCD", mstCD);
		param.put("jisaCD", jisaCD);
		param.put("sortVal", sortVal);
		param.put("useVal", useVal);
		return commonRepository.findCodeDtls(param);
	}

	/**
	 * 지사 별 State 리스트를 가져오는 메서드
	 * @param jisaCD 
	 * @return List<CenterState>
	 */
	public List<CenterState> getCenterStates(String jisaCD) {
		return commonRepository.findCenterStates(jisaCD);
	}
	
	/**
	 * 지사나 가맹점별로 운영중인 과목 리스트를 가져오는 메서드
	 * @param jisaCD
	 * @param deptCD
	 * @param type  =>  1 : 운영중인것, 2 : 한번이라도 운영된 적이 있었던 과목들
	 * @return List<String>
	 */
	public List<String> getOpenSubjsByDeptCD(String jisaCD, String deptCD, String type){
		param.clear();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("type", type);
		return commonRepository.findOpenSubjsByDeptCD(param);
	}
	
	public List<GradeOfSubject> getGradeOfSubject(String jisaCD, String subj, String useYN, String digYN){
		param.clear();
		param.put("jisaCD", jisaCD);
		param.put("subj", subj);
		param.put("useYN", useYN);
		param.put("digYN", digYN);
		return commonRepository.findGradeOfSubject(param);
	}
}
