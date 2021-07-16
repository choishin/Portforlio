<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemService"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemServiceImpl"%>
<%
request.setCharacterEncoding("UTF-8");
String board_id = request.getParameter("board_id");
String get_id = request.getParameter("get_id");
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
<style>
.contaner {
	/*flex : 정렬을 위한 컨테이너*/
	display: flex;
	/*주측 방향 정렬 (가로에서만)*/
	justify-content: flex-start;
	/*교차측 방향 정렬 (가로세로모두에서)*/
	align-items: flex-start;
}

#textbox {
	margin-top: 100px;
	width: 1000px;
}

h1, h4 {
	color: #767676;
}
</style>
</head>
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
					<li class="nav-item"><a class="nav-link"
						href='BoardItemList.jsp?board_id=1&startNum=1&countPage=10'>board1</a></li>
					<li class="nav-item"><a class="nav-link"
						href='BoardItemList.jsp?board_id=2&startNum=1&countPage=10'>board2</a></li>
				</ul>
				<form class="d-flex" method='get' action='BoardItemSearch.jsp'>
					<input class="form-control me-2" type="text" placeholder="Search"
						aria-label="Search" name="keyword"> <input type="hidden"
						name="startNum" value="1" /> <input type="hidden" name="countPage"
						value="10" /> <input class="btn btn-outline-secondary"
						type="submit" value="Search">
				</form>
			</div>
		</div>
	</nav>
	<%
	 BoardItemService bs = BoardItemServiceImpl.getInstance();
	 bs.delete(Integer.parseInt(board_id),Integer.parseInt(get_id));	
	%>
	<div class="container">
		<div id="textbox">
			<table>
				<tr>
					<td colspan="2"><h1>게시물 삭제 완료</h1></td>
				</tr>
				<tr>
					<td width="100"></td>
					<td width="900"><input class="btn btn-outline-secondary"
						type=button value="목록"
						OnClick="location.href='BoardItemList.jsp?board_id=<%=board_id%>&startNum=1&countPage=10'">
						<input class="btn btn-outline-secondary" type=button value="신규"
						OnClick="location.href='BoardItemInsert.jsp?board_id=<%=board_id%>'"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>