package com.jeiglobal.domain.member;

import lombok.*;

/**
 * 
 * 클래스명 : MemberAnalysisDto.java
 *
 * 작성일 : 2015. 11. 2.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
public class MemberDto {
	
	@Data
	public static class MemberSearchInfo{
		private String kind;
		private String type;
		private String memKey;
		private String memName;
		private String birthYM;
	}
	
	@Data
	public static class MemberSearchResult{
		private String memKey;
		private String subj;
		private String statusCD;
		private String statusCDNM;
		private String mFstName;
		private String mLstName;
		private String mBirthDay;
		private String registFnlYMD;
		private String dropFnlYMD;
		private String jisaCD;
		private String jisaCDNM;
		private String gPhone;
		private String addr;
	}
}
	
