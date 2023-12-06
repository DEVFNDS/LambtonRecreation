<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.model.Sport" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sports List</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<%@ include file="../../header.jsp" %>

<div class="sports-cards-div">
	<h2 class="sports-list-header">
	    Sports List 
	    <a href="<%= request.getContextPath() %>/sports?action=addForm"><button id="add-sport-btn">Add Sport</button></a>
	</h2>
	
	<%
	    List<Sport> sports = (List<Sport>) request.getAttribute("sports");
	    if (sports == null) {
	        sports = new ArrayList<>();
	    }
	%>

	<table id="sports-table" border="1">
	    <tr>
	        <th>ID</th>
	        <th>Name</th>
	        <th>Description</th>
	        <th>Rules</th>
	        <th>Equipment Needed</th>
	        <th>Actions</th>
	    </tr>
	    <% for (Sport sport : sports) { %>
	        <tr>
	            <td><%= sport.getId() %></td>
	            <td><%= sport.getName() %></td>
	            <td><%= sport.getDescription() %></td>
	            <td><%= sport.getRules() %></td>
	            <td><%= sport.getEquipmentNeeded() %></td>
	            <td>
	                <a href="<%= request.getContextPath() %>/sports?action=edit&id=<%= sport.getId() %>">Edit</a>
	                |
	                <a href="<%= request.getContextPath() %>/sports?action=delete&id=<%= sport.getId() %>"
	                   onclick="return confirm('Are you sure you want to delete this sport?')">Delete</a>
	            </td>
	        </tr>
	    <% } %>
	</table><br>

</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>