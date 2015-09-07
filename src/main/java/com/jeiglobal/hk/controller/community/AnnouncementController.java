package com.jeiglobal.hk.controller.community;

import java.io.*;
import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.domain.community.*;
import com.jeiglobal.hk.service.community.*;
import com.jeiglobal.hk.utils.*;

/**
 * 
 * 클래스명 : AnnouncementController.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * [Community -> Announcement] Controller
 */
@Controller
@RequestMapping(value="/fa/community/announcements")
public class AnnouncementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);
	
	//페이징 범위
	private static final int PAGE_BLOCK_SIZE = 10;
	//한 페이지에 출력할 게시물 개수
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private MessageSourceAccessor messageSource;// message 사용
	
	@Autowired
	private AnnouncementService announcementService;
	
	//RequestMethod.HEAD : GET 요청에서 컨텐츠(자원)는 제외하고 헤더(Meta 정보)만 가져옴.
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementsPage(Model model, @RequestParam(defaultValue="1") int pageNum){
		LOGGER.debug("Getting Notices List Page");
		//header에 포함할 스크립트 
		//announcement를 추가했기 때문에 /public/js/announcement.js 를 header에 추가
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcement");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("pageNum", pageNum);
		return "community/announcement/list";
	}
	
	@RequestMapping(value="/page/{pageNum:[0-9]+}",method = {RequestMethod.GET, RequestMethod.HEAD}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> getAnnouncementsListJson(Model model, 
			@PathVariable int pageNum,
			@RequestParam(value="searchField", required=false) String searchField,
			@RequestParam(value="searchValue", required=false) String searchValue){
		LOGGER.debug("Getting Notices List Articles searchField : {}, searchValue : {}", searchField, searchValue);
		int totalRowCnt = announcementService.getArticleCnt(searchField, searchValue);
		//페이징 처리에 사용하는 유틸 클래스
		PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, PAGE_SIZE, PAGE_BLOCK_SIZE);
		
		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("articles",announcementService.getArticles(pageUtil.getStartRow(),pageUtil.getEndRow(), searchField, searchValue));
		return map;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementPage(Model model,
			@PathVariable int idx,
			@RequestParam(defaultValue="1") int pageNum){
		LOGGER.debug("Getting Announcement Content Page, Article No : {} ", idx);
		Announcement article = announcementService.getAnnouncementByIdx(idx);
		List<Comment> comments = announcementService.getComments(idx);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcement");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("article", article);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("comments", comments);
		return "community/announcement/view";
	}
	
	@RequestMapping(value="/new", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementWritePage(Model model){
		LOGGER.debug("Getting Announcement Write Page");
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcement");
		model.addAttribute("headerScript", headerScript);
		return "community/announcement/write";
	}

	@RequestMapping(method = {RequestMethod.POST})
	public String addAnnouncement(Model model, 
			Announcement announcement, 
			MultipartHttpServletRequest mreq,
			@ModelAttribute LoginInfo loginInfo,
			Locale locale) throws Exception{
		List<MultipartFile> mf = mreq.getFiles("attachFile");
		LOGGER.debug("Adding Announcement : {}", announcement);
		int addIdx = announcementService.addAnnouncement(announcement, mf, loginInfo);
		String alertMsg = "";
		Object[] MessageArgs = {"등록"};//Message 출력시 사용할 Arguments
		if(addIdx == 0) {
			LOGGER.error("announcement Insert Error : {}", announcement);
			alertMsg = messageSource.getMessage("Community.Announcement.Error", MessageArgs, locale);
		}else{
			LOGGER.info("announcement Insert Success : boardIdx = {}", addIdx);
			alertMsg = messageSource.getMessage("Community.Announcement.Success", MessageArgs, locale);
		}
		model.addAttribute("message", alertMsg);
		model.addAttribute("url", "/fa/community/announcements/"+addIdx);
		return "common/alertAndRedirect";
	}
	
	@RequestMapping(value="/{idx:[0-9]+}/edit",method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementEditForm(Model model,
			@PathVariable int idx) {
		LOGGER.debug("Getting Announcement Edit Form : idx = {}", idx);
		Announcement announcement = announcementService.getAnnouncementByIdx(idx);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcement");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("article", announcement);
		return "community/announcement/write";
	}
	
	@RequestMapping(value="/{idx:[0-9]+}",method = {RequestMethod.DELETE}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> deleteAnnouncementJson(
			@PathVariable int idx, 
			Locale locale) {
		LOGGER.debug("Deleting Announcement : idx = {}", idx);
		int deleteRowCount = announcementService.removeAnnouncementByIdx(idx);
		String alertMsg = "";
		Object[] MessageArgs = {"삭제"};
		if(deleteRowCount > 0) {
			LOGGER.info("announcement Delete Success : idx = {}", idx);
			alertMsg = messageSource.getMessage("Community.Announcement.Success", MessageArgs, locale);
		}else{
			LOGGER.error("announcement Delete Error : idx = {}", idx);
			alertMsg = messageSource.getMessage("Community.Announcement.Error", MessageArgs,locale);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", alertMsg);
		return map;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}",method = {RequestMethod.POST})
	public String setAnnouncement(Model model,
			@PathVariable int idx, 
			Announcement announcement,
			MultipartHttpServletRequest mreq,
			Locale locale) throws Exception {
		LOGGER.debug("Editing Announcement : idx = {}, announcement = {}", idx, announcement);
		List<MultipartFile> mf = mreq.getFiles("attachFile");
		int updateRowCount = announcementService.setAnnouncementByIdx(idx, announcement, mf);
		String alertMsg = "";
		Object[] MessageArgs = {"수정"};
		if(updateRowCount > 0) {
			LOGGER.info("announcement Update Success : boardIdx = {}", idx);
			alertMsg = messageSource.getMessage("Community.Announcement.Success", MessageArgs, locale);
		}else{
			LOGGER.error("announcement Update Error : {}", announcement);
			alertMsg = messageSource.getMessage("Community.Announcement.Error", MessageArgs, locale);
		}
		model.addAttribute("message", alertMsg);
		model.addAttribute("url", "/fa/community/announcements/"+idx);
		return "common/alertAndRedirect";
	}
	
	/**
	 * 파일 다운로드 처리(BeanNameViewResolver)
	 * @param idx
	 * @param fileIdx
	 * @param fileName
	 * @param fileOriginalName
	 * @param request
	 * @return ModelAndView : BeanName
	 */
	@RequestMapping(value = "/{idx:[0-9]+}/{fileIdx:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public ModelAndView attachFileDownload(
			@PathVariable int idx,
			@PathVariable int fileIdx, 
			String fileName, 
			String fileOriginalName){
		LOGGER.debug("fileDownload : {}", fileOriginalName);
		int updateRowCount = announcementService.setFileDownloadCount(fileIdx);
		if(updateRowCount > 0){
			LOGGER.debug("fileDownloadCount Update Success");
		}else{
			LOGGER.error("fileDownloadCount Update Fail");
		}
		File downloadFile = announcementService.getDownloadFile(fileName);
		ModelAndView mav = new ModelAndView("download", "downloadFile", downloadFile);
		mav.addObject("fileOriginalName", fileOriginalName);
		return mav;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}/{fileIdx:[0-9]+}",method = {RequestMethod.DELETE}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> deleteAttachFileJson(
			@PathVariable int idx, 
			@PathVariable int fileIdx, 
			Locale locale) {
		LOGGER.debug("Deleting AttachFile : idx = {}, fileIdx = {}", idx, fileIdx);
		int deleteRowCount = announcementService.removeAttachFileByFileIdx(fileIdx);
		String alertMsg = "";
		Object[] MessageArgs = {"삭제"};
		if(deleteRowCount > 0) {
			LOGGER.info("announcement Delete Success : idx = {}", idx);
			alertMsg = messageSource.getMessage("Community.Announcement.AttachFileSuccess", MessageArgs, locale);
		}else{
			LOGGER.error("announcement Delete Error : idx = {}", idx);
			alertMsg = messageSource.getMessage("Community.Announcement.AttachFileError", MessageArgs,locale);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", alertMsg);
		return map;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}/comment",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> addComment(
			@PathVariable int idx, 
			String commentContent,
			@ModelAttribute LoginInfo loginInfo,
			Locale locale) {
		LOGGER.debug("Adding Comment : idx = {} : User = {}", idx, loginInfo);
		announcementService.addComment(idx, commentContent, loginInfo.getMemberId());
		String alertMsg = "";
		Object[] MessageArgs = {"등록"};
		LOGGER.info("Comment Insert Success : boardIdx = {}", idx);
		alertMsg = messageSource.getMessage("Community.Announcement.CommentSuccess", MessageArgs, locale);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", alertMsg);
		map.put("comments", announcementService.getComments(idx));
		map.put("loginInfo", loginInfo);
		return map;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}/comment/{commentIdx:[0-9]+}",method = {RequestMethod.DELETE}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> deleteComment(
			@PathVariable int idx, 
			@PathVariable int commentIdx, 
			@ModelAttribute LoginInfo loginInfo,
			Locale locale) {
		LOGGER.debug("Deleting Comment : idx = {}, fileIdx = {}", idx, commentIdx);
		int deleteRowCount = announcementService.removeCommentByCommentIdx(commentIdx);
		String alertMsg = "";
		Object[] MessageArgs = {"삭제"};
		if(deleteRowCount > 0) {
			LOGGER.info("Comment Delete Success : commentIdx = {}", commentIdx);
			alertMsg = messageSource.getMessage("Community.Announcement.CommentSuccess", MessageArgs, locale);
		}else{
			LOGGER.error("Comment Delete Error : commentIdx = {}", commentIdx);
			alertMsg = messageSource.getMessage("Community.Announcement.CommentError", MessageArgs,locale);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comments", announcementService.getComments(idx));
		map.put("msg", alertMsg);
		map.put("loginInfo", loginInfo);
		return map;
	}
}
