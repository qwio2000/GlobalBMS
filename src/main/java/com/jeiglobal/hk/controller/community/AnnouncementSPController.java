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
 * 클래스명 : AnnouncementSPController.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * [Community -> Announcement] Controller
 */
@Controller
@RequestMapping(value="/community/announcementsSP")
public class AnnouncementSPController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementSPController.class);
	
	//페이징 범위
	private static final int PAGE_BLOCK_SIZE = 10;
	//한 페이지에 출력할 게시물 개수
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private MessageSourceAccessor messageSource;// message 사용
	
	@Autowired
	private AnnouncementSPService announcementSPService;
	
	//RequestMethod.HEAD : GET 요청에서 컨텐츠(자원)는 제외하고 헤더(Meta 정보)만 가져옴.
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementsPage(Model model, @RequestParam(defaultValue="1") int pageNum){
		LOGGER.debug("Getting Notices List Page");
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcementSP");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("pageNum", pageNum);
		return "community/announcementSP/list";
	}
	
	@RequestMapping(value="/page/{pageNum:[0-9]+}",method = {RequestMethod.GET, RequestMethod.HEAD}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> getAnnouncementsListJson(Model model, 
			@PathVariable int pageNum,
			@RequestParam(value="searchField", required=false) String searchField,
			@RequestParam(value="searchValue", required=false) String searchValue){
		LOGGER.debug("Getting Notices List Articles searchField : {}, searchValue : {}", searchField, searchValue);
		int totalRowCnt = announcementSPService.getArticleCnt(searchField, searchValue);
		PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, PAGE_SIZE, PAGE_BLOCK_SIZE);
		
		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("articles",announcementSPService.getArticles(pageUtil.getStartRow(),pageUtil.getEndRow(), searchField, searchValue));
		return map;
	}
	
	@RequestMapping(value="/{idx:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementPage(Model model,
			@PathVariable int idx,
			@RequestParam(defaultValue="1") int pageNum){
		LOGGER.debug("Getting Announcement Content Page, Article No : {} ", idx);
		Announcement article = announcementSPService.getAnnouncementByIdx(idx);
		List<Comment> comments = announcementSPService.getComments(idx);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcementSP");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("article", article);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("comments", comments);
		return "community/announcementSP/view";
	}
	
	@RequestMapping(value="/new", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementWritePage(Model model){
		LOGGER.debug("Getting Announcement Write Page");
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcementSP");
		model.addAttribute("headerScript", headerScript);
		return "community/announcementSP/write";
	}

	@RequestMapping(method = {RequestMethod.POST})
	public String addAnnouncement(Model model, 
			Announcement announcement, 
			MultipartHttpServletRequest mreq,
			@ModelAttribute LoginInfo loginInfo,
			Locale locale) throws Exception{
		List<MultipartFile> mf = mreq.getFiles("attachFile");
		LOGGER.debug("Adding Announcement : {}", announcement);
		int addIdx = announcementSPService.addAnnouncement(announcement, mf, loginInfo);
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
		model.addAttribute("url", "/community/announcementsSP/"+addIdx);
		return "common/alertAndRedirect";
	}
	
	@RequestMapping(value="/{idx:[0-9]+}/edit",method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getAnnouncementEditForm(Model model,
			@PathVariable int idx) {
		LOGGER.debug("Getting Announcement Edit Form : idx = {}", idx);
		Announcement announcement = announcementSPService.getAnnouncementByIdx(idx);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("announcementSP");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("article", announcement);
		return "community/announcementSP/write";
	}
	
	@RequestMapping(value="/{idx:[0-9]+}",method = {RequestMethod.DELETE}, produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, Object> deleteAnnouncementJson(
			@PathVariable int idx, 
			Locale locale) {
		LOGGER.debug("Deleting Announcement : idx = {}", idx);
		announcementSPService.removeAnnouncementByIdx(idx);
		Object[] MessageArgs = {"삭제"};
		LOGGER.info("announcement Delete Success : idx = {}", idx);
		String alertMsg = messageSource.getMessage("Community.Announcement.Success", MessageArgs, locale);
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
		announcementSPService.setAnnouncementByIdx(idx, announcement, mf);
		Object[] MessageArgs = {"수정"};
		LOGGER.info("announcement Update Success : boardIdx = {}", idx);
		String alertMsg = messageSource.getMessage("Community.Announcement.Success", MessageArgs, locale);
		model.addAttribute("message", alertMsg);
		model.addAttribute("url", "/community/announcementsSP/"+idx);
		return "common/alertAndRedirect";
	}
	
	/**
	 * 파일 다운로드 처리(BeanNameViewResolver)
	 * @param idx
	 * @param fileIdx
	 * @param fileName
	 * @param fileOriginalName
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/{idx:[0-9]+}/{fileIdx:[0-9]+}", method = {RequestMethod.GET, RequestMethod.HEAD})
	public ModelAndView attachFileDownload(
			@PathVariable int idx,
			@PathVariable int fileIdx, 
			String fileName, 
			String fileOriginalName){
		LOGGER.debug("fileDownload : {}", fileOriginalName);
		announcementSPService.setFileDownloadCount(fileIdx);
		LOGGER.debug("fileDownloadCount Update Success");
		File downloadFile = announcementSPService.getDownloadFile(fileName);
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
		announcementSPService.removeAttachFileByFileIdx(fileIdx);
		Object[] MessageArgs = {"삭제"};
		LOGGER.info("announcement Delete Success : idx = {}", idx);
		String alertMsg = messageSource.getMessage("Community.Announcement.AttachFileSuccess", MessageArgs, locale);
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
		announcementSPService.addComment(idx, commentContent, loginInfo.getMemberId());
		String alertMsg = "";
		Object[] MessageArgs = {"등록"};
		LOGGER.info("Comment Insert Success : boardIdx = {}", idx);
		alertMsg = messageSource.getMessage("Community.Announcement.CommentSuccess", MessageArgs, locale);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", alertMsg);
		map.put("comments", announcementSPService.getComments(idx));
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
		announcementSPService.removeCommentByCommentIdx(commentIdx);
		Object[] MessageArgs = {"삭제"};
		LOGGER.info("Comment Delete Success : commentIdx = {}", commentIdx);
		String alertMsg = messageSource.getMessage("Community.Announcement.CommentSuccess", MessageArgs, locale);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comments", announcementSPService.getComments(idx));
		map.put("msg", alertMsg);
		map.put("loginInfo", loginInfo);
		return map;
	}
}
