/**
 * 
 */
package com.jeiglobal.controller.sales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeiglobal.domain.auth.LoginInfo;
import com.jeiglobal.domain.sales.MemSubjMstKeep;
import com.jeiglobal.domain.sales.SalesDaily;
import com.jeiglobal.domain.sales.SalesDailyPop;
import com.jeiglobal.domain.sales.SalesDailyPopTot;
import com.jeiglobal.domain.sales.SalesMonthly;
import com.jeiglobal.domain.sales.SalesMonthlyPop;
import com.jeiglobal.domain.sales.SalesMonthlyPopTot;
import com.jeiglobal.domain.sales.StatMembersByMultiSubj;
import com.jeiglobal.domain.sales.StatProgBySubj;
import com.jeiglobal.domain.sales.StatSubjByAge;
import com.jeiglobal.domain.sales.StatSubjByGrade;
import com.jeiglobal.service.CommonService;
import com.jeiglobal.service.sales.SalesService;

import lombok.extern.slf4j.Slf4j;

/**
 * 클래스명 : SalesController.java
 *
 * 작성일 : 2015. 11. 19.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명 : 실적조회
 */
@Slf4j
@Controller
public class SalesController {
	

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
		cal.add(cal.MONTH, -1); // 1개월전을 디폴트로
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
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("deptName", deptName);
		return "sales/monthlySalesPop";
	}
	@RequestMapping(value={"/ma/sales/salesMemSubjPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getMonthlySalesPop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String deptCD,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj,@RequestParam(defaultValue="") String deptName) throws ParseException{
						
		List<MemSubjMstKeep> dataSalesMemSubjPop = salesService.getSalesMemSubjPop(jisaCD,deptCD,selYY, selMM, selSubj, "");
		log.debug("Getting 월별실적>마감회원수 팝업 Page, dataSalesMemSubjPop : {}", dataSalesMemSubjPop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("salesMemSubjPop", dataSalesMemSubjPop);
		model.addAttribute("deptName", deptName);
		return "sales/salesMemSubjPop";
	}	
	
	/**
	 * 조직찾기
	 */
	@RequestMapping(value={"/ma/sales/deptSearchPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getDeptSearchPop(Model model, @ModelAttribute LoginInfo loginInfo,
			@RequestParam(defaultValue="08") String jisaCD ) {
		List<Map<String, Object>> dataDeptSearchPop = salesService.getDeptSearchPop(jisaCD);
		log.debug("Getting 조직찾기 팝업 Page, dataDeptSearchPop : {}", dataDeptSearchPop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("deptSearchPop", dataDeptSearchPop);
		return "sales/deptSearchPop";
	}		
	
	/**
	 * 복수과목 현황
	 */
	@RequestMapping(value={"/ma/sales/statMultiSubj"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatMultiSubj(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="08") String jisaCD, @RequestParam(defaultValue="00000") String deptCD,
			@RequestParam(defaultValue="Hong Kong") String deptName,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM,
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1); // 1개월전을 디폴트로		
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<StatMembersByMultiSubj> dataStatMultiSubj = salesService.getStatMembersByMultiSubj(jisaCD,deptCD,selYY, selMM, selSubj, "");
		int totCnt = 0;
		if(dataStatMultiSubj.size()>0){
			totCnt = dataStatMultiSubj.size();
		}
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("subjList", commonService.getCodeDtls("0002", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("monthList", commonService.getCodeDtls("0207", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("statMultiSubj", dataStatMultiSubj);
		model.addAttribute("totCnt", totCnt);		
		model.addAttribute("jisaCD", jisaCD);
		model.addAttribute("deptCD", deptCD);
		model.addAttribute("deptName", deptName);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("currentYear", currentYear);	
		return "sales/statMultiSubj";
	}	
	@RequestMapping(value={"/ma/sales/statMultiSubjPop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatMultiSubjPop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String deptCD,
			@RequestParam(defaultValue="") String selSubj, @RequestParam(defaultValue="1") int multiSubjCnt,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String deptName) throws ParseException{
		
		List<MemSubjMstKeep> dataStatMultiSubjPop = salesService.getStatMembersByMultiSubjPop(jisaCD,deptCD,selYY, selMM, selSubj, multiSubjCnt, "");
		log.debug("Getting 복수과목 회원현황>상세 팝업 Page, dataStatMultiSubjPop : {}", dataStatMultiSubjPop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("statMultiSubjPop", dataStatMultiSubjPop);
		model.addAttribute("deptName", deptName);
		return "sales/statMultiSubjPop";
	}		
	/**
	 * 연령별 과목수
	 */
	@RequestMapping(value={"/ma/sales/statSubjByAge"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByAge(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="08") String jisaCD, @RequestParam(defaultValue="00000") String deptCD,
			@RequestParam(defaultValue="Hong Kong") String deptName,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM,
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1); // 1개월전을 디폴트로		
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<StatSubjByAge> dataStatSubjByAge = salesService.getStatSubjByAge(jisaCD,deptCD,selYY, selMM, selSubj, "");
		int totCnt = 0;
		int totSubjCnt = 0;
		double totSubjRate = 0;
		if(dataStatSubjByAge.size()>0){
			totCnt = dataStatSubjByAge.size();
			totSubjCnt = dataStatSubjByAge.get(0).getTotSubjCnt();
			totSubjRate = dataStatSubjByAge.get(0).getTotSubjRate();
		}
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("subjList", commonService.getCodeDtls("0002", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("monthList", commonService.getCodeDtls("0207", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("statSubjByAge", dataStatSubjByAge);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("totSubjCnt", totSubjCnt);
		model.addAttribute("totSubjRate", totSubjRate);		
		model.addAttribute("jisaCD", jisaCD);
		model.addAttribute("deptCD", deptCD);
		model.addAttribute("deptName", deptName);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("currentYear", currentYear);	
		return "sales/statSubjByAge";
	}		
	@RequestMapping(value={"/ma/sales/statSubjByAgePop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByAgePop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String deptCD,
			@RequestParam(defaultValue="") String selSubj, @RequestParam(defaultValue="") String ageCD,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String deptName) throws ParseException{
		
		List<MemSubjMstKeep> dataStatSubjByAgePop = salesService.getStatSubjByAgePop(jisaCD,deptCD,selYY, selMM, selSubj, ageCD, "");
		log.debug("Getting 연령별 과목수>상세 팝업 Page, dataStatSubjByGradePop : {}", dataStatSubjByAgePop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("statSubjByAgePop", dataStatSubjByAgePop);
		model.addAttribute("deptName", deptName);
		return "sales/statSubjByAgePop";
	}	
	/**
	 * 등급별 과목수
	 */
	@RequestMapping(value={"/ma/sales/statSubjByGrade"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByGrade(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="08") String jisaCD, @RequestParam(defaultValue="00000") String deptCD,
			 @RequestParam(defaultValue="Hong Kong") String deptName,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String selSubj) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1); // 1개월전을 디폴트로		
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());

		if("".equals(selYY)){
			selYY=currentYear;
		}
		if("".equals(selMM)){
			selMM=currentMonth;
		}		

		List<StatSubjByGrade> dataStatSubjByGrade = salesService.getStatSubjByGrade(jisaCD,deptCD,selYY, selMM, selSubj, "");
		int totCnt = 0;
		int totSubjCnt = 0;
		double totSubjRate = 0;
		if(dataStatSubjByGrade.size()>0){
			totCnt = dataStatSubjByGrade.size();
			totSubjCnt = dataStatSubjByGrade.get(0).getTotSubjCnt();
			totSubjRate = dataStatSubjByGrade.get(0).getTotSubjRate();
		}
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("subjList", commonService.getCodeDtls("0002", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("monthList", commonService.getCodeDtls("0207", loginInfo.getJisaCD(), 1, "Y"));
		model.addAttribute("statSubjByGrade", dataStatSubjByGrade);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("totSubjCnt", totSubjCnt);
		model.addAttribute("totSubjRate", totSubjRate);		
		model.addAttribute("jisaCD", jisaCD);
		model.addAttribute("deptCD", deptCD);
		model.addAttribute("deptName", deptName);
		model.addAttribute("selYY", selYY);
		model.addAttribute("selMM", selMM);
		model.addAttribute("selSubj", selSubj);
		model.addAttribute("currentYear", currentYear);	
		return "sales/statSubjByGrade";
	}	
	@RequestMapping(value={"/ma/sales/statSubjByGradePop"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatSubjByGradePop(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="") String jisaCD, @RequestParam(defaultValue="") String deptCD,
			@RequestParam(defaultValue="") String selSubj, @RequestParam(defaultValue="") String wbGrade,
			@RequestParam(defaultValue="") String selYY, @RequestParam(defaultValue="") String selMM, 
			@RequestParam(defaultValue="") String deptName) throws ParseException{
		
		List<MemSubjMstKeep> dataStatSubjByGradePop = salesService.getStatSubjByGradePop(jisaCD,deptCD,selYY, selMM, selSubj, wbGrade, "");
		log.debug("Getting 등급별 과목수>상세 팝업 Page, dataStatSubjByGradePop : {}", dataStatSubjByGradePop);
		
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("statSubjByGradePop", dataStatSubjByGradePop);
		model.addAttribute("deptName", deptName);
		return "sales/statSubjByGradePop";
	}	
	/**
	 * 상품별 추이
	 */
	@RequestMapping(value={"/ma/sales/statProgBySubj"},method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getStatProgBySubj(Model model, @ModelAttribute LoginInfo loginInfo, 
			@RequestParam(defaultValue="08") String jisaCD, @RequestParam(defaultValue="00000") String deptCD,
			@RequestParam(defaultValue="Hong Kong") String deptName,			
			@RequestParam(defaultValue="") String selYY) throws ParseException{
		
		Calendar cal = Calendar.getInstance();
		String currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		String currentMonth = new SimpleDateFormat("MM").format(cal.getTime());		
		if("".equals(selYY)){
			selYY=currentYear;
		}
		int prevSelYY = Integer.parseInt(selYY.toString()) - 1 ;
		List<StatProgBySubj> dataStatProgBySubj = salesService.getStatProgBySubj(jisaCD,deptCD,selYY, "");
		log.debug("Getting 상품별 추이 Page, dataStatProgBySubj : {}", dataStatProgBySubj);
				
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("sales");
		model.addAttribute("headerScript", headerScript);
		model.addAttribute("statProgBySubj", dataStatProgBySubj);
		model.addAttribute("jisaCD", jisaCD);
		model.addAttribute("deptCD", deptCD);
		model.addAttribute("deptName", deptName);
		model.addAttribute("selYY", selYY);
		model.addAttribute("prevSelYY", prevSelYY);
		model.addAttribute("currentYear", currentYear);		
		model.addAttribute("currentMonth", currentMonth);
		return "sales/statProgBySubj";
	}		
	
	
	
}
