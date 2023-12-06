<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="lambtonrecreation.model.Sport" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Sport</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<%@ include file="../../header.jsp" %>

<div class="sports-cards-div">
	<h2>Add Sport</h2>
	<form action="<%= request.getContextPath() %>/sports?action=add" method="post">
	 	
	    Name: <input type="text" name="name" value="${not empty param.name ? param.name : ''}">
	    <c:if test="${not empty requestScope.fieldErrors['name']}">
	       <div class="error-message">${requestScope.fieldErrors['name']}</div>
	    </c:if><br>
	   
	    Description: <input type="text" name="description" value="${not empty param.description ? param.description : ''}">
	    <c:if test="${not empty requestScope.fieldErrors['description']}">
	        <div class="error-message">${requestScope.fieldErrors['description']}</div>
	    </c:if><br>
    
	    Rules: <input type="text" name="rules" value="${not empty param.rules ? param.rules : ''}">
	    <c:if test="${not empty requestScope.fieldErrors['rules']}">
	        <div class="error-message">${requestScope.fieldErrors['rules']}</div>
	    </c:if><br>
	    
	    Equipment Needed: <input type="text" name="equipmentNeeded" value="${not empty param.equipmentNeeded ? param.equipmentNeeded : ''}">
	    <c:if test="${not empty requestScope.fieldErrors['equipmentNeeded']}">
	        <div class="error-message">${requestScope.fieldErrors['equipmentNeeded']}</div>
	    </c:if><br>
	    
	    <input type="submit" value="Add Sport"><br>
	    
	    <!-- Link to go back to the sports list page -->
        <a href="<%= request.getContextPath() %>/sports">Back to Sports List</a>
	    
	</form>
</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>