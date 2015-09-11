package com.jeiglobal.domain.auth;

import java.sql.*;

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
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
	private String userId;
	private Long mIdx;
	private String userName;
	private String jisaCD;
	private String deptCD;
	private String deptName;
	private Timestamp regDate;
	private String regID;
	private Timestamp updDate;
	private String updID;
}
