<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*"%>
<%@ page import="kr.ac.kopo.kopo40.domain.Board"%>
<%@ page import="kr.ac.kopo.kopo40.domain.BoardItem"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardService"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardServiceImpl"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemService"%>
<%@ page import="kr.ac.kopo.kopo40.service.BoardItemServiceImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String board_id = request.getParameter("board_id");
String startNum = request.getParameter("startNum");
String countPage = request.getParameter("countPage");
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
	<br>
	<%
	BoardService bs = new BoardServiceImpl();
	Board board = bs.selectOne(Integer.parseInt(board_id));
	pageContext.setAttribute("board", board);
	%>
	<center>
		<h1>
			<c:out value="${board.getTitle()}" />
		</h1>
		<h4>
			<c:out value="${board.getInfo()}" />
		</h4>
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
				<%
				BoardItemService bis = new BoardItemServiceImpl();
				List<BoardItem> boardItems = bis.limitPaging(Integer.parseInt(startNum), Integer.parseInt(countPage),
						Integer.parseInt(board_id));
				pageContext.setAttribute("boardItems", boardItems);
				%>
				<c:forEach var="boardItem" items="${boardItems}" varStatus="status">
					<tr>
						<th scope='row' width='50px;'>
							<p align=center>
								<%-- ${fn:length(boardItems) - status.index} --%>
								<c:out value="${boardItem.id}" />
							</p>
						</th>
						<td width=500 width='400px'><p align=center>
								<a
									href='BoardItemView.jsp?board_id=<%=board_id%>&get_id=${boardItem.id}'>${boardItem.title}</a>
							</p></td>
						<td width=50 width='100px'>${boardItem.viewcnt}</td>
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
							OnClick="location.href='BoardItemList.jsp?board_id=<%=board_id%>&startNum=1&countPage=10'">
					</div>
				</td>
				<td>
					<div class="btn-group btn-group" role="group"
						aria-label="Basic outlined example">
						<input type="button" class="btn btn-outline-secondary" value="신규"
							OnClick="window.location='BoardItemInsert.jsp?board_id=<%=board_id%>'">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<div class="container">
		<div id="paging">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<%
					List<Integer> values = bis.paging(startNum, countPage, Integer.parseInt(board_id));
					int totalCnt = values.get(0);
					int fromPT = values.get(1);
					int cntPT = values.get(2);
					int pageNum = values.get(3);
					int startPage = values.get(4);
					int endPage = values.get(5);
					int totalPage = values.get(6);
					%>
					<li class='page-item'><a class='page-link'
						href='BoardItemList.jsp?board_id=<%=board_id%>&startNum=<%=((pageNum - 2) * (cntPT)) + 1%>&countPage=<%=cntPT%>'>Previous</a></li>
					<%
					if (cntPT == 1) {
						for (int i = startPage; i <= endPage; i++) {
							if (endPage > totalPage) {
						endPage = totalPage;
						endPage++;
							}
							out.println("<li class='page-item'><a class='page-link' href='BoardItemList.jsp?board_id=" + board_id
							+ "&startNum=" + ((i - 1) * (cntPT) + 1) + "&countPage=" + cntPT + "'>" + i + "</a></li>");
						}
					} else {
						for (int i = startPage; i <= endPage; i++) {
							if (endPage > totalPage) {
						endPage = totalPage;
							}
							out.println("<li class='page-item'><a class='page-link' href='BoardItemList.jsp?board_id=" + board_id
							+ "&startNum=" + ((i - 1) * (cntPT) + 1) + "&countPage=" + cntPT + "'>" + i + "</a></li>");
						}
					}
					%>
					<li class='page-item'><a class='page-link'
						href='BoardItemList.jsp?board_id=<%=board_id%>&startNum=<%=((pageNum - 1) * (cntPT)) + 1%>&countPage=<%=cntPT%>'>Next</a></li>
					<%
					// for debuging
					// 						out.println("pageNum : " + pageNum);
					// 						out.println("fromPT : " + Integer.parseInt(fromPT));
					// 						out.println("나머지 : " + BoardCnt % (Integer.parseInt(cntPT)));
					%>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>
