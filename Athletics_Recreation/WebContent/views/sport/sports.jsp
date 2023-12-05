<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="lambtonrecreation.dao.SportDao"%>
<%@page import="lambtonrecreation.model.Sport"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sports</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<script type="text/javascript" src="js/sports.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
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
                <p class="description"><%= sport.getDescription() %></p>
                
                 <% if (username != null){ %>
                 	<button class="favorite-button" onclick="registerUserSport('<%= sport.getId()%>', '<%= 2 %>')">Favourite</button>
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