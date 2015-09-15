package com.jeiglobal.domain;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : CodeDtl.java
 *
 * 작성일 : 2015. 9. 15.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * globalbiz.CodeDtl
 */
@Data
public class CodeDtl {
	private String mstCD;
	private String dtlCD;
	private String jisaCD;
	private String dtlCDNM;
	private String dtlCDNMK;
	private String dtlCDNME;
	private String dtlCDNMC;
	private String dtlCDDesc;
	private String sortVal1;
	private String sortVal2;
	private String sortVal3;
	private String useYN;
	private Date regDate;
	private String regID;
	private Date updDate;
	private String updID;
}
