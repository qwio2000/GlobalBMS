package com.jeiglobal.hk.controller.common;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.hk.domain.menu.*;
import com.jeiglobal.hk.service.common.menu.*;


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
@Controller
@RequestMapping(value="/adminManager")
public class MenuController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value="/menuIndex")
	public String menuIndex(){
		LOGGER.debug("Getting MenuIndex Page");
		return "common/menu/menuIndex";
	}
	
	@RequestMapping(value="/menuList")
	public String menuList(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mEmpKeyLvCD") String mEmpKeyLvCD,@RequestParam("mDepMngCD") String mDepMngCD){
		LOGGER.debug("Getting MenuList Page");
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mEmpKeyLvCD,mDepMngCD,"1"));
		return "common/menu/menuList";
	}
	
	@RequestMapping(value="/menuContent")
	public String content(Model model,@RequestParam("mJisaCD") String mJisaCD
			,@RequestParam("mEmpKeyLvCD") String mEmpKeyLvCD,@RequestParam("mDepMngCD") String mDepMngCD) {
		LOGGER.debug("Getting MenuContent Page");
		model.addAttribute("menuList",menuService.menuList(0,mJisaCD,mEmpKeyLvCD,mDepMngCD,"1"));
		return "common/menu/content";
	}
	
	@RequestMapping(value="/menuChange/{mIdx}",method=RequestMethod.GET)
	public String menuChangeList(Model model,@PathVariable long mIdx){
		LOGGER.debug("Getting MenuChangeList Page");
		model.addAttribute("menuList",menuService.changeList(mIdx));
		return "common/menu/changeList";
	}
	
	@RequestMapping(value="/menuSave.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuSave(GlobalMenu globalMenu){
		LOGGER.debug("Created Menu : {}",globalMenu.getMMenuName());
		String msg = "";
		msg = menuService.create(globalMenu);		
		return msg;
	}
	
	@RequestMapping(value="/menuDelete/{mIdx}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuDelete(@PathVariable long mIdx){
		LOGGER.debug("Deleted Menu : Idx = {}",mIdx);
		String msg = "";
		msg = menuService.delete(mIdx);		
		return msg;
	}
	
	@RequestMapping(value="/menuContent/{mIdx}",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public GlobalMenu menuReadOne(@PathVariable long mIdx){
		LOGGER.debug("Getting Menu Info : Idx = {}",mIdx);
		return menuService.readOne(mIdx);
	}
	
	@RequestMapping(value="/menuUpdate.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuUpdate(GlobalMenu globalMenu){
		LOGGER.debug("Updated Menu : {}", globalMenu.getMMenuName());
		String msg = "";
		msg = menuService.update(globalMenu);	
		return msg;
	}
	
	@RequestMapping(value="/menuChange",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String menuChange(String lan){
		LOGGER.debug("Menu Changed : {}",lan);
		String msg = "";
		msg = menuService.change(lan);	
		return msg;
	}
	
}
