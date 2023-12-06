<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/loginregister.css">
    <link rel="stylesheet" type="text/css" href="css/footer.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript" src="js/registerLoginValidate.js"></script>
</head>

<body class="my-login-style"> <!-- Change class to my-login-style -->
    <%@ include file="../header.jsp" %>
    

    <div class="container my-login-container">
        <div class="row justify-content-center">
            <div class="col-md-8">

                <div class="card">
                    <div class="card-body">
                        <h2>Login</h2>

                    
						<!--<form action="${pageContext.request.contextPath}/LoginServlet" method="post">-->
                        <form id="loginForm" >
						<div id="serverError" style="color: red;"></div>
		
                            <div class="mb-3">
                                <label for="username" class="form-label">Username:</label>
                                <div class="input-group">
                                    <span class="input-group-text">
                                        <i class="fa fa-user"></i>
                                    </span>
                                    <input type="text" name="username" id="username" class="form-control">
                                    <div id="usernameError" class="error"></div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Password:</label>
                                <div class="input-group">
                                    <span class="input-group-text">
                                        <i class="fa fa-lock"></i>
                                    </span>
                                    <input type="password" name="password" id="password" class="form-control">
                                    <div id="passwordError" class="error"></div>
                                </div>
                            </div>
							
							<div id="spinnerID" class="z-3 text-center">
  								<div class="spinner-border" role="status">
    								<span class="sr-only"></span>
  								</div>
  							</div><br/>
                            <button type="submit" class="btn btn-primary">Login</button>
                            <button type="button" id="resetButton" class="btn btn-secondary">Reset</button>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <%@ include file="../footer.jsp" %>
</body>
</html>