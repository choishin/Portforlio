package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.repo.BoardItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardItemRepositoryTest {

	@Autowired
	private BoardItemRepository boardItemRepository;

//	@Test
	void create() {
		BoardItem boardItem1 = new BoardItem();
		boardItem1.setTitle("title1");
		//코드 더 추가해야함
		
		boardItemRepository.save(boardItem1);
	}

	@Transactional
	@Test
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
