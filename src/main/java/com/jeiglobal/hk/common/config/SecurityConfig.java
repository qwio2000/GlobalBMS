package com.jeiglobal.hk.common.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.savedrequest.*;

import com.jeiglobal.hk.common.auth.*;

/**
 * 
 * 클래스명 : SecurityConfig.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 시큐리티 설정 파일
 */
@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProviderImpl authenticationProvider;
	@Autowired
	private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;	
	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandler;
	@Autowired
	private AuthenticationEntryPointImpl authenticationEntryPoint;	
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;
	@Autowired
	private SecurityContextRepositoryImpl securityContextRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
		throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//아래 패턴과 같은 요청이 들어오면 시큐리티 적용 안하고 무시
		web.ignoring().antMatchers("/public/js/**","/public/css/**","/public/img/**","/favicon.ico");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
			http
				.securityContext().securityContextRepository(securityContextRepository)
			.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login","/").permitAll()
				.antMatchers("/adminManager/**").hasAuthority("SUPERADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.usernameParameter("memberId")
					.passwordParameter("memberPassword")
					.loginProcessingUrl("/loginCheck")
					.successHandler(authenticationSuccessHandler)
					.failureHandler(authenticationFailureHandler)
					.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.logoutSuccessHandler(logoutSuccessHandler)
					.permitAll()
			.and()
				.httpBasic()
			.and()
				.exceptionHandling()
					.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.requestCache().requestCache(new NullRequestCache());
	}
}