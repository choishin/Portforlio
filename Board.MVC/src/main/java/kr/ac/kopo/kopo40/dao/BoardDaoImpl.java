package kr.ac.kopo.kopo40.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.kopo40.data.Data;
import kr.ac.kopo.kopo40.domain.Board;

public class BoardDaoImpl implements BoardDao {
	static BoardDaoImpl instance = null;
	public static BoardDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}

	String IP = Data.IP;
	
	@Override
	public void create(Board board) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt;
			QueryTxt = "insert into boardList(title,info) values('" + board.getTitle() + "','"+board.getInfo()+"');";
			stmt.executeUpdate(QueryTxt);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	

	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		List<Board> boardAll = new ArrayList<Board>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from boardList;");

			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				int board_index = rset.getInt(1);
				String board_title = rset.getString(2);
				Board board = new Board();
				board.setBoard_id(board_index);
				board.setTitle(board_title);
				boardAll.add(board);
			}

			rset.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.print(e);
		}
		return boardAll;
	}
	
	@Override
	public Board selectOne(int board_id) {
		Board selectOne = new Board();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "select * from boardList where id=" + board_id + ";";
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				int board_index = rset.getInt(1);
				String board_title = rset.getString(2);
				String board_info = rset.getString(3);
				selectOne.setBoard_id(board_index);
				selectOne.setTitle(board_title);
				selectOne.setInfo(board_info);
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

		return selectOne;
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		String new_title = board.getTitle();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "update boardList set board_title='" + new_title + "';";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		int board_id = board.getBoard_id();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("delete from boardList where board_id=" + board_id + ";");
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
			QueryTxt = String.format("select count(*) from boardList;");
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
		int maxId = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select max(id) from boardList;");

			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String id = rset.getString(1);
				maxId = Integer.parseInt(id);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return maxId;
	}
	
	public void drop() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("drop table boardList;");
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt = "";
			QueryTxt = "create table boardList ("+
			"id int not null primary key auto_increment,"+
			"title varchar (70),"+
			"info varchar(200)"+
			");";
			stmt.execute(QueryTxt);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}