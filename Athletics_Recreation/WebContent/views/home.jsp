<!-- Group-4 Term Project -->
<%@page import="lambtonrecreation.dao.SportDao"%>
<%@page import="lambtonrecreation.model.Sport"%>
<%@page import="java.util.List"%>
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

	<%@ include file="header.jsp" %>
	
	<img src="images/Home.png" alt="Welcome Image" id="welcome-image">
	
	<section id="sportsSection">
		<h2>Sports</h2>
		<div class="card-container">
	        <%-- Use the SportDAO to get sports from the database --%>
	    	<% SportDao sportDAO = new SportDao();
	           List<Sport> sports = sportDAO.getAllSports();
	            for (Sport sport : sports) {
	        %>
	            <div class="card">
	                <h3><%= sport.getName() %></h3>
	                <img src="images/Home.png" alt="<%= sport.getName() %> Image">
	                <p><%= sport.getDescription() %></p>
	            </div>
	        <%
	            }
	        %>
	    </div>
	</section>

	
	<section id="eventsSection">
	    <!-- Content for the Events section -->
	</section>

	
	<section id="newsSection">
	    <!-- Content for the News/Articles section -->
	</section>


</body>
</html>