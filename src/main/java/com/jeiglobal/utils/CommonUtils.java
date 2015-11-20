package com.jeiglobal.utils;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.context.i18n.*;
import org.springframework.web.util.*;


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
	 * 쿠키 추가
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieDomain
	 * @param response
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
	 * 쿠키 삭제
	 * 
	 * @param cookieName
	 * @param cookieDomain
	 * @param response
	 */
	public static void removeCookie(String cookieName, String cookieDomain,
			HttpServletResponse response) {
		if(!"mainPop".equals(cookieName)){
			Cookie cookie = new Cookie(cookieName, "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			if (!"localhost".contains(cookieDomain)) {// localhost는 적용 안됨,
				cookie.setDomain(cookieDomain);
			}
			response.addCookie(cookie);
		}
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

	/**
	 * 이 달의 마지막 날짜 가져오기
	 * @param currentYear
	 * @param currentMonth
	 * @return int
	 */
	public static int getMaxDays(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 현재 사용자의 Id를 가져오기 
	 * 자동로그인으로 들어온 경우 원래 사용자 아이디 가져옴
	 * @param request
	 * @return String
	 */
	public static String getWorkId(HttpServletRequest request){
		String workId = "";
		Cookie cookie = WebUtils.getCookie(request, "BmsAUTHId");
		if(cookie != null){//Bms쿠키가 있는 경우
			workId = cookie.getValue();
		}else{//Bms 없는 경우 지사 쿠키 정보 찾기
			cookie = WebUtils.getCookie(request, "JisaAUTHId");
			if(cookie != null){
				workId = cookie.getValue();
			}else{
				workId = WebUtils.getCookie(request, "AUTHId").getValue();
			}
		}
		return workId;
	}
	
	/**
	 * 오늘 날짜 yyyy-MM-dd 형태로 가져오기
	 * @return String
	 */
	public static String getCurrentYMD(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	/**
	 * 입력 받은 날짜를 yyyy-MM-dd 형태로 가져오기
	 * @param date
	 * @return String
	 */
	public static String getDateForFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * DateFormat 변경하는 함수
	 * @param beforePattern : 변경하기 전 패턴
	 * @param afterPattern : 변경하고자 하는 패턴
	 * @param value : 변경하기 전 값
	 * @return String
	 * @throws ParseException 
	 */
	public static String changeDateFormat(String beforePattern, String afterPattern, String value) throws ParseException{
		SimpleDateFormat beforeSdf = new SimpleDateFormat(beforePattern);
		SimpleDateFormat afterSdf = new SimpleDateFormat(afterPattern, LocaleContextHolder.getLocale());
		Date valueDate = beforeSdf.parse(value);
		return afterSdf.format(valueDate);
	}

	/**
	 * 잔여 주차 가져오기
	 * @param firstManageDate : 첫 관리 방문일로 잔여 주차 가져오기 
	 * @return int
	 * @throws ParseException 
	 */
	public static int getRemainWeekNum(String firstManageDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar end = Calendar.getInstance();
		int endDate = end.getMaximum(Calendar.DAY_OF_MONTH);
		end.setTime(sdf.parse(firstManageDate));
		int manageDay = end.get(Calendar.DATE);
		int week = (manageDay == endDate)? 1 : (int) Math.ceil(((double)(endDate - manageDay)/7));
		week = (week > 4)? 4 : week; // 4주 이상 모두 4로 변경
		return week;
	}	
	
	/**
	 * 문자열 오른쪽 자르는 함수
	 * @param sText : Source 문자열
	 * @param iTextLenth : 자르고자 하는 길이
	 * @return String
	 */
	public static String RightString(String sText, int iTextLenth){
		String sConvertText;

	    if (sText.length() < iTextLenth)
	    {
	        iTextLenth= sText.length();
	    }

	    sConvertText= sText.substring(sText.length() - iTextLenth, sText.length());

	    return sConvertText;
	}
	
	/**
	 * 문자열 오른쪽 자르는 함수
	 * @param sText : Source 문자열
	 * @param iTextLenth : 자르고자 하는 길이
	 * @return String
	 */
	public static String LeftString(String sText, int iTextLenth){
		String sConvertText;

	    if (sText.length() < iTextLenth)
	    {
	        iTextLenth= sText.length();
	    }

	    sConvertText= sText.substring(0, iTextLenth);

	    return sConvertText;
	}
	
	/**
	 * byte 단위로 substring
	 * @param target
	 * @param beginIndex 문자열 자를 시작 인덱스
	 * @param endIndex substr할 바이트 수 , 2-byte문자의 경우 바이트가 부족하면 그 앞 글자까지만 자름
	 * @param bytesForDB 2-byte 문자(한글 등)의 DB에서의 바이트 수
	 * @return String
	 */
	public static String subStrByte(String target, int beginIndex, int endIndex, int bytesForDB){
		if(target == null) return "";
		
		String tmp = target;
		int slen = 0, blen = 0;
		char c;
		
		if(tmp.getBytes().length > endIndex - 1){
			while (blen + 1 < endIndex - 1) {
				c = tmp.charAt(slen);
				blen++;
				slen++;
				
				if(c > 127){
					blen = blen + (bytesForDB - 1);
				}
			}
			tmp = tmp.substring(0, slen);
		}
		return tmp;
	}

	/**
	 * 한 자리 숫자 앞에 0붙여 두자리 만들기
	 * @param month
	 * @return String
	 */
	public static String getMonthAttachZero(int month) {
		if(month >= 10){
			return ""+month;
		}else{
			return "0"+month;
		}
	}

	/**
	 * 현재 날짜 기준으로 년 월 가져오기
	 * @return List<String>
	 */
	public static List<String> getMonthsByOneYearBefore() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM / yyyy");
		List<String> months = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		for (int i = 0; i < 13; i++) {
			months.add(sdf.format(cal.getTime()));
			cal.add(Calendar.MONTH, -1);
		}
		
		return months;
	}
}
