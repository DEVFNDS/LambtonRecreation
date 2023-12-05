<%
    	session = request.getSession(false);
    	String username = (session != null) ? (String) session.getAttribute("username") : null;
    	String roleName = (session != null) ? (String) session.getAttribute("roleName") : null;
%>

<nav>
    <a href="home">Home</a>
    <a href="#sportsSection">Sports</a>
    <a href="#eventsSection">Events</a>
    <a href="#newsSection">News/Articles</a>
    
    <% if (username != null){ %>
     	<a href="logout" id="logoutLink"><%= username %> | <%= roleName %> | Logout</a>
    <% } else { %>
     	<a href="register">Sign up</a>
     	<a href="login">Sign in</a>
    <% } %>
</nav>