package com.jeiglobal.repository.jisamanage;

import java.util.List;
import java.util.Map;

import com.jeiglobal.domain.manage.*;
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
	
	public List<SubjInfo> findJisaSubjInfos(Map<String, Object> param);

	public int findJisaSubjInfosCount(String jisaCD);

	public SubjInfo findSubjInfo(Map<String, Object> param);

	public void insertSubjInfo(Map<String, Object> param);

	public void updateSubjInfo(Map<String, Object> param);

	public void updateSubjInfoStopDate(Map<String, Object> param);

	public void insertSubjInfoToCodeDtl(Map<String, Object> param);

	public void updateSubjInfoToCodeDtl(Map<String, Object> param);

	public void deleteSubjInfo(Map<String, Object> param);

	public void deleteSubjInfoToCodeDtl(Map<String, Object> param);

	public List<SubjTuitionInfo> findJisaTuitionInfos(String jisaCD);

	public SubjTuitionInfo findJisaTuitionInfo(Map<String, Object> param);

	public void updateJisaTuitionInfo(SubjTuitionInfo tuition);
	
	

}
