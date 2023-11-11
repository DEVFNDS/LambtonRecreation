<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="lambtonrecreation.util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>MySQL Connection Example</title>
</head>
<body>
    <%
    	Connection connection = null;
        try {
            
            connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            // Display the retrieved data
            while (resultSet.next()) {
                out.println("first Name: " + resultSet.getString("first_name") + "<br>");
                out.println("Last Name: " + resultSet.getString("last_name") + "<br>");
                out.println("student ID: " + resultSet.getString("student_id") + "<br><br>");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</body>
</html>