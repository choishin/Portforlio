package kr.ac.kopo.kopo40.dao;

import java.util.List;
import kr.ac.kopo.kopo40.domain.Comment;

public interface CommentDao {
	
	void createComment(Comment comment);
	List<Comment> getComments(int post_id);
	void updateComment (Comment comment);
	void deleteComment (int post_id, String comment_date);

}
