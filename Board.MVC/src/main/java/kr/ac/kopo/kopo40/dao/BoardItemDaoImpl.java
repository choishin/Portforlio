package kr.ac.kopo.kopo40.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.kopo40.data.Data;
import kr.ac.kopo.kopo40.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {

	static BoardItemDaoImpl instance = null;

	public static BoardItemDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardItemDaoImpl();
		}
		return instance;
	}

	String IP = Data.IP;

	@Override
	public void create(BoardItem boardItem) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "insert into boardItem(title,date,content,viewcnt,board_id) value('" + boardItem.getTitle()
					+ "',date_format(now(),'%Y-%m-%d %I:%i:%s'),'" + boardItem.getContent() + "',0,"
					+ boardItem.getBoard_id() + ")";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

	@Override
	public List<BoardItem> selectAll(int board_id) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItemAll = new ArrayList<BoardItem>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from boardItem order by id desc where board_id=" + board_id + ";");

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
	public BoardItem selectOne(int board_id, int id) {
		// TODO Auto-generated method stub
		BoardItem newBoardItem = new BoardItem();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select * from boardItem where board_id=" + board_id + " and id=" + id+ ";");
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				String title = rset.getString(2);
				String date = rset.getString(3);
				String content = rset.getString(4);
				int viewcnt = rset.getInt(5);
				newBoardItem.setId(id);
				newBoardItem.setTitle(title);
				newBoardItem.setDate(date);
				newBoardItem.setContent(content);
				newBoardItem.setViewcnt(viewcnt);
			}

			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return newBoardItem;
	}

	@Override
	public void update(BoardItem boardItem) {
		// TODO Auto-generated method stub
		int board_id = boardItem.getBoard_id();
		int boardItem_index = boardItem.getId();
		String boardItem_title = boardItem.getTitle();
		String boardItem_content = boardItem.getContent();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = "UPDATE boardItem SET title = '" + boardItem_title + "', date=date_format(now(),'%Y-%m-%d %I:%i:%s'), content='"
					+ boardItem_content + "' WHERE board_id=" + board_id + " and id=" + boardItem_index + ";";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

	@Override
	public void delete(int board_id, int id) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("delete from boardItem where board_id=" + board_id + " and id=" + id + ";");
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public int totalCount(int board_id) {
		int totalCnt = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt;
			QueryTxt = String.format("select count(*) from boardItem where board_id=" + board_id + ";");
			ResultSet rset = stmt.executeQuery(QueryTxt);
			while (rset.next()) {
				totalCnt = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return totalCnt;
	}

	public int getMax(int board_id) {
		int maxId = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("select max(id) from boardItem where board_id=" + board_id + ";");

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

	public void addViewCount(int board_id, int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format(
					"update boardItem set viewcnt=viewcnt+1 where board_id=" + board_id + " and id=" + id + ";");
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<BoardItem> limitPaging(int startNum, int countPage, int board_id) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItemAll = new ArrayList<BoardItem>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			//startNum에 -1을 해준 이유: 들어온 자료는 0부터 인덱스가 시작되기 때문에, 1부터 출력을 하겠다고 했을 때 실제로 들어와야 하는 값은 0
			QueryTxt = String.format("select * from boardItem where board_id=" + board_id + " order by id desc limit "
					+ (startNum-1) + "," + countPage + ";");

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
	public void drop() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			String QueryTxt;
			QueryTxt = String.format("drop table boardItem;");
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
			QueryTxt = "create table boardItem (" + "id int not null primary key auto_increment,"
					+ "title varchar (70)," + "date datetime," + "content text," + "viewcnt int,"
					+ "board_id int not null" + ");";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public List<BoardItem> searchItems(String keyword) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			String QueryTxt;
			QueryTxt = String.format("SELECT count(*) FROM boardItem WHERE title LIKE '%%" + keyword
					+ "%%' or content like '%%" + keyword + "%%';");
			ResultSet rset = stmt.executeQuery(QueryTxt);
			int id = 0;
			String title = "";
			String date = "";
			String content = "";
			int viewcnt = 0;
			int board_id = 0;
			while (rset.next()) {
				BoardItem boardItem = new BoardItem();
				id = rset.getInt(1);
				title = rset.getString(2);
				date = rset.getString(3);
				content = rset.getString(4);
				viewcnt = rset.getInt(5);
				board_id = rset.getInt(6);

				boardItem.setId(id);
				boardItem.setTitle(title);
				boardItem.setDate(date);
				boardItem.setContent(content);
				boardItem.setViewcnt(viewcnt);
				boardItem.setBoard_id(board_id);
				boardItems.add(boardItem);
			}

			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e);
		}

		return boardItems;
	}

}
