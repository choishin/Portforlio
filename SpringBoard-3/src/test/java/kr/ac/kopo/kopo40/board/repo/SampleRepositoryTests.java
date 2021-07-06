package kr.ac.kopo.kopo40.board.repo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.spring.board.repo.SampleRepository;
import kr.ac.kopo.kopo40.spring.board.repo.SampleSpecs;


@SpringBootTest
public class SampleRepositoryTests {
	
	@Autowired
	private SampleRepository samplerepository;
	
	@Test
	public void findAll() {
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("title", "공지");
		
		PageRequest pageable = PageRequest.of(0, 10);
		Page<Sample> page = samplerepository.findAll(SampleSpecs.search(filter),pageable);
		
		for (Sample sample : page) {
			System.out.println(sample.getTitle());
		}
		
	}

}
