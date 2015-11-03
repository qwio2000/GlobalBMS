package com.jeiglobal.service.member;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.member.*;
import com.jeiglobal.repository.auth.*;

/**
 * 클래스명 : MemberSearchService.java
 *
 * 작성일 : 2015. 11. 3.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Service
public class MemberSearchService {

	@Autowired
	private MssqlRepository mssqlRepository;
	
	Map<String, Object> param = new HashMap<>();
	
	/**
	 * 한국 회원 조회
	 * @param type
	 * @param searchWord
	 * @param birthYM
	 * @param loginInfo
	 * @return Object
	 */
	public List<KoreaMemberInfo> getKoreaMemberSearch(String type, String searchWord,
			String birthYM, LoginInfo loginInfo) {
		param.clear();
		param.put("type", type);
		param.put("searchWord", searchWord);
		param.put("birthYM", birthYM);
		param.put("jisaCD", loginInfo.getJisaCD());
		return mssqlRepository.findKoreaMemberSearch(param);
	}

}
