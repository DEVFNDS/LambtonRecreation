<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sports List</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<h2>Sports List</h2>
	
<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.model.Sport" %>
<%@ page import="java.util.ArrayList" %>

<%
    List<Sport> sports = (List<Sport>) request.getAttribute("sports");
    if (sports == null) {
        sports = new ArrayList<>();
    }
%>

<table border="1">
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
</table>

<h2>Add Sport</h2>
<form action="<%= request.getContextPath() %>/sports?action=add" method="post">
    Name: <input type="text" name="name" required><br>
    Description: <input type="text" name="description"><br>
    Rules: <input type="text" name="rules"><br>
    Equipment Needed: <input type="text" name="equipmentNeeded"><br>
    <input type="submit" value="Add Sport">
</form>

</body>
</html>