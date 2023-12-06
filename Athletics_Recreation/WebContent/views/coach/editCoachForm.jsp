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

<%@ page import="lambtonrecreation.model.Coach" %>
<%@ page import="lambtonrecreation.dao.CoachDao" %>

<%
    Coach coach = (Coach) request.getAttribute("coach");
    if (coach != null) {
%>
<div class="coach-box">
<h2 class="coach-h2">Edit Coach Profile</h2>
<form action="editcoachupdate" method="post">

    <label for="sports_specialized_in">Sports Specialized In:</label>
    <input type="text" id="sports_specialized_in" name="sports_specialized_in" value="<%= coach.getSportsSpecializedIn()%>">
    <br>

    <label for="coaching_experience">Coaching Experience:</label>
    <input type="text" id="coaching_experience" name="coaching_experience" value="<%= coach.getCoachingExperience()%>">
    <br>

    <label for="certifications">Certifications:</label>
    <input type="text" id="certifications" name="certifications" value="<%= coach.getCertifications()%>">
    <br>

    <label for="availability">Availability:</label>
    <input type="text" id="availability" name="availability" value="<%= coach.getAvailability()%>">
    <br>

    <input type="submit" value="Submit" class="coach-submit">
</form>
</div>
<%
    }
%>

</body>
<%@ include file="../footer.jsp" %>
</html>
