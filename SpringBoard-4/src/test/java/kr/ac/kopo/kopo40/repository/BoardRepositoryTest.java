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
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.repo.BoardItemRepository;
import kr.ac.kopo.kopo40.repo.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardItemRepository boardItemRepository;

//	@Test
//	@Transactional
	public void oneToMany_TwoWay() {
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Board board3 = new Board();
		board3.setTitle("board3");

		BoardItem boardItem = new BoardItem();
		boardItem.setBoard(board3);
		boardItem.setTitle("test1");
		boardItem.setDate(sdf.format(date));
		boardItem.setContent("test글!");
		boardItem.setViewCnt(0);
		boardItems.add(boardItem);
		board3.setBoardItems(boardItems);
		boardRepository.save(board3);

	}

//		OnetoMany_TwoWay와 같은 내용
//		@Test
//		void create() {
//			Board board3 = new Board();
//			board3.setTitle("title1");
//
//			BoardItem boardItem1 = new BoardItem();
//			boardItem1.setTitle("boardItem1");
//			boardItem1.setBoard(board3);
//
//			BoardItem boardItem2 = new BoardItem();
//			boardItem2.setTitle("boardItem2");
//			boardItem2.setBoard(board3);
//
//			List<BoardItem> list = new ArrayList<>();
//			list.add(boardItem1);
//			list.add(boardItem2);
//
//			board3.setBoardItems(list);
//
//			boardRepository.save(board3);
//		}

//		@Transactional
		@Test
	void selectAll1() {
		Optional<Board> boardOptional = boardRepository.findById(4);
		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			Hibernate.initialize(board.getBoardItems());
			System.out.println(board.getTitle());
		}
	}

//		@Test
	void selectAll2() {
		List<BoardItem> boardItems = boardItemRepository.findAll();
		for (BoardItem boardItem : boardItems) {
			System.out.println(boardItem.getTitle());
		}
	}

//		@Test
	void selectOne1() {
		Optional<Board> boardOptional = boardRepository.findById(2);
		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			System.out.println(board.getTitle());
		}
	}

//		@Test
	void deleteOne() {
		boardRepository.deleteById(1);
	}

}
