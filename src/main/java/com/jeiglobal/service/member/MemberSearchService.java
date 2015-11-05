package com.jeiglobal.service.member;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.member.MemberDto.MemberSearchInfo;
import com.jeiglobal.domain.member.MemberDto.MemberSearchResult;
import com.jeiglobal.repository.auth.*;
import com.jeiglobal.repository.member.*;

/**
 * 클래스명 : MemberSearchService.java
 *
 * 작성일 : 2015. 11. 3.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Service
public class MemberSearchService {

	@Autowired
	private MssqlRepository mssqlRepository;
	
	@Autowired
	private MemberSearchRepository memberSearchRepository;
	
	Map<String, Object> param = new HashMap<>();
	
	/**
	 * @param memberSearchInfo
	 * @param loginInfo
	 * @return int
	 */
	public int getMemberSearchCount(MemberSearchInfo memberSearchInfo) {
		int count = 0;
		if("hkForeign".equals(memberSearchInfo.getKind())){//홍콩
			count = memberSearchRepository.findHkMemberSearchCount(memberSearchInfo);
		}else if("otherForeign".equals(memberSearchInfo.getKind())){//북경, 대양주
			count = mssqlRepository.findOtherForeignMemberSearchCount(memberSearchInfo);
		}else{//한국
			count = mssqlRepository.findKoreaMemberSearchCount(memberSearchInfo);
		}
		return count;
	}

	/**
	 * 회원 검색 
	 * @param memberSearchInfo
	 * @param startRow
	 * @param endRow
	 * @return List<MemberSearchResult>
	 */
	public List<MemberSearchResult> getMemberSearch(
			MemberSearchInfo memberSearchInfo, int startRow, int endRow) {
		param.clear();
		param.put("memberSearchInfo", memberSearchInfo);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		List<MemberSearchResult> result = new ArrayList<>();
		if("hkForeign".equals(memberSearchInfo.getKind())){//홍콩
			result = memberSearchRepository.findHkMemberSearch(param);
		}else if("otherForeign".equals(memberSearchInfo.getKind())){//북경, 대양주
			result = mssqlRepository.findOtherForeignMemberSearch(param);
		}else{//한국
			result = mssqlRepository.findKoreaMemberSearch(param);
		}
		return result;
	}

}
