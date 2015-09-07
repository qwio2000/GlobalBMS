package com.jeiglobal.hk.common.config;

import org.springframework.cache.*;
import org.springframework.cache.annotation.*;
import org.springframework.cache.ehcache.*;
import org.springframework.context.annotation.*;
import org.springframework.core.io.*;

/**
 * 
 * 클래스명 : CacheConfig.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 메뉴 이동시마다 메뉴 인터셉터가 동작 -> 매번 디비에서 메뉴 정보를 읽어오는 작업
 * => 효율적으로 가져오기 위해 EhCache를 이용하여 첫 DB에서 읽어오는 동작으로 메뉴 정보를 캐시에 저장한 후 이후 캐시에 있는 정보로 메뉴 출력 
 */
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager cacheManager(){
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager(){
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
}
