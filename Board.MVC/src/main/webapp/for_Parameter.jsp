<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<html> 
<head>
</head> 
<body> 
<h4>BoardItemList</h4>
<form method="get" action="BoardItemList.jsp"> 
	게시판 번호: <input type="text" name="board_id"><br> 
	시작 게시물: <input type="text" name="startNum"><br> 
	게시글 개수: <input type="text" name="countPage"><br> 
	<input type="submit" value="전송"> 
</form> 
<form method="get" action="BoardItemAccordion.jsp"> 
	게시판 번호: <input type="text" name="board_id"><br> 
	<input type="submit" value="전송"> 
</form> 
<h4>BoardItemView</h4>
<form method="get" action="BoardItemView.jsp"> 
	게시판번호: <input type="text" name="board_id"><br> 
	글번호: <input type="text" name="get_id"><br> 
	<input type="submit" value="전송"> 
</form> 
<h4>BoardItemInsert</h4>
<form method="get" action="BoardItemInsert.jsp"> 
	게시판 번호: <input type="text" name="board_id"><br> 
	<input type="submit" value="전송"> 
</form> 
<h4>BoardItemUpdate</h4>
<form method="get" action="BoardItemUpdate.jsp"> 
	게시판 번호: <input type="text" name="board_id"><br> 
	글번호: <input type="text" name="get_id"><br> 
		<input type="submit" value="전송"> 
</form> 
<h4>BoardItemDelete</h4>
<form method="get" action="BoardItemDelete.jsp"> 
	게시판번호: <input type="text" name="board_id"><br> 
	글번호: <input type="text" name="get_id"><br> 
	<input type="submit" value="전송"> 
</form> 
</body> 
</html>