package com.jeiglobal.domain;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : CodeMst.java
 *
 * 작성일 : 2015. 11. 13.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Data
public class CodeMst {
	
	private String mstCD;
	private String mstCDName;
	private String mstCDDesc;
	private String useYN;
	private Date regDate;
	private String regID;
	private Date updDate;
	private String updID;
}
