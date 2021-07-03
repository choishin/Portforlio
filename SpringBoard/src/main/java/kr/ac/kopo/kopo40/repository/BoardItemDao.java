package kr.ac.kopo.kopo40.repository;

import java.util.List;
import kr.ac.kopo.kopo40.domain.BoardItem;

public interface BoardItemDao {
	
	void create(BoardItem boardItem);	
	BoardItem selectOne(int id);
	List<BoardItem> selectAll();
	void update(BoardItem boardItem);
	void delete(BoardItem boardItem);
}
