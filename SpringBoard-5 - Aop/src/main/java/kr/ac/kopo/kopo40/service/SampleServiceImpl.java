package kr.ac.kopo.kopo40.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

	@Override
	public String test() {
		// TODO Auto-generated method stub
		String msg = "Hello Spring Boot No AOP";
		logger.info(msg);
		return msg;
	}

	@Override
	public String testAop() {
		// TODO Auto-generated method stub
		String msg = "Hello, Spring Boot AOP";
		logger.info(msg);
		return msg;
	}

}
