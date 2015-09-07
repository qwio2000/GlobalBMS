package com.jeiglobal.hk.repository.common.auth;

import com.jeiglobal.hk.repository.common.*;

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
	public String selectEncryptPassWord(String password);
}
