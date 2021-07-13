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
		try{
				Class.forName("com.mysql.cj.jdbc.Driver");  													
				Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");  
				Statement stmt = conn.createStatement(); 			
				String QueryTxt;
				QueryTxt = "insert into comments(board_id,post_id,name,date,content) value ("+comment.getBoard_id()+","+comment.getPost_id()+",'"+comment.getName()+"',date_format(now(),'%Y-%m-%d %I:%i:%s'),'"+comment.getContent()+"');";
				stmt.execute(QueryTxt);	

				stmt.close(); 
				conn.close(); 
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	@Override
	public List<Comment> getComments(int board_id, int post_id) {
		// TODO Auto-generated method stub
		List<Comment> commentsAll = new ArrayList<Comment>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from comments where board_id="+board_id+" and post_id=" + post_id + ";");
			ResultSet rset = stmt.executeQuery(QueryTxt);

			while (rset.next()) {
				Comment comment = new Comment();
				String name = rset.getString(3);
				String date = rset.getString(4);
				String content = rset.getString(5);
				
				comment.setPost_id(post_id);
				comment.setName(name);
				comment.setDate(date);	
				comment.setContent(content);		
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

	@Override
	public void drop() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt = "drop table comments;";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void createComments() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt = "";
			QueryTxt = "create table comments ("
			+"board_id int not null,"
			+"post_id int not null," 
			+"name varchar(20) not null,"
			+"date datetime," 
			+"content text not null"+");";
			stmt.execute(QueryTxt);
	
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}

}
