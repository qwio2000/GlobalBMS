package com.jeiglobal.hk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeiglobal.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalBmsApplication.class)
@WebAppConfiguration
public class GlobalBMSApplicationTests {

	@Test
	public void contextLoads() {
	}

}
