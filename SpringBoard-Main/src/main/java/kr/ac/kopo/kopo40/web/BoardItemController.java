package kr.ac.kopo.kopo40.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.repository.BoardItemRepository;

@Controller
public class BoardItemController {

	@Autowired
	private BoardItemRepository boardItemRepository;

	@RequestMapping(value = "/board/boardItemView")
	// @ResponseBody
	public String selectAll(Model model) {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = boardItemRepository.findAll(pageable);
		model.addAttribute("BoardItemView", page.getContent());

		return "/board/boardItemView";
	}

	@RequestMapping(value = "/Board/for_boardSearch")
	public String for_searchBoard(Model model) {

		return "/Board/for_boardSearch";
	}

	@RequestMapping(value = "/board/for_view")
	public String for_viewBoardItem(Model model) {

		return "/board/for_view";
	}

	@RequestMapping(value = "/board/for_write")
	public String for_writeBoardItem(Model model) {

		return "/board/for_write";
	}

	@RequestMapping(value = "/board/for_update")
	public String for_updateBoardItem(Model model) {

		return "/board/for_update";
	}

	@RequestMapping(value = "/board/for_delete")
	public String for_deleteBoardItem(Model model) {

		return "/board/for_delete";
	}

	@RequestMapping(value = "/Board/BoardItemList/{board_index}")
	public String listBoardItem(@PathVariable(name="board_index") String board_index) {
		String URLpattern = "";
		if (board_index.equals("1")) {
			URLpattern = "/Board/BoardItemList";
		} else if (board_index.equals("2")) {
			URLpattern = "/Board/BoardItemList";
		} else if (board_index.equals("3")) {
			URLpattern = "/Board/BoardItemList";
		} else if (board_index.equals("4")) {
			URLpattern = "/Board/BoardItemList";
		} else {
			URLpattern = "/BoardList";
		}
		return URLpattern;
	}
	
	@RequestMapping(value = "/Board/BoardItemList/1")
	public String listBoardItem(@RequestParam(name="board_index",required=false,defaultValue="") String board_index,Model model) {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = boardItemRepository.findAllByBoard_id(board_index, pageable);
		model.addAttribute("BoardItemList", page.getContent());
		return "/Board/BoardItemList/1";
	}

	@RequestMapping(value = "/board/boardItemInsert")
	public String InsertBoardItem(Model model) {

		return "/board/boardItemInsert";
	}

	@RequestMapping(value = "/board/boardItemWrite")
	public String writeBoardItem(Model model) {

		return "/board/boardItemWrite";
	}

	@RequestMapping(value = "/board/boardItemUpdate")
	public String updateBoardItem(Model model) {

		return "/board/boardItemUpdate";
	}

	@RequestMapping(value = "/board/boardItemSet")
	public String setBoardItem(Model model) {

		return "/board/boardItemSet";
	}

	@RequestMapping(value = "/board/boardItemDelete")
	public String deleteBoardItem(Model model) {

		return "/board/boardItemDelete";
	}

}
