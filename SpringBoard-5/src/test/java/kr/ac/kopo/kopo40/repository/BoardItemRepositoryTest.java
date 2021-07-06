package kr.ac.kopo.kopo40.repository;

import javax.transaction.Transactional;

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

	@Test
	@Transactional
	//이부분 완성해야 함.
	public void oneToMany_OneWay() {
		for (int i = 0; i < 100; i++) {
			BoardItem boardItem = new BoardItem();
			if (i % 3 == 0) {
				boardItem.setTitle("first");
			} else if (i % 3 == 1) {
				boardItem.setTitle("second");
			} else {
				boardItem.setTitle("Third");
			}
			boardItem.setViewCnt(0);
			boardItem.setContent(dummyString());

		}
	}

}
