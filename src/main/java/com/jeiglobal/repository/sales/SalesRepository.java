/**
 * 
 */
package com.jeiglobal.repository.sales;

import java.util.List;
import java.util.Map;

import com.jeiglobal.domain.sales.MemSubjMstKeep;
import com.jeiglobal.domain.sales.SalesDaily;
import com.jeiglobal.domain.sales.SalesDailyPop;
import com.jeiglobal.domain.sales.SalesDailyPopTot;
import com.jeiglobal.domain.sales.SalesMemSubjDrop;
import com.jeiglobal.domain.sales.SalesMemSubjRegist;
import com.jeiglobal.domain.sales.SalesMonthly;
import com.jeiglobal.domain.sales.SalesMonthlyPop;
import com.jeiglobal.domain.sales.SalesMonthlyPopTot;
import com.jeiglobal.domain.sales.StatMembersByMultiSubj;
import com.jeiglobal.domain.sales.StatProgBySubj;
import com.jeiglobal.domain.sales.StatSubjByAge;
import com.jeiglobal.domain.sales.StatSubjByGrade;
import com.jeiglobal.repository.PrimaryRepositoryAnnoInterface;

/**
 * 클래스명 : SalesRepository.java
 *
 * 작성일 : 2015. 11. 20.
 *
 * 작성자 : 노윤희(IT지원팀)
 * 
 * 설명
 */
@PrimaryRepositoryAnnoInterface
public interface SalesRepository {
	
	/**
	 * 일일실적
	 */
	public List<SalesDaily> dailySales(Map<String, Object> param); 
	public List<SalesDailyPop> dailySalesPop(Map<String, Object> param);
	public SalesDailyPopTot dailySalesPopTot(Map<String, Object> param);
	
	/**
	 * 월별실적
	 */
	public List<SalesMonthly> monthlySales(Map<String, Object> param);
	public List<SalesMonthlyPop> monthlySalesPop(Map<String, Object> param);
	public SalesMonthlyPopTot monthlySalesPopTot(Map<String, Object> param);
	public List<MemSubjMstKeep> salesMemSubjPop(Map<String, Object> parm);
	public List<SalesMemSubjRegist> salesMemSubjRegistPop(Map<String, Object> parm);
	public List<SalesMemSubjDrop> salesMemSubjDropPop(Map<String, Object> parm);
	
	
	/**
	 * 조직찾기
	 */
	public List<Map<String, Object>> deptSearchPop(Map<String, Object> param);
	
	/**
	 * 등급별 과목수
	 */
	public List<StatSubjByGrade> statSubjByGrade(Map<String, Object> parm);
	public List<MemSubjMstKeep> statSubjByGradePop(Map<String, Object> parm);

	/**
	 * 연령별 과목수
	 */
	public List<StatSubjByAge> statSubjByAge(Map<String, Object> parm);
	public List<MemSubjMstKeep> statSubjByAgePop(Map<String, Object> parm);
	
	/**
	 * 복수과목 회원현황
	 */
	//
	public List<StatMembersByMultiSubj> statMembersByMultiSubj(Map<String, Object> parm);
	public List<MemSubjMstKeep> statMembersByMultiSubjPop(Map<String, Object> parm);	
	
	/**
	 * 상품별 추이
	 */
	public List<StatProgBySubj> statProgBySubj(Map<String, Object> parm);
}
