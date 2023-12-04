<!-- Group-4 Term Project -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome to Athletes and Recreation</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

	<nav>
        <a href="#">Home</a>
        <a href="#">Sports</a>
        <a href="#">Events</a>
        <a href="#">News/Articles</a>
        <a href="#">Register</a>
    </nav>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="lambtonrecreation.model.Event" %>
     <%@ page import="lambtonrecreation.dao.EventDao" %>
    
    <div id="event-container">
    
    <%
    List<Event> eventList = EventDao.getAllEvents();
    if (eventList != null && !eventList.isEmpty()) {
        for (Event obj : eventList) {
%>
		
        <!-- Event Card 1 -->
        <div class="event-card">
            <h2><%= obj.getName() %></h2>
            <p><strong>Sport:</strong><%= obj.getSportName() %> </p>
            <p><strong>Date and Time:</strong><%=  obj.getDateTime() %></p>
            <p><strong>Location:</strong><%= obj.getLocation() %></p>
            <p><strong>Description:</strong><%= obj.getDescription() %> </p>
            <p><strong>Registration Deadline:</strong><%= obj.getRegistrationDeadline() %></p>
             <a href='../../EditEventServlet?id=<%= obj.getId() %>'> <button class="edit">Edit</button> </a>
           <a href='../../DeleteEventServlet?id=<%= obj.getId() %>'> <button class="delete">Delete</button> </a>
            <button class="register">Register</button>
        </div>

  
<%
        }
    } else {
%>
    <p>No objects in the list.</p>
<%
    }
%>
  </div>
	
	
</body>
</html>
