<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<%@ page contentType="text/html; charset=utf-8" %> 
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html> 
<head>
</head> 
<body> 
<script>
window.location="/SpringBoard-Main/BoardItemView/<c:out value="${values[0]}"/>/<c:out value="${values[1]}"/>";
</script>
</body> 
</html>