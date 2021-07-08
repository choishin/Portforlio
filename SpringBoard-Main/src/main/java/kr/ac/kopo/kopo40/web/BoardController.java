package kr.ac.kopo.kopo40.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.domain.BoardItem;
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
   
//	@RequestMapping(value = "/Board/BoardItemList")
//	public String getBoard(@RequestParam(name="board_index",required=false,defaultValue="") String board_index,Model model) {
//		Optional<Board> board = boardRepository.findById(Integer.parseInt(board_index));
//		model.addAttribute("Board", board);
//		return "/Board/BoardItemList";
//
//	}
     
}
