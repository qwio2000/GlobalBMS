package com.jeiglobal.hk.common.config;

import javax.sql.*;

import org.apache.ibatis.session.*;
import org.apache.tomcat.dbcp.dbcp.*;
import org.mybatis.spring.*;
import org.mybatis.spring.mapper.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.transaction.*;

import com.jeiglobal.hk.repository.common.*;

/**
 * 
 * 클래스명 : DataSourceConfig.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * DB 정보
 */
@Configuration
public class DataSourceConfig{
	
	/**
	 * Mysql DataSource
	 * @return
	 */
	public DataSource getPrimaryDataSource() {
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(ConnectSetting.mysqlDriverClassName);
		basicDataSource.setUsername(ConnectSetting.mysqlUsername);
		basicDataSource.setPassword(ConnectSetting.mysqlPassword);
		basicDataSource.setUrl(ConnectSetting.mysqlUrl);
		basicDataSource.setMaxActive(10);
		basicDataSource.setMaxIdle(4);
		basicDataSource.setMinIdle(4);
		basicDataSource.setMaxWait(5000);

		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setValidationQuery("SELECT 1");

		basicDataSource.setRemoveAbandoned(true);
		basicDataSource.setRemoveAbandonedTimeout(100);
		basicDataSource.setLogAbandoned(true);

		basicDataSource.setTestOnBorrow(false);
		basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
		basicDataSource.setNumTestsPerEvictionRun(-1);
		return basicDataSource;
	}
	/**
	 * Mssql DataSource
	 * @return
	 */
	public DataSource getAnotherDataSource() {
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(ConnectSetting.mssqlDriverClassName);
		basicDataSource.setUsername(ConnectSetting.mssqlUsername);
		basicDataSource.setPassword(ConnectSetting.mssqlPassword);
		basicDataSource.setUrl(ConnectSetting.mssqlUrl);
		basicDataSource.setMaxActive(10);
		basicDataSource.setMaxIdle(4);
		basicDataSource.setMinIdle(4);
		basicDataSource.setMaxWait(5000);

		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setValidationQuery("SELECT 1");

		basicDataSource.setRemoveAbandoned(true);
		basicDataSource.setRemoveAbandonedTimeout(100);
		basicDataSource.setLogAbandoned(true);

		basicDataSource.setTestOnBorrow(false);
		basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
		basicDataSource.setNumTestsPerEvictionRun(-1);
		return basicDataSource;
	}
	/**
	 * Mysql TransactionManager
	 * @return
	 */
	@Bean
	public PlatformTransactionManager primaryTransactionManager() {
		return new DataSourceTransactionManager(getPrimaryDataSource());
	}
	/**
	 * Mssql TransactionManager
	 * @return
	 */
	@Bean
	public PlatformTransactionManager anotherTransactionManager() {
		return new DataSourceTransactionManager(getAnotherDataSource());
	}
	/**
	 * Mysql SqlSession
	 * @return
	 */
	@Bean(name="mySqlSession")
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getPrimaryDataSource());
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jeiglobal.hk.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * Mssql SqlSession
	 * @return
	 */
	@Bean(name="myAnotherSqlSession")
	public SqlSessionFactory getAnotherSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getAnotherDataSource());
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jeiglobal.hk.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * Mysql MapperScannerConfigurer
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setPrimaryMapperScannerConfigurer(){
		MapperScannerConfigurer primaryMapperScanConfigurer = new MapperScannerConfigurer();
		primaryMapperScanConfigurer.setBasePackage("com.jeiglobal.hk");
		primaryMapperScanConfigurer.setAnnotationClass(PrimaryRepositoryAnnoInterface.class);
		primaryMapperScanConfigurer.setBeanName("primaryMapperScanConfigurer");
		primaryMapperScanConfigurer.setSqlSessionFactoryBeanName("mySqlSession");
		
		return primaryMapperScanConfigurer;
	}
	/**
	 * Mssql MapperScannerConfigurer
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setAnotherMapperScannerConfigurer(){
		MapperScannerConfigurer anotherMapperScanConfigurer = new MapperScannerConfigurer();
		anotherMapperScanConfigurer.setBasePackage("com.jeiglobal.hk");
		anotherMapperScanConfigurer.setAnnotationClass(AnotherRepositoryAnnoInterface.class);
		anotherMapperScanConfigurer.setBeanName("anotherMapperScanConfigurer");
		anotherMapperScanConfigurer.setSqlSessionFactoryBeanName("myAnotherSqlSession");
		
		return anotherMapperScanConfigurer;
	}
	
}
