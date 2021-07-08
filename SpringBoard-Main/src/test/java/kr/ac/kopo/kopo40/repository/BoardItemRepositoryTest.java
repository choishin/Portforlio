package kr.ac.kopo.kopo40.repository;


import java.util.List;
import java.util.Optional;



import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.BoardItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardItemRepositoryTest {

	@Autowired
	private BoardItemRepository boardItemRepository;
//	@Transactional
//	@Test
	void create() {

		//코드를 수정해야할 듯 함...

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
	void selectAll2() {
		List<BoardItem> boardItems = boardItemRepository.findAll();
		for (BoardItem boardItem : boardItems) {
			System.out.println(boardItem.getTitle());
		}
	}

//	@Test
	void selectOne1() {
		Optional<BoardItem> boardItemOptional = boardItemRepository.findById(1);
		if (boardItemOptional.isPresent()) {
			BoardItem boardItem = boardItemOptional.get();
			System.out.println(boardItem.getTitle());
		}
	}

//	@Test
	void deleteOne() {
		boardItemRepository.deleteById(2);
	}

}