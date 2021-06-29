<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*"%>
<!doctype html>
<html lang="en">
<head>
<%
request.setCharacterEncoding("UTF-8");
String budget = request.getParameter("budget");
out.println(budget);
%>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>서현역 주변 맛집 추천봇</title>
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
	width: 1000px;
	margin-top: 100px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#tablebox {
	width: 1000px;
	margin-top: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#space1 {
	display: block;
	width: 10px;
}

#space2 {
	display: block;
	width: 500px;
}

h1, h4 {
	color: #767676;
}
</style>
</head>
<body>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="container">
		<div id="textbox">
			<h1>
				입력하신<%=budget%>원 으로 갈 수 있는 맛집은!
			</h1>
		</div>
		<div id="tablebox">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">First</th>
						<th scope="col">Last</th>
						<th scope="col">Handle</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>