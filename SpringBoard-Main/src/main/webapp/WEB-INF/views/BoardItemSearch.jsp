<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
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
h1, h4 {
	color: #767676;
}

.space1 {
display: block;

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
				<form class="d-flex" method='post' action='/SpringBoard-Main/BoardItemSearch'>
					<input class="form-control me-2" type="text" placeholder="Search"
						aria-label="Search" name="keyword"> <input
						class="btn btn-outline-secondary" type="submit" value="Search">
				</form>
			</div>
		</div>
	</nav>
	<br> 
	<div class="space1" width=10px;></div><h1><c:out value="${keyword} 검색 결과입니다."/></h1>
	<br>
	<div class="space1" width=10px;></div><h4><c:out value="${noResult}"/></h4>
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
			<c:forEach var="boardItem" items="${boardItems.getContent()}">
				<tr>
					<td><c:out value="${boardItem.getBoard().getId()}" /></td>
					<td><c:out value="${boardItem.getId()}" /></td>
					<td><c:out value="${boardItem.getTitle()}" /></td>
					<td><c:out value="${boardItem.getViewCnt()}" /></td>
					<td><c:out value="${boardItem.getDate()}" /></td>
					<td><c:out value="${boardItem.getContent()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
		<!--페이징-->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
			<!-- 페이지 그룹 -->
			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
				<c:choose>
					<c:when test="${boardItems.pageable.pageNumber+1 == i}">
						<li class="page-item disabled"><a class="page-link"
							href="/SpringBoard-Main/BoardItemSearch/${keyword}?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/SpringBoard-Main/BoardItemSearch/${keyword}?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
	<br>
	<br>
	<div class="container">
		<div id="textbox">
			<table>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="1100"></td>
					<td><input class="btn btn-outline-secondary"
						type=button value="게시판 목록" OnClick="location.href='/SpringBoard-Main/BoardList'">
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