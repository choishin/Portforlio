<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<html> 
<head>
</head> 
<body> 
<form method="get" action="BoardItemList.jsp"> 
	게시판 번호: <input type="text" name="board_id"><br> 
	시작 게시물: <input type="text" name="startNum"><br> 
	게시글 개수: <input type="text" name="countPage"><br> 
	<input type="submit" value="전송"> 
</form> 
</body> 
</html>