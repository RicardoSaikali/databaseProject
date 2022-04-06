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
            preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM public.patient WHERE patient_id=" + id);
            resultSet = preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next()) {
                 count = Integer.parseInt(resultSet.getString("count"));
            }
            if(count>0) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static HashMap<String, String> getPatientInfo(int patientId) {
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.user, public.address, public.contactinformation, public.patient WHERE patient_id=" + patientId
                            + " and public.user.address_id=public.address.address_id and public.user.contactinfo_id=public.contactinformation.contactinfo_id and public.user.user_id=public.patient.userid");
            resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put("firstname", resultSet.getString("firstname"));
                map.put("middlename", resultSet.getString("middlename"));
                map.put("lastname", resultSet.getString("lastname"));
                map.put("datebirth", resultSet.getString("datebirth"));
                map.put("gender", resultSet.getString("gender"));
                map.put("ssn", resultSet.getString("ssn"));
                map.put("streetaddress", resultSet.getString("streetaddress"));
                map.put("city", resultSet.getString("city"));
                map.put("province", resultSet.getString("province"));
                map.put("postalcode", resultSet.getString("postalcode"));
                map.put("email", resultSet.getString("email"));
                map.put("phonenumber", resultSet.getString("phonenumber"));                
                map.put("insurancenumber", resultSet.getString("insurancenumber"));                
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Integer> getListAppointments(int userid) {
        // TODO check if this actually works
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.Appointment WHERE user_id=" + userid
                            + " order by Date, Start_time");
            resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> result = new ArrayList<Integer>();
            while (resultSet.next()) {
                result.add(Integer.valueOf(resultSet.getInt("Appointment_id")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> getAppointmentInfo(int appointment_id) {
        // TODO check if this actually works
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.Appointment WHERE Appointment_id=" + appointment_id);
            resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put("Patient_id", resultSet.getString("Date"));
                map.put("Employee_id", resultSet.getString("Date"));
                map.put("Date", resultSet.getString("Date"));
                map.put("Start_time", resultSet.getString("Start_time"));
                map.put("End_time", resultSet.getString("End_time"));
                map.put("Status", resultSet.getString("Status"));
                map.put("Room_assigned", resultSet.getString("Room_assigned"));
                map.put("Notes", resultSet.getString("Notes"));
                map.put("Invoice_id", resultSet.getString("Invoice_id"));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> getMedicalHistory(int patientId) {//this has to be patientID not userID
        try {
            preparedStatement = conn.prepareStatement(
                "SELECT type, symptoms, public.appointmentprocedure.toothinvolved, description FROM public.treatmenttype, public.treatment, public.appointmentprocedure, public.appointment WHERE patient_id="+ patientId+ 
                " and public.treatmenttype.treatment_id=public.treatment.treatment_id and public.appointmentprocedure.appointment_id=public.treatment.appointment_id and public.appointment.appointment_id=public.appointmentprocedure.appointment_id;");
            resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put("type", resultSet.getString("type"));
                map.put("symptoms", resultSet.getString("symptoms"));
                map.put("tooth", resultSet.getString("toothinvolved"));
                map.put("description", resultSet.getString("description"));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
