package lambtonrecreation.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.UserEventDao;

/**
 * Servlet implementation class EventRegisterServlet
 */
@WebServlet("/EventRegisterServlet")
public class EventRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if( request.getSession().getAttribute("username") != null) {
			String username = String.valueOf(request.getSession().getAttribute("username")) ;
			System.out.println("username "+ username);
			
			if(username != null) {
				int event_id = Integer.valueOf(request.getParameter("id"));
				int status = UserEventDao.userEventSave(username, event_id);
				
				response.sendRedirect("views/event/eventCard.jsp");
			}
		}
		else {
			response.sendRedirect("views/user/login.jsp");
		}
		response.getWriter().write("Session ended Please Login Again !!!");
	}

}
