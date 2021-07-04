package kr.ac.kopo.kopo40.dao;

import java.util.List;
import kr.ac.kopo.kopo40.domain.BoardItem;

public interface BoardItemDao {
	
	void create(BoardItem boardItem);	
	BoardItem selectOne(int id);
	List<BoardItem> selectAll(int board_index);
	void update(BoardItem boardItem);
	void delete(BoardItem boardItem);
}
