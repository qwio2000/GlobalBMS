package com.jeiglobal.hk.domain.community;

import java.util.*;

import lombok.*;

/**
 * 클래스명 : Announcement.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Announcement 게시판 정보
 */
@Data
public class Announcement {
	private int boardIdx;
	private String memberId;
	private String boardSubject;
	private String boardContent;
	private String boardReadCount;
	private String boardRegDate;
	private List<AttachFile> attachFiles;
}