package com.jeiglobal.controller.manage;

import java.util.*;

import javax.servlet.http.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.manage.ManageDto.User;
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
	
	@Autowired
	private MessageSourceAccessor msa;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

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
			check = "N";
		}else{
			check = "Y";
		}
		return check;
	}
	
	@RequestMapping(value={"/ma/manage/users"},method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody	
	public String addUserJson(User user, HttpServletRequest request){
		log.debug("Getting Account Manage, User Regist : {}", user.getUserId());
		user.setEmpKey(accountManageService.getNewEmpKey());
		user.setUserPasswd(encoder.encode(user.getUserPasswd()));
		user.setRegID(CommonUtils.getWorkId(request));
		accountManageService.addUser(user);
		return msa.getMessage("manage.user.regist.success");
	}
	
	@RequestMapping(value={"/ma/manage/users/delete"},method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody	
	public String removeUserJson(String userId){
		log.debug("Getting Account Manage, User Delete : {}", userId);
		accountManageService.removeUserByUserId(userId);
		return msa.getMessage("manage.user.delete.success");
	}
	
	@RequestMapping(value="/ma/manage/users/edit", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getUserEditPage(Model model, String userId){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("accountManage");
		log.debug("Getting Account Manage, User Edit Page");
		log.debug(accountManageService.getUserByUserId(userId).toString());
		model.addAttribute("user", accountManageService.getUserByUserId(userId));
		model.addAttribute("headerScript", headerScript);
		return "manage/account/userEdit";
	}
	
	@RequestMapping(value={"/ma/manage/users/edit"},method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody	
	public String setUserJson(User user, HttpServletRequest request){
		log.debug("Getting Account Manage, User Edit : {}", user.getUserId());
		user.setUpdID(CommonUtils.getWorkId(request));
		accountManageService.setUser(user);
		
		return msa.getMessage("manage.user.update.success");
	}
	
	@RequestMapping(value={"/ma/manage/users/clearPwdJson"},method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody	
	public String setUserPwdJson(String userId, HttpServletRequest request){
		log.debug("Getting Account Manage, User Password Clear : {}", userId);
		accountManageService.setUserPasswordClearByUserId(userId, encoder.encode(userId), CommonUtils.getWorkId(request));
		return msa.getMessage("manage.user.passwordclear.success");
	}
}
