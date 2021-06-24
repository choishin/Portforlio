<%@page import="java.text.SimpleDateFormat"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!%@ page errorPage="Read_error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page
	import="java.sql.*, javax.sql.*, java.net.*, java.io.*, java.util.* "%>
<html>
<head>
<title>대한민국 시도별 백신 예방접종 현황표</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);

body {
	font-family: 'Jeju Myeongjo', serif;
	
}

.title {
	margin-top: 50px;
}

.chart {
	margin-top: 0px;
	margin: 0px;
	padding:0px;
	margin-left: 50px;
	background-image: url("\Graph\src\main\webapp\images\korea.png");
}

</style>
<!-- <link href="css/demo.css" rel="stylesheet" type="text/css" /> -->
<link href="/Graph/src/main/webapp/jqbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="jqbar.css" />
</head>
<body>
	<%
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf_title1 = new SimpleDateFormat("yyyyMMdd");
	int lastdate_int = Integer.parseInt(sdf_title1.format(date)) - 1;
	String lastdate_str = Integer.toString(lastdate_int);
	String today_year = lastdate_str.substring(0,4);
	String today_month = lastdate_str.substring(4,6);
	String today_date = lastdate_str.substring(6,8);
	%>
	<center>
		<div class="title">
			<h1 style="font-weight: bold; color: #767676">대한민국 시도별 백신 예방 접종 현황</h1>
			<h2 style="font-weight: bold; color: #767676">기준일자:<%=today_year%>년 <%=today_month%>월 <%=today_date%>일</h2>
		</div>
	</center>
	<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc", "root", "kopoctc");
	Statement stmt = conn.createStatement();
	
	String getPopulation = "select * from population";
	ResultSet rset = stmt.executeQuery(getPopulation);
	int totalCnt = 18;
	String[] city_name = new String[totalCnt];
	double[] city_population = new double[totalCnt];
	int index = 0;
	while (rset.next()) {
		city_name[index] = rset.getString(1);
		city_population[index] = rset.getDouble(2);
		//out.println("<p>"+city_name[index]+"</p>"); 
		//out.println("<p>"+city_population[index]+"</p>"); 
		index++;
	}
	%>
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
	String today = sdf.format(date).replace("0", "");
	int today_parse = Integer.parseInt(today);
	int lastdate = today_parse - 1;
	String getVaccine = "select * from vaccine where vaccine_date=" + lastdate + ";";
	// 	rset = stmt.executeQuery(getVaccine);
	// 	String lastdate_query = "";
	// 	while (rset.next()) {
	// 		lastdate_query = rset.getString(1);
	// 	}
	// 	if (lastdate_query == null || lastdate_query == "") {
	// 		getVaccine = "select * from vaccine where vaccine_date=" + (lastdate - 1) + ";";
	// 	} else {
	// 		getVaccine = "select * from vaccine where vaccine_date=" + lastdate + ";";
	// 	}

	double[] first_day_total = new double[totalCnt];
	double[] second_day_total = new double[totalCnt];
	int index2 = 0;
	rset = stmt.executeQuery(getVaccine);
	while (rset.next()) {
		first_day_total[index2] = rset.getInt(5);
		second_day_total[index2] = rset.getInt(7);
// 				out.println("<p>"+first_day_total[index2] +"</p>"); 
// 				out.println("<p>"+second_day_total[index2]+"</p>"); 
		index2++;
	}
	double[] first_percent = new double[totalCnt];
	double[] second_percent = new double[totalCnt];
	for (int i = 0; i < totalCnt; i++) {
		first_percent[i] = Double.parseDouble(String.format("%.2f", (first_day_total[i] / city_population[i]) * 100));
		second_percent[i] = Double.parseDouble(String.format("%.2f", (second_day_total[i] / city_population[i]) * 100));
		// 		out.println("<p>"+first_percent[i]+"</p>");
		// 		out.println("<p>"+second_percent[i]+"</p>");
	}
	%>
<!-- 	<div class="chart"> -->
		<img src="C:\Users\최신\Documents\workspace-spring-tool-suite-4-4.10.0.RELEASE\Graph\src\main\webapp\images\korea2.ai">
		<div id="bar-1"></div>
		<div id="bar-1-1"></div>
		<div id="bar-2"></div>
		<div id="bar-2-1"></div>
		<div id="bar-3"></div>
		<div id="bar-3-1"></div>
		<div id="bar-4"></div>
		<div id="bar-4-1"></div>
		<div id="bar-5"></div>
		<div id="bar-5-1"></div>
		<div id="bar-6"></div>
		<div id="bar-6-1"></div>
		<div id="bar-7"></div>
		<div id="bar-7-1"></div>
		<div id="bar-8"></div>
		<div id="bar-8-1"></div>
		<div id="bar-9"></div>
		<div id="bar-9-1"></div>
		<div id="bar-10"></div>
		<div id="bar-10-1"></div>
		<div id="bar-11"></div>
		<div id="bar-11-1"></div>
		<div id="bar-12"></div>
		<div id="bar-12-1"></div>
		<div id="bar-13"></div>
		<div id="bar-13-1"></div>
		<div id="bar-14"></div>
		<div id="bar-14-1"></div>
		<div id="bar-15"></div>
		<div id="bar-15-1"></div>
		<div id="bar-16"></div>
		<div id="bar-16-1"></div>
		<div id="bar-17"></div>
		<div id="bar-17-1"></div>
		<div id="bar-18"></div>
		<div id="bar-18-1"></div>
<!-- 	</div> -->
	<br>
	<br>
	<br>
	<br>
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/jqbar.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function () {
		$('#bar-1').jqbar({ label: '<%=city_name[0]%> 1차 접종률', value: <%=first_percent[0]%>, barColor: '#D64747' });
		$('#bar-1-1').jqbar({ label: '<%=city_name[0]%> 2차 접종률', value: <%=second_percent[0]%>, barColor: '#D64747' });
		$('#bar-2').jqbar({ label: '<%=city_name[1]%> 1차 접종률', value: <%=first_percent[1]%>, barColor: '#FF681F' });
		$('#bar-2-1').jqbar({ label: '<%=city_name[1]%> 2차 접종률', value: <%=second_percent[1]%>, barColor: '#FF681F' });
		$('#bar-3').jqbar({ label: '<%=city_name[2]%> 1차 접종률', value: <%=first_percent[2]%>, barColor: '#ea805c' });
		$('#bar-3-1').jqbar({ label: '<%=city_name[2]%> 2차 접종률', value: <%=second_percent[2]%>, barColor: '#ea805c' });
		$('#bar-4').jqbar({ label: '<%=city_name[3]%> 1차 접종률', value: <%=first_percent[3]%>, barColor: '#88bbc8' });
		$('#bar-4-1').jqbar({ label: '<%=city_name[3]%> 2차 접종률', value: <%=second_percent[3]%>, barColor: '#88bbc8' });
		$('#bar-5').jqbar({ label: '<%=city_name[4]%> 1차 접종률', value: <%=first_percent[4]%>, barColor: '#939393' });
		$('#bar-5-1').jqbar({ label: '<%=city_name[4]%> 2차 접종률', value: <%=second_percent[4]%>, barColor: '#939393'});
		$('#bar-6').jqbar({ label: '<%=city_name[5]%> 1차 접종률', value: <%=first_percent[5]%>, barColor: '#3a89c9' });
		$('#bar-6-1').jqbar({ label: '<%=city_name[5]%> 2차 접종률', value: <%=second_percent[5]%>, barColor: '#3a89c9'});
		$('#bar-7').jqbar({ label: '<%=city_name[6]%> 1차 접종률', value: <%=first_percent[6]%>, barColor: '#D64747'});
		$('#bar-7-1').jqbar({ label: '<%=city_name[6]%> 2차 접종률', value: <%=second_percent[6]%>, barColor: '#D64747'});
		$('#bar-8').jqbar({ label: '<%=city_name[7]%> 1차 접종률', value: <%=first_percent[7]%>, barColor: '#FF681F',});
		$('#bar-8-1').jqbar({ label: '<%=city_name[7]%> 2차 접종률', value: <%=second_percent[7]%>, barColor: '#FF681F',});
		$('#bar-9').jqbar({ label: '<%=city_name[8]%> 1차 접종률', value: <%=first_percent[8]%>, barColor: '#ea805c',  });
		$('#bar-9-1').jqbar({ label: '<%=city_name[8]%> 2차 접종률', value: <%=second_percent[8]%>, barColor: '#ea805c',  });
		$('#bar-10').jqbar({ label: '<%=city_name[9]%> 1차 접종률', value: <%=first_percent[9]%>, barColor: '#88bbc8' });
		$('#bar-10-1').jqbar({ label: '<%=city_name[9]%> 2차 접종률', value: <%=second_percent[9]%>, barColor: '#88bbc8' });
		$('#bar-11').jqbar({ label: '<%=city_name[10]%> 1차 접종률', value: <%=first_percent[10]%>,  barColor: '#939393',});
		$('#bar-11-1').jqbar({ label: '<%=city_name[10]%> 2차 접종률', value: <%=second_percent[10]%>,  barColor: '#939393',});
		$('#bar-12').jqbar({ label: '<%=city_name[11]%> 1차 접종률', value: <%=first_percent[11]%>, barColor: '#3a89c9', });
		$('#bar-12-1').jqbar({ label: '<%=city_name[11]%> 2차 접종률', value: <%=second_percent[11]%>, barColor: '#3a89c9', });
		$('#bar-13').jqbar({ label: '<%=city_name[12]%> 1차 접종률', value: <%=first_percent[12]%>, barColor: '#D64747' });
		$('#bar-13-1').jqbar({ label: '<%=city_name[12]%> 2차 접종률', value: <%=second_percent[12]%>, barColor: '#D64747' });
		$('#bar-14').jqbar({ label: '<%=city_name[13]%> 1차 접종률', value: <%=first_percent[13]%>, barColor: '#FF681F' });
		$('#bar-14-1').jqbar({ label: '<%=city_name[13]%> 2차 접종률', value: <%=second_percent[13]%>, barColor: '#FF681F' });
		$('#bar-15').jqbar({ label: '<%=city_name[14]%> 1차 접종률', value:  <%=first_percent[14]%>, barColor: '#ea805c' });
		$('#bar-15-1').jqbar({ label: '<%=city_name[14]%> 2차 접종률', value:  <%=second_percent[14]%>, barColor: '#ea805c' });
		$('#bar-16').jqbar({ label: '<%=city_name[15]%> 1차 접종률', value:  <%=first_percent[15]%>, barColor: '#88bbc8' });
		$('#bar-16-1').jqbar({ label: '<%=city_name[15]%> 2차 접종률', value:  <%=second_percent[15]%>, barColor: '#88bbc8' });
		$('#bar-17').jqbar({ label: '<%=city_name[16]%> 1차 접종률', value:  <%=first_percent[16]%>, barColor: '#939393' });
		$('#bar-17-1').jqbar({ label: '<%=city_name[16]%> 2차 접종률', value:  <%=second_percent[16]%>, barColor: '#939393' });
		$('#bar-18').jqbar({ label: '<%=city_name[17]%> 1차 접종률', value:  <%=first_percent[17]%>, barColor: '#3a89c9' });
		$('#bar-18-1').jqbar({ label: '<%=city_name[17]%> 2차 접종률',value :<%=second_percent[17]%>,barColor : '#3a89c9'});
		});
	</script>
	<br />
	<br />
	<br />
	<br />
	<%
	rset.close();
	stmt.close();
	conn.close();
	%>

</body>
<center>
	<footer style="color: #767676">
        <p>출처: 질병관리청 코로나19 백신 및 예방접종 - 시도별 접종 현황, KOSIS - 시도별 주민등록 인구현황</p>
    </footer>
</center>
</html>
