package kr.ac.kopo.kopo40.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.repository.SampleRepository;

@Controller
public class SampleController {

//스프링이 이 주석을 해석해서 new를 하지 않아도 스프링이 알아서 객체를 만들어줌 (new BoardServiceImpl 이라는 것에서 자유로워짐)
	@Autowired
	private SampleRepository sampleRepository;

	@RequestMapping(value = "/sample/list")
	// view로 돌려주는게 아니라, 그냥 출력을 함. (Open API같은 json 파일로) RESTful 서버를 만들 수 있음.
	@ResponseBody
	public List<Sample> list(Model model) {

		return sampleRepository.findAll();
	}

//	@RequestMapping(value = "/sample/pageable")
//	@ResponseBody
//	public List<Sample> pageable(Model model) {
//		PageRequest pageable = PageRequest.of(0, 2);
//		Page<Sample> page = sampleRepository.findAll(pageable);
//
//		return page.getContent();
//	}
//
//	@RequestMapping(value = "/sample/search")
//	@ResponseBody
//	public List<Sample> search() {
//		Map<String, Object> filter = new HashMap<String, Object>();
//		filter.put("title", "공지");
//
//		PageRequest pageable = PageRequest.of(0, 2);
//		Page<Sample> page = sampleRepository.findAll(SampleSpecs.search(filter), pageable);
//		return page.getContent();
//
//	}

}
