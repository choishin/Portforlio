package kr.ac.kopo.kopo40.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.web.PageableDefault;
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

	/* 게시글 목록 */
	@RequestMapping("/BoardItemList/{board_index}")
	public String listBoardItem(Model model, @PathVariable("board_index") int board_index,
			@PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {

		Page<BoardItem> boardItems = boardItemRepository.findAllByBoard_idOrderByIdDesc(board_index, pageable);

		int pageNumber = boardItems.getPageable().getPageNumber(); // 현재페이지
		int totalPages = boardItems.getTotalPages(); // 총 페이지 수. 검색에따라 10개면 10개..
		int pageBlock = 5; // 블럭의 수 1, 2, 3, 4, 5
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1; // 현재 페이지가 7이라면 1*5+1=6
		int endBlockPage = startBlockPage + pageBlock - 1; // 6+5-1=10. 6,7,8,9,10해서 10.
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("boardItems", boardItems);

		Optional<Board> board = boardRepository.findById(board_index);
		model.addAttribute("board", board.get());

		return "/BoardItemList";

	}

	/* 새로운 게시글 작성 */
	@RequestMapping(value = "/BoardItemInsert/{board_index}")
	public String InsertBoardItem(@PathVariable("board_index") int board_index, Model model) {
		int lastCnt = boardItemRepository.max(board_index);
		int[] values = { board_index, lastCnt };
		model.addAttribute("values", values);
		return "/BoardItemInsert";
	}

	/* 새로운 게시글 등록 */
	@Transactional
	@RequestMapping("/BoardItemWrite/{board_index}")
	public String writeBoardItem(@RequestParam("board_index") int board_index, @RequestParam("get_id") int id,
			@RequestParam("get_viewcnt") int viewcnt, @RequestParam("get_title") String title,
			@RequestParam("get_content") String content, Model model) {

		int[] values = { board_index, id };
		model.addAttribute("values", values);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Optional<Board> board = boardRepository.findById(board_index);

		BoardItem boardItem = new BoardItem();
		boardItem.setBoard(board.get());
		boardItem.setId(id);
		boardItem.setTitle(title);
		boardItem.setContent(content);
		boardItem.setViewCnt(viewcnt);
		boardItem.setDate(sdf.format(date));
		board.get().addBoardItem(boardItem);
		Hibernate.initialize(boardItem);
		boardRepository.save(board.get());

		return "/BoardItemWrite";
	}

	/* 게시글 보기 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/BoardItemView/{board_index}/{id}")
	public String selectOne(@PathVariable("board_index") int board_id, @PathVariable("id") int id, Model model) {

		boardItemRepository.addViewCnt(board_id, id);
		Optional<BoardItem> returnedBoardItem = boardItemRepository.findOneByIdAndBoard_id(id, board_id);
		List<Comment> returnedComments = commentRepository.findOneByBoard_idAndPost_id(board_id, id);
		BoardItem boardItem = new BoardItem();
		boardItem = returnedBoardItem.get();

		model.addAttribute("boardItem", boardItem);
		model.addAttribute("comments", returnedComments);

		return "/BoardItemView";
	}

	/* 게시글 수정 페이지 */
	@RequestMapping(value = "/BoardItemUpdate/{board_id}/{id}")
	public String updateBoardItem(@PathVariable("board_id") int board_id, @PathVariable("id") int id, Model model) {
		Optional<BoardItem> boardItem = boardItemRepository.findOneByBoard_idAndId(board_id, id);
		model.addAttribute("boardItem", boardItem.get());

		return "/BoardItemUpdate";
	}

	/* 게시글 수정 등록 */
	@RequestMapping(value = "/BoardItemSet/{board_id}/{id}")
	public String setBoardItem(@PathVariable("board_id") int board_id, @PathVariable("id") int id,
			@RequestParam("get_title") String title, @RequestParam("get_content") String content, Model model) {
		Optional<BoardItem> boardItem = boardItemRepository.findOneByBoard_idAndId(board_id, id);
		model.addAttribute("boardItem", boardItem.get());
		boardItemRepository.setBoardItem(title, content, board_id, id);

		return "/BoardItemSet";
	}

	/* 게시글 삭제 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/BoardItemDelete/{board_id}/{id}")
	public String deleteBoardItem(@PathVariable("board_id") int board_id, @PathVariable("id") int id, Model model) {
		int[] values = { board_id, id };
		model.addAttribute("values", values);

		Optional<Board> board = boardRepository.findById(board_id);
		Optional<BoardItem> boardItem = boardItemRepository.findOneByBoard_idAndId(board_id, id);
		board.get().getBoardItems().remove(boardItem.get());
		boardItem.get().setBoard(null);
		boardItemRepository.delete(boardItem.get());
		boardRepository.save(board.get());

		return "/BoardItemDelete";
	}

	@RequestMapping(value = "/BoardItemSearch")
	public String searchBoardItem(@RequestParam("keyword") String keyword, Model model) {

		List<BoardItem> boardItems = boardItemRepository.searchByKeywordList(keyword);
		model.addAttribute("keyword", keyword);
		model.addAttribute("boardItems", boardItems);

		return "/BoardItemSearch";
	}

//	@RequestMapping(value = "/BoardItemView")
//	public String selectAll(Model model) {
//		PageRequest pageable = PageRequest.of(0, 10);
//		Page<BoardItem> page = boardItemRepository.findAll(pageable);
//		model.addAttribute("BoardItems", page.getContent());
//
//		return "/BoardItemView";
//	}

}
