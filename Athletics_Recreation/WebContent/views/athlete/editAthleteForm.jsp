<!-- Group-4 Term Project -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome to Athletes and Recreation</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>
<body>

<%@ include file="../header.jsp" %>


 	<%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="lambtonrecreation.model.Athlete" %>
    <%@ page import="lambtonrecreation.dao.AthleteDao" %>

	<%

	    Athlete athlete  = (Athlete) request.getAttribute("athlete");
	    if (athlete != null) {
	%>
	<div class="athlete-box">
		<h2 class="athlete-h2">Edit Athlete Profile</h2>
		<form action="editathleteupdate" method="post">
	        
	        <label for="bio">Bio:</label>
	        <textarea id="bio" name="bio" rows="4" cols="50" > <%= athlete.getBio()%></textarea>
	        <br>
	        
	        <label for="interests">Interests:</label>
	        <textarea id="interests" name="interests" rows="4" cols="30" > <%= athlete.getInterests()%></textarea>
	        <br>
	        
	        <label for="achievements">Achievements:</label>
	        <textarea id="achievements" name="achievements" rows="4" cols="30"  > <%= athlete.getAchievements()%> </textarea>
	        <br>
	        
	        
	        <!-- Add more fields here based on your Athlete model -->
	        
	        <input class="athlete-submit" type="submit" value="Submit">
	    </form>
	   </div>
    
    <%
    	}
	%>


</body>
<%@ include file="../footer.jsp" %>
</html>
