package kr.ac.kopo.kopo40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment>  {
	
	List<Comment> findAllByPost_id(int post_id);

}
