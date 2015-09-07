package com.jeiglobal.hk.domain.menu;

import java.sql.Timestamp;

import lombok.*;

/**
 * 
 * 클래스명 : GlobalMenu.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 메뉴 처리에 필요한 객체
 */
@Data
public class GlobalMenu {

	private Long mIdx;
	private Long mParentIdx;
	private String mJisaCD;
	private String mEmpKeyLvCD;
	private String mDepMngCD;
	private String mMenuName;
	private String mMenuLink;
	private String mAntPattern;
	private String mCon;
	private int mSort;
	private String mMenuCode;
	private String mHasChildren;
	private int mDepth;
	private Long m1;
	private Long m2;
	private Long m3;
	private Long m4;
	private Long m5;
	private Long m6;
	private Long m7;
	private Long m8;
	private Long m9;
	private Timestamp mRegDate;
	private Timestamp mUpdate;
	private String mUseState;
}
