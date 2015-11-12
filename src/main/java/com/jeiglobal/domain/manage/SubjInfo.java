package com.jeiglobal.domain.manage;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : SubjInfo.java
 *
 * 작성일 : 2015. 11. 9.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Data
public class SubjInfo {
	private String jisaCD;
	private String subj;
	private String subjName;
	private String subjShortName;
	private int studyNum;
	private int bookNum;
	private int monthNum;
	private String digYN;
	private int sortVal;
	private String startDate;
	private String stopDate;
	private String useYN;
	private Date regDate;
	private String regID;
	private Date updDate;
	private String updID;
	private String chk;
	private int deptCnt;
}
