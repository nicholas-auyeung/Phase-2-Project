<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<div align="center">
		<h2>Register</h2>
		<form action="RegisterController" method="post">
		Name: <input type="text" name="name"><br/>
		Email: <input type="text" name="email"><br/>
		Username: <input type="text" name="username"><br/>
		Password: <input type="password" name="password"><br/>
		Confirm password: <input type="password" name="confirmPassword"><br/>
			<input type="submit" value="Register">
		<span class="error">${errors.emptyName}</span>
		<span class="error">${errors.invalidName}</span>
		<span class="error">${errors.emptyEmail}</span>
		<span class="error">${errors.invalidEmail}</span>
		<span class="error">${errors.emptyUsername}</span>
		<span class="error">${errors.emptyPass}</span>
		<span class="error">${errors.emptyConfirmPass}</span>
		<span class="error">${errors.failConfirmPass}</span>
		<span class="error">${errors.usernameInUse}</span>
		<span class="error">${errors.emailInUse}</span>
		<%
		session.invalidate();
		%>
		</form>
		<a href="login.jsp">Already registered? Click here to login</a>
	</div>
</body>
</html>