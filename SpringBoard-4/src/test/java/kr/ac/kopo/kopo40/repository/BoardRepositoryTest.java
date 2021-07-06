package kr.ac.kopo.kopo40.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.Board;
import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.repo.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
//	@Transactional
	public void oneToMany_TwoWay() {
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		Board board1 = new Board();
		board1.setTitle("board1");
		
		BoardItem boardItem = new BoardItem();
		boardItem.setBoard(board1);
		boardItem.setTitle("test1");
		boardItem.setDate(sdf.format(date));
		boardItem.setContent("test글!");
		boardItem.setViewCnt(0);
		boardItems.add(boardItem);
		board1.setBoardItems(boardItems);
		boardRepository.save(board1);
			
	}
}
