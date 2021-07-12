<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

#mainbody {
	width: 1100px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#paging {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 100px;
}

#buttons {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: 1050px;
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
						aria-current="page" href="/SpringBoard-Main/BoardList">Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href='/SpringBoard-Main/BoardItemList/1'>board1</a></li>
					<li class="nav-item"><a class="nav-link"
						href='/SpringBoard-Main/BoardItemList/2'>board2</a></li>
					<li class="nav-item"><a class="nav-link"
						href='/SpringBoard-Main/BoardItemList/3'>board3</a></li>
				</ul>
				<form class="d-flex" method='post'
					action='/SpringBoard-Main/BoardItemSearch'>
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
		<h1>
			<c:out value="${board.title}" />
		</h1>
	</center>
	<br>
	<br>
	<br>
	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" width="50px"><p align=center>번호</p></th>
					<th scope="col" width="400px"><p align=center>제목</p></th>
					<th scope="col" width="100px"><p align=center>조회수</p></th>
					<th scope="col" width="200px"><p align=center>등록일</p></th>
				</tr>
			<tbody>
				<c:forEach var="boardItem" items="${boardItems.getContent()}" varStatus="status">
					<tr>
						<th scope='row' width='50px;'>
							<p align=center>
								<%-- ${fn:length(boardItems) - status.index} --%>
								<c:out value="${boardItem.id}" />
							</p>
						</th>
						<td width=500 width='400px'><p align=center>
								<a
									href='/SpringBoard-Main/BoardItemView/${board.id}/${boardItem.id}'>${boardItem.title}</a>
							</p></td>
						<td width=50 width='100px'>${boardItem.viewCnt}</td>
						<td width='200px'>
							<p align=center>${boardItem.date}</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container">
		<table class="buttons">
			<tr>
				<td width=1100></td>
				<td>
					<div class="btn-group btn-group" role="group"
						aria-label="Basic outlined example">
						<input type="button" class="btn btn-outline-secondary" value="목록"
							OnClick="location.href='/SpringBoard-Main/BoardItemList/${board.getId()}'">
					</div>
				</td>
				<td>
					<div class="btn-group btn-group" role="group"
						aria-label="Basic outlined example">
						<input type="button" class="btn btn-outline-secondary" value="신규"
							OnClick="window.location='/SpringBoard-Main/BoardItemInsert/${board.getId()}'">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<!--페이징-->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
			<!-- 이전 -->
			<c:choose>
				<c:when test="${boardItems.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=0">처음</a></li>
					<li class="page-item"><a class="page-link"
						href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=${boardItems.number-1}">&larr;</a></li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 그룹 -->
			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
				<c:choose>
					<c:when test="${boardItems.pageable.pageNumber+1 == i}">
						<li class="page-item disabled"><a class="page-link"
							href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 -->
			<c:choose>
				<c:when test="${boardItems.last}"></c:when>
				<c:otherwise>
					<li class="page-item "><a class="page-link"
						href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=${boardItems.number+1}">&rarr;</a></li>
					<li class="page-item "><a class="page-link"
						href="/SpringBoard-Main/BoardItemList/${board.getId()}?field=${param.field}&word=${param.word}&page=${boardItems.totalPages-1}">마지막</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

</body>
</html>