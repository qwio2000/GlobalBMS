package com.jeiglobal.domain.auth;

import lombok.*;
/**
 * 
 * 클래스명 : LoginInfo.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그인 사용자 정보
 */
@AllArgsConstructor
@Getter
@ToString
public class LoginInfo {
	private String userId;
	@Setter
	private String userPasswd;
	private String userFstName;
	private String userLstName;
	private String statusCD;
	private String jisaCD;
	private String deptCD;
	private String deptName;
	private String empKey;
	private String userType;
	private String userLevel;
	private String dutyCD;
	@Setter
	private String encodeCookie;
	private String stateCD;
}
