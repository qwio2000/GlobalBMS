package com.jeiglobal.bms;

import static org.junit.Assert.assertNotNull;

import java.util.*;

import lombok.extern.slf4j.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import com.jeiglobal.*;
import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.member.*;
import com.jeiglobal.service.member.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalBmsApplication.class)
@WebAppConfiguration
@Slf4j
public class GlobalBMSApplicationTests {

	@Autowired
	private MemberSearchService memberSearchService;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void korMemberSearch(){
		  
		  List<KoreaMemberInfo> result = memberSearchService.getKoreaMemberSearch("1", "ACD14581", "zz", new LoginInfo("", "", "", "", "", "08", "", "", "", "", "", "", "", ""));
		  for (KoreaMemberInfo koreaMemberInfo : result) {
			log.debug(koreaMemberInfo.toString());
		  }
		  
		  assertNotNull(result);
	}

}
