package lambtonrecreation.servlets;

import java.io.IOException;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Base64;
import java.util.Base64.Encoder;

import lambtonrecreation.dao.ApplicationDao;

/**
 * @author Nikita_Kapoor
 * 
 * Implement the Login functionality
 * requests and responses are handles via AJAX calls
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/login.jsp");
		dispatcher.forward(request, response);
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
					if(mapOfUserValAndRole.containsKey("roleName")) {
						session.setAttribute("roleName", (String) mapOfUserValAndRole.get("roleName"));
					}
					
					if(mapOfUserValAndRole.containsKey("userId")) {
						session.setAttribute("userId", (int) mapOfUserValAndRole.get("userId"));
					}
					
					response.getWriter().write("success");	
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
