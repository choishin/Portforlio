package kr.ac.kopo.kopo40.board.repo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.spring.board.repo.SampleRepository;
import kr.ac.kopo.kopo40.spring.board.repo.SampleSpecs;

@SpringBootTest
class SpringBoard3ApplicationTests {
	@Autowired
	private SampleRepository samplerepository;

	@Test
	void contextLoads() {
		Map<String, Object> filter = new HashMap<String ,Object>();
		filter.put("title", "공지1");
		
		PageRequest pageable = PageRequest.of(0, 2);
		Page<Sample> page = samplerepository.findAll(SampleSpecs.search(filter),pageable);
		
		for (Sample sample : page) {
			System.out.println(sample.getTitle());
		}
		
	}

}
