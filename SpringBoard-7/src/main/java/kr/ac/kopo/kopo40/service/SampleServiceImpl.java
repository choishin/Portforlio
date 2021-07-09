package kr.ac.kopo.kopo40.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.repo.SampleRepository;

@Service
public class SampleServiceImpl implements SampleService {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	private static SampleRepository sampleRepository; 

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

	@Override
	public String testNoTransactional() {
		// TODO Auto-generated method stub
		Sample sample = sampleRepository.findById(1).get();
		sample.setTitle("update1");
		sampleRepository.save(sample);
		
		throw new RuntimeException("Spring Boot No Transactional Test");
	}

	@Override
	public String testTransactional() {
		// TODO Auto-generated method stub
		Sample sample = sampleRepository.findById(1).get();
		sample.setTitle("update1");
		sampleRepository.save(sample);
		
		throw new RuntimeException("Spring Boot Transactional Test");
	}

}
