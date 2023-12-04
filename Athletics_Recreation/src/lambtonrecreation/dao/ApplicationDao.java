package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lambtonrecreation.util.DBConnection;
import lambtonrecreation.model.Role;
import lambtonrecreation.model.User;

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
	
	public static Map<String, Object> validateUser(String username, String password) {
		Map<String, Object> mapOfUserValAndRole = new HashMap<String, Object>();
		mapOfUserValAndRole.put("userExists", -5);
		
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement;
		
			String sqlQuery = "SELECT u.username, u.password, r.role " +
                    "FROM user u " +
                    "INNER JOIN user_role r ON u.role = r.id " +
                    "WHERE u.username = ?";
		
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				System.out.println("Inside if DAo");
				if(resultSet.getString("password").equals(password)) {
	                mapOfUserValAndRole.put("userExists", 1);
	                mapOfUserValAndRole.put("roleName", resultSet.getString("role"));
	                System.out.println("Everything fine Dao");
	             }else {
	                mapOfUserValAndRole.put("userExists", 0);
	                System.out.println("Wrong password DAo");
	              }
	               	
			}else {
				System.out.println("Inside else DAo");
				mapOfUserValAndRole.put("userExists", -1);
            	System.out.println("Wrong username DAo");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Dao mapOfUserValAndRole: "+mapOfUserValAndRole);
		return mapOfUserValAndRole;

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
