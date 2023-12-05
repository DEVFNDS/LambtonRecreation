<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="lambtonrecreation.dao.SportDao"%>
<%@page import="lambtonrecreation.model.Sport"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sports</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<script type="text/javascript" src="js/sports.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
	
<div class="sports-cards-div">
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
                
                 <% if (username != null){ %>
                 	<button onclick="registerUserSport('<%= sport.getId()%>', '<%= username %>')">Register</button>
                 <% } %>
            </div>
        <%
            }
        %>
    </div>
</div>

</body>
<%@ include file="../footer.jsp" %>
</html>