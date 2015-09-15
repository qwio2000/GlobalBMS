package com.jeiglobal.repository;

import java.util.*;

import com.jeiglobal.domain.*;

/**
 * 클래스명 : CommonRepository.java
 *
 * 작성일 : 2015. 9. 15.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * mapper/CommonRepository.xml
 */
@PrimaryRepositoryAnnoInterface
public interface CommonRepository {

	public CodeDtl findCodeDtl(Map<String, Object> param);

	public List<CodeDtl> findCodeDtls(Map<String, Object> param);

}
