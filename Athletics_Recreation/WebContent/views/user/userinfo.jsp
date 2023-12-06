<!DOCTYPE html>
<html>

<head>
  <title>User Profile</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous">
  </script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
  <link rel="stylesheet" type="text/css" href="css/userinfo.css" />
  <link rel="icon" type="image/x-icon" href="../assets/images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="css/footer.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>

<body>
 <%@ include file="../header.jsp" %>
  
<%@ page import="java.util.List" %>
<%@ page import="lambtonrecreation.model.Event" %>
<%@ page import="lambtonrecreation.model.User" %> 
  
  <%
    List<Event> eventList = (List<Event>) request.getAttribute("eventList");
	User user = (User) request.getAttribute("user");
	if(user == null){
		user = new User();
	}
%>
  
  <main>
  <br><br>
    <section>
      <h2 class="mt-3 text-center" style="color: #007bff">User Profile</h2>
      <div class="cards">
        <!-- User profile picture -->
        <div class="card-text">
          <img src="images/team-1.jpg" alt="" id="userimg" />
        </div>
        <!-- Card for user information
        <div class="card" style="width: 50vh">
          <div class="card-text">
            <h2><%= user.getFirstName() %> <%= user.getLastName() %></h2>
             <p class="text-muted"><%= user.getGender() %></p>
            <p class="text-muted"><%= user.getDob() %></p>
            <p style="text-decoration: underline">Contact Info:</p>
            <h5><%= user.getEmail() %></h5>
          </div>
        </div> -->
        <div class="card text-center" style="width: 50vh; border: none; background-color: #fff; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
  <img src="images/team-1.png" class="card-img-top rounded-circle" alt="User Image" style="width: 100px; height: 100px; object-fit: cover; margin: 20px auto;">
  <div class="card-body">
    <h2 class="card-title mb-2"><%= user.getFirstName() %> <%= user.getLastName() %></h2>
    <p class="text-muted mb-2"><strong>Gender:</strong> <%= user.getGender() %></p>
    <p class="text-muted mb-2"><strong>Date of Birth:</strong> <%= user.getDob() %></p>
    <div class="divider mb-3"></div>
    <p class="text-muted mb-1" style="text-decoration: underline">Contact Info:</p>
    <h5 class="mb-4"><%= user.getEmail() %></h5>
  </div>
</div>
        
      </div>
    </section>
    <!-- Cards for user Overview and Performance -->
    <section><br>
      <h2 class="mt-3 text-center" style="color: #007bff">Registered Events</h2>
      <div class="cards">
        <!-- Event 1 -->
        
        
        <%
    if (eventList != null && !eventList.isEmpty()) {
        for (Event obj : eventList) {
%>
<div class="card mb-4">
  <div class="card-body">
    <h2 class="card-title mb-3" style="color: #ae34eb"><%= obj.getName() %></h2>
    <p class="mb-2"><strong>Sport:</strong> <%= obj.getSportName() %></p>
    <p class="mb-2"><strong>Date and Time:</strong> <%= obj.getDateTime() %></p>
    <p class="mb-2"><strong>Location:</strong> <%= obj.getLocation() %></p>
    <p class="mb-2"><strong>Description:</strong> <%= obj.getDescription() %></p>
    <p class="mb-2"><strong>Registration Deadline:</strong> <%= obj.getRegistrationDeadline() %></p>
  </div>
</div>


          <%
        }
    } else {
%>
    <p>No objects in the list.</p>
<%
    }
%>
        
        </div>
    </section>
  </main>
  
  
  <%@ include file="../footer.jsp" %>
</body>

</html>
