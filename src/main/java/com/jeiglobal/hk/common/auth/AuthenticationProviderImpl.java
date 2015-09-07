package com.jeiglobal.hk.common.auth;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.auth.*;
import com.jeiglobal.hk.service.common.auth.*;
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
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		LoginInfo loginInfo =  authoritiesService.findMemberById(authToken.getName());
		if(loginInfo == null){
			throw new UsernameNotFoundException(authToken.getName());
		}
		
		String inputPass = authoritiesService.selectEncryptPassWord(authToken.getCredentials().toString());
		
		if(!matchPassword(loginInfo.getMemberPassword(),inputPass)){
			throw new BadCredentialsException("계정ID와 비밀번호가 맞지않습니다.");
		}
		
		List<GrantedAuthority> authorities = getAuthorities(loginInfo.getMemberId());
		return new UsernamePasswordAuthenticationToken(new LoginInfo(loginInfo.getMemberId(),loginInfo.getMemberPassword()
				,loginInfo.getMemberEnabled(),loginInfo.getJisaCD(),loginInfo.getDepid1(),loginInfo.getDepid2(),loginInfo.getEmpKey(),loginInfo.getEmpName(),loginInfo.getEmpKeyLvCD(),loginInfo.getDepMngCD(),loginInfo.getEncodeCookie()),null,authorities);
	}

	private List<GrantedAuthority> getAuthorities(String memberId) {
		List<Authority> perms = authoritiesService.findPermissionById(memberId);
		if (perms == null)
			return Collections.emptyList();

		List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
		for (Authority perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getAuthority()));
		}
		return authorities;
	}
	
	private boolean matchPassword(String memberPassword, Object credentials) {
		return memberPassword.equals(credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
