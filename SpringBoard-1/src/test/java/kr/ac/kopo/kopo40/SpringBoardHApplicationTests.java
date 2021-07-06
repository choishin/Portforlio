package kr.ac.kopo.kopo40;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.repository.SampleRepository;
import kr.ac.kopo.kopo40.repository.SampleSpecs;

@SpringBootTest
public class SpringBoardHApplicationTests {
	
	@Autowired
	private SampleRepository samplerepository;
	
	@Test
	public void findAll() {
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("title", "공지1");
		
		PageRequest pageable = PageRequest.of(1, 10);
		Page<Sample> page = samplerepository.findAll(SampleSpecs.search(filter),pageable);
		
		for (Sample sample : page) {
			System.out.println(sample.getTitle());
		}
		
	}

}
