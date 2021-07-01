<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*"%>
<%@ page import="Gourmet.Price"%>
<%@ page import="Gourmet.GourmetDao"%>
<!doctype html>
<html lang="en">
<head>
<%
request.setCharacterEncoding("UTF-8");
String budget = request.getParameter("budget");
// out.println(budget);
%>
<!-- Required meta tags -->
<meta charset="utf-8">
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
				입력하신 '<%=budget%>'원 으로 갈 수 있는 맛집은!
			</h1>
		</div>
		<div id="tablebox">
			<table class="table">
				<thead>
					<tr>
						<th scope="col" width=400px>상호명</th>
						<th scope="col" width=400px>메뉴</th>
						<th scope="col" width=400px>가격</th>
					</tr>
				</thead>
				<tbody>
				<%
				GourmetDao gourmetDao = new GourmetDao();			
						List<Price> selected = gourmetDao.getPrice(Integer.parseInt(budget));				
						for(int i=0; i<selected.size(); i++) {
							Price price = new Price();
							price = selected.get(i);						
							out.println("<tr>");
							out.println("<td width=400px><a href='Gourmet_detail.jsp?gourmet_name="+price.getGourmet_name()+"'>"+price.getGourmet_name()+"</a></td>");
							out.println("<td width=400px>"+price.getGourmet_menu()+"</td>");
							out.println("<td width=400px>"+price.getGourmet_price()+"</td>");
							out.println("</tr>");					
						}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>