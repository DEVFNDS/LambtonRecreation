package lambtonrecreation.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        } else if ("addForm".equals(action)) {
        	addSportForm(request, response);
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
        
		if (request.getSession().getAttribute("userId") != null) {
        	int userId = (int) request.getSession().getAttribute("userId");
        	
        	for (Sport sport : sports) {
        		boolean isFavouriteForUser = sportDao.isSportFavouriteForUser(sport.getId(), userId);
        		if(isFavouriteForUser) {
        			sport.setFavourite(true);
        		}
			}
        }
        request.setAttribute("sports", sports);
        
        if(request.getSession().getAttribute("roleName") != null && 
			String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
        	request.getRequestDispatcher("/views/sport/admin/sports.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("/views/sport/sports.jsp").forward(request, response);
        }
	}
	
	private void addSportForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
            request.getRequestDispatcher("/views/sport/admin/add_sport.jsp").forward(request, response);	            
		} else {
			// User does not have the "admin" role, redirect or display an error message
            response.sendRedirect(request.getContextPath() + "/sports?error=permissionDenied");
		}
    }
	
	private void addSport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
			Sport sport = createSportFromRequest(request);
			
			System.out.println("Validate the data");
			Map<String, String> fieldErrors = validateSportData(sport);
        	if(fieldErrors.isEmpty()) {
        		try {
					sportDao.createSport(sport);
					response.sendRedirect(request.getContextPath() + "/sports");
				} catch (SQLException e) {
					e.printStackTrace();
					// Handle the exception appropriately, e.g., display an error message
	                response.sendRedirect(request.getContextPath() + "/sports?error=failedToAddSport");
				}
        	} else {
        		System.out.println("Invalid Data!");
        		System.out.println(fieldErrors);
        		request.setAttribute("fieldErrors", fieldErrors);
        		
        		//Retain form data by setting parameters as attributes
        	    request.setAttribute("name", sport.getName());
        	    request.setAttribute("description", sport.getDescription());
        	    request.setAttribute("rules", sport.getRules());
        	    request.setAttribute("equipmentNeeded", sport.getEquipmentNeeded());
        	    
        	    request.getRequestDispatcher("/views/sport/admin/add_sport.jsp").forward(request, response);
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
	
	private void updateSport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getSession().getAttribute("roleName") != null && 
				String.valueOf(request.getSession().getAttribute("roleName")).equalsIgnoreCase("admin")) {
			Sport sport = createSportFromRequest(request);
	        sport.setId(Integer.parseInt(request.getParameter("id")));
	
	        System.out.println("Validate the data");
			Map<String, String> fieldErrors = validateSportData(sport);
        	if(fieldErrors.isEmpty()) {
        		 try {
     	            sportDao.updateSport(sport);
     	            response.sendRedirect(request.getContextPath() + "/sports");
     	        } catch (SQLException e) {
     	            e.printStackTrace();
     	            // Handle the exception appropriately, e.g., display an error message
     	            response.sendRedirect(request.getContextPath() + "/sports?error=failedToUpdateSport");
     	        }
        	} else {
        		request.setAttribute("fieldErrors", fieldErrors);
        		request.setAttribute("sport", sport);
        	    request.getRequestDispatcher("/views/sport/admin/edit_sport.jsp").forward(request, response);
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
    private Map<String, String> validateSportData(Sport sport) {
        Map<String, String> fieldErrors = new HashMap<>();
        
    	if(ValidationUtils.isNullOrEmpty(sport.getName())) {
        	fieldErrors.put("name", "Name is required.");
        } else {
        	if (!sport.getName().matches("^[a-zA-Z ]+$")) {
                fieldErrors.put("name", "Name must contain only characters.");
            } else if(!ValidationUtils.isWithinLengthLimit(sport.getName(), 50)) {
        		fieldErrors.put("name", "Name must be within 50 characters.");
        	}
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getDescription()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getDescription(), 150)) {
        	 fieldErrors.put("description", "Description must be within 150 characters.");
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getRules()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getRules(), 100)) {
        	fieldErrors.put("rules", "Rules must be within 100 characters.");
        }
        if(!ValidationUtils.isNullOrEmpty(sport.getEquipmentNeeded()) && 
        		!ValidationUtils.isWithinLengthLimit(sport.getEquipmentNeeded(), 100)) {
        	fieldErrors.put("equipmentNeeded", "Equipment Needed must be within 100 characters.");
        }
        return fieldErrors;
    }

}
