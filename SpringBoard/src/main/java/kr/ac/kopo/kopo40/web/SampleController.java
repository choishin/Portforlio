package kr.ac.kopo.kopo40.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.kopo40.domain.Sample;
import kr.ac.kopo.kopo40.repository.SampleRepository;

//<-이 어노테이션을 보고 Controller라는 것을 알게됨
@Controller
public class SampleController {

//<-스프링이 이 주석을 해석해서 new를 하지 않아도 스프링이 알아서 객체를 만들어줌 (new BoardServiceImpl 이라는 것에서 자유로워짐)
	@Autowired
	private SampleRepository sampleRepository;

	@RequestMapping(value = "/sample/list")
	@ResponseBody
	public List<Sample> list(Model model) {
		return sampleRepository.findAll();
	}
}
