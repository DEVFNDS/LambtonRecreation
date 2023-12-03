<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.dao.ApplicationDao" %>
<%@ page import="lambtonrecreation.beans.User" %>
<%@ page import="lambtonrecreation.beans.Role" %>

<%
    // Retrieve roles from the database
    List<Role> roles = ApplicationDao.getRoles();
	roles.add(0, ApplicationDao.selectRoleOption());
	System.out.println("--nk jsp roles : "+roles);
	request.setAttribute("roles", roles);
    // Set roles as a request attribute
    request.setAttribute("roles", roles);
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript" src="js/registerLoginValidate.js"></script>
</head>
<body>
    <h2>User Registration Form</h2>

    <!--  <c:if test="${not empty error}">
        <p style="color: red;"><%= request.getAttribute("error") %>qwerty</p>
        <p>abc</p>
    </c:if>-->

    <form id="registrationForm" action="../register" method="post">
        <!-- server-side error message here -->
        <div id="serverError" style="color: red;"></div>

		<label for="fname">First Name:</label>
		<input type="text" name="fname" id="fname"><br>
		<div id="fnameError" class="error"></div>
		
		<label for="lname">Last Name:</label>
		<input type="text" name="lname" id="lname"><br>
		<div id="lnameError" class="error"></div>
		
		<label for="email">Email:</label>
        <input type="email" name="email" id="email"><br>
        <div id="emailError" class="error"></div>
        
        <label for="dob">Date of Birth:</label>
		<input type="date" name="dob" id="dob"><br>
		<div id="dobError" class="error"></div>      
		
		<label for="gender">Gender:</label>
        <input type="radio" name="gender" id="gender" value="Male"> Male
        <input type="radio" name="gender" id="gender" value="Female"> Female
        <div id="genderError" class="error"></div>
        <br>

		<label for="role">Role:</label>
        <select name="role" id="role">
            <% for (Role role : roles) { %>
                <option value="<%= role.getRole() %>"><%= role.getRole() %></option>
            <% } %>
        </select>
        <div id="roleError" class="error"></div>
        
        <br>
        <hr/>
        <br>
        
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"><br>
        <div id="usernameError" class="error"></div>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password">
        <div id="passwordError" class="error"></div>
        <br>
        
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword"><br>
        <div id="confirmPasswordError" class="error"></div>
		
		<br>
		<hr/>
		<br>
		
		<input type="checkbox" name="agreement" id="agreement"> I agree to the terms and conditions
        <div id="agreementError" class="error"></div>
        <br>
        <input type="submit" value="Register">
        <input type="reset" value="Clear Form">
    </form>
</body>
</html>