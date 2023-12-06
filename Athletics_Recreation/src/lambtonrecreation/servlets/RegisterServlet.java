package lambtonrecreation.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.ApplicationDao;
import lambtonrecreation.model.Role;
import lambtonrecreation.model.User;


/**
 * @author Nikita_Kapoor
 * 
 * Implement the Registration functionality
 * requests and responses are handles via AJAX calls
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Role> roles = ApplicationDao.getRoles();
		roles.add(0, ApplicationDao.selectRoleOption());
		request.setAttribute("roles", roles);
        request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password =request.getParameter("password"); 
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String dobSrtring = request.getParameter("dob");
		String gender = request.getParameter("gender");
	    String agreementStr = request.getParameter("agreement");
	    String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobSrtring);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean agreement = "on".equals(agreementStr);
		
        int roleId = ApplicationDao.getRoleIdByName(role);		
        Map<String, Boolean> uniqueChecksMap = ApplicationDao.usernameAlreadyExists(username, email);
		int userSaved = 0;
		
		if(!uniqueChecksMap.containsKey("exception")) {
			if(uniqueChecksMap.get("userPresent")) {
				response.getWriter().write("usernameTaken");
			} else if(uniqueChecksMap.get("email")){
				System.out.println("email taken");
				response.getWriter().write("emailTaken");
			} else {
				
				Encoder encoder = Base64.getEncoder();
		        String encodedpassword = encoder.encodeToString(password.getBytes());		 
		        System.out.println("Encrypted Value :: " +encodedpassword);
				
				User user = new User(firstName, lastName, username, encodedpassword, dob, gender, agreement, email, roleId);
				
				String registerMessage = null;
							
				try {
					userSaved = ApplicationDao.registerUser(user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if (userSaved == 1) {
					response.getWriter().write("success");

				} else {
					response.getWriter().write("DB error");
				}
			}				
		}
		else {
			response.getWriter().write("Exception occurred while username and email validation.");
		}
	}
	
}
