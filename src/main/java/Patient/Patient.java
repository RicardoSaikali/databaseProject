package Patient;

import TheConnection.DBConnection;

import java.util.*;
import java.util.Date;

import javax.print.DocFlavor.READER;
import javax.print.attribute.HashDocAttributeSet;
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
    private static int patientId;
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
            patientId=id;
            preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM public.patient WHERE patient_id=" + id);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString("count"));
            }
            if (count > 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<String> getBranchs() {
        try {
            // Get contact information id
            preparedStatement = conn.prepareStatement("SELECT branch_id FROM public.branch");
            resultSet = preparedStatement.executeQuery();
            ArrayList<String> arr = new ArrayList<String>();
            while (resultSet.next()) {
               arr.add(resultSet.getString("branch_id"));
            }
            return arr;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void submitReview(HashMap<String,String> map){
        try {
            
            preparedStatement = conn.prepareStatement("SELECT max(review_id) FROM public.review");
            resultSet = preparedStatement.executeQuery();
            int reviewId=0;
            while (resultSet.next())
                reviewId = resultSet.getInt("max");
            reviewId++;
            preparedStatement = conn.prepareStatement("INSERT INTO public.review values (" + reviewId+", "+map.get("professionalism")+", "+ map.get("communication")+", "+map.get("cleanliness")+", "+ map.get("value")+", '"+ map.get("date")+ ", "+patientId+", "+ map.get("branch")+")");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, String> getPatientInfo(int patientId) {
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.user, public.address, public.contactinformation, public.patient WHERE patient_id="
                            + patientId
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

    public static ArrayList<HashMap<String, String>> getMedicalHistory(int patientId) {// this has to be patientID not
                                                                                       // userID
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT type, symptoms, medication, comments, description, public.appointmentprocedure.toothinvolved, date FROM public.treatmenttype, public.treatment, public.appointmentprocedure, public.appointment WHERE patient_id="
                            + patientId +
                            " and public.treatmenttype.treatmenttype_id=public.treatment.type_id and public.appointmentprocedure.appointment_id=public.treatment.appointment_id and public.appointment.appointment_id=public.appointmentprocedure.appointment_id;");
            resultSet = preparedStatement.executeQuery();
            ArrayList<HashMap<String, String>> medicalHistory = new ArrayList<HashMap<String, String>>();
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("type", resultSet.getString("type"));
                map.put("symptoms", resultSet.getString("symptoms"));
                map.put("tooth", resultSet.getString("toothinvolved"));
                map.put("description", resultSet.getString("description"));
                map.put("comments", resultSet.getString("comments"));
                map.put("date", resultSet.getString("date"));
                medicalHistory.add(map);
            }
            return medicalHistory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<HashMap<String, String>> upcomingAppointments(int patientId) {// this has to be patientID
                                                                                          // not userID
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT * FROM public.appointment WHERE patient_id=" + patientId);
            resultSet = preparedStatement.executeQuery();
            ArrayList<HashMap<String, String>> appointment = new ArrayList<HashMap<String, String>>();
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

    public static int getPatientIDWithAppointmentID(int appointment_id) {
        try {
            preparedStatement = conn.prepareStatement(
                    "SELECT patient_id FROM public.appointment WHERE appointment_id=" + appointment_id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("patient_id");
        } catch (
        SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
