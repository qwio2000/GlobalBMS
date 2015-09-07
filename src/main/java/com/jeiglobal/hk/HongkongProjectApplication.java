package com.jeiglobal.hk;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.context.web.*;
import org.springframework.context.annotation.*;

/**
 * 클래스명 : HongkongProjectApplication.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 스프링 부트를 실행하는 클래스
 */
@Configuration
//기본적으로 스프링부트는 AutoConfiguration을 제공
//But, DB 2개를 붙여야 하기 때문에 AutoConfig는 exclude 한 후 수동으로 설정
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan(basePackages="com.jeiglobal.hk")
public class HongkongProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HongkongProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(HongkongProjectApplication.class);
	}
}
