<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome to Coaches and Recreation</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/footer.css">
</head>
<body>

    <%@ include file="../header.jsp" %>

    <%@ page import="lambtonrecreation.model.Coach" %>
    <%@ page import="lambtonrecreation.dao.CoachDao" %>

    <div class="coach-container">
        <%
            Coach coach = (Coach) request.getAttribute("coach");
            if (coach != null) {
        %>

                
                <div class="coach-subcontainer">
                    <div class="specialization">
                        <h3>Sports Specialized In</h3>
                        <p><%=coach.getSportsSpecializedIn() %></p>
                    </div>

                    <div class="experience">
                        <h3>Coaching Experience</h3>
                        <p><%=coach.getCoachingExperience() %></p>
                    </div>
				</div>
				   <div class="coach-subcontainer">
                    <div class="certifications">
                        <h3>Certifications</h3>
                        <p><%=coach.getCertifications() %></p>
                    </div>

                    <div class="availability">
                        <h3>Availability</h3>
                        <p><%=coach.getAvailability() %></p>
                    </div>
                </div>
                <a class="edit-coach-link" href='editcoach'> <button class="edit-coach-button">Edit Profile</button> </a>

        <%
            }
        %>
    </div>

</body>
<%@ include file="../footer.jsp" %>
</html>
