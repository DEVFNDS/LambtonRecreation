package lambtonrecreation.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.SportDao;

/**
 * Servlet implementation class RegisterSportServlet
 * @author Jagraj-kaur
 */
@WebServlet("/registerSport")
public class RegisterSportServlet extends HttpServlet {
	
	private final SportDao sportDao = new SportDao();   
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
        String sportId = request.getParameter("sportId");
        
        boolean isRegistered = sportDao.saveUserSportRelation(userId, sportId);
        if (isRegistered) {
            response.getWriter().write("User-sport relationship saved successfully!");
        } else {
            response.getWriter().write("Failed to save user-sport relationship!");
        }
	}

}
