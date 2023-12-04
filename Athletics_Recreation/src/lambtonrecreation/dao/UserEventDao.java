package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lambtonrecreation.util.DBConnection;
import lambtonrecreation.model.Event;
import lambtonrecreation.dao.EventDao;

public class UserEventDao {

	public static int userEventSave(String username,int event_id) {
		int status=0;
		
		try {
			int user_id = getUserIdFromUsername(username);
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement(
					"insert into user_events_rln(user_id,event_id) values(?,?)");
			System.out.println("::: Prepared Statement Created :::");
			
			ps.setInt(1, user_id);
			ps.setInt(2, event_id);
			
			status=ps.executeUpdate();
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int getUserIdFromUsername(String username) {
		int value = -1;
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement("select * from user where username=?");
			ps.setString(1,username);
		
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				value = rs.getInt(1);
			}
			
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
			
			return value;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	

	public static List<Event> getUserEvents(int user_id){
		
		List<Event> eventList = new ArrayList<>();
		try {
				Connection connection = DBConnection.getConnection();
				System.out.println(":::  Connection Established Successfully :::");
				PreparedStatement ps=connection.prepareStatement("select * from user_events_rln where user_id=?");
				ps.setInt(1,user_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Event event = EventDao.getEventById(String.valueOf(rs.getInt(1)));
					eventList.add(event);
				}
				DBConnection.closeConnection(connection);
				System.out.println("::: Connection Closed Sucessfully :::");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return eventList;
	}
	
}
