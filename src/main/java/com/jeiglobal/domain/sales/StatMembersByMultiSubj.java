/**
 * 
 */
package com.jeiglobal.domain.sales;

import lombok.Getter;

/**
 * 클래스명 : StatMembersByMultiSubj.java
 *
 * 작성일 : 2015. 11. 24.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명 : 복수과목 회원현황
 */
@Getter
public class StatMembersByMultiSubj {
	private String mgYYMM;
	private String jisaCD;
	private String deptCD;
	private String subj;
	private int multi1;
	private int multi2;
	private int multi3;
	private int multi4;
	private int multi5;
	private int multi6;
	private double multi1Rate;
	private double multi2Rate;
	private double multi3Rate;
	private double multi4Rate;
	private double multi5Rate;
	private double multi6Rate;
	private String stateName;
	private String deptName;
	private String mgYY;
	private String mgMM;
}
