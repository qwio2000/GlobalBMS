package com.jeiglobal.repository.manage;

import java.util.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.manage.ManageDto.User;
import com.jeiglobal.domain.manage.ManageDto.Users;
import com.jeiglobal.repository.*;

/**
 * 클래스명 : AccountManageRepository.java
 *
 * 작성일 : 2015. 11. 6.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 설명
 */
@PrimaryRepositoryAnnoInterface
public interface AccountManageRepository {

	public int findUsersCount(LoginInfo loginInfo);

	public List<Users> findUsers(Map<String, Object> param);

	public int findUsersCountByUserId(String userId);

	public String findNewEmpKey();

	public void insertNewUser(User user);

	public void deleteUserByUserId(String userId);

	public User findUserByUserId(String userId);

	public void updateUserPasswordClearByUserId(Map<String, Object> param);

	public void updateUser(User user);

}
