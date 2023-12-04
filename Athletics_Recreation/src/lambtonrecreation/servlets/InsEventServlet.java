package lambtonrecreation.servlets;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.EventDao;
import lambtonrecreation.dao.SportDao;
import lambtonrecreation.model.Event;
import lambtonrecreation.model.Sport;

/**
 * Servlet implementation class InsEventServlet
 */
@WebServlet("/InsEventServlet")
public class InsEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final SportDao sportDao = new SportDao(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Sport> sports = sportDao.getAllSports();
        request.setAttribute("sports", sports);
        request.getRequestDispatcher("/views/event/eventForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        try {
        	String name = request.getParameter("name");
    		String sportId = request.getParameter("sport_id");
    		
    		String dateTimeStr = request.getParameter("date_time");

            // Convert String to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dateTime = dateFormat.parse(dateTimeStr);
            
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            String deadlineStr = request.getParameter("registration_deadline");
            
            dateFormat = new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            Date registrationDeadline = dateFormat.parse(deadlineStr);
            
            Event event = new Event();
            event.setName(name);
            event.setSportId(Integer.valueOf(sportId));
            event.setDateTime(new java.sql.Timestamp (dateTime.getTime()));
            event.setLocation(location);
            event.setDescription(description);
            event.setRegistrationDeadline(new java.sql.Timestamp(registrationDeadline.getTime()));
            
            int status = EventDao.saveEvent(event);
            if(status > 0) {
            	response.sendRedirect("/views/event/eventCard.jsp");
            }

            System.out.println("Parsed Date and Time: " + dateTime);
        } catch (ParseException e) {
            e.printStackTrace(); 
            
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            
            request.getRequestDispatcher("eventForm.jsp").forward(request, response);
        }
		
		
		
	}

}
