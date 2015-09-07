package com.jeiglobal.hk.utils;

import java.util.*;

import org.springframework.context.*;
/**
 * 클래스명 : MessageSourceAccesor.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * properties에 저장된 메시지를 읽어오는 역할을 하는 Util 클래스
 * 
 */
public class MessageSourceAccessor {

	private MessageSource messageSource;

	public MessageSourceAccessor(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, null, "No Message Available", locale);
	}

	public String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, "No Message Available", locale);
	}

	public String getMessage(MessageSourceResolvable resolvable, Locale locale) {
		try {
			return messageSource.getMessage(resolvable, locale);
		} catch (NoSuchMessageException e) {
			return "No Message Available";
		}
	}

}
