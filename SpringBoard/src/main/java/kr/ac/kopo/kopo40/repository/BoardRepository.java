package kr.ac.kopo.kopo40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
//	void create(Board board);
//	Board selectOne(int id);
//	List<Board> selectAll();
//	void update(Board board);
//	void delete(int id);
//	void totalCount();
//	void getMax();

}
