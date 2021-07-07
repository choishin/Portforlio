package kr.ac.kopo.kopo40.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

	@Autowired
	private SampleService sampleService;
	
	@Ignore
	@Test
	public void testNoAop() {
		sampleService.test();
	}
	
	@Test
	public void testAop() {
		sampleService.testAop();
	}
	
}
