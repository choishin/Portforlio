<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*,java.io.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.Board"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardDaoImpl"%>
<html>
<head>
</head>
<body>
	<h1>테이블 만들기</h1>
	<%
	String IP = "192.168.171.18";
	BoardDaoImpl bd = new BoardDaoImpl();
	//테이블이 있다면 없애기
	bd.drop("board1");
	out.println("drop table board1 OK<br>");

	//기본 테이블 만들기
// 			Board board = new Board();
// 			board.setBoard_title("board1");
// 			board.setBoard_info("클릭해서 게시글을 보고, 댓글 달기와 조회수 보기가 가능한 게시판");
// 			bd.create(board);
// 			out.println("create table OK<br>");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();

		String QueryTxt;
		QueryTxt = "insert into boardList(board_title,board_info)values('board1','클릭해서 게시글을 보고, 댓글 달기와 조회수 보기가 가능한 게시판');";
		stmt.executeUpdate(QueryTxt);
		
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String QueryTxt = "";
		QueryTxt = "create table board1 (" + "id int not null primary key auto_increment," + "title varchar (70),"
		+ "date datetime," + "content text," + "viewcnt int" + ");";
		stmt.execute(QueryTxt);
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}
// 	//테이블에 내용 넣기
	// 		String IP = "192.168.171.18";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String sql = "";
		int amount = 5;
		for (int i = 1; i <= amount; i++) {
			sql = "insert into board1(title,date,content,viewcnt) value('공지" + i
			+ "',date_format(now(),'%Y-%m-%d %I:%i:%s'),'공지내용" + i + "',0)";
			stmt.execute(sql);
		}
		out.println("insert data OK<br>");
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}
	%>
</body>
</html>