package lambtonrecreation.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lambtonrecreation.dao.ApplicationDao;
import lambtonrecreation.util.DBConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
