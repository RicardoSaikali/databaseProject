package Dentist;

import TheConnection.DBConnection;
import java.util.*;
import java.sql.*;

public class Dentist {
    private static Connection conn = null;
    private static Scanner scanner;
    private static String email;
    private static String phonenumber;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int contactInformationId;

    public Dentist() {
        // String user = "mzjycxzivsmkni";
        // String pass =
        // "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
        // String LINK =
        // "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";

        // try {
        // conn = DriverManager.getConnection(LINK, user, pass);
        // if (conn != null) {
        // System.out.println("Connected to the database!");
        // } else {
        // System.out.println("Failed to make connection!");
        // }

        // } catch (SQLException e) {
        // System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        DBConnection connection = new DBConnection();
        conn = connection.Connection();
    }

    public boolean isDentist(int id) { // checks to see if ID belongs to a dentist
        try {
            // Get contact information id
            preparedStatement = conn
                    .prepareStatement("SELECT employee_role FROM public.employee WHERE employee_id=" + id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("employee_role").equals("Dentist")) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<HashMap<String,String>> upcomingAppointments(int employeeId) {//this has to be patientID not userID
        try {
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM public.appointment WHERE employee_id="+ employeeId);
            resultSet = preparedStatement.executeQuery();
            ArrayList<HashMap<String,String>> appointment = new ArrayList<HashMap<String,String>>();
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("date", resultSet.getString("date"));
                map.put("starttime", resultSet.getString("starttime"));
                map.put("endtime", resultSet.getString("endtime"));
                map.put("status", resultSet.getString("status"));
                map.put("roomassigned", resultSet.getString("roomassigned"));
                map.put("notes", resultSet.getString("notes"));
                appointment.add(map);
            }
            System.out.println(appointment.toString());

            return appointment; // returns an arraylist that has many hashmaps with appointment information
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //call Patient.getMedicalHistory with patientId to get medical history 


}
