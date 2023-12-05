package lambtonrecreation.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class EditEventServlet
 */
@WebServlet("/EditEventServlet")
public class EditEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final SportDao sportDao = new SportDao(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
//		Event event = EventDao.getEventById(id);
//		
//		List<Sport> sports = sportDao.getAllSports();
//        request.setAttribute("sports", sports);
//		
//		request.setAttribute("event", event);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/event/eventFormEdit.jsp");
		dispatcher.forward(request, response);
	}


}
