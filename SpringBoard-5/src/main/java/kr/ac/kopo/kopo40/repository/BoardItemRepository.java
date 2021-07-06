package kr.ac.kopo.kopo40.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.BoardItem;

@Repository
public interface BoardItemRepository {

	Page<BoardItem> findAll(Pageable pageable);
	Page<BoardItem> findAllByOrderByIdDesc(Pageable pageable);
	Page<BoardItem> findAllByTitle(String title, Pageable pageable);
	
	@Query("select t from BoardItem t where content like concat('%', :searchString , '%')")
	Page<BoardItem> findAllSearch(@Param("searchString") String searchString, Pageable pageable);
}
