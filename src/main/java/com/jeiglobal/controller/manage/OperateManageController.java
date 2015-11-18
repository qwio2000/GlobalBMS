package com.jeiglobal.controller.manage;

import java.util.*;

import javax.servlet.http.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.*;
import com.jeiglobal.domain.manage.ManageDto.MagamDate;
import com.jeiglobal.service.*;
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
	
	@Autowired
	private CommonService commonService;
	
	private final static String GRADE_MSTCD = "0003";
	private final static String REGIST_WHY_MSTCD = "0202";
	private final static String REGIST_HOW_MSTCD = "0009";
	private final static String DROP_REASON_MSTCD = "0201";
	
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
	
	@RequestMapping(value="/ma/manage/operate/closingDate/edit", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String setClosingDateJson(MagamDate magamDate){
		log.debug("Setting Closing Date : magamDate = {}", magamDate);
		operateManageService.setClosingDate(magamDate);
		return msa.getMessage("manage.operate.closingdate.update.success");
	}
	
	@RequestMapping(value="/ma/manage/operate/closingDate", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String addClosingDateJson(MagamDate magamDate){
		log.debug("Adding Closing Date : magamDate = {}", magamDate);
		int count = operateManageService.getMagamDateCountByMgMonth(magamDate.getMgMonth());
		if(count > 0){
			return msa.getMessage("manage.operate.closingdate.regist.alreadyexist");
		}
		String[] jisaCD = {"00", "08"};
		for (int i = 0; i < jisaCD.length; i++) {
			magamDate.setJisaCD(jisaCD[i]);
			operateManageService.addClosingDate(magamDate);
		}
		return msa.getMessage("manage.operate.closingdate.regist.success");
	}
	
	@RequestMapping(value="/ma/manage/operate/code", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getCodeManagePage(Model model, String jisaCD, String mstCD, @RequestParam(defaultValue="1", required=false) int pageNum){
		String[] mstCDAry = {GRADE_MSTCD, REGIST_WHY_MSTCD, REGIST_HOW_MSTCD, DROP_REASON_MSTCD}; 
		List<CodeMst> codeMsts = operateManageService.getCodeMstsByMstCDs(mstCDAry);
		List<CodeDtl> jisaCDs = commonService.getCodeDtls("0001", "08", 1, "Y");
		List<String> headerScript = new ArrayList<>();
		headerScript.add("codeManage");
		log.debug("Getting Code Manage Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("codeMsts", codeMsts);
		model.addAttribute("jisaCDs", jisaCDs);
		model.addAttribute("sJisaCD", jisaCD);
		model.addAttribute("sMstCD", mstCD);
		model.addAttribute("pageNum", pageNum);
		return "manage/operate/code";
	}
	
	@RequestMapping(value="/ma/manage/operate/code/{pageNum:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public Map<String, Object> getCodeDtlJson(@PathVariable int pageNum, String mstCD, String jisaCD){
		log.debug("Getting Code Dtl, mstCD : {}, jisaCD : {} ", mstCD, jisaCD);
		PageUtil pageInfo = new PageUtil(pageNum, operateManageService.getCodeDtlCount(mstCD, jisaCD), 10, 10);
		List<CodeDtl> codeDtls = operateManageService.getCodeDtls(mstCD, jisaCD, pageInfo.getStartRow(), pageInfo.getEndRow()); 
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("codeDtls", codeDtls);
		return map;
	}
	@RequestMapping(value="/ma/manage/operate/code/new", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getCodeRegistPage(Model model, String mstCD, String jisaCD, @RequestParam(defaultValue="1", required=false) int pageNum){
		String[] mstCDAry = {GRADE_MSTCD, REGIST_WHY_MSTCD, REGIST_HOW_MSTCD, DROP_REASON_MSTCD}; 
		List<CodeMst> codeMsts = operateManageService.getCodeMstsByMstCDs(mstCDAry);
		List<CodeDtl> jisaCDs = commonService.getCodeDtls("0001", "08", 1, "Y");
		List<String> headerScript = new ArrayList<>();
		headerScript.add("codeManage");
		log.debug("Getting Code Manage Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("codeMsts", codeMsts);
		model.addAttribute("jisaCDs", jisaCDs);
		model.addAttribute("mstCD", mstCD);
		model.addAttribute("jisaCD", jisaCD);
		model.addAttribute("pageNum", pageNum);
		
		return "manage/operate/codeRegist";
	}
	@RequestMapping(value="/ma/manage/operate/code/edit", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getCodeEditPage(Model model, String mstCD, String jisaCD, String dtlCD, int pageNum){
		CodeDtl codeDtl = commonService.getCodeDtl(mstCD, jisaCD, dtlCD, "");
		List<CodeDtl> jisaCDs = commonService.getCodeDtls("0001", "08", 1, "Y");
		List<String> headerScript = new ArrayList<>();
		headerScript.add("codeManage");
		log.debug("Getting Code Manage Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaCDs", jisaCDs);
		model.addAttribute("codeDtl", codeDtl);
		model.addAttribute("pageNum", pageNum);
		
		return "manage/operate/codeEdit";
	}
	
	@RequestMapping(value="/ma/manage/operate/code/edit", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String setCodeDtlJson(CodeDtl codeDtl, String beforeDtlCD, HttpServletRequest request){
		log.debug("Setting CodeDtl : mstCD = {}, dtlCD = {}", codeDtl.getMstCD(), beforeDtlCD);
		List<CodeDtl> codeDtls = commonService.getCodeDtls(codeDtl.getMstCD(), codeDtl.getJisaCD(), 1, "");
		for (CodeDtl item : codeDtls) {
			if(item.getDtlCD().equals(codeDtl.getDtlCD()) && !beforeDtlCD.equals(item.getDtlCD())){
				return msa.getMessage("manage.operate.code.update.alreadyexist");
			}
		}
		String workId = CommonUtils.getWorkId(request);
		operateManageService.setCodeDtl(codeDtl, beforeDtlCD, workId);
		return msa.getMessage("manage.operate.code.update.success");
	}
	
	@RequestMapping(value="/ma/manage/operate/code", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String addCodeDtlJson(CodeDtl codeDtl, HttpServletRequest request){
		log.debug("Adding CodeDtl : codeDtl = {}", codeDtl.toString());
		List<CodeDtl> codeDtls = commonService.getCodeDtls(codeDtl.getMstCD(), codeDtl.getJisaCD(), 1, "");
		for (CodeDtl item : codeDtls) {
			if(item.getDtlCD().equals(codeDtl.getDtlCD())){
				return msa.getMessage("manage.operate.code.regist.alreadyexist");
			}
		}
		String workId = CommonUtils.getWorkId(request);
		operateManageService.addCodeDtl(codeDtl, workId);
		return msa.getMessage("manage.operate.code.regist.success");
	}
	@RequestMapping(value="/ma/manage/operate/closeregist", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getRegistClosePage(Model model){
		List<CodeDtl> jisaCDs = commonService.getCodeDtls("0001", "08", 1, "Y");
		List<String> headerScript = new ArrayList<>();
		headerScript.add("registCloseManage");
		log.debug("Getting Regist Close Manage Page");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaCDs", jisaCDs);
		return "manage/operate/registClose";
	}
	@RequestMapping(value="/ma/manage/operate/closeRegist", method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String addCloseRegistJson(String jisaCD, String statusCD, String closeReason, HttpServletRequest request){
		log.debug("Adding closeRegist : jisaCD = {}, statusCD = {}, closeReason = {}", jisaCD, statusCD, closeReason);
		String workId = CommonUtils.getWorkId(request);
		if("1".equals(statusCD)){//입회 불가 처리
			String getCloseReason = operateManageService.getLatestCloseReason(jisaCD);
			if(getCloseReason != null && !"".equals(getCloseReason)){
				return msa.getMessage("manage.operate.closeregist.alreadyexist");
			}
			operateManageService.addMemRegistClose(jisaCD, statusCD, closeReason, workId);
		}else{//입회 가능 처리
			operateManageService.setMemRegistClose(jisaCD, statusCD, workId);
		}
		return msa.getMessage("manage.operate.closeregist.success");
	}
}
