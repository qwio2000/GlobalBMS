package com.jeiglobal.domain.lead;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : CenterLeadsNote.java
 *
 * 작성일 : 2015. 11. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * CenterLeadsNote
 */
@Data
public class CenterLeadsNote {
	private int idx;
	private int clIdx;
	private String noteDate;
	private String notes;
	private String jisaCD;
	private Date regDate;
	private String regID;
}
