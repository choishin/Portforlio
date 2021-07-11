package kr.ac.kopo.kopo40.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.domain.Comment;
import kr.ac.kopo.kopo40.repository.CommentRepository;

@Controller
public class CommentController {
   
   @Autowired
   private CommentRepository commentRepository;
   
	/* 새로운 게시글 등록 */
	@RequestMapping("/CommentInsert")
	public String insertComment(@RequestParam("board_index") String board_index,
			@RequestParam("post_id") int id, @RequestParam("comment_name") String name,
			@RequestParam("comment_contents") String contents, Model model) {
		
		int[] values = {Integer.parseInt(board_index), id};
		model.addAttribute("values",values);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BoardItem boardItem = new BoardItem();
		boardItem.setId(id);
		Comment comment = new Comment();
		comment.setBoardItem(boardItem);
		comment.setBoard_id(Integer.parseInt(board_index));
		comment.setDateTime(sdf.format(date));
		comment.setName(name);
		comment.setContents(contents);
		commentRepository.save(comment);

		return "/CommentInsert";
	}
     
}
