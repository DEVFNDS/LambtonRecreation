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
    <form action="editcoachupdate" method="post" onsubmit="return validateEditForm()">

        <label for="sportsSpecializedIn">Sports Specialized In:</label>
        <input type="text" id="editSportsSpecializedIn" name="sportsSpecializedIn" value="<%= coach.getSportsSpecializedIn()%>">
         <span id="editSportsSpecializedInError" class="error-message"></span>
        <br>
		
        <label for="coachingExperience">Coaching Experience:</label>
        <input type="text" id="editCoachingExperience" name="coachingExperience" value="<%= coach.getCoachingExperience()%>">
        <span id="editCoachingExperienceError" class="error-message"></span>
        <br>

        <label for="certifications">Certifications:</label>
        <input type="text" id="editCertifications" name="certifications" value="<%= coach.getCertifications()%>">
        <span id="editCertificationsError" class="error-message"></span>
        <br>

        <label for="availability">Availability:</label>
        <input type="text" id="editAvailability" name="availability" value="<%= coach.getAvailability()%>">
        <span id="editAvailabilityError" class="error-message"></span>
        <br>

        <input type="submit" value="Submit" class="coach-submit">
    </form>
</div>
<%
    }
%>
<script>
function validateEditForm() {
    // Get input values
    var editSportsSpecializedIn = document.getElementById('editSportsSpecializedIn').value;
    var editCoachingExperience = document.getElementById('editCoachingExperience').value;
    var editCertifications = document.getElementById('editCertifications').value;
    var editAvailability = document.getElementById('editAvailability').value;

    // Set max and min length for each field
    var maxLength = 50;
    var minLength = 3;

    // Validate Sports Specialized In
    if (editSportsSpecializedIn.trim() === '' || editSportsSpecializedIn.length > maxLength || editSportsSpecializedIn.length < minLength) {
        document.getElementById('editSportsSpecializedInError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('editSportsSpecializedInError').innerText = "";
    }

    // Validate Coaching Experience
    if (editCoachingExperience.trim() === '' || editCoachingExperience.length > maxLength || editCoachingExperience.length < minLength) {
        document.getElementById('editCoachingExperienceError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('editCoachingExperienceError').innerText = "";
    }

    // Validate Certifications
    if (editCertifications.trim() === '' || editCertifications.length > maxLength || editCertifications.length < minLength) {
        document.getElementById('editCertificationsError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('editCertificationsError').innerText = "";
    }

    // Validate Availability
    if (editAvailability.trim() === '' || editAvailability.length > maxLength || editAvailability.length < minLength) {
        document.getElementById('editAvailabilityError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('editAvailabilityError').innerText = "";
    }

    // If all validations pass, the form will be submitted
    return true;
}
</script>
</body>
<%@ include file="../footer.jsp" %>
</html>
