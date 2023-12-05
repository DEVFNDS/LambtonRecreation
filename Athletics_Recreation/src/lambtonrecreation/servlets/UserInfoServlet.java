package lambtonrecreation.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import lambtonrecreation.dao.UserEventDao;
import lambtonrecreation.model.Event;
import lambtonrecreation.model.User;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = String.valueOf(request.getSession().getAttribute("userName"));
		System.out.println("username "+ username);
		
		if(username != null) {
			
			int user_id = UserEventDao.getUserIdFromUsername(username);
			List<Event> eventList = UserEventDao.getUserEvents(user_id);
			
			User user = UserEventDao.getUserFromUsername(username);
			
			request.setAttribute("eventList", eventList);
			request.setAttribute("user", user);
	        request.getRequestDispatcher("views/user/userinfo.jsp").forward(request, response);
		}
		response.getWriter().write("Session ended Please Login Again !!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
