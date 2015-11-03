package com.jeiglobal.controller.member;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.service.member.*;

/**
 * 
 * 클래스명 : MemberSearchController.java
 *
 * 작성일 : 2015. 11. 3.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 회원조회 
 */
@Slf4j
@Controller
public class MemberSearchController {

	@Autowired
	private MemberSearchService memberSearchService;
	
	@RequestMapping(value="/ma/member/search", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getJisaLoginPage(Model model, 
			String type, // 1 : 회원 번호, 2 : 회원 이름 
			String searchWord, // 검색어
			String birthYM, // 생년월
			@ModelAttribute LoginInfo loginInfo){
		log.debug("Getting Jisa Login Page");
		Map<String, Object> map = new HashMap<>();
		map.put("korMemberSearchList", memberSearchService.getKoreaMemberSearch(type, searchWord, birthYM, loginInfo));
		return map;
	}
	
}
