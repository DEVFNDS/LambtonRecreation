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
		if(mapOfUserValAndRole.size() > 0 && mapOfUserValAndRole.containsKey("userExists")) {
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
					
					System.out.println("Fine Sev");
					response.getWriter().write("success");	
					break;
				
				case 0:
					System.out.println("Wrong password Ser");
					response.getWriter().write("Wrong password. Try again.");
					break;
					
				case -1:
					System.out.println("Wrong user Ser");
					response.getWriter().write("Username doesn't exist.");
					break;
					
				default:
					System.out.println("Some Db Ser "+ userExists);
					response.getWriter().write("Some DB error occurred while validating.");
					break;
			}
		}
	}
}
