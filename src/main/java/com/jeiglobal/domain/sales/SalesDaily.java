/**
 * 
 */
package com.jeiglobal.domain.sales;

import lombok.Getter;

/**
 * 클래스명 : SalesDaily.java
 *
 * 작성일 : 2015. 11. 20.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명
 */
@Getter
public class SalesDaily {
	
	private String jisaCD;
	private String deptName;
	private int subjBegin;
	private int subjNew;
	private int subjNewPrev;
	private int subjDrop;
	private int subjNet;
	private int subjEnd;	
	private String salesYMD;
	private String subj;
}
