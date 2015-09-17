package com.jeiglobal.common.auth;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.service.auth.*;
/**
 * 
 * 클래스명 : AuthenticationProviderImpl.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 로그인 인증을 처리(성공시 AuthenticationSuccessHandlerImpl, 실패시 AuthenticationFailureHandlerImpl)
 * 
 * 1. 사용자가 입력한 ID, PW 정보로 globalbiz.ComLoginInfo에 존재하는지 여부를 체크
 * 	1-1. 존재하지 않을 경우 UsernameNotFoundException을 발생
 * 2. 사용자가 입력한 PW 값을 MSSQL에 SECURE DB에서 SHA256으로 암호화
 * 3. 암호화한 값과 DB에 암호화된 값을 비교 
 * 	3-1. 입력한 PW와 DB값과 같지 않은 경우 BadCredentialsException을 발생
 * 4. 해당 사용자의 권한 정보를 DB에서 가져옴
 * 5. 사용자 정보와 권한 정보를 UsernamePasswordAuthenticationToken 객체에 담아서 리턴
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		LoginInfo loginInfo =  authoritiesService.findMemberById(authToken.getName());
		if(loginInfo == null){
			throw new UsernameNotFoundException(authToken.getName());
		}
		if(!passwordEncoder.matches(authToken.getCredentials().toString(), loginInfo.getUserPasswd())){
			throw new BadCredentialsException("비밀번호가 맞지않습니다.");
		}
		
		return new UsernamePasswordAuthenticationToken(new LoginInfo(loginInfo.getUserId(), loginInfo.getUserPasswd(), loginInfo.getUserFstName(), loginInfo.getUserLstName(), loginInfo.getStatusCD(), loginInfo.getJisaCD(), loginInfo.getDeptCD(), loginInfo.getDeptName(), loginInfo.getEmpKey(), loginInfo.getUserType(), loginInfo.getUserLevel(), loginInfo.getEncodeCookie(), loginInfo.getStateCD())
				,null, null);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
