package com.jeiglobal.hk.repository.community;

import java.util.*;

import com.jeiglobal.hk.domain.community.*;
import com.jeiglobal.hk.repository.common.*;

/**
 * 
 * 클래스명 : AnnouncementRepository.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Mysql 커뮤니티 -> 알림 관련 Repository
 * src/main/resource/mapper/community/AnnouncementRepository.xml
 */
@PrimaryRepositoryAnnoInterface
public interface AnnouncementRepository {

	public int findTotalArticleCount(Map<String, Object> paramMap);

	public List<Announcement> findAnnouncements(Map<String, Object> paramMap);

	public Announcement findAnnouncement(int idx);

	public void updateAnnouncementReadCount(int idx);

	public void insertAnnouncement(Announcement announcement);

	public int deleteAnnouncement(int idx);

	public int updateAnnouncement(Map<String, Object> paramMap);

	public void insertAttachFile(AttachFile attachFile);

	public List<AttachFile> findAttachFiles(int idx);

	public int updateFileDownloadCount(int fileIdx);

	public AttachFile findAttachFile(int fileIdx);

	public int deleteAttachFileByFileIdx(int fileIdx);

	public void insertComment(Map<String, Object> paramMap);

	public List<Comment> findCommentsByIdx(int idx);

	public int deleteCommentByCommentIdx(int commentIdx);

	public void deleteCommentByBoardIdx(int idx);
	
}
