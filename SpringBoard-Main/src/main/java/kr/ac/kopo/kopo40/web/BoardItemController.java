package kr.ac.kopo.kopo40.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.repository.BoardItemRepository;


@Controller
public class BoardItemController {

	   @Autowired
	   private BoardItemRepository boardItemRepository;
	   
	   @RequestMapping(value = "/board/boardItemView")
	   //@ResponseBody
	   public String selectAll(Model model) {
	      PageRequest pageable = PageRequest.of(0, 10);
	      Page<BoardItem> page = boardItemRepository.findAll(pageable);
	      model.addAttribute("BoardItemView", page.getContent());
	   
	      return "/board/boardItemView";
	   }
	   
	   @RequestMapping(value = "/board/for_boardSearch")
	   public String for_searchBoard(Model model) {
	   
	      return "/board/for_boardSearch";
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
	   
	   @RequestMapping(value = "/board/boardItemList")
	   public String listBoardItem(Model model) {		   
		   PageRequest pageable = PageRequest.of(0, 10);
		   Page<BoardItem> page = boardItemRepository.findAll(pageable);
		   model.addAttribute("boardItemList", page.getContent());
	   
	      return "/board/boardItemList";
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
