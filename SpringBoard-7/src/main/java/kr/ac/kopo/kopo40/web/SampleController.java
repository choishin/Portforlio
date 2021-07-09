package kr.ac.kopo.kopo40.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.kopo40.service.SampleService;
import kr.ac.kopo.kopo40.service.SampleServiceImpl;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	private SampleService sampleService;
	
	@RequestMapping(value = "/sample/noAop")
	@ResponseBody
	public String noAop() {
		return sampleService.test();
	}
	
	@RequestMapping(value = "/sample/aop")
	@ResponseBody
	public String aop() {
		return sampleService.testAop();
	}
	
	@RequestMapping(value = "/sample/noTransactional")
	@ResponseBody
	public String noTransactional() {
		return sampleService.testNoTransactional();
	}
	
	@RequestMapping(value = "/sample/transactional")
	@ResponseBody
	public String Transactional() {
		return sampleService.testTransactional();
	}	

}
