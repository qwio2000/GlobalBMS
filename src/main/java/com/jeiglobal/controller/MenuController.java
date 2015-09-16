package com.jeiglobal.controller;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.menu.*;
import com.jeiglobal.service.*;
import com.jeiglobal.service.menu.*;


/**
 * 
 * 클래스명 : MenuController.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 메뉴 처리 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping(value="/ma/managemenu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/menuIndex")
	public String menuIndex(Model model){
		log.debug("Getting MenuIndex Page");
		List<String> headerCss = new ArrayList<>();
		headerCss.add("jquery.treeview");
		headerCss.add("menu");
		List<String> headerScript = new ArrayList<>();
		headerScript.add("/common/jquery.form.stylishSelect");
		headerScript.add("/common/jquery.treeview");
		headerScript.add("globalmenu");
		model.addAttribute("headerCss", headerCss);
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaCDs", commonService.getCodeDtls("0001", "08"));
		model.addAttribute("userTypes", commonService.getCodeDtls("0400", "08"));
		model.addAttribute("userLevels", commonService.getCodeDtls("0401", "08"));
		return "menu/menuIndex";
	}
	
	@RequestMapping(value="/menuList")
	public String menuList(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mUserType") String mUserType,@RequestParam("mUserLevel") String mUserLevel){
		log.debug("Getting MenuList Page");
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mUserType,mUserLevel,"1","all"));
		return "menu/menuList";
	}
	
	@RequestMapping(value="/menuContent")
	public String content(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mUserType") String mUserType,@RequestParam("mUserLevel") String mUserLevel) {
		log.debug("Getting MenuContent Page");
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mUserType,mUserLevel,"1","all"));
		model.addAttribute("jisaCDs", commonService.getCodeDtls("0001", "08"));
		model.addAttribute("userTypes", commonService.getCodeDtls("0400", "08"));
		model.addAttribute("userLevels", commonService.getCodeDtls("0401", "08"));
		return "menu/content";
	}
	
	@RequestMapping(value="/menuChange/{mIdx}",method=RequestMethod.GET)
	public String menuChangeList(Model model,@PathVariable long mIdx){
		log.debug("Getting MenuChangeList Page");
		model.addAttribute("menuList",menuService.changeList(mIdx));
		return "menu/changeList";
	}
	
	@RequestMapping(value="/menuSave.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuSave(GlobalMenu globalMenu, @ModelAttribute(value="loginInfo")LoginInfo loginInfo){
		log.debug("Created Menu : {}",globalMenu.getMMenuName());
		String msg = "";
		globalMenu.setRegID(loginInfo.getUserId());
		globalMenu.setUpdID(loginInfo.getUserId());
		msg = menuService.create(globalMenu);		
		return msg;
	}
	
	@RequestMapping(value="/menuDelete/{mIdx}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuDelete(@PathVariable long mIdx){
		log.debug("Deleted Menu : Idx = {}",mIdx);
		String msg = "";
		msg = menuService.delete(mIdx);		
		return msg;
	}
	
	@RequestMapping(value="/menuContent/{mIdx}",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public GlobalMenu menuReadOne(@PathVariable long mIdx){
		log.debug("Getting Menu Info : Idx = {}",mIdx);
		return menuService.readOne(mIdx);
	}
	
	@RequestMapping(value="/menuUpdate.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuUpdate(GlobalMenu globalMenu, @ModelAttribute(value="loginInfo")LoginInfo loginInfo){
		log.debug("Updated Menu : {}", globalMenu.getMMenuName());
		String msg = "";
		globalMenu.setUpdID(loginInfo.getUserId());
		msg = menuService.update(globalMenu);	
		return msg;
	}
	
	@RequestMapping(value="/menuChange",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuChange(String lan){
		log.debug("Menu Changed : {}",lan);
		String msg = "";
		msg = menuService.change(lan);	
		return msg;
	}
	
}
