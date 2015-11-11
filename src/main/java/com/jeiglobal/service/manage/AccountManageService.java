package com.jeiglobal.service.manage;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.manage.ManageDto.User;
import com.jeiglobal.domain.manage.ManageDto.Users;
import com.jeiglobal.repository.manage.*;

/**
 * 클래스명 : AccountManageService.java
 *
 * 작성일 : 2015. 11. 6.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@Service
public class AccountManageService {

	@Autowired
	private AccountManageRepository accountManageRepository;
	
	private Map<String, Object> param = new HashMap<>();
	
	/**
	 * 계정 개수
	 * @param loginInfo 
	 * @return int
	 */
	public int getUsersCount(LoginInfo loginInfo) {
		return accountManageRepository.findUsersCount(loginInfo);
	}

	/**
	 * @param userType
	 * @param startRow
	 * @param endRow
	 * @return List<Users>
	 */
	public List<Users> getUsers(String userType, int startRow, int endRow) {
		param.clear();
		param.put("userType", userType);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		return accountManageRepository.findUsers(param);
	}

	/**
	 * @param userId
	 * @return int
	 */
	public int getUsersCountByUserId(String userId) {
		return accountManageRepository.findUsersCountByUserId(userId);
	}

	/**
	 * @return String
	 */
	public String getNewEmpKey() {
		return accountManageRepository.findNewEmpKey();
	}

	/**
	 * @param user void
	 */
	public void addUser(User user) {
		accountManageRepository.insertNewUser(user);
	}

	/**
	 * @param userId void
	 */
	public void removeUserByUserId(String userId) {
		accountManageRepository.deleteUserByUserId(userId);
	}

	/**
	 * @param userId
	 * @return User
	 */
	public User getUserByUserId(String userId) {
		return accountManageRepository.findUserByUserId(userId);
	}

	/**
	 * @param userId void
	 * @param changePwd 
	 * @param string 
	 */
	public void setUserPasswordClearByUserId(String userId, String changePwd, String workId) {
		param.clear();
		param.put("userId", userId);
		param.put("changePwd", changePwd);
		param.put("workId", workId);
		accountManageRepository.updateUserPasswordClearByUserId(param);
		
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		accountManageRepository.updateUser(user);
	}

}
