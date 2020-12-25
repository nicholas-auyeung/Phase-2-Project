<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<div align="center">
		<h2>Welcome ${name}</h2>
		<div>${welcomeLogin}</div>
		<span class="success">${dbSuccess}</span>
	</div>
	<a href="login.jsp">Logout</a>
</body>
</html>