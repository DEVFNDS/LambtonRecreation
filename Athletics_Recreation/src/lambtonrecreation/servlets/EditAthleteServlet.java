package lambtonrecreation.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.AthleteDao;
import lambtonrecreation.model.Athlete;

/**
 * Servlet implementation class EditAthleteServlet
 */
@WebServlet("/editathlete")
public class EditAthleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAthleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
        	if( request.getSession().getAttribute("username") != null) {
    			String username = String.valueOf(request.getSession().getAttribute("username")) ;
    			System.out.println("username "+ username);
    			if(username != null) {
    				Athlete athlete = AthleteDao.getAthleteByUserId(username);
    				System.out.println("athlete "+ athlete);
    				request.setAttribute("athlete", athlete);
    				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/athlete/editAthleteForm.jsp");
	    			dispatcher.forward(request, response);		
    			}  			
        	}
    		response.getWriter().write("Session ended Please Login Again !!!");
        }  catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("/views/athlete/athleteForm.jsp").forward(request, response);
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
