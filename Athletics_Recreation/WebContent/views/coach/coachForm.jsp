
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
<form action="insertcoach" method="post">
	

    <label for="sports_specialized_in">Sports Specialized In:</label>
    <input type="text" id="sports_specialized_in" name="sports_specialized_in" required>
    <br>

    <label for="coaching_experience">Coaching Experience:</label>
    <input type="text" id="coaching_experience" name="coaching_experience" required>
    <br>

    <label for="certifications">Certifications:</label>
    <input type="text" id="certifications" name="certifications" required>
    <br>

    <label for="availability">Availability:</label>
    <input type="text" id="availability" name="availability" required>
    <br>

    <!-- Add more fields here based on your Coach model -->

    <input type="submit" value="Submit" class="coach-submit">
</form>
</div>

</body>
<%@ include file="../footer.jsp" %>
</html>
