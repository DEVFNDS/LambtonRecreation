<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome to Athletes and Recreation</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
</head>
<body>

	<nav>
        <a href="#">Home</a>
        <a href="#sportsSection">Sports</a>
        <a href="#eventsSection">Events</a>
        <a href="#newsSection">News/Articles</a>
        <a href="#">Register</a>
    </nav>
	
	<img src="images/Home.png" alt="Welcome Image" id="welcome-image">
	
	
	<section id="sportsSection">
		<h2>Sports</h2>
		<div class="card-container">
	        <%-- Use the SportDAO to get sports from the database --%>
	    	<% SportDao sportDAO = new SportDao();
	           List<Sport> sports = sportDAO.getAllSports();
	            for (Sport sport : sports) {
	        %>
	            <div class="card">
	                <h3><%= sport.getName() %></h3>
	                <img src="images/Home.png" alt="<%= sport.getName() %> Image">
	                <p><%= sport.getDescription() %></p>
	            </div>
	        <%
	            }
	        %>
	    </div>
	</section>

	
	<section id="eventsSection">
	    <!-- Content for the Events section -->
	</section>

	
	<section id="newsSection">
	    <!-- Content for the News/Articles section -->
	</section>


</body> 

<footer id="footer">
      <div class="content">
        <div class="top">
          <div class="logo-details">
            <span class="logo_name">Lambton Athletics & Recreation</span>
          </div>
          <div class="media-icons">
            <a href="#"><i class="fa fa-facebook-f"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-instagram"></i></a>
            <a href="#"><i class="fa fa-pinterest"></i></a>
            <a href="#"><i class="fa fa-youtube"></i></a>
          </div>
        </div>
        <div class="link-boxes">
          <ul class="box" id="contact_us">
            <li class="link_name">Address</li>
            <li>MarketPlace Corporate Inc.</li>
            <li>121 Brunel Road</li>
            <li>Mississauga</li>
            <li>Ontario</li>
            <li>Canada.</li>
          </ul>

          <ul class="box">
            <li class="link_name">Contact</li>
            <li>&#9742; : <a href="tel:1234567890">(123) 456-7890</a></li>
            <li>
              <i class="fa fa-envelope"> :</i
              ><a href="recreation@lambton.ca"> recreation@lambton.ca</a>
            </li>
          </ul>
          <ul class="box">
            <li class="link_name">Services For</li>
            <li><a href="#" id="All">Sports</a></li>
            <li><a href="#" id="all-devices">Events</a></li>
            <li><a href="#" id="all-properties">News</a></li>
            <li><a href="#" id="all-pets">Articles</a></li>
          </ul>
          <ul class="box">
            <li class="link_name" id="working_hours">Support Hours</li>
            <li>Mon-Fri: 8:00AM to 9:00PM</li>
            <li>Saturday: 10:00AM to 6:00PM</li>
            <li>Sunday: Closed</li>
          </ul>
          <ul class="box input-box">
            <li class="link_name">Subscribe</li>
            <li><input type="text" placeholder="Enter your email" /></li>
            <li><input type="button" value="Subscribe" /></li>
          </ul>
        </div>
      </div>
      <div class="bottom-details">
        <div class="bottom_text">
          <span class="copyright_text"
            >Copyright � 2023
            <a href="home.html">Lambton Canada Inc.</a>All rights
            reserved</span
          >
          <span class="policy_terms">
            <a href="#">Privacy policy</a>
            <a href="#">Terms & condition</a>
          </span>
        </div>
      </div>
    </footer>
 
</html>