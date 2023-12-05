/**
 * 
 */

const specialCharsRegExp = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,<>\/?~]/;
const digitRegExp = /[0-9]/;

$(document).ready(function () {
    $("#registrationForm").submit(function (event) {
        event.preventDefault();
        validateRegistrationForm();
    });
    
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        validateLoginForm();
    });
    
    $("#resetButton").click(function () {
        $("#loginForm")[0].reset();
        $("#serverError").text("");
    });
});

function validateRegistrationForm() {
    $(".error").text("");
    var isValid = true;

    // Validation logic for each field
    isValid = validateFirstname() && isValid;
    isValid = validateLastname() && isValid;
    isValid = validateEmail() && isValid;
    isValid = validateDOB() && isValid;
    isValid = validateGender() && isValid;
    isValid = validateRole() && isValid;
    isValid = validateUsername() && isValid;
    isValid = validatePassword() && isValid;
    isValid = validateConfirmPassword() && isValid;
    isValid = validateAgreement() && isValid;

    if (isValid) {
        submitRegistrationForm();
    }
}


function validateLoginForm(){
	 $(".error").text("");
	 var isValid = true;
	 
	 isValid = validateUsername() && isValid;
	 isValid = validatePassword() && isValid;
	 
	 if (isValid) {
	     submitLoginForm();
	 }
}


function validateFirstname() {
    var fname = $("#fname").val().trim();
    if (fname === "") {
        $("#fnameError").text("First Name is required");
        return false;
    }
    
    if(fname.length <3 ){
    	$("#fnameError").text("Minimum 3 characters required");
        return false;
    }
    
    if(fname.length > 50 ){
    	$("#fnameError").text("Maximum 50 characters allowed");
        return false;
    }
    
    if(specialCharsRegExp.test(fname) || digitRegExp.test(fname)){
    	$("#fnameError").text("No special character or digits allowed except dot (.)");
        return false;
    }
    
    return true;
}


function validateLastname() {
    var lname = $("#lname").val().trim();
    if (lname === "") {
        $("#lnameError").text("Last Name is required.");
        return false;
    }
    
    if(lname.length <3 ){
    	$("#lnameError").text("Minimum 3 characters required");
        return false;
    }
    
    if(lname.length > 50 ){
    	$("#lnameError").text("Maximum 50 characters allowed");
        return false;
    }
    
    if(specialCharsRegExp.test(lname) || digitRegExp.test(lname)){
    	$("#lnameError").text("No special character or digits allowed except dot (.)");
        return false;
    }
    return true;
}


function validateUsername() {
    var username = $("#username").val().trim();
    if (username === "") {
        $("#usernameError").text("Username is required.");
        return false;
    }
    
    if(username.length <10 ){
    	$("#usernameError").text("Minimum 10 characters required");
        return false;
    }
    
    if(username.length > 50 ){
    	$("#usernameError").text("Maximum 50 characters allowed");
        return false;
    }
       
    return true;
}



function validatePassword() {
    // Validation logic for password
    var password = $("#password").val();
    if (password.trim() === "") {
        $("#passwordError").text("Password is required.");
        return false;
    }
    return true;
}

function validateConfirmPassword() {
    // Validation logic for confirming password
    var confirmPassword = $("#confirmPassword").val();
    var password = $("#password").val();
    if (confirmPassword.trim() === "") {
        $("#confirmPasswordError").text("Confirm Password is required.");
        return false;
    } else if (password !== confirmPassword) {
        $("#confirmPasswordError").text("Passwords do not match.");
        return false;
    }
    return true;
}

function validateEmail() {
    // Validation logic for email
    var email = $("#email").val();
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email.trim() === "") {
        $("#emailError").text("Email is required.");
        return false;
    } else if (!emailPattern.test(email)) {
        $("#emailError").text("Invalid email address.");
        return false;
    }
    return true;
}

function validateDOB() {
    // Validation logic for date of birth
    var dob = $("#dob").val();
    if (dob.trim() === "") {
        $("#dobError").text("Date of Birth is required.");
        return false;
    }
    return true;
}

function validateGender() {
    // Validation logic for gender
    var gender = $("input[name='gender']:checked").val();
    if (!gender) {
        $("#genderError").text("Gender is required.");
        return false;
    }
    return true;
}

function validateAgreement() {
    // Validation logic for agreement
	console.log("--nk in JavaScript");
    var agreement = $("#agreement").prop("checked");
    if (!agreement) {
        $("#agreementError").text("You must agree to the terms and conditions.");
        return false;
    }
    return true;
}

function validateRole() {
    // Validation logic for role
    var role = $("#role").val();
    if (role.trim() === "" || role.trim() === "--Select Role--") {
        $("#roleError").text("Role is required.");
        return false;
    }
    return true;
}

function submitRegistrationForm() {
    var formData = $("#registrationForm").serialize();
    $.ajax({
        type: "POST",
        url: "register",
        data: formData,
        success: function (response) {
        	console.log("--nk inside response "+response);
        	
            if (response === "success") {
            	
            	console.log("--nk inside success response");
            	alert("Registration successful! Please proceed to login.");
                window.location.href = "login";
                
            } else if(response == 'usernameTaken'){
            	
            	$("#usernameError").text("This username is already taken");
            	
            }else if(response == "emailTaken"){
            	
            	$("#emailError").text("This email is already taken");
            	
            }else if(response == 'DB error'){
            	
            	$("#serverError").text("Some DB Error occurred. Cannot be saved. Please contact Admin.");
            	
            }else {
            	
                $("#serverError").text(response);
            }
        },
        error: function () {
            $("#serverError").text("An error occurred while processing your request. \n");
        }
    });
}

function submitLoginForm(){
	var formData = $("#loginForm").serialize();
    $.ajax({
        type: "POST",
        url: "login",
        data: formData,
        success: function (response) {
        	console.log("--nk inside response "+response);
            if (response === "success") {
                window.location.href = "home";      
            }
            else{
            	$("#serverError").text(response);
            }
        },
        error: function () {
            $("#serverError").text("An error occurred while processing your request. \n");
        }
    });
}