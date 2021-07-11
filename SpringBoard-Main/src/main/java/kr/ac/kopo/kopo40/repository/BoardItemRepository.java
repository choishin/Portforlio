package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.BoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Integer>, JpaSpecificationExecutor<BoardItem> {
		
	Optional<BoardItem> findOneByBoard_id(int board_id);
	
	Optional<BoardItem> findOneByBoard_idAndId(int board_id, int id);

	Page<BoardItem> findAllByBoard_id(String board_id, Pageable pageable);

	List<BoardItem> findAllByBoard_id(String board_id);
	
	@Query(value="SELECT b from BoardItem b where board_id=:board_id order by b.id desc")
	List<BoardItem> sortBoardItems(int board_id);
		
	@Modifying
	@Transactional
	@Query(value="DELETE from BoardItem b where board_id=:board_id and id=:id")
	void deleteOneByBoard_idAndId(int board_id, int id);
	
	@Query(value = "SELECT max(id) FROM BoardItem")
	Integer totalMax();
	
	@Query(value = "SELECT max(id) FROM BoardItem where board_id=:board_id")
	Integer max(@Param("board_id") int board_id);
		
	@Query(value = "SELECT b FROM BoardItem b where b.id=:id and board_id=:board_id")
	Optional<BoardItem> findOneByIdAndBoard_id(@Param("id")int id, @Param("board_id")int board_id);
		
	@Modifying
	@Transactional
	@Query(value = "update BoardItem b set b.viewCnt=viewCnt+1 where board_id=:board_id and b.id=:id")
	Integer addViewCnt(@Param("board_id") int board_id, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "update BoardItem b set b.title=:title, b.content=:content where board_id=:board_id and b.id=:id")
	Integer setBoardItem(@Param("title") String title, @Param("content") String content,@Param("board_id") int board_id, @Param("id") int id);
	
}

