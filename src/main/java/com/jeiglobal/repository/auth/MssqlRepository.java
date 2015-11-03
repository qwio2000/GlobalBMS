package com.jeiglobal.repository.auth;

import java.util.*;

import com.jeiglobal.domain.member.*;
import com.jeiglobal.repository.*;

/**
 * 
 * 클래스명 : MssqlRepository.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Mssql 전용 Repository src/main/resource/mapper/auth/MssqlRepository.xml
 */
@AnotherRepositoryAnnoInterface
public interface MssqlRepository {
	
	public List<KoreaMemberInfo> findKoreaMemberSearch(Map<String, Object> param);
	
}
