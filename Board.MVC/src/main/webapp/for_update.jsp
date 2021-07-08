<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<html> 
<head>
</head> 
<body> 

<form method="get" action="BoardItemUpdate.jsp"> 
	게시판 번호: <input type="text" name="board_index"><br> 
	글번호: <input type="text" name="get_id"><br> 
		<input type="submit" value="전송"> 
</form> 
</body> 
</html>