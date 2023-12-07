
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
<div class="coach-box">
<h2 class="coach-h2">Coach Profile</h2>
<form action="insertcoach" method="post" onsubmit="return validateForm()">
    <label for="sportsSpecializedIn">Sports Specialized In:</label>
    <input type="text" id="sportsSpecializedIn" name="sportsSpecializedIn">
    <span id="sportsSpecializedInError" class="error-message"></span>
    
    <br>

    <label for="coachingExperience">Coaching Experience:</label>
    <input type="text" id="coachingExperience" name="coachingExperience">
    <span id="coachingExperienceError" class="error-message"></span>
    <br>

    <label for="certifications">Certifications:</label>
    <input type="text" id="certifications" name="certifications">
    <span id="certificationsError" class="error-message"></span>
    <br>

    <label for="availability">Availability:</label>
    <input type="text" id="availability" name="availability">
    <span id="availabilityError" class="error-message"></span>
    <br>

    

    <input type="submit" value="Submit" class="coach-submit">
</form>
</div>

<script>
function validateForm() {
    // Get input values
    var sportsSpecializedIn = document.getElementById('sportsSpecializedIn').value;
    var coachingExperience = document.getElementById('coachingExperience').value;
    var certifications = document.getElementById('certifications').value;
    var availability = document.getElementById('availability').value;

    // Set max and min length for each field
    var maxLength = 50; 
    var minLength = 3;  

    // Validate Sports Specialized In
    if (sportsSpecializedIn.trim() === '' || sportsSpecializedIn.length > maxLength || sportsSpecializedIn.length < minLength) {
        document.getElementById('sportsSpecializedInError').innerText = "Must be between" + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('sportsSpecializedInError').innerText = "";
    }

    // Validate Coaching Experience
    if (coachingExperience.trim() === '' || coachingExperience.length > maxLength || coachingExperience.length < minLength) {
        document.getElementById('coachingExperienceError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('coachingExperienceError').innerText = "";
    }

    // Validate Certifications
    if (certifications.trim() === '' || certifications.length > maxLength || certifications.length < minLength) {
        document.getElementById('certificationsError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('certificationsError').innerText = "";
    }

    // Validate Availability
    if (availability.trim() === '' || availability.length > maxLength || availability.length < minLength) {
        document.getElementById('availabilityError').innerText = "Must be between " + minLength + " and " + maxLength + " characters. Mention Not Applicable otherwise";
        return false;
    } else {
        document.getElementById('availabilityError').innerText = "";
    }

    // If all validations pass, the form will be submitted
    return true;
}
</script>

</body>
<%@ include file="../footer.jsp" %>
</html>

