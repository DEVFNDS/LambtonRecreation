<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<%
    	session = request.getSession(false);
    	String username = (session != null) ? (String) session.getAttribute("username") : null;
	%>
	<nav>
        <a href="home">Home</a>
        <a href="#">Sports</a>
        <a href="eventCard.jsp">Events</a>
        <a href="#">News/Articles</a>
        <% if (username != null){ %>
        	<p style="color: pink;"><%= username %> | </p><a href="logout">Logout</a>
        	
       <% } else { %>
        	<a href="register">Sign up</a>
        	<a href="login">Sign in</a>
       <% } %>
    </nav>
    
</body>
</html>