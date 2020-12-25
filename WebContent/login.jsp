<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<div align="center">
		<h2>Login</h2>
		<form action="LoginController" method="post">
		Username:  	<input type="text" name="username"><br/>
		Password: 	<input type="password" name="password"><br/>
					<input type="submit" value="Login">
		<span class="error">${invalidCreds}</span>
		<%
		session.invalidate();
		%>
		</form>
		<a href="register.jsp">Not registered? Click here to register</a>
	</div>
</body>
</html>