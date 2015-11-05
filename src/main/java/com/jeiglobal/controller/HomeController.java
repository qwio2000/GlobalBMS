package com.jeiglobal.controller;

import java.io.*;
import java.net.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.service.auth.*;
import com.jeiglobal.utils.*;

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
public class HomeController {

	@Autowired
	private MessageSourceAccessor messageSource;// message 사용
	
	@Autowired
	private AuthoritiesService service;

	@RequestMapping(value={"/","/login"})
	public String getLoginPage(Model model, 
			@RequestParam(value="error", required=false) String error,
			@RequestParam(value="returl", required=false) String returl) throws UnsupportedEncodingException {
		if(error != null) {
    		model.addAttribute("error",error);
    		log.debug("Getting login page, error={}", error);
    	}else{
    		log.debug("Getting Login Page");
    	}
		if(returl != null){
			model.addAttribute("returl", URLDecoder.decode(returl, "UTF-8"));
			log.debug("### Return Url : {}", URLDecoder.decode(returl, "UTF-8"));
		}
		return "login";
	}
	
	/**
	 * favicon.ico 요청 시 No Mapping Log 방지하기 위해 만듬 
	 */
	@RequestMapping("favicon.ico")
    @ResponseBody
    void favicon() {}
	
	@RequestMapping(value={"/ma/records","/ma/inventory","/ma/leads","/ma/community","/ma/membersearch","/ma/manageproduct"})
	public String getLayoutPage(){
		log.debug("Getting BMS Layout Page");
		return "layout";
	}
	
}
