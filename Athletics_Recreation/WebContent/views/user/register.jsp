<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.dao.ApplicationDao" %>
<%@ page import="lambtonrecreation.model.User" %>
<%@ page import="lambtonrecreation.model.Role" %>

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
<html lang="en">
<head>
    <title>User Registration</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/footer.css">
    <link rel="stylesheet" type="text/css" href="css/loginregister.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript" src="js/registerLoginValidate.js"></script>
</head>
<body>
    <%@ include file="../header.jsp" %>

    <div class="custom-container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div id="registration-box">
                    <h2>User Registration Form</h2>
                    <form id="registrationForm" class="needs-validation" novalidate>
			        <!-- server-side error message here -->
			        <div id="serverError" style="color: red;"></div>

                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="fname" class="form-label">First Name:</label>
                                <input type="text" name="fname" id="fname" class="form-control" required>
                                <div id="fnameError" class="error"></div>
                            </div>

                            <div class="col-md-6">
                                <label for="lname" class="form-label">Last Name:</label>
                                <input type="text" name="lname" id="lname" class="form-control" required>
                                <div id="lnameError" class="error"></div>
                            </div>
                        </div><br/>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" name="email" id="email" class="form-control" required>
                            <div id="emailError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="dob" class="form-label">Date of Birth:</label>
                            <input type="date" name="dob" id="dob" class="form-control" required>
                            <div id="dobError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender:</label>
                            <div class="form-check form-check-inline">
                                <input type="radio" name="gender" id="male" value="Male" class="form-check-input" required>
                                <label for="male" class="form-check-label">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" name="gender" id="female" value="Female" class="form-check-input" required>
                                <label for="female" class="form-check-label">Female</label>
                            </div>
                            <div id="genderError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="role" class="form-label">Role:</label>
                            <select name="role" id="role" class="form-select" required>
                       		 <% for (Role role : roles) { %>
                            <option value="<%= role.getRole() %>"><%= role.getRole() %></option>
                       		 <% } %>
                    		</select>
                            <div id="roleError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="username" class="form-label">Username:</label>
                            <input type="text" name="username" id="username" class="form-control">
                            <div id="usernameError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password:</label>
                            <input type="password" name="password" id="password" class="form-control">
                            <div id="passwordError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm Password:</label>
                            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control">
                            <div id="confirmPasswordError" class="error"></div>
                        </div>
                        <div class="mb-3">
                            <input type="checkbox" name="agreement" id="agreement" class="form-check-input shadow" required>
                            <label for="agreement" class="form-check-label">I agree to the terms and conditions</label>
                            <div id="agreementError" class="error"></div>
                        </div>

                        <div class="mb-3">
                            <input type="submit" value="Register" class="btn btn-primary">
                            <input type="reset" id="resetButtonRegister" value="Clear Form" class="btn btn-secondary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> -->
</body>
<%@ include file="../footer.jsp" %>
</html>