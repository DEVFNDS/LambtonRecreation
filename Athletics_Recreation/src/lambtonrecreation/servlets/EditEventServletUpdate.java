package lambtonrecreation.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.EventDao;
import lambtonrecreation.model.Event;

/**
 * Servlet implementation class EditEventServletUpdate
 */
@WebServlet("/EditEventServletUpdate")
public class EditEventServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventServletUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
        	String name = request.getParameter("name");
    		String sportId = request.getParameter("sport_id");
    		String id = request.getParameter("id");
    		
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
            event.setId(Integer.valueOf(id));
            event.setName(name);
            event.setSportId(Integer.valueOf(sportId));
            event.setDateTime(new java.sql.Timestamp (dateTime.getTime()));
            event.setLocation(location);
            event.setDescription(description);
            event.setRegistrationDeadline(new java.sql.Timestamp(registrationDeadline.getTime()));
            
            int status = EventDao.updateEvent(event);
            if(status > 0) {
            	
            	request.setAttribute("alertMessage", "Successfully Updated the Event !!!");

                // Forward the request to the JSP page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home.jsp");
                dispatcher.forward(request, response);
            }

            System.out.println("Parsed Date and Time: " + dateTime);
        } catch (ParseException e) {
            e.printStackTrace(); 
            
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            
            request.getRequestDispatcher("eventForm.jsp").forward(request, response);
        }
		
		
		
	}

}
