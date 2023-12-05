package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import lambtonrecreation.model.Event;
import lambtonrecreation.model.Sport;
import lambtonrecreation.util.DBConnection;

public class EventDao {

	private static final SportDao sportDao = new SportDao(); 
	
	public static int saveEvent(Event event) {
		int status=0;
		
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement(
					"insert into events(name,sport_id,date_time,location,description,registration_deadline) values(?,?,?,?,?,?)");
			System.out.println("::: Prepared Statement Created :::");
			System.out.println(event.getSportId());
			ps.setString(1,event.getName());
			ps.setInt(2, event.getSportId());
			ps.setTimestamp(3, event.getDateTime());
			ps.setString(4, event.getLocation());
			ps.setString(5, event.getDescription());
			ps.setTimestamp(6, event.getRegistrationDeadline());
			
			
			status=ps.executeUpdate();
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public static List<Event> getAllEvents(){
		
		List<Event> eventList = new ArrayList<>();
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps= connection.prepareStatement("select * from events");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Event event =new Event();
				
				event.setId(rs.getInt(1));
				event.setName(rs.getString(2));
				int sportId = rs.getInt(3);
				event.setSportId(sportId);
				Sport sport = sportDao.getSportById(sportId);
				event.setSportName(sport.getName());
				event.setDateTime(rs.getTimestamp(4));
				event.setLocation(rs.getString(5));
				event.setDescription(rs.getString(6));
				event.setRegistrationDeadline(rs.getTimestamp(7));
				eventList.add(event);
				}
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return eventList;
	}
	
	
	public static Event getEventById(String id) {
		Event event = new Event();
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement("select * from events where id=?");
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				event.setId(Integer.valueOf(id));
				event.setName(rs.getString(2));
				int sportId = rs.getInt(3);
				event.setSportId(sportId);
				Sport sport = sportDao.getSportById(sportId);
				event.setSportName(sport.getName());
				event.setDateTime(rs.getTimestamp(4));
				event.setLocation(rs.getString(5));
				event.setDescription(rs.getString(6));
				event.setRegistrationDeadline(rs.getTimestamp(7));
			}
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return event;
	}
	
	
	public static int updateEvent(Event event) {
		int status = 0;
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
		
			String sql = "UPDATE events SET name=?, sport_id=?, date_time=?, location=?, description=?, registration_deadline=? WHERE id=?";
			
			System.out.println(sql);
			PreparedStatement preparedStatement  =connection.prepareStatement(sql);
			
		 	preparedStatement.setString(1, event.getName());
		    preparedStatement.setInt(2, event.getSportId());
		    preparedStatement.setTimestamp(3, event.getDateTime());
		    preparedStatement.setString(4, event.getLocation());
		    preparedStatement.setString(5, event.getDescription());
		    preparedStatement.setTimestamp(6, event.getRegistrationDeadline());
		    preparedStatement.setInt(7, event.getId());
			
			status= preparedStatement.executeUpdate();
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int delete(String event_id){
		int status=0;
		try{
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			String sql="delete from events where id='"+event_id+"'";
			System.out.println(sql);
			PreparedStatement ps=connection.prepareStatement(sql);
			status=ps.executeUpdate();
			
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public static int deleteUserEventMapping(String event_id){
		int status=0;
		try{
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			String sql="delete from user_events_rln where event_id='"+event_id+"'";
			System.out.println(sql);
			PreparedStatement ps=connection.prepareStatement(sql);
			status=ps.executeUpdate();
			
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
}
