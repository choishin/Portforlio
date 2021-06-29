<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.Board"%>
<%@ page import="kr.ac.kopo.kopo40.dao.BoardDaoImpl"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardServiceImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

table {
	text-align: center;
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
						href='BoardItem1.jsp'>board1</a></li>
					<li class="nav-item"><a class="nav-link"
						href='gongji_list2.jsp'>board2</a></li>
				</ul>
				<form class="d-flex" method='get' action='gongji_search.jsp'>
					<input class="form-control me-2" type="text" placeholder="Search"
						aria-label="Search" name="keyword"> <input
						class="btn btn-outline-secondary" type="submit" value="Search">
				</form>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<center>
		<h1>새 게시판 만들기</h1>
	</center>
	<br>
	<br>
	<br>
	<%
	try {
		BoardDaoImpl bd = new BoardDaoImpl();
		BoardServiceImpl bs = new BoardServiceImpl();
		int get_id = bs.getMax();
	%>
	<FORM METHOD=POST action="BoardWrite.jsp">
		<div class="container">
			<div id="table">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="vertical-align: top;"><b>번호</b></th>
							<th scope="col" style="vertical-align: top;">
								<div class="input-group mb-3">
									<input type="text" class="form-control" aria-label="Username"
										name=get_id value=<%=get_id + 1%> readonly>
								</div>
							</th>
						</tr>
						<tr>
							<th scope="col"><b>제목</b></th>
							<th scope="col"><input type="text" class="form-control"
								placeholder="제목" aria-label="Username" name=new_title size="20"
								maxlength="70" minlength="1" required></th>
						</tr>
						<tr>
							<th scope="col"><b>내용</b></th>
							<th scope="col">
								<div class="input-group">
									<textarea class="form-control" aria-label="With textarea"
										placeholder="내용을 작성해 주세요."
										style='width: 500px; height: 250px;' name=get_content cols=70
										row=600 required></textarea>
								</div>
							</th>
						</tr>

					</thead>
				</table>
			</div>
		</div>
		<%
		} catch (Exception e) {
		out.print(e);
		}
		%>
		<div class="container">
			<table class="buttons">
				<tr>
					<td width=780></td>
					<td>
						<button class="btn btn-outline-secondary"
							OnClick="location.href='BoardList.jsp'">취소</button>
					</td>
					<td><input class="btn btn-outline-secondary" type="submit"
						value="만들기"></td>
				</tr>
			</table>
		</div>
	</FORM>
	<script>
		function characterCheck(obj) {
			var regExp = /[\{\}\\?.,;(\)*~~\'!^-_+<>!\#$%&\'\"\(\=]/gi;
			if (regExp.test(obj.value)) {
				alert('특수문자는 입력하실수 없습니다.');
				obj.value = obj.value.substring(0, obj.value.length - 1);
			}
		}
		function noSpaceForm(obj) {
			var str_space = /\s/;
			if (str_space.exec(obj.value)) {
				alert("해당 항목에는 공백을 사용할수 없습니다.");
				obj.focus();
				obj.value = obj.value.replace(' ', '');
			}
		}
	</script>
	<br>
	<br>
	<br>
	<%-- <c:set var="board" value="<%=boards%>"/> --%>
	<c:forEach var="board" items="${boards}" varStatus="status">
		<p>${status.count}:
			<c:out value="${board}" />
		</p>
		<br>
	</c:forEach>

</body>
</html>
