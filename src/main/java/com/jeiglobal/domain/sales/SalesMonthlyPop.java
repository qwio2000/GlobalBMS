/**
 * 
 */
package com.jeiglobal.domain.sales;

import lombok.Getter;

/**
 * 클래스명 : SalesMonthlyPop.java
 *
 * 작성일 : 2015. 11. 23.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명
 */
@Getter
public class SalesMonthlyPop {
	private String jisaCD;	
	private String deptCD;	
	private String subj;
	private String deptName;
	private int subjBegin;
	private int subjNew;
	private int subjNewPrev;
	private int subjDrop;
	private int subjNet;
	private int subjEnd;
	private int subjKM;
	private int subjKK;
	private int subjKG;
	private int subjEM;
	private int subjEE;
	private int subjKP;
	private int subjKS;
	private int subjPS;
	private int subjER;
	private int subjCP;
	private int subjCL;
	private int subjEP;
	private String mgYY;
	private String mgMM;
	private String mgYYMM;
}
