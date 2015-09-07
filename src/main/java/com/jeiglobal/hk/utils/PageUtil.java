package com.jeiglobal.hk.utils;

import lombok.*;

/**
 * 
 * 클래스명 : PageUtil.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 페이징 처리 유틸 클래스
 */
@Data
@NoArgsConstructor
@ToString
public class PageUtil {
	
	private int pageNum;
	private int startRow;
	private int endRow;
	private int totalPageCnt;
	private int startPageNum;
	private int endPageNum;
	private int rowBlockSize;
	private int pageBlockSize;
	private int totalRowCnt;
	
	public PageUtil(int pageNum, int totalRowCnt, int rowBlockSize,
			int pageBlockSize) {
		this.pageNum = pageNum;
		this.totalRowCnt = totalRowCnt;
		this.rowBlockSize = rowBlockSize;
		this.pageBlockSize = pageBlockSize;
		//시작번호 구하기
		startRow = (pageNum - 1) * rowBlockSize;
		//끝행번호 구하기
		endRow = rowBlockSize;
		//전체 페이지 개수 구하기
		totalPageCnt = (int)Math.ceil(totalRowCnt/(double)rowBlockSize);
		//시작 페이지 번호 구하기
		startPageNum = (pageNum - 1)/pageBlockSize * pageBlockSize + 1;
		//끝페이지번호 구하기
		endPageNum = startPageNum + pageBlockSize -1;
		if(totalPageCnt < endPageNum){
			endPageNum = totalPageCnt;
		}
	}
	
	
}
