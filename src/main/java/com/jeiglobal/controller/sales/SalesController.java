/**
 * 
 */
package com.jeiglobal.controller.sales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeiglobal.domain.auth.LoginInfo;
import com.jeiglobal.domain.sales.SalesDaily;
import com.jeiglobal.domain.sales.SalesDailyPop;
import com.jeiglobal.domain.sales.SalesDailyPopTot;
import com.jeiglobal.domain.sales.SalesMonthly;
import com.jeiglobal.domain.sales.SalesMonthlyPop;
import com.jeiglobal.domain.sales.SalesMonthlyPopTot;
import com.jeiglobal.service.CommonService;
import com.jeiglobal.service.sales.SalesService;
import com.jeiglobal.utils.MessageSourceAccessor;

import lombok.extern.slf4j.Slf4j;

/**
 * 클래스명 : SalesController.java
 *
 * 작성일 : 2015. 11. 19.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명
 */
@Slf4j
@Controller
public class SalesController {
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SalesService salesService;
	
	/**
	 * 일일실적
	 */
	@RequestMapping(value={"/ma/sales/dailySales"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getDailySales(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYMD, @RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentDay = new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime());

		if("".equals(selYMD)){
			selYMD=currentDay;
		}
		
		List<SalesDaily> dataDailySales = salesService.getDailySales(selYMD, selSubj, "");
		log.debug("Getting 일일실적 Page, dataDailySales : {}", dataDailySales);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("subjList", commonService.getCodeDtls("0002", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("dailySales", dataDailySales);		
		model.addAttribute("selYMD", selYMD);
		model.addAttribute("selSubj", selSubj);
		return "sales/dailySales";
	}	
	@RequestMapping(value={"/ma/sales/dailySalesPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getDailySalesPop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String selYMD, 
			@RequestParam(defaultValue="") String selSubj,@RequestParam(defaultValue="") String deptName) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentDay = new SimpleDateFormat("YYYY-MM-DD").format(cal.getTime());

		if("".equals(selYMD)){
			selYMD=currentDay;
		}

		List<SalesDailyPop> dataDailySalesPop = salesService.getDailySalesPop(jisaCD,selYMD, selSubj, "");
		SalesDailyPopTot dataDailySalesPopTot = salesService.getDailySalesPopTot(jisaCD,selYMD, selSubj, "");
		log.debug("Getting 일일실적>상세 팝업 Page, dataDailySalesPop : {}", dataDailySalesPop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("dailySalesPop", dataDailySalesPop);
		model.addAttribute("dailySalesPopTot", dataDailySalesPopTot);		
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("deptName", deptName);		
		return "sales/dailySalesPop";
	}	
	/**
	 * 
	 * 월별실적
	 */
	@RequestMapping(value={"/ma/sales/monthlySales"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMonthlySales(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		
		List<SalesMonthly> dataMonthlySales = salesService.getMonthlySales(selYY,selMM, selSubj, "");
		log.debug("Getting 월별실적 Page, dataMonthlySales : {}", dataMonthlySales);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("subjList", commonService.getCodeDtls("0002", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("monthList", commonService.getCodeDtls("0207", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("monthlySales", dataMonthlySales);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("currentYear", currentYear);
		return "sales/monthlySales";
	}	
	@RequestMapping(value={"/ma/sales/monthlySalesPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMonthlySalesPop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj,@RequestParam(defaultValue="") String deptName) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		
		if("TT".equals(selSubj)){
			selSubj="";
		}				
		List<SalesMonthlyPop> dataMonthlySalesPop = salesService.getMonthlySalesPop(jisaCD,selYY, selMM, selSubj, "");
		SalesMonthlyPopTot dataMonthlySalesPopTot = salesService.getMonthlySalesPopTot(jisaCD,selYY, selMM, selSubj, "");
		log.debug("Getting 월별실적>상세 팝업 Page, dataMonthlySalesPop : {}", dataMonthlySalesPop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("monthlySalesPop", dataMonthlySalesPop);
		model.addAttribute("monthlySalesPopTot", dataMonthlySalesPopTot);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("deptName", deptName);
		return "sales/monthlySalesPop";
	}	
	/**
	 * 복수과목 현황
	 */
	@RequestMapping(value={"/ma/sales/statMultiSubj"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatMultiSubj(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statMultiSubj";
	}	
	@RequestMapping(value={"/ma/sales/statMultiSubjPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatMultiSubjPop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statMultiSubjPop";
	}
	/**
	 * 연령별 과목수
	 */
	@RequestMapping(value={"/ma/sales/statSubjByAge"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByAge(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statSubjByAge";
	}	
	@RequestMapping(value={"/ma/sales/statSubjByAgePop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByAgePop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statSubjByAgePop";
	}	
	/**
	 * 등급별 과목수
	 */
	@RequestMapping(value={"/ma/sales/statSubjByGrade"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByGrade(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statSubjByGrade";
	}	
	@RequestMapping(value={"/ma/sales/statSubjByGradePop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByGradePop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statSubjByGradePop";
	}	
	/**
	 * 상품별 추이
	 */
	@RequestMapping(value={"/ma/sales/statProgBySubj"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatProgBySubj(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		return "sales/statProgBySubj";
	}		
	
	
	
}
