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

	/**
	 * @return int
	 */
	public int getMagamDatesCount() {
		String mgMonth = getOneYearAgo();
		return operateManageRepository.findMagamDatesCount(mgMonth);
	}
	
	/**
	 * 마감 리스트
	 * @return List<MagamDate>
	 */
	public List<MagamDate> getMagamDates() {
		String mgMonth = getOneYearAgo();
		return operateManageRepository.findMagamDates(mgMonth);
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

	
}
