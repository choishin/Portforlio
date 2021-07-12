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
<script>
	function getDate() {
		var now = new Date();
		var year = now.getFullYear();
		//자바스크립트의 시스템 시간이 1달 늦음
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();
		document.write(year + "년 " + month + "월 " + date + "일 " + hours + ":"
				+ minutes + ":" + seconds);
	}
</script>
<style>
.contaner {
	/*flex : 정렬을 위한 컨테이너*/
	display: flex;
	/*주측 방향 정렬 (가로에서만)*/
	justify-content: flex-start;
	/*교차측 방향 정렬 (가로세로모두에서)*/
	align-items: flex-start;
}

#buttons {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: 1050px;
}

h1, h2, h3, h4 {
	color: #767676;
}

.writer {
	width: 200px;
}

.contents {
	width: 600px;
}

.date {
	width: 300px;
}

.buttons {
	width: 200px;
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
	<FORM METHOD=post name='fm'>
		<div class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope='col' width=200px><b>번호</b></th>
						<td><c:out value="${boardItem.id}" /></td>
					</tr>
					<tr>
						<th scope='col' width=200px><b>조회수</b></th>
						<td><c:out value="${boardItem.viewCnt}" /></td>
					</tr>
					<tr>
						<th scope='col' width=200px><b>제목</b></th>
						<td><c:out value="${boardItem.title}" /></td>
					</tr>
					<tr>
						<th scope='col' width=200px><b>일자</b></th>
						<td><c:out value="${boardItem.date}" /></td>
					</tr>
					<tr>
						<th scope='col' width=200px
							style='vertical-align: top; height: auto;'><b>내용</b></th>
						<td height=200px style='vertical-align: top; height: auto;'><c:out
								value="${boardItem.content}" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</FORM>
	<br>
	<!-- 댓글보기 -->
	<div class="container">
		<h3>댓글보기</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" class="writer">작성자</th>
					<th scope="col" class="contents">내용</th>
					<th scope="col" class="date">일자</th>
					<th class="buttons"></th>
				</tr>
				<c:forEach var="comment" items="${comments}" varStatus="status">
					<tr>
						<td class='writer'><c:out value="${comment.name}" /></td>
						<td class='contents'><c:out value="${comment.getContents()}" /></td>
						<td class='date'><c:out value="${comment.getDateTime()}" /></td>
						<td class='buttons'></td>
					</tr>
				</c:forEach>
			</thead>
		</table>
	</div>
	<!--댓글창-->
	<br>
	<div class="container">
		<form method="post" action="/SpringBoard-Main/CommentInsert">
			<table class="table table-hover">
				<thead>
					<tr>
						<input type=hidden name="board_index"
							value="${boardItem.getBoard().getId()}">
						<input type=hidden name="post_id" value="${boardItem.id}">
					</tr>
					<tr>
						<th scope="col" class="writer" style="vertical-align: top;">
							<div class="input-group mb-3">
								<input type="text" class="form-control" placeholder="작성자"
									aria-label="Username" aria-describedby="basic-addon1"
									name='comment_name' placeholder='작성자' maxlength=70 minlength=1
									required>
							</div>
						</th>
						<th scope="col" class="contents">
							<div class="input-group">
								<textarea class="form-control" aria-label="With textarea"
									name='comment_contents' rows='2' cols='70'
									placeholder='댓글을 작성해주세요' maxlength=200 minlength=1 required></textarea>
							</div>
						</th>
						<th scope="col" class="date" style="vertical-align: top;"><script>
						getDate()
					</script></th>
						<th scope="col" style="vertical-align: top;"><input
							class="btn btn-outline-secondary" type="submit" value="등록">
							<input class="btn btn-outline-secondary" type="reset" value="취소">
						</th>
					</tr>
			</table>
		</form>
	</div>
	<div id="buttons">
		<table width=1000>
			<tr>
				<th scope="col" width=450></th>
				<th scope="col"><input class="btn btn-outline-secondary"
					type="button" value="목록"
					OnClick="location.href='/SpringBoard-Main/BoardItemList/${boardItem.getBoard().getId()}'">
				</th>
				<th scope="col"><input class="btn btn-outline-secondary"
					type="button" value="수정"
					OnClick="location.href='/SpringBoard-Main/BoardItemUpdate/${boardItem.getBoard().getId()}/${boardItem.id}'">
				</th>
				<th scope="col" width=450></th>
			</tr>
		</table>
	</div>
	<script>
		function characterCheck(obj) {
			var regExp = /[\{\}\\?.,;(\)*~~\'!^-_+<>!\#$%&\'\"\(\=]/gi;
			if (regExp.test(obj.value)) {
				alert('특수문자는 입력하실수 없습니다.');
				obj.value = obj.value.substring(0,
						obj.value.length - 1);
			}
		}
		function noSpaceForm(obj) {
			var str_space = /\s/;
			if (str_space.exec(obj.value)) {
				alert("해당 항목에는 공백을 사용할수 없습니다.
				obj.focus();
				obj.value = obj.value.replace(' ', '');
			}
		}
	</script>
</body>
</html>