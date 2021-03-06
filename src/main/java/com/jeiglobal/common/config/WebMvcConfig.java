package com.jeiglobal.common.config;

import java.util.*;

import org.springframework.boot.context.embedded.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.filter.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.*;

import com.jeiglobal.common.*;
import com.jeiglobal.utils.*;
import com.navercorp.lucy.security.xss.servletfilter.*;

/**
 * 
 * 클래스명 : WebMvcConfig.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * Web MVC 설정
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * Message를 이용하기 위한 Bean 설정
	 * @param messageSource
	 * @return MessageSourceAccessor
	 */
	@Bean
	public MessageSourceAccessor messageSourceAccesor(MessageSource messageSource) { return new MessageSourceAccessor(messageSource); }

	/**
	 * PUT method Filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean httpPutFormContentFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HiddenHttpMethodFilter());
		filterRegistrationBean.setOrder(10);
		return filterRegistrationBean;
	}
	/**
	 * XSS 대응 필터
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean xssEscapeServletFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	/**
	 * 정적자원 설정 브라우져에서 캐쉬기간 1년 362~3 page
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/js/**")
		.addResourceLocations("/public/js/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/css/**")
		.addResourceLocations("/public/css/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/img/**")
		.addResourceLocations("/public/img/")
		.setCachePeriod(31556926);
	}
	
	/**
	 * 메뉴 인터셉터 Bean 설정
	 * @return MenuInterceptor
	 */
	@Bean
	public MenuInterceptor menuIntercepter(){ return new MenuInterceptor(); }
	
	//Interceptor 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuIntercepter()).addPathPatterns("/**")
			.excludePathPatterns("","/","/login","/public/**","/error","/favicon.ico");
	}
	
	@Bean
	public BCryptPasswordEncoder setBCryptPasswordEncoder(){ return new BCryptPasswordEncoder(); }
	
	/**
	 * Default Locale 설정
	 * @return LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale(Locale.KOREA.getLanguage(), Locale.KOREA.getCountry()));
//		resolver.setDefaultLocale(new Locale(Locale.US.getLanguage(), Locale.US.getCountry()));
//		resolver.setDefaultLocale(new Locale(Locale.CHINA.getLanguage(), Locale.CHINA.getLanguage()));
		return resolver;
	}
	
}
