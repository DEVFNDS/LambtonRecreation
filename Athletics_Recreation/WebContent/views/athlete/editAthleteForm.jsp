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
		<h2 class="athlete-h2">Edit Player Profile</h2>
		<form id="athleteForm" action="editathleteupdate" method="post">
	        
	        <label for="bio">Bio:</label>
	        <textarea id="bio" name="bio" rows="4" cols="50" > <%= athlete.getBio()%></textarea>
	        <span id="bioError" class="profile-error"></span>
	        <br>
	        
	        <label for="interests">Interests:</label>
	        <textarea id="interests" name="interests" rows="4" cols="30" > <%= athlete.getInterests()%></textarea>
	        <span id="interestsError" class="profile-error"></span>
	        <br>
	        
	        <label for="achievements">Achievements:</label>
	        <textarea id="achievements" name="achievements" rows="4" cols="30"  > <%= athlete.getAchievements()%> </textarea>
	        <span id="achievementsError" class="profile-error"></span>
	        <br>
	        
	        
	        <!-- Add more fields here based on your Athlete model -->
	        
	        <input class="athlete-submit" type="submit" value="Submit">
	    </form>
	   </div>
    
    <%
    	}
	%>


	<script>
        	document.getElementById('athleteForm').addEventListener('submit', function(event) {
            // Reset previous error messages
            document.getElementById('bioError').innerHTML = '';
            document.getElementById('interestsError').innerHTML = '';
            document.getElementById('achievementsError').innerHTML = '';

            // Validate Bio
            var bio = document.getElementById('bio').value;
            if (bio.length < 10 || bio.length > 600) {
                document.getElementById('bioError').innerHTML = 'Bio should be between 10 and 600 characters.';
                event.preventDefault();
            }

            // Validate Interests
            var interests = document.getElementById('interests').value;
            if (interests.length > 250) {
                document.getElementById('interestsError').innerHTML = 'Interests should not exceed 250 characters.';
                event.preventDefault();
            }

            // Validate Achievements
            var achievements = document.getElementById('achievements').value;
            if (achievements.length > 250) {
                document.getElementById('achievementsError').innerHTML = 'Achievements should not exceed 250 characters.';
                event.preventDefault();
            }
        });
    </script>
</body>
<%@ include file="../footer.jsp" %>
</html>
