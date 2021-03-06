package com.jeiglobal.common.exception;

import java.util.*;

import javax.servlet.http.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import com.jeiglobal.utils.*;
/**
 * 
 * 클래스명 : ErrorHandler.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Error 발생시 default로 /error 로 redirect 된다
 * 이 /error로 들어온 요청을 처리하는 ErrorHandler
 * ======================현재============================
 * 404 => java/main/resource/template/error/error404.ftl
 * 그 외 => java/main/resource/template/error/error500.ftl
 */
@Slf4j
@Controller
public class ErrorHandler implements ErrorController {

	private final String ERROR_VIEW_PATH = "/error/";
	private String errorPath;
	private ErrorAttributes errorAttributes;
	private MessageSourceAccessor messageSourceAccessor;
		
	@RequestMapping(value = "${error.path:/error}")
	public String error(Model model, HttpServletRequest request, Locale locale) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> body = errorAttributes.getErrorAttributes(requestAttributes, true);
		Iterator<String> keys = body.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			log.debug("Key : {}, Value : {}",key, body.get(key));
		}
		String view = ERROR_VIEW_PATH;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if(body.get("status") != null) {
			try {
				status = HttpStatus.valueOf((Integer) body.get("status"));
			} catch(Exception ignore) { } 
		}
		String message = messageSourceAccessor.getMessage("error." + status, null, locale);
		if(message != null) {
			body.put("message", message);
		}
		log.error("Error Status : {}, Message : {}", status, message);
		model.addAttribute("error", status.getReasonPhrase());
		model.addAttribute("message", message);
		if(HttpStatus.NOT_FOUND.equals(status)){
			view += "error404";
		}else{
			view += "error500";
		}
		return view;
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}
	
	
	@Value("${error.path:/error}")
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}

	@Autowired
	public void setErrorAttributes(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

}
