<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Sport</title>
</head>
<body>

<h2>Edit Sport</h2>

<%@ page import="lambtonrecreation.model.Sport" %>

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

</body>
</html>