package com.jeiglobal.controller.lead;

import java.util.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.*;
import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.lead.*;
import com.jeiglobal.service.*;
import com.jeiglobal.service.lead.*;
import com.jeiglobal.utils.*;

/**
 * 클래스명 : LeadController.java
 *
 * 작성일 : 2015. 11. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Lead 컨트롤러
 */
@Slf4j
@Controller
public class LeadController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private LeadService leadService;
	
	//한 페이지에 출력할 레코드 개수
	@Value("${page.size}")
	private int pageSize;
	
	//한 페이지에 출력할 레코드 개수
	@Value("${page.blockSize}")
	private int blockSize;
	
	@RequestMapping(value = { "/ma/leads" }, method = { RequestMethod.GET, RequestMethod.HEAD })
	public String getLeadsPage(Model model, @ModelAttribute LoginInfo loginInfo) {
		List<String> headerScript = new ArrayList<String>();
		List<CodeDtl> leadStatus = commonService.getCodeDtls("0320", loginInfo.getJisaCD(), 1, "Y");
		headerScript.add("lead");
		log.debug("Getting Leads Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("leadStatus", leadStatus);
		return "lead/list";
	}
	
	@RequestMapping(value={"/ma/leads/{pageNum:[0-9]+}"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getLeadResultJson(Model model, @PathVariable int pageNum,
			String contactName, String statusCD, String orderBy, String ord,
			@ModelAttribute LoginInfo loginInfo){
		log.debug("Getting Leads Result : contactName : {}, statusCD : {}, orderBy : {}, ord : {}", contactName, statusCD, orderBy, ord);
		PageUtil pageInfo = new PageUtil(pageNum, leadService.getLeadsCount(contactName, statusCD, orderBy, ord), blockSize, pageSize);
		Map<String, Object> map = new HashMap<>();
		if(pageInfo.getTotalRowCnt() > 0){
			List<CenterLeads> leads = leadService.getLeads(contactName, statusCD, orderBy, ord, pageInfo.getStartRow(), pageInfo.getEndRow());
			map.put("leads", leads);
		}
		map.put("pageInfo", pageInfo);
		return map;
	}
	
	@RequestMapping(value = { "/ma/leads/{idx:[0-9]+}" }, method = { RequestMethod.GET, RequestMethod.HEAD })
	public String getLeadPage(Model model, @ModelAttribute LoginInfo loginInfo, @PathVariable int idx) {
		log.debug("Getting Lead Page, idx = {}", idx);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("lead");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("centerLead", leadService.getCenterLeadByIdx(idx));
		model.addAttribute("leadStatus", commonService.getCodeDtls("0320", loginInfo.getJisaCD(), 1, "Y"));
		return "lead/view";
	}
	
	@RequestMapping(value={"/ma/leads/notes/{idx:[0-9]+}"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getLeadNotesJson(@PathVariable int idx){
		Map<String, Object> map = new HashMap<>();
		map.put("notes", leadService.getLeadNotesByIdx(idx));
		return map;
	}
	
}
