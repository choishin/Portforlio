<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*"%>
<%@ page import="kr.ac.kopo.kopo40.data.Data" %>
<%
	String IP = Data.IP;
%>
<%
request.setCharacterEncoding("UTF-8");
String keyword = request.getParameter("keyword");
%>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<SCRIPT LANGUAGE="JavaScript">
	function getDate() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth();
		var date = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();
		document.write(year + "�� " + month + "�� " + date + "�� " + hours + ":"
				+ minutes + ":" + seconds);
	}
</SCRIPT>
<style>
.contaner {
	/*flex : ������ ���� �����̳�*/
	display: flex;
	/*���� ���� ���� (���ο�����)*/
	justify-content: flex-start;
	/*������ ���� ���� (���μ��θ�ο���)*/
	align-items: flex-start;
}
#table {
	width: 900px;
	margin-top: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}
tr, th {
	text-align: center;
	vertical-align: middle;
}
</style>
</head>
<%
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
	Statement stmt = conn.createStatement();
	String QueryTxt;
	QueryTxt = String.format(
	"SELECT count(*) FROM board1 WHERE title LIKE '%%" + keyword + "%%' or content like '%%" + keyword + "%%';");
	ResultSet rset = stmt.executeQuery(QueryTxt);
	String result_id = "";
	String Result = "";
	while (rset.next()) {
		result_id = rset.getString(1);
		if (Integer.parseInt(result_id)==0) {
			Result = "<h3>검색 결과가 없습니다.</h3>";
		} else {
			Result = "<h3>" + keyword + " 검색 결과 입니다. </h3>";
		}
	}
%>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Board-Project</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="BoardList.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href='BoardItemList.jsp?board_index=1'>board1</a></li>
					<li class="nav-item"><a class="nav-link" href='BoardItemView_accordion.jsp'>board2</a></li>
				</ul>
				<form class="d-flex" method='get' action='BoardItemSearch.jsp'>
					<input class="form-control me-2" type="text" placeholder="Search"
						aria-label="Search" name="keyword"> <input
						class="btn btn-outline-secondary" type="submit" value="Search">
				</form>
			</div>
		</div>
	</nav>
	<br>
		<%=Result%>
		<br>
	<table class="table table-hover">
		<thead>
				<tr>
					<th scope="col" width="50px"><p align=center>게시판번호</p></th>
					<th scope="col" width="50px"><p align=center>글번호</p></th>
					<th scope="col" width="200px"><p align=center>제목</p></th>
					<th scope="col" width="50px"><p align=center>조회수</p></th>
					<th scope="col" width="200px"><p align=center>등록일</p></th>
					<th scope="col" width="500px"><p align=center>내용</p></th>
				</tr>
				<tbody>
		<% 
				stmt = conn.createStatement();
				QueryTxt = String.format(
				"SELECT * FROM board1 WHERE title LIKE '%%" + keyword + "%%' or content like '%%" + keyword + "%%';");
				rset = stmt.executeQuery(QueryTxt);
				String id = "";
				String title = "";
				String date = "";
				String content = "";
				String viewcnt = "";
				String board_index ="";
				while (rset.next()) {
					id = rset.getString(1);
					title = rset.getString(2);
					date = rset.getString(3);
					content = rset.getString(4);
					viewcnt = rset.getString(5);
					board_index = rset.getString(6);
					
					out.println("<tr>");
					out.println("<td>");
					out.println(board_index);
					out.println("</td>");
					out.println("<td>");
					out.println(id);
					out.println("</td>");
					out.println("<td>");
					out.println(title);					
					out.println("</td>");
					out.println("<td>");
					out.println(viewcnt);
					out.println("</td>");					
					out.println("<td>");
					out.println(date);
					out.println("</td>");
					out.println("<td>");
					out.println(content);
					out.println("</td>");
					out.println("</tr>");
				}
			%>	
		</tbody>
	</table>
	<div class="container">
		<div id="textbox">
			<table>
				<tr>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td width="100"></td>
					<td width="900"><input class="btn btn-outline-secondary"
						type=button value="게시판 목록" OnClick="location.href='BoardList.jsp'">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		function characterCheck(obj) {
			var regExp = /[\{\}\\?.,;(\)*~~\'!^-_+<>!\#$%&\'\"\(\=]/gi;
			if (regExp.test(obj.value)) {
				alert('특수문자는 입력할 수 없습니다.');
				obj.value = obj.value.substring(0, obj.value.length - 1);
			}
		}
		function noSpaceForm(obj) {
			var str_space = /\s/;
			if (str_space.exec(obj.value)) {
				alert("공백은 입력할 수 없습니다.");
				obj.focus();
				obj.value = obj.value.replace(' ', '');
			}
		}
	</script>
	<%
	rset.close();
	stmt.close();
	conn.close();
	} catch (Exception e) {
	out.print(e);
	}
	%>
</body>
</html>