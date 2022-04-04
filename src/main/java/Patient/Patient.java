package Patient;

import TheConnection.DBConnection;

import java.util.*;
import java.util.Date;

import javax.print.DocFlavor.READER;
import javax.swing.undo.StateEdit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//databse imports
import java.sql.*;

public class Patient {
    private static Connection conn = null;
    private static Scanner scanner;
    private static String firstName;
    private static String lastName;
    private static String middleName;
    private static String gender;
    private static int ssn;
    private static String dateOfBirth;
    private static Integer apartmentNumber;
    private static int streetNumber;
    private static String street;
    private static String city;
    private static String province;
    private static String postalCode;

    private static String email;
    private static String phonenumber;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int contactInformationId;
    private static int addressId;
    private static int userId;
    private static Statement statement;

    public Patient() {
        DBConnection connection = new DBConnection();
        conn = connection.Connection();
    }

    public static void getSomething() {
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM public.USER");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println(resultSet.getString("firstName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPatient(int id) {
        try {
            // Get contact information id
            preparedStatement = conn.prepareStatement("SELECT * FROM public.user WHERE user_id=" + id);
            resultSet = preparedStatement.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static HashMap<String, String> getPatientInfo(int userid) {
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.user, public.address, public.contactinformation WHERE user_id=" + userid
                            + "and public.user.address_id=public.address.address_id and public.user.contactinfo_id=public.contactinformation.contactinfo_id");
            resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put("firstname", resultSet.getString("firstname"));
                map.put("middlename", resultSet.getString("middlename"));
                map.put("lastname", resultSet.getString("lastname"));
                map.put("gender", resultSet.getString("gender"));
                map.put("datebirth", resultSet.getString("datebirth"));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
