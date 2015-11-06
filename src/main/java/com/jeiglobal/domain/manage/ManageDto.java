package com.jeiglobal.domain.manage;

import lombok.*;

/**
 * 클래스명 : MemberDto.java
 *
 * 작성일 : 2015. 9. 17.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
public class ManageDto {
	
	@Data
	public static class Users{
		private String userId;
		private String userFstName;
		private String userLstName;
		private String departMent;
		private String statusCD;
		private String statusCDNM;
		private String regDate;
		private String latestLoginDate;
	}
	
	@Data
	public static class User{
		private String userId;
		private String userPasswd;
		private String userFstName;
		private String userLstName;
		private String userLevel; //관리자, 스태프
		private String empKey; //자동생성
		private String dutyCD; //M0
		private String email;
		private String phone;
		private String jisaCD; //00
		private String deptCD; //00000
		private String deptName; //재능교육 본사
		private String title; //공백
		private String departMent; 
		private String statusCD; 
		private String regDate;
		private String regID;
		private String updDate; //NULL
		private String updID; //공백
		private String passUpdDate; //NULL
		private String latestLoginDate; //공백
		private String latestLoginIp; //공백
		private String encodeCookie; //공백
	}
}
	
