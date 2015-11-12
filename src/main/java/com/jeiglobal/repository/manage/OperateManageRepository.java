package com.jeiglobal.repository.manage;

import java.util.*;

import com.jeiglobal.domain.manage.ManageDto.MagamDate;
import com.jeiglobal.repository.*;

/**
 * 클래스명 : OperateManageRepository.java
 *
 * 작성일 : 2015. 11. 11.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@PrimaryRepositoryAnnoInterface
public interface OperateManageRepository {

	public int findMagamDatesCount(String mgMonth);

	public List<MagamDate> findMagamDates(Map<String, Object> param);

	public MagamDate findMagamDateByMgMonth(String mgMonth);


}