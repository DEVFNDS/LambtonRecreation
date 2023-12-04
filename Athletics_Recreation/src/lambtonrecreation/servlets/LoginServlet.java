package lambtonrecreation.servlets;

import java.io.IOException;

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
		
		int userExists = ApplicationDao.validateUser(username, password);
		
		if(userExists == 1){
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //response.sendRedirect("/jsps/Home.jsp");
            response.getWriter().write("success");			
		}else if(userExists == -2){			
			response.getWriter().write("Incorrect Username and password. Try again.");
		}
		else if(userExists == -1){			
			//request.setAttribute("error", "Invalid Credentials, please login again!");
			//request.getRequestDispatcher("/jsps/Login.jsp").forward(request,response);
			response.getWriter().write("Username doesn't exist.");
		}else if(userExists == 0){			
			response.getWriter().write("Wrong password. Try again.");
		}else {
			response.getWriter().write("Some DB error occurred while validating.");
		}
	}

}
