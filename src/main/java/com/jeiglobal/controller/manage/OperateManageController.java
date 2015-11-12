package com.jeiglobal.controller.manage;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.manage.ManageDto.MagamDate;
import com.jeiglobal.service.manage.*;
import com.jeiglobal.utils.*;

/**
 * 
 * 클래스명 : OperateManageController.java
 *
 * 작성일 : 2015. 11. 11.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 전산운영
 */
@Slf4j
@Controller
public class OperateManageController {

	@Autowired
	private OperateManageService operateManageService;
	
	@Autowired
	private MessageSourceAccessor msa;
	
	@RequestMapping(value="/ma/manage/operate", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getUsersPage(Model model){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("operateManage");
		log.debug("Getting Operate Manage Page");
		model.addAttribute("headerScript", headerScript);
		return "manage/operate/list";
	}
	
	@RequestMapping(value="/ma/manage/operate/closingDate", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMagamDatePage(Model model){
		List<String> headerScript = new ArrayList<>();
		headerScript.add("operateManage");
		log.debug("Getting Operate Manage Page");
		model.addAttribute("headerScript", headerScript);
		return "manage/operate/magamDate";
	}
	
	@RequestMapping(value="/ma/manage/operate/closingDate/{pageNum:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getClosingDateJson(@PathVariable int pageNum){
		log.debug("Getting Closing Date From 1 Years ago");
		PageUtil pageInfo = new PageUtil(pageNum, operateManageService.getMagamDatesCount(), 10, 10);
		List<MagamDate> magamDates = operateManageService.getMagamDates(pageInfo.getStartRow(), pageInfo.getEndRow()); 
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("magamDates", magamDates);
		return map;
	}
	
	@RequestMapping(value="/ma/manage/operate/closingDate/{mgMonth:[0-9]{4}-[0-9]{2}}", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getClosingDateJson(@PathVariable String mgMonth){
		log.debug("Getting Closing Date : mgMonth = {}", mgMonth);
		MagamDate magamDate = operateManageService.getMagamDateByMgMonth(mgMonth); 
		Map<String, Object> map = new HashMap<>();
		map.put("magamDate", magamDate);
		return map;
	}
	
}
