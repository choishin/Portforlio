package kr.ac.kopo.kopo40.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kr.ac.kopo.kopo40.domain.BoardItem;
import kr.ac.kopo.kopo40.domain.Comment;

public class BoardItemDaoImpl implements BoardItemDao {
	static final String IP = "192.168.171.18";
	static BoardItemDaoImpl instance = null;
	public static BoardItemDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardItemDaoImpl();
		}
		return instance;
	}

	@Override
	public void create(BoardItem boardItem) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-DD-mm");		
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "insert into gongji(title,date,content) value('" +boardItem.getTitle()+ "',"+sdf.format(date)+",'" + boardItem.getContent()+ "',0)";
			stmt.execute(QueryTxt);
			System.out.print("<h1>게시물 등록 완료</h1>");

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}

	@Override
	public BoardItem selectOne(int id) {
		// TODO Auto-generated method stub
		BoardItem newBoardItem = new BoardItem();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  													
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc","root" , "kopoctc");  
			Statement stmt = conn.createStatement(); 
				
			String QueryTxt;
			QueryTxt = String.format("select * from board"+id+";");
			
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while(rset.next()) {	
				String title = rset.getString(2);
				String date = rset.getString(3);
				String content = rset.getString(4);
				newBoardItem.setId(id);
				newBoardItem.setTitle(title);
				newBoardItem.setDate(date);
				newBoardItem.setContent(content);
			}
				
			rset.close();
			stmt.close(); 
			conn.close(); 
		}
	catch (Exception e) {
		System.out.print(e);
	}		
		return newBoardItem ;
	}

	@Override
	public List<BoardItem> selectAll(int board_index) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItemAll = new ArrayList<BoardItem>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from board"+board_index+" order by id desc;");

			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				int id = rset.getInt(1);
				String title = rset.getString(2);
				String date = rset.getString(3);
				String content = rset.getString(4);
				int viewcnt = rset.getInt(5);
				BoardItem boardItem = new BoardItem();
				boardItem.setId(id);
				boardItem.setTitle(title);
				boardItem.setDate(date);
				boardItem.setContent(content);
				boardItem.setViewcnt(viewcnt);				
				boardItemAll.add(boardItem);
			}

			rset.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.print(e);
		}
		
		return boardItemAll;
	}

	@Override
	public void update(BoardItem boardItem) {
		// TODO Auto-generated method stub
			int boardItem_index = boardItem.getId();
			String boardItem_title = boardItem.getTitle();
			String boardItem_date= boardItem.getDate();
			String boardItem_content = boardItem.getContent();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
				Statement stmt = conn.createStatement();

				String QueryTxt;
				QueryTxt = "UPDATE gongji SET title = '"+boardItem_title+"', date="+boardItem_date+", content='"+boardItem_content+"' WHERE id="+boardItem_index+";";
				stmt.execute(QueryTxt);

				stmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.print(e);
			}

		}
		
	@Override
	public void delete(BoardItem boardItem) {
		// TODO Auto-generated method stub
		int boardItem_index = boardItem.getId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("delete from gongji where id=" + boardItem_index + ";");
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public int totalCount() {
		int totalCnt = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt;
			QueryTxt = String.format("select count(*) from gongji;");
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				totalCnt = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return totalCnt;
	}

	public int getMax() {
		int get_id = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select max(id) from gongji;");

			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String id = rset.getString(1);
				get_id = Integer.parseInt(id);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return get_id;
	}
	
	public void addCount(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("update gongji set viewcnt=viewcnt+1 where id=" + id + ";");
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
