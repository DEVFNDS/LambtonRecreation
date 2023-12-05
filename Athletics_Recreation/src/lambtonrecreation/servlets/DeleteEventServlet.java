package lambtonrecreation.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.EventDao;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/deleteevent")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		// Delete the user event mapping first
		EventDao.deleteUserEventMapping(id);
		EventDao.delete(id);
		
		request.setAttribute("alertMessage", "Successfully delted the Event !!!");

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/event/eventCard.jsp");
        dispatcher.forward(request, response);
		
	}

	

}
