package com.jeiglobal.bms;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import com.jeiglobal.*;
import com.jeiglobal.service.member.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalBmsApplication.class)
@WebAppConfiguration
public class GlobalBMSApplicationTests {

	@Autowired
	private MemberSearchService memberSearchService;
	
	@Test
	public void contextLoads() {
	}

}
