package lambtonrecreation.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import lambtonrecreation.dao.ApplicationDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/login.jsp");
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("assign privileges to the user for a successful login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		request.getSession().setAttribute("userName", username);
		
		Map<String, Object> mapOfUserValAndRole = ApplicationDao.validateUser(username, password);
			int userExists = (int) mapOfUserValAndRole.get("userExists");
			
			switch(userExists) {
				case 1:
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					
					String roleName = null;
					if(mapOfUserValAndRole.containsKey("roleName")) {
						roleName = (String) mapOfUserValAndRole.get("roleName");
						session.setAttribute("roleName", roleName);
					}
						
					if(mapOfUserValAndRole.containsKey("userId")) {
						session.setAttribute("userId", (int) mapOfUserValAndRole.get("userId"));
					}
					
					// Create a JSON object to include roleName and userId
                    Gson gson = new Gson();
                    Map<String, Object> responseData = new HashMap<>();
                    
                    responseData.put("status", "success");
                    responseData.put("roleName", roleName);
                    responseData.put("userId", session.getAttribute("userId"));
                    
                    if ("admin".equalsIgnoreCase(roleName)) {
                        responseData.put("isAdmin", true);
                    }
                    
                    response.setContentType("application/json");
                    response.getWriter().write(gson.toJson(responseData));
					break;
				
				case 0:
					response.getWriter().write("Wrong password. Try again.");
					break;
					
				case -1:
					response.getWriter().write("Username doesn't exist.");
					break;
					
				default:
					response.getWriter().write("Some DB error occurred while validating. Please contact Admin.");
					break;
			}
	}
}
