package lambtonrecreation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.EventDao;
import lambtonrecreation.model.Event;

/**
 * Servlet implementation class ViewEventServlet
 */
@WebServlet("/viewevent")
public class ViewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Event> eventList = EventDao.getAllEvents();
		
		 request.setAttribute("eventList", eventList);
		 System.out.println(request.getSession().getAttribute("roleName"));
		 
		 if(request.getSession().getAttribute("roleName") != null && 
				 String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
			 System.out.println("entereddddd-----");
			 request.setAttribute("show", true);
		 }else {
			 System.out.println("entereddddd");
			 request.setAttribute("show", false);
		 }
		 
		 System.out.println(request.getAttribute("show"));
		 RequestDispatcher dispatcher = request.getRequestDispatcher("views/event/eventCard.jsp");
	      dispatcher.forward(request, response);
	}


}
