package kr.ac.kopo.kopo40.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kr.ac.kopo.kopo40.domain.Board;

public class BoardDaoImpl implements BoardDao {
	static BoardDaoImpl instance = null;
	public static BoardDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}

	@Override
	public void create(Board board) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "insert into boardList(board_title) values('" + board.getBoard_title() + "');";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Override
	public Board selectOne(int id) {
		Board selectOne = new Board();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "select * from BoardList where board_index=" + id + ";";
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				int board_index = rset.getInt(1);
				String board_title = rset.getString(2);
				selectOne.setBoard_index(board_index);
				selectOne.setBoard_title(board_title);
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

		return selectOne;
	}

	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		List<Board> boardAll = new ArrayList<Board>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from boardList;");

			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				int board_index = rset.getInt(1);
				String board_title = rset.getString(2);
				Board board = new Board();
				board.setBoard_index(board_index);
				board.setBoard_title(board_title);
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
	public void update(Board board) {
		// TODO Auto-generated method stub
		String new_title = board.getBoard_title();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
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
		int board_index = board.getBoard_index();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("delete from boardList where board_idex=" + board_index + ";");
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
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
		int get_id = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select max(board_index) from boardList;");

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
}