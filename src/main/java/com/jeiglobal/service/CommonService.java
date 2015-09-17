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
	 * @return CodeDtl
	 */
	public CodeDtl getCodeDtl(String mstCD, String jisaCD, String dtlCD){
		param.clear();
		param.put("mstCD", mstCD);
		param.put("jisaCD", jisaCD);
		param.put("dtlCD", dtlCD);
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
	
}
