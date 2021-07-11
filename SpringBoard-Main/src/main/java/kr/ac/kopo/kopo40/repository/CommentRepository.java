package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import kr.ac.kopo.kopo40.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment>  {
	
	@Query(value = "SELECT c FROM Comment c where post_id=:post_id")
	List<Comment> findOneByPost_id(@Param("post_id") int post_id);
	
	@Query(value = "SELECT c FROM Comment c where board_id=:board_id and post_id=:post_id")
	List<Comment> findOneByBoard_idAndPost_id(@Param("board_id") int board_id, @Param("post_id") int post_id);
	

	

}
