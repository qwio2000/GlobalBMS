/**
 * 
 */
package com.jeiglobal.domain.sales;

import lombok.Getter;

/**
 * 클래스명 : MemSubjMstKeep.java
 *
 * 작성일 : 2015. 11. 24.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명 : 마감 기준 유지 과목 정보
 */
@Getter
public class MemSubjMstKeep {
	private String mgYYMM;
	private String jisaCD;
	private String deptCD;
	private String memKey;
	private String memName;
	private String subj;
	private String yoil;
	private int studyNum;
	private int bookNum;
	private String wbGrade;
	private int registFstYMD;
	private int registFnlYMD;
	private int dropFnlYMD;
	private int expireYMD;
	private int studyMonth;
	private int multiSubjCnt;
	private String multiSubj;
	private String stateName;
	private String deptName;
	private String mgYY;
	private String mgMM;
	private String ageName;
}
