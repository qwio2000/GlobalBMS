package com.jeiglobal.hk.service.community;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.domain.community.*;
import com.jeiglobal.hk.repository.community.*;
import com.jeiglobal.hk.utils.*;
/**
 * 
 * 클래스명 : AnnouncementService.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 알림에 관련된 비즈니스 로직을 처리하는 서비스
 */
@Service
public class AnnouncementSPService {
	
	@Autowired
	private AnnouncementSPRepository announcementSPRepository;
	
	//application.yml 파일에 저장된 프로퍼티 값
	@Value("${uploadpath.announcements}")
	private String uploadPath;
	
	/**
	 * 전체 게시물 개수
	 * @param searchField
	 * @param searchValue
	 * @return int
	 */
	public int getArticleCnt(String searchField, String searchValue) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchField", searchField);
		paramMap.put("searchValue", searchValue);
		return announcementSPRepository.findTotalArticleCount(paramMap);
	}
	
	/**
	 * 전체 게시물 리스트
	 * @param startRow : 시작 행 번호
	 * @param endRow : 시작 행 번호부터 가져올 개수
	 * @param searchField
	 * @param searchValue
	 * @return List<Announcement>
	 */
	public List<Announcement> getArticles(int startRow, int endRow, String searchField, String searchValue) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("endRow", endRow);
		paramMap.put("searchField", searchField);
		paramMap.put("searchValue", searchValue);
		return announcementSPRepository.findAnnouncements(paramMap);
	}
	
	public Announcement getAnnouncementByIdx(int idx) {
		announcementSPRepository.updateAnnouncementReadCount(idx);
		Announcement article = announcementSPRepository.findAnnouncement(idx);
		article.setAttachFiles(getAttachFiles(idx));
		//줄바꿈 처리
		article.setBoardContent(article.getBoardContent().replaceAll("\r\n", "<br/>"));
		return article;
	}

	public int addAnnouncement(Announcement announcement, List<MultipartFile> mf, LoginInfo loginInfo) throws IllegalStateException, IOException {
		announcement.setMemberId(loginInfo.getMemberId());
		int lastInsertIdx = announcementSPRepository.insertAnnouncement(announcement);
		insertAttachFiles(mf, lastInsertIdx);
		return lastInsertIdx;
	}
	
	/**
	 * 첨부파일 업로드
	 * @param mf
	 * @param boardIdx
	 * @throws IllegalStateException
	 * @throws IOException void
	 */
	private void insertAttachFiles(List<MultipartFile> mf, int boardIdx) throws IllegalStateException, IOException {
		File dir = new File(uploadPath);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		for (int i = 0; i < mf.size(); i++) {
			if(!"".equals(mf.get(i).getOriginalFilename())){
				AttachFile attachFile = new AttachFile(0,
						boardIdx,
						mf.get(i).getOriginalFilename(), 
						CommonUtils.getFileName(mf.get(i).getOriginalFilename()),
						mf.get(i).getSize(), 
						uploadPath,
						CommonUtils.getExtension(mf.get(i).getOriginalFilename()), 
						0);
				String savePath = uploadPath + File.separator + attachFile.getFileName();
				mf.get(i).transferTo(new File(savePath));
				//DB에 파일정보 저장
				announcementSPRepository.insertAttachFile(attachFile);
			}
		}
	}
	
	public void removeAnnouncementByIdx(int idx) {
		announcementSPRepository.deleteCommentByBoardIdx(idx);
		announcementSPRepository.deleteAnnouncement(idx);
	}

	public void setAnnouncementByIdx(int idx, Announcement announcement, List<MultipartFile> mf) throws IllegalStateException, IOException {
		insertAttachFiles(mf, idx);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idx", idx);
		paramMap.put("announcement", announcement);
		announcementSPRepository.updateAnnouncement(paramMap);
	}

	public List<AttachFile> getAttachFiles(int idx) {
		return announcementSPRepository.findAttachFiles(idx);
	}

	public void setFileDownloadCount(int fileIdx) {
		announcementSPRepository.updateFileDownloadCount(fileIdx);
	}
	
	/**
	 * 다운로드 할 파일 객체 생성
	 * @param fileName
	 * @return File
	 */
	public File getDownloadFile(String fileName) {
		return new File(uploadPath + File.separator + fileName);
	}

	public void removeAttachFileByFileIdx(int fileIdx) {
		AttachFile attachFile = announcementSPRepository.findAttachFile(fileIdx);
		deleteAttachFile(attachFile);
		announcementSPRepository.deleteAttachFileByFileIdx(fileIdx);
	}
	
	/**
	 * 첨부파일 삭제 처리
	 * @param attachFile
	 */
	private void deleteAttachFile(AttachFile attachFile) {
		File realFile = new File(attachFile.getFileUrl()+File.separator+attachFile.getFileName());
		if(realFile.exists()){
			realFile.delete();
		}
	}

	public void addComment(int idx, String commentContent, String memberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idx", idx);
		paramMap.put("content", commentContent);
		paramMap.put("memberId", memberId);
		announcementSPRepository.insertComment(paramMap);
	}

	public List<Comment> getComments(int idx) {
		return announcementSPRepository.findCommentsByIdx(idx);
	}

	public void removeCommentByCommentIdx(int commentIdx) {
		announcementSPRepository.deleteCommentByCommentIdx(commentIdx);
	}
}
