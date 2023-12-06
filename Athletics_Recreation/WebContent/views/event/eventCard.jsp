<!-- Group-4 Term Project -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome to Athletes and Recreation</title>
	 <link rel="stylesheet" type="text/css" href="css/style.css">
	 <link rel="stylesheet" type="text/css" href="css/footer.css">
	 <link rel="stylesheet" type="text/css" href="css/eventcard.css">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

	<%@ include file="../header.jsp" %>
    
    
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="lambtonrecreation.model.Event" %>
     <%@ page import="lambtonrecreation.dao.EventDao" %>
    
    
    <%
    // Retrieve the message attribute from the request
    String alertMessage = (String) request.getAttribute("alertMessage");

    // Check if the message is not null or empty
    if (alertMessage != null && !alertMessage.isEmpty()) {
%>
    <!-- Use JavaScript to display an alert when the page loads -->
    <script>
        alert("<%= alertMessage %>");
    </script>
<%
    }
%>


<%
    // Retrieve the value from the request
     Boolean showAnchor = (Boolean) request.getAttribute("show");
    
    // Check the value and conditionally hide the anchor tag
%>
	<br><br><br><br>
	<a href='insertevent'> <button class="my-button <%= (showAnchor != null && !showAnchor) ? "hidden" : "" %>">Add Event</button></a>
	<h1 style="color: #007bff">Ongoing Events</h1>
    <div id="event-container">
    
    <%
    List<Event> eventList =  (List<Event>) request.getAttribute("eventList") ;
    if (eventList != null && !eventList.isEmpty()) {
        for (Event obj : eventList) {
%>
		
        <!-- Event Card 1 -->
        
        <div class="container">
  <div class="event-card">
    <h2><%= obj.getName() %></h2>
    <p><strong>Sport:</strong> <%= obj.getSportName() %></p>
    <p><strong>Date and Time:</strong> <%= obj.getDateTime() %></p>
    <p><strong>Location:</strong> <%= obj.getLocation() %></p>
    <p><strong>Description:</strong> <%= obj.getDescription() %></p>
    <p><strong>Registration Deadline:</strong> <%= obj.getRegistrationDeadline() %></p>
    <div class="d-grid gap-2">
      <a href='EditEventServlet?id=<%= obj.getId() %>'><button class="btn btn-primary edit <%= (showAnchor != null && !showAnchor) ? "hidden" : "" %>">Edit</button></a>
      <a href='deleteevent?id=<%= obj.getId() %>'><button class="btn btn-danger delete <%= (showAnchor != null && !showAnchor) ? "hidden" : "" %>">Delete</button></a>
      <a href='EventRegisterServlet?id=<%= obj.getId() %>'><button class="btn btn-success register">Register</button></a>
    </div>
  </div>
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
	
	  <%@ include file="../footer.jsp" %>
</body>
</html>
