package kr.ac.kopo.kopo40.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.repository.BoardRepository;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;

	@RequestMapping(value = "/BoardList")
	public String selectAll(Model model) {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<Board> page = boardRepository.findAll(pageable);
		model.addAttribute("BoardList", page.getContent());

		return "BoardList";
	}

}
