<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*"%>
<%@ page import="Gourmet.Price"%>
<%@ page import="Gourmet.Main"%>
<%@ page import="Gourmet.Image"%>
<%@ page import="Gourmet.GourmetDao"%>
<!doctype html>
<html lang="en">
<head>
<%
request.setCharacterEncoding("UTF-8");
String gourmet_name = request.getParameter("gourmet_name");
// out.println(budget);
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
<title>맛집을 알려드립니다!</title>
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

#imagebox {
	margin-top: 20px;
	margin-bottom: 100px;
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

#space3 {
	display: block;
	width: 200px;
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
				'<%=gourmet_name%>'의 상세정보 입니다.
			</h1>
		</div>
		<div id="tablebox">
			<table class="table">
				<tbody>
					<%
					GourmetDao gourmetDao = new GourmetDao();
					Main main = gourmetDao.getMain(gourmet_name);					
					%>
					<tr>
						<th scope='col' width=200px>상호명</th>
						<td width><%=main.getGourmet_name()%></td>
					</tr>
					<tr>
						<th scope='col' width>별점</th>
						<td width><%=main.getGourmet_star()%></td>
					</tr>
					<tr>
						<th scope='col' width>방문자 리뷰수</th>
						<td width><%=main.getGourmet_visitor_review_cnt()%></td>
					</tr>
					<tr>
						<th scope='col' width>블로거 리뷰수</th>
						<td width><%=main.getGourmet_blogger_review_cnt()%></td>
					</tr>
					<tr>
						<th scope='col' width>주소</th>
						<td width><%=main.getGourmet_address()%></td>
					</tr>
					<tr>
						<th scope='col' width>운영시간</th>
						<td width><%=main.getGourmet_openinghour()%></td>
					</tr>
					<tr>
						<th scope='col' width>편의</th>
						<td width><%=main.getGourmet_service()%></td>
					</tr>
					<tr>
						<th scope='col' width>주소</th>
						<td width><%=main. getGourmet_info()%></td>
					</tr>
				</tbody>
			</table>

			<%
				Image image = gourmetDao.getImage(gourmet_name);
				
			%>
		</div>
			<div class="container">
		<div id="imagebox">
			<iframe src="<%=image.getGourmet_image()%>" width=600px height=600px></iframe>
							<div id="space3"></div>
		</div>
	</div>
	</div>
</body>
</html>