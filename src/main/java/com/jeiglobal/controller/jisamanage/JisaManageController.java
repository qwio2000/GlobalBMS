package com.jeiglobal.controller.jisamanage;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.*;
import com.jeiglobal.domain.auth.LoginInfo;
import com.jeiglobal.domain.manage.*;
import com.jeiglobal.service.*;
import com.jeiglobal.service.jisamanage.JisaManageService;
import com.jeiglobal.utils.*;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 클래스명 : HomeController.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 수정자 : 노윤희(IT지원팀)
 * 
 * 지사관리
 */
@Slf4j
@Controller
public class JisaManageController {

	@Autowired
	private JisaManageService jisaManageService;
	
	@Autowired
	private MessageSourceAccessor msa;
	
	@Value("${serverurl.hongkong}")
	private String hongkongUrl;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/ma/jisamanage")
	public String getJisaManageList(Model model){
		List<Map<String, Object>> jisaList = jisaManageService.getJisaList();
		log.debug("Getting Jisa Login Page");
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("jisamanage");
		model.addAttribute("headerScript", headerScript);		
		model.addAttribute("jisaList", jisaList);
		return "jisamanage/index";
	}

	// 지사 뷰
	@RequestMapping(value={"/ma/jisamanage/jisaView"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getJisaView(Model model, @ModelAttribute LoginInfo loginInfo, String jisaCD){
		Map<String, Object> dataJisaInfo = jisaManageService.getJisaView(jisaCD, "00000");
		String chk = (dataJisaInfo == null)? "N" : "Y";
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("jisamanage");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaInfo", dataJisaInfo);
		model.addAttribute("chk", chk);
		return "jisamanage/jisaView";
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/{jisaCD:[0-9]{2}}"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getSubjManagePage(Model model, @PathVariable String jisaCD){
		log.debug("Getting Subject Manage Page : jisaCD = {}", jisaCD);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("jisamanage");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaCD", jisaCD);
		return "jisamanage/subj";
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/{jisaCD:[0-9]{2}}/{pageNum:[0-9]{1}}"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getSubjInfos(@PathVariable String jisaCD, @PathVariable int pageNum){
		log.debug("Getting Subject Manage Page : jisaCD = {}", jisaCD);
		PageUtil pageInfo = new PageUtil(pageNum, jisaManageService.getJisaSubjInfosCount(jisaCD), 5, 10);
		List<SubjInfo> subjInfos = jisaManageService.getJisaSubjInfos(jisaCD, pageInfo.getStartRow(), pageInfo.getEndRow());
		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("subjInfos", subjInfos);
		return map;
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/{subj:^[A-Za-z]{2}}"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getSubjInfos(String jisaCD, @PathVariable String subj){
		log.debug("Getting Subject Manage Page : jisaCD = {}", jisaCD);
		SubjInfo subjInfo = jisaManageService.getSubjInfo(jisaCD, subj);
		Map<String, Object> map = new HashMap<>();
		map.put("subjInfo", subjInfo);
		return map;
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj"}, method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String addSubjInfo(SubjInfo subjInfo, HttpServletRequest request){
		log.debug("Adding Subject : subjInfo = {}", subjInfo.toString());
		String workId = CommonUtils.getWorkId(request);
		jisaManageService.addSubjInfo(subjInfo, workId);
		jisaManageService.addCodeDtl(subjInfo, workId);
		return msa.getMessage("jisamanage.subj.regist.success");
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/update"}, method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String setSubjInfo(String beforeSubj, SubjInfo subjInfo, HttpServletRequest request){
		log.debug("Updating Subject : subjInfo = {}", subjInfo.toString());
		String workId = CommonUtils.getWorkId(request);
		jisaManageService.setSubjInfo(subjInfo, workId, beforeSubj);
		jisaManageService.setSubjInfoToCodeDtl(subjInfo, workId, beforeSubj);
		if(subjInfo.getDeptCnt() > 0 && !"".equals(subjInfo.getStopDate())){
			jisaManageService.setDeptSubjInfo(subjInfo.getSubj(), subjInfo.getJisaCD());
		}
		return msa.getMessage("jisamanage.subj.update.success");
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/updatestopdate"}, method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String setSubjInfo(String subj, String jisaCD, String stopDate, int deptCnt, HttpServletRequest request){
		log.debug("deptCnt : {}", deptCnt);
		String workId = CommonUtils.getWorkId(request);
		jisaManageService.setSubjInfoStopDate(subj, jisaCD, stopDate, workId);
		if(deptCnt > 0 && !"".equals(stopDate)){
			jisaManageService.setDeptSubjInfo(subj, jisaCD);
		}
		return msa.getMessage("jisamanage.subj.update.success");
	}
	
	@RequestMapping(value={"/ma/jisamanage/subj/delete"}, method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String removeSubjInfo(String subj, String jisaCD, int deptCnt){
		jisaManageService.removeSubjInfo(subj, jisaCD);
		jisaManageService.removeSubjInfoToCodeDtl(subj, jisaCD);
		if(deptCnt > 0){
			jisaManageService.setDeptSubjInfo(subj, jisaCD);
		}
		return msa.getMessage("jisamanage.subj.delete.success");
	}

	@RequestMapping(value={"/ma/jisamanage/tuition/{jisaCD:[0-9]{2}}"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getTuitionManagePage(Model model, @PathVariable String jisaCD){
		log.debug("Getting Tuition Manage Page : jisaCD = {}", jisaCD);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("tuitionManage");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("jisaCD", jisaCD);
		return "jisamanage/tuition";
	}
	
	@RequestMapping(value={"/ma/jisamanage/tuition/{jisaCD:[0-9]{2}}/list"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getTuitionInfos(@PathVariable String jisaCD){
		log.debug("Getting Subject Manage Page : jisaCD = {}", jisaCD);
		List<SubjTuitionInfo> tuitionInfos = jisaManageService.getJisaTuitionInfos(jisaCD);
		Map<String, Object> map = new HashMap<>();
		map.put("tuitionInfos", tuitionInfos);
		return map;
	}
	
	@RequestMapping(value={"/ma/jisamanage/tuition/edit"}, method = {RequestMethod.GET, RequestMethod.HEAD}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> getTuitionInfo(String jisaCD, String deptType, String feeType){
		log.debug("Getting Subject Manage Page : jisaCD = {}", jisaCD);
		SubjTuitionInfo tuitionInfo = jisaManageService.getJisaTuitionInfo(jisaCD, deptType, feeType);
		List<CodeDtl> feeUnits = commonService.getCodeDtls("0303", jisaCD, 1, "Y");
		Map<String, Object> map = new HashMap<>();
		map.put("tuitionInfo", tuitionInfo);
		map.put("feeUnits", feeUnits);
		return map;
	}
	@RequestMapping(value={"/ma/jisamanage/tuition"}, method = {RequestMethod.POST}, produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String setTuitionInfo(SubjTuitionInfo tuition, HttpServletRequest request){
		log.debug("Getting Subject Manage Page : tuition = {}", tuition);
		String workId = CommonUtils.getWorkId(request);
		jisaManageService.setJisaTuitionInfo(tuition, workId);
		return msa.getMessage("jisamanage.tuition.edit.success");
	}
	
	
	
	
	/**
	 * 본사에서 지사로 로그인 할 경우 로그인 처리
	 * 1. 현재 쿠키(AUTHId, AUTHKey)를 다른 쿠키(BmsAUTHId, BmsAUTHKey)에 백업
	 * 2. 로그인 하고자 하는 지사 정보를 AUTHId, AUTHKey로 쿠키 값 변경
	 * 3. Security Context에 위치한 Authentication을 제거
	 * 4. Redirect 요청시 파라미터가 붙지 않도록 model clear
	 * @param memberId 로그인 하고자 하는 지사 아이디
	 * @param AuthId 현재 인증 정보를 담고 있는 쿠키의 AUTHId 값
	 * @param AuthKey 현재 인증 정보를 담고 있는 쿠키의 AUTHKey 값
	 * @param request
	 * @param response
	 * @param model
	 * @return 지사 계층으로 Redirect
	 */
	@RequestMapping(value="/ma/jisamanage/login",method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getJisaLogin(String memberId, 
			@CookieValue(value="AUTHId") String AuthId, 
			@CookieValue(value="AUTHKey") String AuthKey,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		log.debug("BMS : {} ===> JA : {} Login", AuthId, memberId);
		jisaManageService.addBackupCookies(AuthId, AuthKey, response);
		jisaManageService.addJACookies(memberId, response);
		HttpSessionSecurityContextRepository hsscr = new HttpSessionSecurityContextRepository();
		HttpRequestResponseHolder hrrh = new HttpRequestResponseHolder(request, response);
		hsscr.loadContext(hrrh).setAuthentication(null);//기존 Authentication에 저장된 객체 제거
		model.asMap().clear();//ModelAttribute parameter 제거
		return "redirect:"+hongkongUrl+"/ja";
	}
	
}
