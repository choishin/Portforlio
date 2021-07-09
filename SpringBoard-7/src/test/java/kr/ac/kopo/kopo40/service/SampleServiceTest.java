package kr.ac.kopo.kopo40.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

	@Autowired
	private SampleService sampleService;
	
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceTest.class);
		
	@Ignore
//	@Test
	public void testNoAop() {
		sampleService.test();
	}
	
//	@Test
	public void testAop() {
		sampleService.testAop();
	}
	
	@Ignore
//	@Test
	public void testNoTransactional() {
		try {
		sampleService.testNoTransactional();
		}
		catch (RuntimeException re) {
			logger.info(re.toString());
		}
	
	}
	
	@Test
	public void testTransactional() {
		try {
		sampleService.testTransactional();
		}
		catch (RuntimeException re) {
			logger.info(re.toString());
		}
		
	}

}
