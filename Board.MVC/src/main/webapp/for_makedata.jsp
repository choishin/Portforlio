<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*,java.io.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.Board"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardDaoImpl"%>
<%@ page import="kr.ac.kopo.kopo40.data.Data" %>
<%
	String IP = Data.IP;
%>
<html>
<head>
</head>
<body>
	<h1>테이블 만들기</h1>
	<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String QueryTxt = "drop table boardList;";
		stmt.execute(QueryTxt);
		out.println("drop boardList OK<br>");
		stmt.close();
		conn.close();
	}
	catch (Exception e) {
		out.println(e);
	}
		
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
		out.println("create boardList OK<br>");
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();

		String QueryTxt;
		QueryTxt = "insert into boardList(title,info)values('board1','클릭해서 게시글을 보고, 댓글 달기와 조회수 보기가 가능한 게시판');";
		stmt.executeUpdate(QueryTxt);
		out.println("insert boardList 'board1' OK<br>");
		
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String QueryTxt = "drop table board1;";
		stmt.execute(QueryTxt);
		out.println("drop table 'board1' OK<br>");
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
		+ "date datetime," + "content text," + "viewcnt int," + "board_index int not null"+");";
		stmt.execute(QueryTxt);
		out.println("create table 'board1' OK<br>");
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}	
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String sql = "";
		int amount = 5;
		for (int i = 1; i <= amount; i++) {
			sql = "insert into board1(title,date,content,viewcnt,board_index) value('공지" + i
			+ "',date_format(now(),'%Y-%m-%d %I:%i:%s'),'공지내용" + i + "',0,1)";
			stmt.execute(sql);
		}
		out.println("insert board1 data OK<br>");
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/kopoctc", "root", "kopoctc");
		Statement stmt = conn.createStatement();
		String QueryTxt = "drop table comments;";
		stmt.execute(QueryTxt);
		out.println("drop table comments OK<br>");
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
		QueryTxt = "create table comments ("
		+"board_index int not null,"
		+"post_id int not null," +"name varchar(20) not null,"
		+"date datetime," 
		+"content text not null"+");";
		stmt.execute(QueryTxt);
		out.println("create table comments OK<br>");
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
		QueryTxt = "ALTER TABLE boardList ADD FOREIGN KEY (id) REFERENCES board1(id);";
		stmt.execute(QueryTxt);
		out.println("set foreign key boardList OK<br>");
		stmt.close();
		conn.close();
	} catch (Exception e) {
		out.println(e);
	}

	%>
</body>
</html>