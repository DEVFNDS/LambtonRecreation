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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

	<%@ include file="../header.jsp" %>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="lambtonrecreation.model.Athlete" %>
     <%@ page import="lambtonrecreation.dao.AthleteDao" %>
    
    <div class="athlete-container">
    <%

	    Athlete athlete  = (Athlete) request.getAttribute("athlete");
	    if (athlete != null) {
	%>
		
		
		    <div class="bio">
		        <h3>Bio</h3>
		        <p id="bioValue"><%=athlete.getBio() %></p>
		    </div>
		    
		    <div class="athlete-subcontainer">
		    	<div class="interests">
			        <h3>Interests</h3>
			        <p><%=athlete.getInterests() %></p>
			    </div>
			    
			    <div class="achievements">
			        <h3>Achievements</h3>
			        <p id="bioValue"><%=athlete.getAchievements() %></p>
			    </div>
		    </div>
		    <a  class="edit-athlete-link" href='editathlete'> <button class="edit-athlete-button">Edit Profile</button> </a>
		    
		
	<%
    	}
	%>
    </div>
	
</body>
<%@ include file="../footer.jsp" %>
</html>
