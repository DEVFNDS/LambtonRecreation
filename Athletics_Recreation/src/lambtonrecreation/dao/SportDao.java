package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lambtonrecreation.model.Sport;
import lambtonrecreation.util.DBConnection;

/**
 * @author Jagraj-kaur
 *
 */
public class SportDao {
	
	private static final String INSERT_SQL = "INSERT INTO sports (name, description, rules, equipment_needed) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL_SQL = "SELECT * FROM sports";
	private static final String SELECT_BY_ID_SQL = "SELECT * FROM sports WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE sports SET name = ?, description = ?, rules = ?, equipment_needed = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM sports WHERE id = ?";

	//Instance variable for the connection
	private final Connection connection;
	PreparedStatement  preparedStatement = null;
	
	public SportDao() {
		try {
			this.connection = DBConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
	        throw new RuntimeException("Failed to establish a database connection.", e);
		}
	}
	
	public Sport createSport(Sport sport) throws SQLException {
		try {
			preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, sport.getName());
			preparedStatement.setString(2, sport.getDescription());
			preparedStatement.setString(3, sport.getRules());
			preparedStatement.setString(4, sport.getEquipmentNeeded());
			
			int affectedRows = preparedStatement.executeUpdate();
			
			if(affectedRows > 0) {
				try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if(generatedKeys.next()) {
						sport.setId(generatedKeys.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
		return sport;
	}
	
	public  List<Sport> getAllSports() {
        List<Sport> sports = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
                Sport sport = new Sport();
                sport.setId(resultSet.getInt("id"));
                sport.setName(resultSet.getString("name"));
                sport.setDescription(resultSet.getString("description"));
                sport.setRules(resultSet.getString("rules"));
                sport.setEquipmentNeeded(resultSet.getString("equipment_needed"));
                sports.add(sport);
            }
					
		}  catch (SQLException e) {
            e.printStackTrace();
        }
		return sports;
	}
	
	public Sport getSportById(int id) {
		try {
			preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return mapResultSetToSport(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateSport(Sport sport) throws SQLException{
		try {
			preparedStatement = connection.prepareStatement(UPDATE_SQL);
			setSportParameters(preparedStatement, sport);
			preparedStatement.setInt(5, sport.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSport(int id) throws SQLException{
        try {
        	preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	private Sport mapResultSetToSport(ResultSet resultSet) throws SQLException {
		Sport sport = new Sport();
		sport.setId(resultSet.getInt("id"));
	    sport.setName(resultSet.getString("name"));
	    sport.setDescription(resultSet.getString("description"));
	    sport.setRules(resultSet.getString("rules"));
	    sport.setEquipmentNeeded(resultSet.getString("equipment_needed"));
	    return sport;
	}
	
	private void setSportParameters(PreparedStatement preparedStatement, Sport sport) throws SQLException {
        preparedStatement.setString(1, sport.getName());
        preparedStatement.setString(2, sport.getDescription());
        preparedStatement.setString(3, sport.getRules());
        preparedStatement.setString(4, sport.getEquipmentNeeded());
    }

}
