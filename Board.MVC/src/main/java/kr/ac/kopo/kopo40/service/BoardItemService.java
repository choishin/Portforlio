package kr.ac.kopo.kopo40.service;

import java.util.List;
import kr.ac.kopo.kopo40.domain.BoardItem;

public interface BoardItemService {
	void create(BoardItem boardItem);
	List<BoardItem> selectAll(int board_index);
	BoardItem selectOne(int board_id,int id);
	void update(BoardItem boardItem);
	void delete(int board_id, int id);
	int totalCount(int board_id);
	int getMax(int board_id);
	void addViewCount(int board_id,int id);
	List<BoardItem> searchItems (String startNum, String countPage,String keyword);
	List<Integer> searchPaging (String startNum, String countPage, String keyword);
	List<BoardItem> listItems(int startNum, int countPage,int board_id);
	List<Integer> listPaging(String startNum, String countPage, int board_id);
}
