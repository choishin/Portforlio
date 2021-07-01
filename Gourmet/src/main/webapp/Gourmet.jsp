<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*"%>
<!doctype html>
<html lang="en">
<head>
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
@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);

body {
	font-family: 'Jeju Myeongjo', serif;
}

html {
	background: linear-gradient(0deg, rgba(255, 255, 255, 0.7),
		rgba(255, 255, 255, 0.7)), url('resources/img/main.jpg') no-repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}

.layer {
	z-index: -3;
	background-color: rgba(255, 255, 255, 0.25);
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.contaner {
	width: 1000px;
	display: flex;
	justify-content: flex-start;
	align-items: flex-start;
}

.textcontainer {
 	display: flex; 
 	justify-content: center; 
 	align-items: center; 

}

#textbox {
	position: fixed;
	top: 180px;
	left: 300px;
	width: 800px;
	padding: 50px 0px;
	border: 1px solid #ffffff;
	background: rgba(255, 255, 255, 0);
	text-align: center;
	z-index: -1;
}

#textbox:hover {
	background: rgba(0, 0, 0, 0.2);
	opacity: 1.0;
	color: #767676;
}

#inputbox {
	display: flex;
	justify-content: center;
	align-items: center;
	opacity: 1;
}

#space1 {
	display: block;
	width: 10px;
}

#space2 {
	display: block;
	width: 350px;
}
#space4 {
	display: block;
	width: 300px;
}

h1, h4 {
	display: inline;
	color: #ffffff;
}
</style>
</head>
<body>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="layer">
		<div class="container">
			<div class="textcontainer">
				<div id="textbox">
					<h1>
						서현역에서
						<div id="inputbox">
							<div id="space2"></div>
							<div class="input-group input-group-lg">
								<form class="d-flex" method='get' action='Gourmet_search.jsp'>
									<div class="input-group input-group-lg">
										<input type="number" class="form-control"
											placeholder="가격을 입력해주세요" aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-lg" name="budget"
											required>
									</div>
									<div id="space1"></div>
									<input type="submit" class="btn btn-outline-primary btn-lg"
										value="입력">
								</form>
							</div>
						</div>
						원으로 갈 수 있는 맛집은?
					</h1>
				</div>
				<div id="space4"></div>
			</div>
		</div>
	</div>
</body>
</html>