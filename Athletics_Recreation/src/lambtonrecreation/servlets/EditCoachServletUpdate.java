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

@WebServlet("/editcoachupdate")
public class EditCoachServletUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCoachServletUpdate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("username") != null) {
                String username = String.valueOf(request.getSession().getAttribute("username"));
                System.out.println("username " + username);
                if (username != null) {
                    String sportsSpecializedIn = request.getParameter("sportsSpecializedIn");
                    String coachingExperience = request.getParameter("coachingExperience");
                    String certifications = request.getParameter("certifications");
                    String availability = request.getParameter("availability");

                    Coach coach = new Coach();
                    coach.setSportsSpecializedIn(sportsSpecializedIn);
                    coach.setCoachingExperience(coachingExperience);
                    coach.setCertifications(certifications);
                    coach.setAvailability(availability);

                    int status = CoachDao.updateCoach(coach, username);

                    if (status > 0) {
                        request.setAttribute("coach", coach);
                        request.getRequestDispatcher("views/coach/coachCard.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "Failed to save Coach information.");
                        request.getRequestDispatcher("/views/coach/coachForm.jsp").forward(request, response);
                    }
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home.jsp");
                dispatcher.forward(request, response);
            }
            response.getWriter().write("Session ended. Please login again.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("/views/coach/coachForm.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
