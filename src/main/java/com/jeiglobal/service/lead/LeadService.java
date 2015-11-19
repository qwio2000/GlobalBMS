package com.jeiglobal.service.lead;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.lead.*;
import com.jeiglobal.repository.lead.*;

/**
 * 클래스명 : LeadService.java
 *
 * 작성일 : 2015. 11. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Lead 서비스
 */
@Service
public class LeadService {

	@Autowired
	private LeadRepository leadRepository;

	Map<String, Object> param = new HashMap<>();
	/**
	 * Leads 가져오기
	 * @param contactName
	 * @param statusCD
	 * @param orderBy
	 * @param ord
	 * @param jisaCD
	 * @param startRow 
	 * @param endRow 
	 * @return List<CenterLeads>
	 */
	public List<CenterLeads> getLeads(String contactName, String statusCD,
			String orderBy, String ord, int startRow, int endRow) {
		param.clear();
		param.put("contactName", contactName);
		param.put("statusCD", statusCD);
		param.put("orderBy", orderBy);
		param.put("ord", ord);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		return leadRepository.findLeads(param);
	}
	/**
	 * leads Count
	 * @param contactName
	 * @param statusCD
	 * @param orderBy
	 * @param ord
	 * @param jisaCD
	 * @return int
	 */
	public int getLeadsCount(String contactName, String statusCD,
			String orderBy, String ord) {
		param.clear();
		param.put("contactName", contactName);
		param.put("statusCD", statusCD);
		param.put("orderBy", orderBy);
		param.put("ord", ord);
		return leadRepository.findLeadsCount(param);
	}

	/**
	 * @param idx
	 * @return CenterLeads
	 */
	public CenterLeads getCenterLeadByIdx(int idx) {
		return leadRepository.findCenterLeadByIdx(idx);
	}
	/**
	 * @param idx
	 * @return List<CenterLeadsNote>
	 */
	public List<CenterLeadsNote> getLeadNotesByIdx(int idx) {
		return leadRepository.findLeadNotesByIdx(idx);
	}
}
