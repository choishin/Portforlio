package kr.ac.kopo.kopo40.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.repo.BoardRepository;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;

	@RequestMapping(value = "/hello")
	// view로 돌려주는게 아니라, 그냥 출력을 함. (Open API같은 json 파일로) RESTful 서버를 만들 수 있음.
	public String list(Model model) {	
		List<Board> list = new ArrayList<Board>();
		list = boardRepository.findAll();
		
		String[] boardTitles = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Board board = new Board();
			board = list.get(i);
			boardTitles[i] = board.getTitle();
		}
		model.addAttribute("list", boardTitles);
		
		return "hello";
	}
}
