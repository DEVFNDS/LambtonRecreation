package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lambtonrecreation.util.DBConnection;
import lambtonrecreation.beans.Role;
import lambtonrecreation.beans.User;

public class ApplicationDao {
	
	public static int registerUser(User user) throws SQLException {
		int rowsAffected = 0;
		String insertUserQuery = "insert into user(first_name, last_name, username, password, dob, role, gender, email, agreement) values(?,?,?,?,?,?,?,?,?)";
		try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			java.sql.Date sqlDob = new java.sql.Date(user.getDob().getTime());
			statement.setDate(5, sqlDob);
			System.out.println("--nk 2 roleId : "+user.getRole());
			statement.setInt(6, user.getRole());
			statement.setString(7, user.getGender());
			statement.setString(8, user.getEmail());
			statement.setBoolean(9, user.getAgreement());
			
			System.out.println(insertUserQuery);

			rowsAffected = statement.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	public static int validateUser(String username, String password) {
		int userExists = -5;
		try {
			Connection connection = DBConnection.getConnection();

			String sqlQuery = "select * from user where username=? or password=?";
			PreparedStatement statement;
		
		
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
                if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)) {
                	userExists = 1;
                }else if(!resultSet.getString("username").equals(username)) {
                	userExists = -1;
                }else if(!resultSet.getString("password").equals(password)) {
                	userExists = 0;
                }else if(!resultSet.getString("username").equals(username) && !resultSet.getString("password").equals(password)) {
                	userExists = -2;
                }
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userExists;

	}
    
	/*---------Role methods----------------------*/
	
	public static Role selectRoleOption() {
		Role dummyRole = new Role (0, "--Select Role--", "dummy role not in database");
		return dummyRole;
	}
	
	public static List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM user_role";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Role role = new Role();
                role.setRole(resultSet.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }
    
	
	public static int getRoleIdByName(String roleName) {
        String sql = "SELECT id FROM user_role WHERE role = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, roleName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return an invalid roleId if not found
    }
	

}
