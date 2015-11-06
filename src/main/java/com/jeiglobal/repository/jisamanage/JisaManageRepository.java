package com.jeiglobal.repository.jisamanage;

import java.util.List;
import java.util.Map;

import com.jeiglobal.repository.PrimaryRepositoryAnnoInterface;

/**
 * 클래스명 : JisaLoginRepository.java
 *
 * 작성일 : 2015. 9. 18.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 수정자 : 노윤희(IT지원팀)
 * 
 * 지사관리
 */
@PrimaryRepositoryAnnoInterface
public interface JisaManageRepository {

	public List<Map<String, Object>> findJisaList();
	public Map<String, Object> findJisaView(Map<String, Object> param);
	
	

}
