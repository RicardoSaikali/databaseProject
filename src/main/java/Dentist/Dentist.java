package Dentist;

import TheConnection.DBConnection;
import java.util.*;
import java.sql.*;

public class Dentist {
    private static Connection conn = null;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static ResultSet newSet;

    public Dentist() {
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
                preparedStatement = conn.prepareStatement("SELECT firstname, middlename, lastname FROM public.user,public.appointment, public.patient where public.patient.patient_id="+ 
                resultSet.getInt("patient_id")+ " and public.patient.patient_id=public.appointment.patient_id and public.user.user_id=public.patient.userid");
                newSet = preparedStatement.executeQuery();
                while (newSet.next()) { //get patient names
                    map.put("firstname", newSet.getString("firstname"));
                    map.put("middlename", newSet.getString("middlename"));
                    map.put("lastname", newSet.getString("lastname"));
                }
                
                map.put("date", resultSet.getString("date"));
                map.put("starttime", resultSet.getString("starttime"));
                map.put("endtime", resultSet.getString("endtime"));
                map.put("status", resultSet.getString("status"));
                map.put("roomassigned", resultSet.getString("roomassigned"));
                map.put("notes", resultSet.getString("notes"));
                map.put("Appointment_id", resultSet.getString("appointment_id"));
                map.put("Patient_id", resultSet.getString("patient_id"));
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

    public static ArrayList<HashMap<String,String>> getTreatmentsForAppointment(int Appointment_id){
        try {
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM public.treatment WHERE Appointment_id="+ Appointment_id);
            resultSet = preparedStatement.executeQuery();
            ArrayList<HashMap<String,String>> treatments = new ArrayList<HashMap<String,String>>();
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<String, String>();                
                map.put("Treatment_id", resultSet.getString("treatment_id"));
                map.put("Record_id", resultSet.getString("record_id"));
                map.put("Appointment_id", resultSet.getString("appointment_id"));
                map.put("Type_id", resultSet.getString("type_id"));
                map.put("Medication", resultSet.getString("medication"));
                map.put("Symptoms", resultSet.getString("symptoms"));
                map.put("Tooth_involved", resultSet.getString("toothinvolved"));
                map.put("Comments", resultSet.getString("comments"));
                treatments.add(map);
            }
            System.out.println(treatments.toString());

            return treatments; // returns an arraylist that has many hashmaps with appointment information
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<HashMap<String,String>> getAppointment_ProceduresForAppointment(int Appointment_id){
        try {
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM public.appointmentprocedure WHERE Appointment_id="+ Appointment_id);
            resultSet = preparedStatement.executeQuery();
            ArrayList<HashMap<String,String>> appointmentProcedures = new ArrayList<HashMap<String,String>>();
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<String, String>();                
                map.put("Procedure_code", resultSet.getString("procedurecode"));
                map.put("Appointment_id", resultSet.getString("appointment_id"));
                map.put("Description", resultSet.getString("description"));
                map.put("Tooth_involved", resultSet.getString("toothinvolved"));
                map.put("Amount_procedures", resultSet.getString("amountprocedures"));
                appointmentProcedures.add(map);
            }
            System.out.println(appointmentProcedures.toString());

            return appointmentProcedures; // returns an arraylist that has many hashmaps with appointment information
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
