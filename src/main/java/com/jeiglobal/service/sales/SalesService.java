/**
 * 
 */
package com.jeiglobal.service.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import com.jeiglobal.repository.sales.SalesRepository;

/**
 * 클래스명 : SalesService.java
 *
 * 작성일 : 2015. 11. 20.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명
 */
@Service
public class SalesService {
	
	@Value("${cookieShare.domain}")
	private String cookieDomain;
		
	@Autowired
	private SalesRepository salesRepository;
	
	/**
	 * 일일실적
	 */
	public List<SalesDaily> getDailySales(String salesYMD, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("salesYMD", salesYMD);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);
		return salesRepository.dailySales(param);
	}
	public List<SalesDailyPop> getDailySalesPop(String jisaCD, String salesYMD, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("salesYMD", salesYMD);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);
		return salesRepository.dailySalesPop(param);
	}
	public SalesDailyPopTot getDailySalesPopTot(String jisaCD, String salesYMD, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("salesYMD", salesYMD);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);
		return salesRepository.dailySalesPopTot(param);
	}	
	/**
	 * 월별실적
	 */
	public List<SalesMonthly> getMonthlySales(String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);
		return salesRepository.monthlySales(param);
	}	
	public List<SalesMonthlyPop> getMonthlySalesPop(String jisaCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);		
		return salesRepository.monthlySalesPop(param);
	}
	public SalesMonthlyPopTot getMonthlySalesPopTot(String jisaCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);
		return salesRepository.monthlySalesPopTot(param);
	}
	public List<MemSubjMstKeep> getSalesMemSubjPop(String jisaCD, String deptCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);		
		return salesRepository.salesMemSubjPop(param);
	}	
		
	
	
	/**
	 * 조직 찾기
	 */
	public List<Map<String, Object>> getDeptSearchPop(String jisaCD){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);		
		return salesRepository.deptSearchPop(param);
	}	
	
	/**
	 * 등급별 과목수 
	 */
	public List<StatSubjByGrade> getStatSubjByGrade(String jisaCD, String deptCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statSubjByGrade(param);
	}	
	public List<MemSubjMstKeep> getStatSubjByGradePop(String jisaCD, String deptCD, String mgYY, String mgMM, String subj,String wbGrade, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("wbGrade", wbGrade);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statSubjByGradePop(param);
	}	
	
	/**
	 * 연령별 과목수
	 */
	public List<StatSubjByAge> getStatSubjByAge(String jisaCD, String deptCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statSubjByAge(param);
	}		
	public List<MemSubjMstKeep> getStatSubjByAgePop(String jisaCD, String deptCD, String mgYY, String mgMM, String subj,String ageCD, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("ageCD", ageCD);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statSubjByAgePop(param);
	}		
	
	/**
	 * 복수과목 회원현황
	 */
	public List<StatMembersByMultiSubj> getStatMembersByMultiSubj(String jisaCD, String deptCD, String mgYY, String mgMM, String subj, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statMembersByMultiSubj(param);
	}		
	public List<MemSubjMstKeep> getStatMembersByMultiSubjPop(String jisaCD, String deptCD, String mgYY, String mgMM, String subj,int multiSubjCnt, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("mgMM", mgMM);
		param.put("subj", subj);
		param.put("multiSubjCnt", multiSubjCnt);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statMembersByMultiSubjPop(param);
	}	
	/**
	 * 상품별 추이
	 */
	public List<StatProgBySubj> getStatProgBySubj(String jisaCD, String deptCD, String mgYY, String jobFlag){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("jisaCD", jisaCD);
		param.put("deptCD", deptCD);
		param.put("mgYY", mgYY);
		param.put("jobFlag", jobFlag);		
		return salesRepository.statProgBySubj(param);
	}	
	
}
