<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="lambtonrecreation.model.Sport" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Sport</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<%@ include file="../../header.jsp" %>

<div class="sports-cards-div">
	<h2>Edit Sport</h2>
	<%
	    Sport sport = (Sport) request.getAttribute("sport");
	    if (sport == null) {
	        // Handle the case where the sport is not found
	        response.sendRedirect(request.getContextPath() + "/sports?error=sportNotFound");
	    }
	%>
	
	<form action="<%= request.getContextPath() %>/sports?action=update" method="post">
	    <input type="hidden" name="id" value="<%= sport.getId() %>">
	    Name: <input type="text" name="name" value="<%= sport.getName() %>" required><br>
	    Description: <input type="text" name="description" value="<%= sport.getDescription() %>"><br>
	    Rules: <input type="text" name="rules" value="<%= sport.getRules() %>"><br>
	    Equipment Needed: <input type="text" name="equipmentNeeded" value="<%= sport.getEquipmentNeeded() %>"><br>
	    <input type="submit" value="Update Sport">
	</form>
</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>