/**
 * 
 */
package com.jeiglobal.repository.sales;

import java.util.List;
import java.util.Map;

import com.jeiglobal.domain.sales.SalesDaily;
import com.jeiglobal.domain.sales.SalesDailyPop;
import com.jeiglobal.domain.sales.SalesDailyPopTot;
import com.jeiglobal.domain.sales.SalesMonthly;
import com.jeiglobal.domain.sales.SalesMonthlyPop;
import com.jeiglobal.domain.sales.SalesMonthlyPopTot;
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
	
}
