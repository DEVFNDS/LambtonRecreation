package lambtonrecreation.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


import lambtonrecreation.dao.AthleteDao;
import lambtonrecreation.model.Athlete;

/**
 * Servlet implementation class InsAthleteServlet
 */
@WebServlet("/insertathlete")
public class InsAthleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsAthleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	if( request.getSession().getAttribute("username") != null) {
    			String username = String.valueOf(request.getSession().getAttribute("username")) ;
    			System.out.println("username "+ username);
    			if(username != null) {
    				Athlete athlete = AthleteDao.getAthleteByUserId(username);
    				System.out.println("athlete "+ athlete);
    				if(athlete.getBio() != null || athlete.getInterests() != null || athlete.getAchievements() != null) {
    					request.setAttribute("athlete", athlete);
        				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/athlete/athleteCard.jsp");
        				dispatcher.forward(request, response);
    				}else {
    	        		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/athlete/athleteForm.jsp");
    	    			dispatcher.forward(request, response);	
    	    		}	
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        try {
        	if( request.getSession().getAttribute("username") != null) {
    			String username = String.valueOf(request.getSession().getAttribute("username")) ;
    			System.out.println("username "+ username);
    			if(username != null) {
	    			String bio = request.getParameter("bio");
	    			String interests = request.getParameter("interests");
	    			String achievements = request.getParameter("achievements");
	    			Athlete athlete = new Athlete();
	                athlete.setBio(bio);
	                athlete.setInterests(interests);
	                athlete.setAchievements(achievements);
	                
	                int status = AthleteDao.saveAthlete(athlete, username);
	                if (status > 0) {
	                	request.setAttribute("athlete", athlete);
	                    request.getRequestDispatcher("views/athlete/athleteCard.jsp").forward(request, response);
	                } else {
	                    request.setAttribute("errorMessage", "Failed to save Athlete information.");
	                    request.getRequestDispatcher("/views/athlete/athleteForm.jsp").forward(request, response);
	                }
    			}
        	}
        	else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home.jsp");
    			dispatcher.forward(request, response);	
    		}
    		response.getWriter().write("Session ended Please Login Again !!!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("/views/athlete/athleteForm.jsp").forward(request, response);
        }
        
    }

}
