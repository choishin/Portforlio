package kr.ac.kopo.kopo40.web;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.repository.BoardRepository;

@RestController
public class BoardRestController {

//스프링이 이 주석을 해석해서 new를 하지 않아도 스프링이 알아서 객체를 만들어줌 (new BoardServiceImpl 이라는 것에서 자유로워짐)
	@Autowired
	private BoardRepository boardRepository;

//	@RequestMapping(value = "BoardList")
//	@ResponseBody
//	public List<Board> list(Model model) {
//
//		return boardRepository.findAll();
//	}

	@RequestMapping(value = "/board/pageable")
	@ResponseBody
	public List<Board> pageable(Model model) {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<Board> page = boardRepository.findAll(pageable);

		return page.getContent();
	}

//	@RequestMapping(value = "/board/search")
//	@ResponseBody
//	public List<Board> search() {
//		Map<String, Object> filter = new HashMap<String, Object>();
//		filter.put("title", "공지1");
//
//		PageRequest pageable = PageRequest.of(0, 10);
//		Page<Sample> page = sampleRepository.findAll(SampleSpecs.search(filter), pageable);
//		return page.getContent();
//
//	}

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	@RequestMapping("/findOne")
	public Board findOne(@RequestParam HashMap<String, String> map) {
		int id = Integer.parseInt(map.get("id"));
		String title = map.get("title");
		logger.info("id: " + id);
		logger.info("title: " + title);
		return boardRepository.findById(id).get();
	}

}
