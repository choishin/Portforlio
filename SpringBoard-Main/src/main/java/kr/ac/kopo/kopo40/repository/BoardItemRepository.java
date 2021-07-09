package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.BoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Integer>, JpaSpecificationExecutor<BoardItem> {
	
	Optional<BoardItem> findOneByBoard_id(int board_id);
	
	Optional<BoardItem> findOneByIdAndBoard_id(int id, int board_id);

	Page<BoardItem> findAllByBoard_id(String board_id, Pageable pageable);

	List<BoardItem> findAllByBoard_id(String board_id);
	
	@Query(value = "SELECT max(id) FROM BoardItem")
	Integer totalMax();
	
	@Query(value = "SELECT max(id) FROM BoardItem where board_id=:board_id")
	Integer max(@Param("board_id") int board_id);
	
//	@Query(value = "SELECT * FROM BoardItem where id=:id and board_id=:board_id")
//	Optional<BoardItem> findOneByIdAndBoard_id(@Param("id")int id, @Param("board_id")int board_id);
}