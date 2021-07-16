package kr.ac.kopo.kopo40.service;

import java.util.List;
import kr.ac.kopo.kopo40.domain.Board;

public interface BoardService {
	void create(Board board);
	List<Board> selectAll();
	Board selectOne(int id);
	void update(Board board);
	void delete(Board board);
	int totalCount();
	int getMax();
}
