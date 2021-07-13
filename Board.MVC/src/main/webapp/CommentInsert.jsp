<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<%@ page import="kr.ac.kopo.kopo40.domain.Comment"%>
<%@ page import="kr.ac.kopo.kopo40.dao.CommentDao"%>
<%@ page import="kr.ac.kopo.kopo40.dao.CommentDaoImpl"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String board_id = request.getParameter("board_id"); 
	String post_id = request.getParameter("post_id"); 
	String name = request.getParameter("name"); 	
	String content = request.getParameter("content"); 			
%>
<html> 
<head>
<SCRIPT LANGUAGE="JavaScript"> 
function getDate(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var date = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	document.write(year+"년 "+month+"월 "+date+"일 "+hours+":"+minutes+":"+seconds);
}
</SCRIPT> 
</head> 
<body> 
<%
	CommentDao cd = new CommentDaoImpl();
	Comment comment = new Comment();
	comment.setBoard_id(Integer.parseInt(board_id));
	comment.setPost_id(Integer.parseInt(post_id));
	comment.setName(name);
	comment.setContent(content);
	cd.createComment(comment);

%>
<script>
window.location='BoardItemView.jsp?board_id=<%=board_id%>&get_id=<%=post_id%>';
function characterCheck(obj){
	var regExp = /[\{\}\\?.,;(\)*~~\'!^-_+<>!\#$%&\'\"\(\=]/gi;
	if( regExp.test(obj.value) ) {
	alert('특수문자는 입력하실수 없습니다.');
	obj.value = obj.value.substring(0, obj.value.length - 1 );
	}
}
function noSpaceForm(obj) { 
	var str_space = /\s/;
	if(str_space.exec(obj.value)) { 
		alert("해당 항목에는 공백을 사용할수 없습니다.");
		obj.focus();
		obj.value = obj.value.replace(' ',''); 
	}
}
</script>
</body> 
</html>
