package com.jeiglobal.controller.jisaLogin;

import java.util.*;

import javax.servlet.http.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.web.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.service.jisaLogin.*;

/**
 * 
 * 클래스명 : HomeController.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * HomeController
 */
@Slf4j
@Controller
public class JisaLoginController {

	@Autowired
	private JisaLoginService jisaLoginService;
	
	@Value("${serverurl.hongkong}")
	private String hongkongUrl;
	
	@RequestMapping(value="/ma/jisalogin")
	public String getJisaLoginPage(Model model){
		List<Map<String, Object>> jisaLogins = jisaLoginService.getJisaLogins();
		log.debug("Getting Jisa Login Page");
		model.addAttribute("jisaLogins", jisaLogins);
		return "jisaLogin/index";
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
	@RequestMapping(value="/ma/jisalogin/login",method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getJisaLogin(String memberId, 
			@CookieValue(value="AUTHId") String AuthId, 
			@CookieValue(value="AUTHKey") String AuthKey,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		log.debug("BMS : {} ===> JA : {} Login", AuthId, memberId);
		jisaLoginService.addBackupCookies(AuthId, AuthKey, response);
		jisaLoginService.addJACookies(memberId, response);
		HttpSessionSecurityContextRepository hsscr = new HttpSessionSecurityContextRepository();
		HttpRequestResponseHolder hrrh = new HttpRequestResponseHolder(request, response);
		hsscr.loadContext(hrrh).setAuthentication(null);//기존 Authentication에 저장된 객체 제거
		model.asMap().clear();//ModelAttribute parameter 제거
		return "redirect:"+hongkongUrl+"/ja";
	}
	
}
