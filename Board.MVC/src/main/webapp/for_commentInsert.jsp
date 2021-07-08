<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<%@ page import="kr.ac.kopo.kopo40.data.Data" %>
<%
	String IP = Data.IP;
%>
<% 
	request.setCharacterEncoding("UTF-8");
	String board_index = request.getParameter("board_index"); 
	String post_id = request.getParameter("post_id"); 
	String comment_name = request.getParameter("comment_name"); 	
	String comment_contents = request.getParameter("comment_contents"); 		
	
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
try{
		Class.forName("com.mysql.cj.jdbc.Driver");  													
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");  
		Statement stmt = conn.createStatement(); 
		
		String QueryTxt;
		QueryTxt = "insert into comments(post_id,name,date,content) value ("+post_id+",'"+comment_name+"',date_format(now(),'%Y-%m-%d %I:%i:%s'),'"+comment_contents+"');";
		stmt.execute(QueryTxt);	
							
%>
<%
		stmt.close(); 
		conn.close(); 

}
catch (Exception e) {
	out.print(e);
}
%>
<script>
window.location='BoardItemView.jsp?board_index=<%=board_index%>&get_id=<%=post_id%>;
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
