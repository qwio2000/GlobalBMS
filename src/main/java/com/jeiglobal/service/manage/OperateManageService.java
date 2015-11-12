package com.jeiglobal.service.manage;

import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.manage.ManageDto.MagamDate;
import com.jeiglobal.repository.manage.*;

/**
 * 클래스명 : OperateManageService.java
 *
 * 작성일 : 2015. 11. 11.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Service
public class OperateManageService {

	@Autowired
	private OperateManageRepository operateManageRepository;
	
	Map<String, Object> param = new HashMap<>();
	
	/**
	 * @return int
	 */
	public int getMagamDatesCount() {
		String mgMonth = getOneYearAgo();
		return operateManageRepository.findMagamDatesCount(mgMonth);
	}
	
	/**
	 * 마감 리스트
	 * @param endRow 
	 * @param startRow 
	 * @return List<MagamDate>
	 */
	public List<MagamDate> getMagamDates(int startRow, int endRow) {
		param.clear();
		String mgMonth = getOneYearAgo();
		param.put("mgMonth", mgMonth);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		return operateManageRepository.findMagamDates(param);
	}

	/**
	 * 1년전 yyyy-MM 가져오기
	 * @return String
	 */
	private String getOneYearAgo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		String mgMonth = sdf.format(cal.getTime());
		return mgMonth;
	}

	/**
	 * mgMonth로 MagamDate 가져오기
	 * @param mgMonth
	 * @return MagamDate
	 */
	public MagamDate getMagamDateByMgMonth(String mgMonth) {
		return operateManageRepository.findMagamDateByMgMonth(mgMonth);
	}

	
}
