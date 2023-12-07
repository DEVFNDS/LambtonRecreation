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


<div class="athlete-box">
	<h2 class="athlete-h2">Edit Athlete Profile</h2>
	<form action="insertathlete" method="post">
        
        <label for="bio">Bio:</label>
        <textarea id="bio" name="bio" rows="4" cols="50" ></textarea>
        <br>
        
        <label for="interests">Interests:</label>
        <textarea id="interests" name="interests" rows="4" cols="30" ></textarea>
        <br>
        
        <label for="achievements">Achievements:</label>
        <textarea id="achievements" name="achievements" rows="4" cols="30" ></textarea>
        <br>
        
        
        <!-- Add more fields here based on your Athlete model -->
        
        <input class="athlete-submit" type="submit" value="Submit">
    </form>
</div>


</body>
<%@ include file="../footer.jsp" %>
</html>
