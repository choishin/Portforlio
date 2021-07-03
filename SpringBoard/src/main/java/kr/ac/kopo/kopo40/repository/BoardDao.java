package kr.ac.kopo.kopo40.repository;

import java.util.List;

import kr.ac.kopo.kopo40.domain.Board;

public interface BoardDao {
	void create(Board board);	
	Board selectOne(int id);
	List<Board> selectAll();
	void update(Board board);
	void delete(Board board);
}
