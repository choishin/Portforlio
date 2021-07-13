<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.BoardItem"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardService"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardServiceImpl"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemService"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemServiceImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
.contaner {
	display: flex;
	justify-content: flex-start;
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
	BoardItemService bis = new BoardItemServiceImpl();
	List<BoardItem> boardItems = bis.searchItems(keyword);
	
	String result ="";
	if (boardItems.size() == 0) {
			result = "<h3>검색 결과가 없습니다.</h3>";
		} else {
			result = "<h3>" + keyword + " 검색 결과 입니다. </h3>";
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
					<li class="nav-item"><a class="nav-link"
						href='BoardItemList.jsp?board_id=1&startNum=1&countPage=10'>board1</a></li>
					<li class="nav-item"><a class="nav-link"
						href='BoardItemList.jsp?board_id=2&startNum=1&countPage=10'>board2</a></li>
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
	<%=result%>
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
			<c:forEach var="boardItem" items="${boardItems}" varStatus="status">
				<tr>
					<td><c:out value="${boardItem.board_id}" /></td>
					<td><c:out value="${boardItem.id}" /></td>
					<td><c:out value="${boardItem.title}" /></td>
					<td><c:out value="${boardItem.viewcnt}" /></td>
					<td><c:out value="${boardItem.date}" /></td>
					<td><c:out value="${boardItem.content}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="container">
		<div id="textbox">
			<table>
				<tr>
					<td colspan="2"></td>
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
</body>
</html>