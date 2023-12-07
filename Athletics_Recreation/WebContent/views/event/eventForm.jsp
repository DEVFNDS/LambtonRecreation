<!-- Group-4 Term Project -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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

<%@ include file="../header.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.model.Sport" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lambtonrecreation.dao.SportDao" %>



<%
	 final SportDao sportDao = new SportDao(); 
    List<Sport> sports = sportDao.getAllSports();
    if (sports == null) {
        sports = new ArrayList<>();
    }
    request.setAttribute("sports", sports);
%>

<br><br><br>
<form id="eventFormId" action="insertevent" method="post" onsubmit="return validateForm()" novalidate>
    <label for="name">Event Name:</label>
    <input type="text" id="name" name="name" required>
    <div id="name-error" class="error-message"></div>

     <label for="sport_id">Sport</label>
	 
	 <select name="sport_id" id="sport_id">
            <% for (Sport sport : sports) { %>
                <option value="<%= sport.getId() %>"><%= sport.getName() %></option>
            <% } %>
        </select>
	 
    <div id="sport_id-error" class="error-message"></div>

    <label for="date_time">Date and Time:</label>
    <input type="datetime-local" id="date_time" name="date_time" required>
    <div id="date_time-error" class="error-message"></div>

    <label for="location">Location:</label>
    <input type="text" id="location" name="location" required>
    <div id="location-error" class="error-message"></div>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50" required></textarea>
    <div id="description-error" class="error-message"></div>

    <label for="registration_deadline">Registration Deadline:</label>
    <input type="datetime-local" id="registration_deadline" name="registration_deadline" required>
    <div id="registration_deadline-error" class="error-message"></div>

    <input type="submit" value="Submit">
</form>

<script>

	const currentDate = new Date();
	
	const formattedDate = currentDate.toISOString().slice(0,16);
	document.getElementById("date_time").min = formattedDate;

	
	const myDateTimeInput = document.getElementById("date_time");
	const otherdate = document.getElementById("registration_deadline");
	
	myDateTimeInput.addEventListener('input',function(){
		otherdate.min = myDateTimeInput.value;
	});
	

    function validateForm() {
        // Reset error messages
        clearErrors();

        // Validate Name
        var nameField = document.getElementById("name");
        if (nameField.value.trim() === "") {
            showError("name", "Event Name is required.");
            return false;
        }
        
        // Set max and min length for each field
        
        
        // Validate Sport ID
        var sportIdField = document.getElementById("sport_id");
        var sportIdValue = sportIdField.value.trim();
        if (sportIdValue === "") {
            showError("sport_id", "Sport ID is required.");
            return false;
        }
        if (!/^\d+$/.test(sportIdValue) || parseInt(sportIdValue, 10) < 0) {
            showError("sport_id", "Sport ID should be a valid non-negative integer.");
            return false;
        }

        // Validate Date and Time
        var dateTimeField = document.getElementById("date_time");
        if (dateTimeField.value.trim() === "") {
            showError("date_time", "Date and Time are required.");
            return false;
        }
        // Use a custom check for datetime-local
        if (!isValidDateTime(dateTimeField.value)) {
            showError("date_time", "Invalid date and time format.");
            return false;
        }

        // Validate Location
        var locationField = document.getElementById("location");
        if (locationField.value.trim() === "") {
            showError("location", "Location is required.");
            return false;
        }

        // Validate Description
         var descriptionField = document.getElementById("description");
        if (descriptionField.value.trim() === "") {
            showError("description", "Description is required.");
             return false;
         }

        
        
        
        // Validate Registration Deadline
        var registrationDeadlineField = document.getElementById("registration_deadline");
        
        if (registrationDeadlineField.value.trim() === "") {
            showError("registration_deadline", "Registration deadline is required.");
            return false;
        }
        // Use a custom check for datetime-local
        if (!isValidDateTime(registrationDeadlineField.value)) {
            showError("registration_deadline", "Invalid date and time format.");
            return false;
        }
       
        submitForm();
        // If all validations pass, return true to submit the form
        return false;
    }
	
    function submitForm() {
        // Perform any additional actions before form submission (if needed)

        // Submit the form
        document.getElementById("eventFormId").submit();
        
        // After successful submission, clear the form fields
        clearForm();
    }
    
    function clearForm() {
    	
        document.getElementById("eventFormId").reset(); // Replace "formId" with the actual ID of your form
    }
    
    function isValidDate(value) {
        var regex = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/;
        return regex.test(value);
    }

    function isValidDateTime(value) {
        var regex = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/;
        return regex.test(value);
    }

    function showError(fieldId, errorMessage) {
        var errorDiv = document.getElementById(fieldId + "-error");
        errorDiv.textContent = errorMessage;
        var field = document.getElementById(fieldId);
        field.setCustomValidity(errorMessage);
    }

    function clearErrors() {
        var errorDivs = document.getElementsByClassName("error-message");
        for (var i = 0; i < errorDivs.length; i++) {
            errorDivs[i].textContent = "";
        }
    }
</script>

<%@ include file="../footer.jsp" %>
</body>
</html>
