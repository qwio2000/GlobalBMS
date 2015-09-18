package com.jeiglobal.repository.jisaLogin;

import java.util.*;

import com.jeiglobal.repository.*;

/**
 * 클래스명 : JisaLoginRepository.java
 *
 * 작성일 : 2015. 9. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@PrimaryRepositoryAnnoInterface
public interface JisaLoginRepository {

	public List<Map<String, Object>> findJisaLogins();

}
