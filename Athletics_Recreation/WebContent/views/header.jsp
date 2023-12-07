<%
    	session = request.getSession(false);
    	String username = (session != null) ? (String) session.getAttribute("username") : null;
    	String roleName = (session != null) ? (String) session.getAttribute("roleName") : null;
    	Integer userIdObj = (session != null) ? (Integer) session.getAttribute("userId") : null;
        int userId = (userIdObj != null) ? userIdObj.intValue() : 0;

%>

<nav>
    <a href="home">Home</a>
    <a href="sports">Sports</a>
    <a href="viewevent">Events</a>
    <% if (roleName != null && roleName.equals("Coach")) { %>
    <a href="insertcoach">Profile</a>
	<% } %>
	<% if (roleName != null && roleName.equals("Player")) { %>
    <a href="insertathlete">Profile</a>
	<% } %>
   
    <% if (username != null){ %>
     	<a href="userinfo"><p style="color: pink; margin: 0;"><%= username %> | <%= roleName %></p></a>
     	<a href="logout">Logout</a>
    <% } else { %>
     	<a href="register">Sign up</a>
     	<a href="login">Sign in</a>
    <% } %>
</nav>