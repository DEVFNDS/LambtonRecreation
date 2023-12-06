package lambtonrecreation.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.CoachDao;
import lambtonrecreation.model.Coach;

@WebServlet("/editcoach")
public class EditCoachServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCoachServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("username") != null) {
                String username = String.valueOf(request.getSession().getAttribute("username"));
                System.out.println("username " + username);
                if (username != null) {
                    Coach coach = CoachDao.getCoachByUserId(username);
                    System.out.println("coach " + coach);
                    request.setAttribute("coach", coach);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/coach/editCoachForm.jsp");
                    dispatcher.forward(request, response);
                }
            }
            response.getWriter().write("Session ended. Please Login Again !!!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("/views/coach/coachForm.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle coach form submission if needed
        doGet(request, response);
    }
}
