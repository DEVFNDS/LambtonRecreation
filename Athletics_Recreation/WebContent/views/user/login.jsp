<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript" src="js/registerLoginValidate.js"></script>
</head>
<body>
	<%
    	String error = (String) request.getAttribute("error");
	%>

	<h2>Login</h2>

	<% if (error != null) { %>
    	<p style="color: red;"><%= error %></p>
	<% } %>
	
	<!--<form action="${pageContext.request.contextPath}/LoginServlet" method="post">-->
	<form id="loginForm" action="login" method="post">
		<div id="serverError" style="color: red;"></div>
		
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"><br>
        <div id="usernameError" class="error"></div>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password"><br>
        <div id="passwordError" class="error"></div>

        <input type="submit" value="Login">
        <input type="button" id="resetButton" value="Reset">
    </form>
</body>
</html>