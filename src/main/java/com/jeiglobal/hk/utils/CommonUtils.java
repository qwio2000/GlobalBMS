package com.jeiglobal.hk.utils;

import java.util.*;


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
	 * @param originalFilename
	 * @return String
	 */
	public static String getExtension(String originalFilename){
		return originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	}
	
	/**
	 * 서버에 저장할 고유한 파일명 생성
	 * @param originalFilename
	 * @return String
	 */
	public static String getFileName(String originalFilename) {
		return UUID.randomUUID().toString() + "." + getExtension(originalFilename);
	}
}
