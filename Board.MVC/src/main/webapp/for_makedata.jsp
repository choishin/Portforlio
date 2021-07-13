<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*,java.io.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.Board"%>
<%@ page import="kr.ac.kopo.kopo40.domain.BoardItem"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardDao"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardDaoImpl"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardItemDao"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardItemDaoImpl"%>
<%@ page import="kr.ac.kopo.kopo40.dao.CommentDao"%>
<%@ page import="kr.ac.kopo.kopo40.dao.CommentDaoImpl"%>
<html>
<head>
</head>
<body>
	<h1>테이블 만들기</h1>
	<%
	BoardDao bd = new BoardDaoImpl();
	bd.drop();
	out.println("drop table boardList OK<br>");
	bd.createTable();
	out.println("create table boardList OK<br>");
	
	Board board1 = new Board();
	board1.setTitle("board1");
	board1.setInfo("게시판1");
	bd.create(board1);
	
	board1.setTitle("board2");
	board1.setInfo("게시판2");
	bd.create(board1);
	out.println("insert boardList data OK<br>");
	
	BoardItemDao bid = new BoardItemDaoImpl();
	bid.drop();
	out.println("drop table boardItem<br>");
	bid.createTable();
	out.println("create table boardItem<br>");
	
	int amount = 5;
	for (int i = 1; i <= amount; i++) {
	BoardItem boardItem = new BoardItem();
	boardItem.setId(1);
	boardItem.setTitle("공지"+i);
	boardItem.setContent("공지내용");
	boardItem.setViewcnt(0);
	boardItem.setBoard_id(1);
	bid.create(boardItem);
	}
	out.println("insert boardItem data OK<br>");
	
	CommentDao cd = new CommentDaoImpl();
	cd.drop();
	out.println("drop table comment OK<br>");
	cd.createComments();
	out.println("create table comment OK<br>");

	%>
</body>
</html>