package lambtonrecreation.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lambtonrecreation.model.Athlete;
import lambtonrecreation.dao.UserEventDao;

import lambtonrecreation.util.DBConnection;

public class AthleteDao {
	
	public static int saveAthlete (Athlete athlete, String username) {
		int status=0;
		
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement(
					"insert into athletes(user_id,bio,interests,achievements) values(?,?,?,?)");
			System.out.println("::: Prepared Statement Created :::");
			int user_id = UserEventDao.getUserIdFromUsername(username);
			
			ps.setInt(1,user_id);
			ps.setString(2, athlete.getBio());
			ps.setString(3, athlete.getInterests());
			ps.setString(4, athlete.getAchievements());
			status=ps.executeUpdate();
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int updateAthlete (Athlete athlete, String username) {
		int status=0;
		
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			PreparedStatement ps=connection.prepareStatement(
					"update athletes set bio=? ,interests=? ,achievements=? where user_id=?");
			System.out.println("::: Prepared Statement Created :::");
			int user_id = UserEventDao.getUserIdFromUsername(username);
			ps.setString(1, athlete.getBio());
			ps.setString(2, athlete.getInterests());
			ps.setString(3, athlete.getAchievements());
			ps.setInt(4,user_id);
			status=ps.executeUpdate();
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public static Athlete getAthleteByUserId (String username) {
		Athlete athlete = new Athlete();
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(":::  Connection Established Successfully :::");
			int user_id = UserEventDao.getUserIdFromUsername(username);
			PreparedStatement ps=connection.prepareStatement("select * from athletes where user_id=?");
			ps.setInt(1,user_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				athlete.setId(rs.getInt(1));
				athlete.setUserId(Integer.valueOf(user_id));
				athlete.setBio(rs.getString(3));
				athlete.setInterests(rs.getString(4));
				athlete.setAchievements(rs.getString(5));
			}
			DBConnection.closeConnection(connection);
			System.out.println("::: Connection Closed Sucessfully :::");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return athlete;
	}
}
