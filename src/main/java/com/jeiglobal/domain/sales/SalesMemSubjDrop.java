/**
 * 
 */
package com.jeiglobal.domain.sales;

import lombok.Getter;

/**
 * 클래스명 : SalesMemSubjDrop.java
 *
 * 작성일 : 2015. 11. 26.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명 : 실적 > 퇴회 내역
 */
@Getter
public class SalesMemSubjDrop {
	private String mgYYMM;
	private String dropYMD;	
	private String memKey;
	private String subj;
	private String memName;
	private String dropReason;
	private String notes;
	private String empKey;
	private String deptCD;
	private String jisaCD;
	private String stateName;
	private String deptName;
	private String mgYY;
	private String mgMM;	
	private String dropReasonName;
	
}
