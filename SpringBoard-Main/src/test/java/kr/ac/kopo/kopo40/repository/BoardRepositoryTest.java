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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardItemRepository boardItemRepository;

//	@Transactional
	@Test
	public void oneToMany_TwoWay() {
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

	}

//	@Transactional
//	@Test
	void selectAll1() {
		Optional<Board> boardOptional = boardRepository.findById(4);
		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			Hibernate.initialize(board.getBoardItems());
			System.out.println(board.getTitle());
		}
	}

//	@Test
	void selectAll2() {
		List<BoardItem> boardItems = boardItemRepository.findAll();
		for (BoardItem boardItem : boardItems) {
			System.out.println(boardItem.getTitle());
		}
	}

//	@Test
	void selectOne1() {
		Optional<Board> boardOptional = boardRepository.findById(2);
		if (boardOptional.isPresent()) {
			Board board = boardOptional.get();
			System.out.println(board.getTitle());
		}
	}

//	@Test
	void deleteOne() {
		boardRepository.deleteById(1);
	}

}