package com.jeiglobal.service.manage;

import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.*;
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

	/**
	 * 마감 일자 수정
	 * @param magamDate
	 */
	public void setClosingDate(MagamDate magamDate) {
		operateManageRepository.updateMagamDate(magamDate);
		
	}

	/**
	 * 등록 전 등록하려고 하는 mgMonth가 있는지 판별하기 위해 카운트를 가져옴
	 * @param mgMonth
	 * @return int
	 */
	public int getMagamDateCountByMgMonth(String mgMonth) {
		return operateManageRepository.findMagamDateCountByMgMonth(mgMonth);
	}

	/**
	 * 등록
	 * @param magamDate void
	 */
	public void addClosingDate(MagamDate magamDate) {
		operateManageRepository.insertMagamDate(magamDate);
		
	}

	/**
	 * 코드 수정 가능한 mstCD 정보 가져오기
	 * @param mstCDAry
	 * @return List<CodeMst>
	 */
	public List<CodeMst> getCodeMstsByMstCDs(String[] mstCDAry) {
		param.clear();
		param.put("mstCDAry", mstCDAry);
		return operateManageRepository.findCodeMstsByMstCDs(param);
	}

	/**
	 * 페이징 처리를 위한 코드 Dtl Count
	 * @param mstCD 
	 * @param jisaCD 
	 * @return int
	 */
	public int getCodeDtlCount(String mstCD, String jisaCD) {
		param.clear();
		param.put("mstCD", mstCD);
		param.put("jisaCD", jisaCD);
		return operateManageRepository.findCodeDtlsCountByMstCD(param);
	}

	/**
	 * @param mstCD
	 * @param jisaCD
	 * @param startRow
	 * @param endRow
	 * @return List<CodeDtl>
	 */
	public List<CodeDtl> getCodeDtls(String mstCD, String jisaCD, int startRow,
			int endRow) {
		param.clear();
		param.put("mstCD", mstCD);
		param.put("jisaCD", jisaCD);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		return operateManageRepository.findCodeDtlsByMstCD(param);
	}

	
}
