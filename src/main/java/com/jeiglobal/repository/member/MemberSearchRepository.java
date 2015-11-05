package com.jeiglobal.repository.member;

import java.util.*;

import com.jeiglobal.domain.member.MemberDto.MemberSearchInfo;
import com.jeiglobal.domain.member.MemberDto.MemberSearchResult;
import com.jeiglobal.repository.*;

/**
 * 클래스명 : MemberSearchRepository.java
 *
 * 작성일 : 2015. 11. 5.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@PrimaryRepositoryAnnoInterface
public interface MemberSearchRepository {

	public int findHkMemberSearchCount(MemberSearchInfo memberSearchInfo);

	public List<MemberSearchResult> findHkMemberSearch(Map<String, Object> param);


}
