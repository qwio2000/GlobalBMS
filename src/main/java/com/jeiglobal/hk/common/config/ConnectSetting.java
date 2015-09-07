package com.jeiglobal.hk.common.config;

import java.util.*;

import org.springframework.beans.factory.config.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.core.env.*;
import org.springframework.core.io.*;

/**
 * 
 * 클래스명 : ConnectSetting.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * db.yml에 등록된 DB 정보를 담는 객체
 * ActiveProfile에 따라서 읽는 yml 파일 상이하도록 설정
 * activeProfile = default => db_default.yml
 * 				   develop => db_develop.yml
 *  			   product => db_product.yml
 */
public class ConnectSetting {
static private ConnectSetting connectSetting = new ConnectSetting();
	
	static public String mysqlDriverClassName;
	static public String mysqlUrl;
	static public String mysqlUsername;
	static public String mysqlPassword;
	static public String mssqlDriverClassName;
	static public String mssqlUrl;
	static public String mssqlUsername;
	static public String mssqlPassword;
	
	private ConnectSetting() {
		super();
		ConfigurableApplicationContext context = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = context.getEnvironment();
		String dbActive = "";
		if(env.getActiveProfiles().length > 0){
			dbActive = env.getActiveProfiles()[0];
		}else{
			dbActive = env.getDefaultProfiles()[0];
		}
		context.close();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("db_"+dbActive+".yml"));
		Properties proper = yaml.getObject();
		mysqlDriverClassName = proper.getProperty("db.mysqlDriverClassName");
		mysqlUrl = proper.getProperty("db.mysqlUrl");
		mysqlUsername = proper.getProperty("db.mysqlUsername");
		mysqlPassword = proper.getProperty("db.mysqlPassword");
		
		mssqlDriverClassName= proper.getProperty("db.mssqlDriverClassName");
		mssqlUrl= proper.getProperty("db.mssqlUrl");
		mssqlUsername= proper.getProperty("db.mssqlUsername");
		mssqlPassword= proper.getProperty("db.mssqlPassword");
	}
	
	public static ConnectSetting getInstance(){
        return connectSetting;
    }

	
	
}
