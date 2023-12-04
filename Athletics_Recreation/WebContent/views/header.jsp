<%
  	session = request.getSession(false);
  	String username = (session != null) ? (String) session.getAttribute("username") : null;
%>

<nav>
    <a href="home">Home</a>
    <a href="#sportsSection">Sports</a>
    <a href="#eventsSection">Events</a>
    <a href="#newsSection">News/Articles</a>
    
    <% if (username != null){ %>
     	<p style="color: pink; margin: 0;"><%= username %> | </p><a href="logout">Logout</a>
    <% } else { %>
     	<a href="register">Sign up</a>
     	<a href="login">Sign in</a>
    <% } %>
</nav>