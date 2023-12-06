/**
 * @author Nikita_Kapoor
 * Implement client side validations in login and registration
 * Make requests to servlets using AJAX
 * display appropriate screens or redirect based on server responses through AJAX call
 */

const specialCharsRegExp = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,<>\/?~]/;
const digitRegExp = /[0-9]/;
const charRegExp = /[a-zA-z]/;
var passwordRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,50}$/;

$(document).ready(function () {
    $("#registrationForm").submit(function (event) {
        event.preventDefault();
        validateRegistrationForm();   
    });
    
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        validateLoginForm();
    });
    
    $("#resetButtonRegister").click(function () {
        $("#registrationForm")[0].reset();
        $("#serverError").text("");
        $("#fnameError").text("");
        $("#lnameError").text("");
        $("#emailError").text("");
        $("#dobError").text("");
        $("#genderError").text("");
        $("#roleError").text("");
        $("#usernameError").text("");
        $("#passwordError").text("");
        $("#confirmPasswordError").text("");
        $("#agreementError").text("");
    });
    
    $("#resetButton").click(function (){
    	$("#loginForm")[0].reset();
    	$("#serverError").text("");
    	$("#usernameError").text("");
        $("#passwordError").text("");
    });
    
    $("#spinnerID").hide();
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
    
    if(validateDOB() && validateRole()){
    	isValid = roleAgeValidation() && isValid;
    }

    if (isValid) {
    	console.log("Everything valid");
    	$('#spinnerID').show();
        submitRegistrationForm();
    }else{
    	$("html, body").animate({ scrollTop: 0 }, "fast");
    	$("#serverError").text("Review all errors in form.");
    }
}


function validateLoginForm(){
	 $(".error").text("");
	 var isValid = true;
	 
	 isValid = validateloginUsername() && isValid;
	 isValid = validateloginPassword() && isValid;
	 if (isValid) {
		 $('#spinnerID').show();
	     submitLoginForm();
	 }else{
		 $("html, body").animate({ scrollTop: 0 }, "fast");
		 $("#serverError").text("Review all errors in form.");
	 }
}

function validateloginUsername() {
    var username = $("#username").val().trim();
    if (username === "") {
    	console.log("qwer");
        $("#usernameError").text("Username is required.");
        return false;
    }
    return true;
}

function validateloginPassword() {
    var password = $("#password").val().trim();
    if (password === "") {
    	console.log("poiu");
        $("#passwordError").text("Password is required.");
        return false;
    }
    return true;
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
    
    if(username.length <5 ){
    	$("#usernameError").text("Minimum 5 characters required");
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
    var password = $("#password").val().trim();
    if (password === "") {
        $("#passwordError").text("Password is required");
        return false;
    }
    
    if(password.length < 8 || password.length > 50){
    	$("#passwordError").text("Minimum 8 and maximum 50 characters allowed");
        return false;
    }
    
    if(!passwordRegExp.test(password)){
    	$("#passwordError").text("Password must include symbols, digits, capital & small letters.");
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
        $("#emailError").text("Invalid email address format.");
        return false;
    }
    return true;
}

function validateDOB() {
    // Validation logic for date of birth
    var dob = $("#dob").val();
    if (dob === "") {
        $("#dobError").text("Date of Birth is required.");
        return false;
    }
    
    dob = new Date($("#dob").val());    
    var currentDate = new Date();
    
    if (dob > currentDate) {
        $("#dobError").text("DOB cannot be a future date");
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


function roleAgeValidation(){
	let role = $("#role").val().trim();
	let dob = new Date($("#dob").val().trim());
	
	let coachAge = new Date();
	coachAge.setFullYear(coachAge.getFullYear() - 18);
	
	let playerAge = new Date();
	playerAge.setFullYear(playerAge.getFullYear() - 5);
	
	if(role === "Coach" && dob >= coachAge){
		$("#dobError").text("Coach should be atleast 18 years of age.");
		return false;
	}
		
	if(role === "Player" && dob >= playerAge){
		$("#dobError").text("Player should be atleast 5 years of age.");
		return false;
	}
	
	return true;
}


/*
 * submit registration form after all validations are successful
 * use AJAX to request to servlet and handle response from servlet 
 * display appropriate messages on the screen based on server response
 * */

function submitRegistrationForm() {
    var formData = $("#registrationForm").serialize();
    $.ajax({
        type: "POST",
        url: "register",
        data: formData,
        success: function (response) {        	
            if (response === "success") {
            	alert("Registration successful! Please proceed to login.");
                window.location.href = "login";
                
            } else if(response == 'usernameTaken'){
            	$("#serverError").text("Review all errors in form.");
            	$("#usernameError").text("This username is already taken");
            	$("html, body").animate({ scrollTop: 0 }, "slow");
            	
            }else if(response == "emailTaken"){
            	$("#serverError").text("Review all errors in form.");
            	$("#emailError").text("This email is already taken");
            	$("html, body").animate({ scrollTop: 0 }, "slow");
            	
            }else if(response == 'DB error'){
            	
            	$("#serverError").text("Some DB Error occurred. Cannot be saved. Please contact Admin.");
            	$("html, body").animate({ scrollTop: 0 }, "slow");
            	
            }else {
            	
                $("#serverError").text(response);
                $("html, body").animate({ scrollTop: 0 }, "slow");
            }
        },
        error: function () {
            $("#serverError").text("An error occurred while processing your request. \n");
            $("html, body").animate({ scrollTop: 0 }, "slow");
        },
        complete: function () {
            console.log("request completed");
             $('#spinnerID').hide();
        }
    });
}



/*
 * submit login form after all validations are successful
 * use AJAX to request to servlet and handle response from servlet 
 * display appropriate messages on the screen based on server response
 * */

function submitLoginForm(){
	console.log("inside submitLogin");
	var formData = $("#loginForm").serialize();
    $.ajax({
        type: "POST",
        url: "login",
        data: formData,
        success: function (response) {
        	console.log("--nk inside response "+response);
            if (response.status === "success") {
            	if (response.isAdmin) {
            		window.location.href = "adminPortal";
            	} else {
            		window.location.href = "home";
            	}
            }
            else{
            	$("#serverError").text(response);
            }
        },
        error: function () {
            $("#serverError").text("An error occurred while processing your request. \n");
        },
        complete: function () {
            console.log("request completed");
             $('#spinnerID').hide();
        }
    });
}