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

	<%@ include file="header.jsp" %>
	
	<img src="images/Home.png" alt="Welcome Image" id="welcome-image">
	
	<section id="sportsSection">
		<img src="images/Sports.png" alt="Sports Image" id="sports-image">
		<button class="overlay-button" onclick="window.location.href='sports'" id="view_sports_btn">View Sports</button>
	</section>
	
</body>
<%@ include file="footer.jsp" %>
</html>
