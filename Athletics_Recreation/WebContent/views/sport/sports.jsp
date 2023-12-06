<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <%-- Use the sports list coming from the servlet --%>
        <c:forEach var="sport" items="${sports}">
            <div class="card">
                <h3>${sport.name}</h3>
                <img src="images/Home.png" alt="${sport.name} Image">
                <p class="description">${sport.description}</p>
                
                 <% if (username != null){ %>
                 	<button class="favorite-button <c:if test="${sport.favourite}">disabled</c:if>" onclick="registerUserSport('${sport.id}', '<%= userId %>')">Favourite</button>
                 <% } else { %>
                    <button class="favorite-button disabled">Favorite</button>
                <% } %>
            </div>
        </c:forEach>
    </div>
</div>

</body>
<%@ include file="../footer.jsp" %>
</html>