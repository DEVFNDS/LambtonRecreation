package lambtonrecreation.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lambtonrecreation.dao.ApplicationDao;
import lambtonrecreation.model.Role;
import lambtonrecreation.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get of register");
		List<Role> roles = ApplicationDao.getRoles();
		roles.add(0, ApplicationDao.selectRoleOption());
		System.out.println("--nk roles : "+roles);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post of register");

		// take all the form data

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
		System.out.println("--nk 1 roleId : "+roleId);
		/*StringBuilder errorMsg = new StringBuilder();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || role.isEmpty()) {
            errorMsg.append("All fields are required. ");
        }

        if (!password.equals(confirmPassword)) {
            errorMsg.append("Passwords do not match. ");
        }
        
         if (errorMsg.length() > 0) {
            request.setAttribute("error", errorMsg.toString());
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }
        */
		
		User user = new User(firstName, lastName, username, password, dob, gender, agreement, email, roleId);
		
		String registerMessage = null;
		int userSaved = 0;
		
		try {
			userSaved = ApplicationDao.registerUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (userSaved == 1) {
			registerMessage = "User registered successfully!";
			System.out.println(registerMessage);
			response.getWriter().write("success");
			//response.sendRedirect("jsps/Login.jsp");

		} else {
			/*registerMessage = "Sorry! Some error occurred!";
			System.out.println(registerMessage);
			request.setAttribute("error", "Registration failed. Please try again.");*/
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
		}

		//response.getWriter().write(registerMessage);
		
		/*String page = getHTMLString(request.getServletContext().getRealPath("/jsps/Register.jsp"), registerMessage);
		response.getWriter().write(page);*/
	}
	
	public String getHTMLString(String filePath, String message) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		reader.close();
		String page = buffer.toString();
		page = MessageFormat.format(page, message);
		return page;
	}
}
