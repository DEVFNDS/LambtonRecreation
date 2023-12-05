package lambtonrecreation.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.SportDao;
import lambtonrecreation.model.Sport;
import lambtonrecreation.util.ValidationUtils;

/**
 * Servlet implementation class SportServlet
 * @author Jagraj-kaur
 */
@WebServlet("/sports")
public class SportServlet extends HttpServlet {
	
	private final SportDao sportDao = new SportDao();   
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if ("edit".equals(action)) {
            editSportForm(request, response);
        } else if ("delete".equals(action)) {
            deleteSport(request, response);
        } else {
            // Default action: List all sports
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
        List<Sport> sports = sportDao.getAllSports();
        request.setAttribute("sports", sports);
        if(request.getSession().getAttribute("roleName") != null && 
			String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
        	request.getRequestDispatcher("/views/sport/admin/sports.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("/views/sport/sports.jsp").forward(request, response);
        }
	}
	
	private void addSport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
			Sport sport = createSportFromRequest(request);

        	if(isValidSportData(sport)) {
        		try {
					sportDao.createSport(sport);
					response.sendRedirect(request.getContextPath() + "/sports");
				} catch (SQLException e) {
					e.printStackTrace();
					// Handle the exception appropriately, e.g., display an error message
	                response.sendRedirect(request.getContextPath() + "/sports?error=failedToAddSport");
				}
        	} else {
        		// Data is invalid, handle the validation errors
                // For simplicity, let's set an error message and forward to the form page
                request.setAttribute("error", "Invalid sports data. Please check your input.");
                response.sendRedirect(request.getContextPath() + "/sports");
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
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
			int sportId = Integer.parseInt(request.getParameter("id"));
	        Sport sport = sportDao.getSportById(sportId);
	
	        if (sport != null) {
	            request.setAttribute("sport", sport);
	            request.getRequestDispatcher("/views/sport/admin/edit_sport.jsp").forward(request, response);
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
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
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
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
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
	
	// Perform validation checks
    private boolean isValidSportData(Sport sport) {
        boolean isValid = true;
        if(ValidationUtils.isNullOrEmpty(sport.getName())) {
        	isValid = false;
        } else {
        	if(!ValidationUtils.isWithinLengthLimit(sport.getName(), 50)) {
        		isValid = false;
        	}
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getDescription()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getDescription(), 150)) {
        	isValid = false;
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getRules()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getRules(), 100)) {
        	isValid = false;
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getEquipmentNeeded()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getEquipmentNeeded(), 100)) {
        	isValid = false;
        }
        return isValid;
    }

}
