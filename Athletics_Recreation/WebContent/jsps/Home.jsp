<!-- Group-4 Term Project -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome to Athletes and Recreation</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<nav>
        <a href="#">Home</a>
        <a href="#">Sports</a>
        <a href="#">Events</a>
        <a href="#">News/Articles</a>
        <a href="#">Register</a>
    </nav>
    
    Home page
 <a href="Register.jsp">Sign up</a>
 <a href="Login.jsp">Login</a>
 <% 
 HttpSession sess = request.getSession(false);
 String username = (session != null) ? (String) sess.getAttribute("username") : null;
 if (username != null) { %>
    <p>Welcome, <%= username %>!</p>
    <a href="../logout">Logout</a>
<% }  else { %>
    <p>You are not logged in.</p>
<% } %>
	
	<img src="images/Home.png" alt="Welcome Image" id="welcome-image">
	
</body>
</html>