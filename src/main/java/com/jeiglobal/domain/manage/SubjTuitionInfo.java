package com.jeiglobal.domain.manage;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : SubjTuitionInfo.java
 *
 * 작성일 : 2015. 11. 10.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Data
public class SubjTuitionInfo {
	private String jisaCD;
	private String deptType;
	private String memType;
	private String feeType;
	private String subj;
	private int bookNum;
	private int registFee;
	private int monthFee;
	private int sectionFee1;
	private int sectionFee2;
	private int sectionFee3;
	private int sectionFee4;
	private String feeUnit;
	private Date regDate;
	private String regID;
	private Date updDate;
	private String updID;
}
