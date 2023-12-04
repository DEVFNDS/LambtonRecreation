package lambtonrecreation.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.SportDao;
import lambtonrecreation.model.Sport;

/**
 * Servlet implementation class SportServlet
 * @author Jagraj-kaur
 */
@WebServlet("/sports")
public class SportServlet extends HttpServlet {
	
	private final SportDao sportDao = new SportDao();   
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside get method of sports servlet");
		String action = request.getParameter("action");
		System.out.println("Action :"+action);
        if ("edit".equals(action)) {
            editSportForm(request, response);
        } else if ("delete".equals(action)) {
            deleteSport(request, response);
        } else {
            // Default action: List all sports
        	System.out.println("inside else part");
            listAllSports(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSport(request, response);
        } else if ("update".equals(action)) {
            updateSport(request, response);
        } else {
            // Default action: List all sports
            listAllSports(request, response);
        }
	}
	
	private void listAllSports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside list all sports method");
		List<Sport> sports = sportDao.getAllSports();
        request.setAttribute("sports", sports);
        request.getRequestDispatcher("/views/sport/sports.jsp").forward(request, response);
	}
	
	private void addSport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.isUserInRole("admin")) {
        	Sport sport = createSportFromRequest(request);

            try {
                sportDao.createSport(sport);
                response.sendRedirect(request.getContextPath() + "/sports");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately, e.g., display an error message
                response.sendRedirect(request.getContextPath() + "/sports?error=failedToAddSport");
            }
        }else {
            // User does not have the "admin" role, redirect or display an error message
            response.sendRedirect(request.getContextPath() + "/sports?error=permissionDenied");
            // You can also forward to an error page or display a message on the current page
            // request.setAttribute("error", "Permission Denied");
            // request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
	
	private void editSportForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole("admin")) {
			int sportId = Integer.parseInt(request.getParameter("id"));
	        Sport sport = sportDao.getSportById(sportId);
	
	        if (sport != null) {
	            request.setAttribute("sport", sport);
	            request.getRequestDispatcher("/views/sport/edit_sport.jsp").forward(request, response);
	        } else {
	            // Handle the case where the sport is not found
	            response.sendRedirect(request.getContextPath() + "/sports?error=sportNotFound");
	        }
		} else {
			// User does not have the "admin" role, redirect or display an error message
            response.sendRedirect(request.getContextPath() + "/sports?error=permissionDenied");
		}
    }
	
	private void updateSport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.isUserInRole("admin")) {
			Sport sport = createSportFromRequest(request);
	        sport.setId(Integer.parseInt(request.getParameter("id")));
	
	        try {
	            sportDao.updateSport(sport);
	            response.sendRedirect(request.getContextPath() + "/sports");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately, e.g., display an error message
	            response.sendRedirect(request.getContextPath() + "/sports?error=failedToUpdateSport");
	        }
		} else {
			// User does not have the "admin" role, redirect or display an error message
            response.sendRedirect(request.getContextPath() + "/sports?error=permissionDenied");
		}
    }
	
	private void deleteSport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.isUserInRole("admin")) {
			int sportId = Integer.parseInt(request.getParameter("id"));
	
	        try {
	            sportDao.deleteSport(sportId);
	            response.sendRedirect(request.getContextPath() + "/sports");
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle the exception appropriately, e.g., display an error message
	            response.sendRedirect(request.getContextPath() + "/sports?error=failedToDeleteSport");
	        }
		} else {
			// User does not have the "admin" role, redirect or display an error message
            response.sendRedirect(request.getContextPath() + "/sports?error=permissionDenied");
		}
    }
	
	private Sport createSportFromRequest(HttpServletRequest request) {
        Sport sport = new Sport();
        sport.setName(request.getParameter("name"));
        sport.setDescription(request.getParameter("description"));
        sport.setRules(request.getParameter("rules"));
        sport.setEquipmentNeeded(request.getParameter("equipmentNeeded"));
        return sport;
    }

}
