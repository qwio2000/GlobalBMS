package com.jeiglobal.utils;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * 클래스명 : CommonUtils.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 공통 유틸 클래스
 */
public class CommonUtils {

	/**
	 * 파일 확장자만 추출
	 * 
	 * @param originalFilename
	 * @return String
	 */
	public static String getExtension(String originalFilename) {
		return originalFilename
				.substring(originalFilename.lastIndexOf(".") + 1);
	}

	/**
	 * 서버에 저장할 고유한 파일명 생성
	 * 
	 * @param originalFilename
	 * @return String
	 */
	public static String getFileName(String originalFilename) {
		return UUID.randomUUID().toString() + "."
				+ getExtension(originalFilename);
	}

	/**
	 * @param authId
	 * @param authKey
	 * @param response
	 *            void
	 */
	public static void addCookie(String cookieName, String cookieValue,
			String cookieDomain, HttpServletResponse response) {
		try {
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(
					cookieValue, "utf-8"));
			cookie.setPath("/");
			if (!"localhost".contains(cookieDomain)) {// localhost는 적용 안됨,
				cookie.setDomain(cookieDomain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param CookieName
	 * @param response
	 *            void
	 */
	public static void removeCookie(String cookieName, String cookieDomain,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		if (!"localhost".contains(cookieDomain)) {// localhost는 적용 안됨,
			cookie.setDomain(cookieDomain);
		}
		response.addCookie(cookie);
	}
	
	public static List<String> weekCalendar(String day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		if(!"".equals(day)){
			cal.setTime(sdf.parse(day));
		}
		List<String> rtnList = new ArrayList<String>();
		rtnList.add(String.valueOf(cal.get(Calendar.WEEK_OF_MONTH)));
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		rtnList.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, 6);
		rtnList.add(sdf.format(cal.getTime()));
		return rtnList;
	}
}
