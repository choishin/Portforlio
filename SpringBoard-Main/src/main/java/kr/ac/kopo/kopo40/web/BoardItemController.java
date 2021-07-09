package kr.ac.kopo.kopo40.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.domain.Comment;
import kr.ac.kopo.kopo40.repository.BoardItemRepository;
import kr.ac.kopo.kopo40.repository.BoardRepository;
import kr.ac.kopo.kopo40.repository.CommentRepository;

@Controller
public class BoardItemController {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@RequestMapping(value = "/BoardItemList/{board_index}")
	public String listBoardItem(@PathVariable("board_index") String board_index, Model model) {
		Optional<Board> board = boardRepository.findById(Integer.parseInt(board_index));
		Board boardForReturn = board.get();
		model.addAttribute("board", boardForReturn);
		return "/BoardItemList";
	}

	@RequestMapping(value = "/BoardItemInsert/{board_index}")
	public String InsertBoardItem(@PathVariable("board_index") String board_index, Model model) {
		int lastCnt = boardItemRepository.max(Integer.parseInt(board_index));
		int[] values = { Integer.parseInt(board_index), lastCnt };
		model.addAttribute("values", values);
		return "/BoardItemInsert";
	}

	@RequestMapping("/BoardItemWrite/{board_index}")
	public String insertBoardItem(@RequestParam("board_index") int board_index,
			@RequestParam("get_viewcnt") int viewcnt, @RequestParam("get_title") String title,
			@RequestParam("get_content") String content, Model model) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Board board = new Board();
		board.setId(board_index);
		BoardItem boardItem = new BoardItem();
		boardItem.setBoard(board);
		boardItem.setViewCnt(viewcnt);
		boardItem.setTitle(title);
		boardItem.setDate(sdf.format(date));
		boardItem.setContent(content);
		boardItemRepository.save(boardItem);

		return "/BoardItemWrite";
	}
	
	@RequestMapping(value = "/BoardItemView/{board_index}/{id}")
	public String selectOne(@PathVariable("board_index") int board_id,@PathVariable("id") int id, Model model) {
		Optional<BoardItem> boardItem = boardItemRepository.findOneByIdAndBoard_id(id, board_id);
		model.addAttribute("boardItem",boardItem.get());
		
		List<Comment> comments = commentRepository.findAllByPost_id(id);
		model.addAttribute("comments",comments);
		
		return "/BoardItemView";
	}

	@RequestMapping(value = "/BoardItemUpdate/{board_index}")
	public String updateBoardItem(Model model) {

		return "/BoardItemUpdate";
	}

	@RequestMapping(value = "/BoardItemSet/{board_index}")
	public String setBoardItem(Model model) {

		return "/BoardItemSet";
	}

	@RequestMapping(value = "/BoardItemDelete/{board_index}")
	public String deleteBoardItem(Model model) {

		return "/BoardItemDelete";
	}
	
	@RequestMapping(value = "/BoardItemView")
	// @ResponseBody
	public String selectAll(Model model) {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = boardItemRepository.findAll(pageable);
		model.addAttribute("BoardItems", page.getContent());

		return "/BoardItemView";
	}

}
