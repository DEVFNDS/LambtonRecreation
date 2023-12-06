package lambtonrecreation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lambtonrecreation.model.Coach;
import lambtonrecreation.dao.UserEventDao;
import lambtonrecreation.util.DBConnection;

public class CoachDao {

    public static int saveCoach(Coach coach, String username) {
        int status = 0;

        try {
            Connection connection = DBConnection.getConnection();
            System.out.println(":::  Connection Established Successfully :::");
            PreparedStatement ps = connection.prepareStatement(
                    "insert into coaches(user_id, sports_specialized_in, coaching_experience, certifications, availability) values(?,?,?,?,?)");
            System.out.println("::: Prepared Statement Created :::");
            int userId = UserEventDao.getUserIdFromUsername(username);

            ps.setInt(1, userId);
            ps.setString(2, coach.getSportsSpecializedIn());
            ps.setString(3, coach.getCoachingExperience());
            ps.setString(4, coach.getCertifications());
            ps.setString(5, coach.getAvailability());
            status = ps.executeUpdate();
            DBConnection.closeConnection(connection);
            System.out.println("::: Connection Closed Successfully :::");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int updateCoach(Coach coach, String username) {
        int status = 0;

        try {
            Connection connection = DBConnection.getConnection();
            System.out.println(":::  Connection Established Successfully :::");
            PreparedStatement ps = connection.prepareStatement(
                    "update coaches set sports_specialized_in=?, coaching_experience=?, certifications=?, availability=? where user_id=?");
            System.out.println("::: Prepared Statement Created :::");
            int userId = UserEventDao.getUserIdFromUsername(username);
            ps.setString(1, coach.getSportsSpecializedIn());
            ps.setString(2, coach.getCoachingExperience());
            ps.setString(3, coach.getCertifications());
            ps.setString(4, coach.getAvailability());
            ps.setInt(5, userId);
            status = ps.executeUpdate();
            DBConnection.closeConnection(connection);
            System.out.println("::: Connection Closed Successfully :::");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static Coach getCoachByUserId(String username) {
        Coach coach = new Coach();
        try {
            Connection connection = DBConnection.getConnection();
            System.out.println(":::  Connection Established Successfully :::");
            int userId = UserEventDao.getUserIdFromUsername(username);
            PreparedStatement ps = connection.prepareStatement("select * from coaches where user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                coach.setId(rs.getInt(1));
                coach.setUserId(userId);
                coach.setSportsSpecializedIn(rs.getString(3));
                coach.setCoachingExperience(rs.getString(4));
                coach.setCertifications(rs.getString(5));
                coach.setAvailability(rs.getString(6));
            }
            DBConnection.closeConnection(connection);
            System.out.println("::: Connection Closed Successfully :::");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coach;
    }
}
