package com.jeiglobal.hk.repository.common.menu;

import java.util.*;

import org.springframework.cache.annotation.*;

import com.jeiglobal.hk.domain.menu.*;
import com.jeiglobal.hk.repository.common.*;
/**
 * 
 * 인터페이스명 : MenuRepository.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Mysql 메뉴 관련 Repository
 * src/main/resource/mapper/menu/MenuRepository.xml
 */
//ehCache 적용, 로그인 처리가 성공적으로 된 경우 기존 캐시 삭제
@Cacheable(value="menuCache")
@PrimaryRepositoryAnnoInterface
public interface MenuRepository {
	
	public GlobalMenu findOneByMParentIdx(long mIdx);
	
	public List<GlobalMenu> findByMParentIdxAndJisaCDAndEmpKeyLvCDAndDepMngCD(Map<String, Object> map);
	
	public GlobalMenu findByMIdx(long mIdx);
	
	public void updateMHasChildrenByMidx(Map<String, Object> map);
	
	public GlobalMenu findOneByMParentOrderByMOrderDESC(long mIdx);
	
	public void insertGlobalMenu(GlobalMenu globalMenu);
	
	public long selectLastId();
	
	public void updateMDepthByMIdx(Map<String, Object> map);
	
	public long countMIdxByMParentIdx(long mIdx);
	
	public long selectMParentIdxByMIdx(long mIdx);
	
	public void deleteGlobalMenuByMidx(long mIdx);
	
	public int selectMDepthByMIdx(long mIdx);
	
	public List<GlobalMenu> findByMDepth(Map<String, Object> map);
	
	public void updateGlobalMenuByMIdx(GlobalMenu globalMenu);
	
	public void updateMSortByMIdx(Map<String, Object> map);
	
	public List<GlobalMenu> findByMParentIdx(long mIdx);
}
