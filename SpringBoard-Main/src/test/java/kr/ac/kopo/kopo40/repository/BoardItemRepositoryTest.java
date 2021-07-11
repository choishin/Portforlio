package kr.ac.kopo.kopo40.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.domain.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardItemRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardItemRepository boardItemRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Test 
	void searchByKeywordList() {
		List<BoardItem> boardItems = boardItemRepository.searchByKeywordList("글");
		for(int i=0; i<boardItems.size(); i++) {
			BoardItem boardItem = new BoardItem();
			boardItem = boardItems.get(i);
			System.out.println(boardItem.getId());
			System.out.println(boardItem.getTitle());
			System.out.println(boardItem.getDate());
			System.out.println(boardItem.getContent());
		}

	}
	
//	@Test
	void sortBoardItems() {
		List<BoardItem> boardItems = boardItemRepository.sortBoardItems(1);
	}
	
//	@Modifying
//	@Transactional
//	@Test
	void deleteOneByBoard_idAndId() {
		Optional<Board> board = boardRepository.findById(2);
		Optional<BoardItem> boardItem = boardItemRepository.findOneByBoard_idAndId(2,2);
	    board.get().getBoardItems().remove(boardItem.get());
	    boardItem.get().setBoard(null);
	    boardItemRepository.delete(boardItem.get());
	    boardRepository.save(board.get());
	}

//	@Transactional
//	@Test
	void writeBoardItem() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Optional<Board> board = boardRepository.findById(1);
		
		BoardItem boardItem = new BoardItem();
		boardItem.setBoard(board.get());
		boardItem.setId(1);
		boardItem.setTitle("제목");
		boardItem.setContent("내용");
		boardItem.setViewCnt(0);
		boardItem.setDate(sdf.format(date));
		board.get().addBoardItem(boardItem);
		Hibernate.initialize(boardItem);
		boardRepository.save(board.get());

	}
		
//	@Test
	void viewCnt() {
		Optional<BoardItem> returnedBoardItem = boardItemRepository.findOneByIdAndBoard_id(1, 1);
		List<Comment> returnedComments = commentRepository.findOneByBoard_idAndPost_id(1,1);
		List<BoardItem> boardItems = new ArrayList<BoardItem>();

	}
	
//	@Transactional
//	@Test
	void selectAll1() {
		Optional<BoardItem> boardItemOptional = boardItemRepository.findById(2);
		if (boardItemOptional.isPresent()) {
			BoardItem boardItem = boardItemOptional.get();
			Hibernate.initialize(boardItem.getBoard());
			System.out.println(boardItem.getBoard().getId());
		}
	}

//	@Test
	void selectOne() {
		Optional<BoardItem> returnedBoardItem = boardItemRepository.findOneByIdAndBoard_id(1, 1);
		List<Comment> returnedComments = commentRepository.findOneByBoard_idAndPost_id(1, 1);

		System.out.println(returnedBoardItem.get().getId());
		System.out.println(returnedBoardItem.get().getTitle());
		System.out.println(returnedBoardItem.get().getDate());
		System.out.println(returnedBoardItem.get().getContent());

		for (int i = 0; i < returnedComments.size(); i++) {
			Comment comment = new Comment();
			comment = returnedComments.get(i);
			System.out.println(comment.getId());
			System.out.println(comment.getName());
			System.out.println(comment.getDateTime());
			System.out.println(comment.getContents());
		}
	}

//	@Test
	void findOneByIdAndBoard_id(int id, int board_id) {
		Optional<BoardItem> boardItem = boardItemRepository.findOneByIdAndBoard_id(id, board_id);
		BoardItem newBoard = boardItem.get();
	}

//	@Test
	void totalMax() {
		int result = boardItemRepository.totalMax();
		System.out.println(result);
	}

//	@Test
	void max() {
		int result = boardItemRepository.max(1);
		System.out.println(result);
	}


}