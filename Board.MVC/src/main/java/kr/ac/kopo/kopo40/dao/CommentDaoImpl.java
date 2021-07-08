package kr.ac.kopo.kopo40.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.kopo40.data.Data;
import kr.ac.kopo.kopo40.domain.Comment;

public class CommentDaoImpl implements CommentDao{

	String IP = Data.IP;
	
	@Override
	public void createComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Comment> getComments(int post_id) {
		// TODO Auto-generated method stub
		List<Comment> commentsAll = new ArrayList<Comment>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from comments where post_id=" + post_id + ";");
			ResultSet rset = stmt.executeQuery(QueryTxt);

			while (rset.next()) {
				Comment comment = new Comment();
				String comment_name = rset.getString(2);
				String comment_contents = rset.getString(3);
				String comment_date = rset.getString(4);
				
				comment.setPost_id(post_id);
				comment.setComment_name(comment_name);
				comment.setComment_contents(comment_contents);
				comment.setComment_date(comment_date);			
				commentsAll.add(comment);
			}

			rset.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return commentsAll;
	}

	@Override
	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(int post_id, String comment_date) {
		// TODO Auto-generated method stub
		
	}

}
