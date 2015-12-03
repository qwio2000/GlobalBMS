package com.jeiglobal.common;

import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.utils.*;
/**
 * 
 * 클래스명 : CommonControllerAdvice.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 컨트롤러 처리 전 ModelAttribute를 통해 정보를 이용할 수 있도록 설정
 */
@ControllerAdvice
public class CommonControllerAdvice {
	
	//회원 정보
	@ModelAttribute("loginInfo")
	public LoginInfo getLoginInfo(Authentication authentication){
		return (authentication == null) ? null : (LoginInfo) authentication.getPrincipal();
	}
	
	//헤더 부분의 MainWeek
	@ModelAttribute("mainWeek")
	public List<String> getMainWeek() throws ParseException{
		return CommonUtils.weekCalendar("");
	}
	
	@Value("${filePath.img}")
	private String imgPath;
	
	@Value("${filePath.css}")
	private String cssPath;
	
	@Value("${filePath.js}")
	private String jsPath;
	
	
	@ModelAttribute("imgPath")
	public String getImgPath(){return imgPath;}
	@ModelAttribute("cssPath")
	public String getCssPath(){return cssPath;}
	@ModelAttribute("jsPath")
	public String getJsPath(){return jsPath;}

}
