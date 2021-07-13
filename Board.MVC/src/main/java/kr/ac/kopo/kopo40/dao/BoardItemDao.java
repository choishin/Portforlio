package kr.ac.kopo.kopo40.dao;

import java.util.List;
import kr.ac.kopo.kopo40.domain.BoardItem;

public interface BoardItemDao {
	
	void create(BoardItem boardItem);	
	List<BoardItem> selectAll(int board_index);
	BoardItem selectOne(int id, int board_id);
	void update(BoardItem boardItem);
	void delete(int board_id, int id);
	int totalCount(int board_id);
	int getMax(int board_id);
	void addViewCount(int board_id, int id);
	List<BoardItem> limitPaging(int startNum, int countPage, int board_id);
	void drop();
	void createTable();
	List<BoardItem> searchItems(String keyword);
}
