package com.jeiglobal.controller.member;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.member.MemberDto.MemberSearchInfo;
import com.jeiglobal.domain.member.MemberDto.MemberSearchResult;
import com.jeiglobal.service.member.*;
import com.jeiglobal.utils.*;

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
	
	@Value("${page.size}")
	private int pageSize;
	
	@Value("${page.blockSize}")
	private int pageBlockSize;
	
	@RequestMapping(value="/ma/member/searchForm", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMemberSearchFormPage(Model model, 
			@ModelAttribute LoginInfo loginInfo){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("memberSearch");
		log.debug("Getting Member Search Page");
		model.addAttribute("headerScript", headerScript);
		return "member/searchForm";
	}
	
	@RequestMapping(value="/ma/member/search", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMemberSearchInfo(Model model, 
			MemberSearchInfo memberSearchInfo,
			@ModelAttribute LoginInfo loginInfo){
		if(memberSearchInfo.getMemKey() != null && !memberSearchInfo.getMemKey().isEmpty()){
			memberSearchInfo.setType("1"); // 회원번호로 검색
		}else{
			memberSearchInfo.setType("2"); // 회원이름으로 검색
		}
		List<String> headerScript = new ArrayList<>();
		headerScript.add("memberSearch");
		log.debug("Getting Member Search Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("memberSearchInfo", memberSearchInfo);
		return "member/result";
	}
	
	@RequestMapping(value="/ma/member/search/{pageNum:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getMemberSearchInfo(Model model, 
			MemberSearchInfo memberSearchInfo,
			@ModelAttribute LoginInfo loginInfo,
			@PathVariable int pageNum){
		log.debug("Getting Jisa Login Page");
		PageUtil pageInfo = new PageUtil(pageNum, memberSearchService.getMemberSearchCount(memberSearchInfo), pageSize, pageBlockSize);
		List<MemberSearchResult> result = memberSearchService.getMemberSearch(memberSearchInfo, pageInfo.getStartRow(), pageInfo.getEndRow());
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("result", result);
		return map;
	}
	
}
