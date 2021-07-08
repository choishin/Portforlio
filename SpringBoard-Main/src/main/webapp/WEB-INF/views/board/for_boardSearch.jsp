<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<html> 
<head>
</head> 
<body> 
<form method="get" action="/SpringBoard-Main/board/boardItemSearch"> 
	게시판 번호: <input type="text" name="board_index"><br> 
	<input type="submit" value="전송"> 
</form> 
</body> 
</html>