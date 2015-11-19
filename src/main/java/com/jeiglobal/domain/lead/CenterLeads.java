package com.jeiglobal.domain.lead;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : CenterLeads.java
 *
 * 작성일 : 2015. 11. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Leads
 */
@Data
public class CenterLeads {
	private int idx;
	private String leadYMD;
	private String contactFstName;
	private String contactLstName;
	private String contactEmail;
	private String partnerFstName;
	private String partnerLstName;
	private String partnerEmail;
	private String phone;
	private String cellPhone;
	private String city;
	private String stateCD;
	private String stateName;
	private String zip;
	private String addr;
	private String country;
	private String howHear;
	private String howHearName;
	private String howHearMore;
	private String areaInterested;
	private String notes;
	private String statusCD;
	private String statusName;
	private String lastContactYMD;
	private String jisaCD;
	private Date regDate;
	private String regID;
	private Date updDate;
	private String updID;
}
