<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 

<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*,java.io.*" %> 
<html> 
<head>
</head> 
<body>
 <h1>테이블 만들기</h1>
 <%
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.171.18:3306/kopoctc", "root", "kopoctc");
	Statement stmt = conn.createStatement(); 
%>
<%
try{
	stmt.execute("drop table comments;");
	out.println("drop table comments OK<br>");
}
catch (Exception e) {
	out.println("drop table commments Not OK<br>");
	out.println(e.toString());
}
%>
<%
	stmt.execute("create table comments("
				+ "post_id int not null,"
				+ "comment_name varchar(20),"
				+ "comment_contents varchar(200),"
				+ "comment_date datetime"
				+ ");"); 
	out.println("create table OK<br>");
	stmt.close();
	conn.close(); 
%> 
</body> 
</html>