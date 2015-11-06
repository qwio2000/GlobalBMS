package com.jeiglobal.controller.manage;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.manage.ManageDto.Users;
import com.jeiglobal.service.manage.*;
import com.jeiglobal.utils.*;

/**
 * 
 * 클래스명 : AccountManageController.java
 *
 * 작성일 : 2015. 11. 6.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 사용자 계정 관리(본사)
 */
@Slf4j
@Controller
public class AccountManageController {

	@Autowired
	private AccountManageService accountManageService;
	
	@Value("${page.size}")
	private int pageSize;
	
	@Value("${page.blockSize}")
	private int pageBlockSize;
	
	@RequestMapping(value="/ma/manage/users", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getUsersPage(Model model){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("accountManage");
		log.debug("Getting Account Manage, Users Page");
		model.addAttribute("headerScript", headerScript);
		return "manage/account/users";
	}
	
	@RequestMapping(value="/ma/manage/users/{pageNum:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getUsersJson(@ModelAttribute LoginInfo loginInfo ,@PathVariable int pageNum){
		log.debug("Getting Account Manage, Users Page");
		PageUtil pageInfo = new PageUtil(pageNum, accountManageService.getUsersCount(loginInfo), pageSize, pageBlockSize);
		List<Users> users = accountManageService.getUsers(loginInfo.getUserType(), pageInfo.getStartRow(), pageInfo.getEndRow());
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("users", users);
		return map;
	}
	
	@RequestMapping(value="/ma/manage/users/new", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getUserRegistPage(Model model){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("accountManage");
		log.debug("Getting Account Manage, User Regist Page");
		model.addAttribute("headerScript", headerScript);
		return "manage/account/userRegist";
	}
	
	@RequestMapping(value={"/ma/manage/idcheck"},method = {RequestMethod.GET}, produces="application/json;charset=UTF-8;")
	@ResponseBody	
	public String getUserIdChkJson(String userId){
		int count = accountManageService.getUsersCountByUserId(userId);
		String check = "";
		if(count > 0){
			check = "Y";
		}else{
			check = "N";
		}
		return check;
	}
}
