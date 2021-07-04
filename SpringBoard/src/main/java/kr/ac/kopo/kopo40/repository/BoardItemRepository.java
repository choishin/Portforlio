package kr.ac.kopo.kopo40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.BoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Long> {
//	void create(BoardItem boardItem);
//	Board selectOne(int id);
//	List<Board> selectAll();
//	void update(BoardItem boardItem);
//	void delete(BoardItem boardItem);
//	void totalCount();
//	void getMax();
//	void addViewCnt(int id);
//	
}
