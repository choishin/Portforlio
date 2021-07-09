package kr.ac.kopo.kopo40.service;

public interface SampleService {
	//aop
	String test();
	String testAop();
	
	//transactional
	String testNoTransactional();
	String testTransactional();
}