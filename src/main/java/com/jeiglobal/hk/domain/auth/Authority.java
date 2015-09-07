package com.jeiglobal.hk.domain.auth;

import lombok.*;
/**
 * 
 * 클래스명 : Authority.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그인 사용자 권한 정보
 */
@Data
public class Authority {
	private String memberId;
	private String authority;
}
