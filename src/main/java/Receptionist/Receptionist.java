package Receptionist;

import TheConnection.DBConnection;
import java.util.*;

//databse imports
import java.sql.*;

public class Receptionist {
    private static Connection conn = null;
    private static Scanner scanner;
    private static String firstName;
    private static String lastName;
    private static String middleName;
    private static String gender;
    private static int ssn;
    private static int recordId;
    private static int procedurecode;
    private static int appointmenttypeId;
    private static int treatmentId;
    private static int treatmenttypeId;
    private static int proceduretype;
    private static String dateOfBirth;
    private static String street;
    private static String city;
    private static String province;
    private static String postalCode;
    private static String insuranceNumber;

    private static HashMap<String, String> patientInfo;
    private static String email;
    private static String phonenumber;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int contactInformationId;
    private static int addressId;
    private static int userId;
    private static int appointmentId;
    private static int patientId;
    private static int dentistId;
    private static Statement statement;
    private static String[] rooms = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    public Receptionist() {
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

    // TODO Add constraints on all inputs and change scanner to using UI
    public void helper(HashMap<String, String> map) {
        patientInfo = map;

        firstName = patientInfo.get("firstname");
        middleName = patientInfo.get("middlename");
        lastName = patientInfo.get("lastname");
        email = patientInfo.get("email");
        phonenumber = patientInfo.get("phonenumber");
        gender = patientInfo.get("gender");
        ssn = Integer.parseInt(patientInfo.get("ssn"));
        dateOfBirth = patientInfo.get("datebirth");
        street = patientInfo.get("street");
        city = patientInfo.get("city");
        province = patientInfo.get("province");
        postalCode = patientInfo.get("postalCode");
        insuranceNumber = patientInfo.get("insurancenumber");

        insertUserInformation();

        // scanner = new Scanner(System.in);
        // System.out.println("Please enter the patients First Name:");
        // firstName = scanner.nextLine();
        // System.out.println("Please enter the patients Middle Name:");
        // middleName = scanner.nextLine();
        // System.out.println("Please enter the patients Last Name:");
        // lastName = scanner.nextLine();
        // System.out.println("Please enter the patients Email:");
        // email = scanner.nextLine();
        // System.out.println("Please enter the patients Phone Number:");
        // phonenumber = scanner.nextLine();
        // System.out.println("Please enter the patients Gender:");
        // gender = scanner.nextLine();
        // System.out.println("Please enter the patients SSN:");
        // ssn = Integer.parseInt(scanner.nextLine());
        // System.out.println("Please enter the patients Date of Birth (yyyy-mm-dd):");
        // dateOfBirth = scanner.nextLine();
        // System.out.println("Please enter the patients Full Address:");
        // street = scanner.nextLine();
        // System.out.println("Please enter the patients City:");
        // city = scanner.nextLine();
        // System.out.println("Please enter the patients Province:");
        // province = scanner.nextLine();
        // System.out.println("Please enter the patients Postal Code:");
        // postalCode = scanner.nextLine();
        // System.out.println("Please enter the patients Insurance Number:");
        // insuranceNumber = scanner.nextLine();
        // if (flag) insertUserInformation();
    }

    public boolean isReceptionist(int id) {
        try {
            // Get contact information id
            preparedStatement = conn
                    .prepareStatement("SELECT employee_role FROM public.employee WHERE employee_id=" + id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("employee_role").equals("Receptionist")) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Insert user information into ContactInfo, Address and User Tables
    public void insertUserInformation() {
        try {
            // Get contact information id
            preparedStatement = conn.prepareStatement("SELECT max(contactinfo_id) FROM public.contactinformation");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                contactInformationId = resultSet.getInt("max");
            contactInformationId++;
            // Get address id
            preparedStatement = conn.prepareStatement("SELECT max(address_id) FROM public.address");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                addressId = resultSet.getInt("max");
            addressId++;
            // Get User id
            preparedStatement = conn.prepareStatement("SELECT max(user_id) FROM public.user");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                userId = resultSet.getInt("max");
            userId++;

            preparedStatement = conn.prepareStatement("SELECT max(patient_id) FROM public.patient");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                patientId = resultSet.getInt("max");
            patientId++;

            String contactInfo = contactInformationId + ", '" + email + "', '" + phonenumber + "'";
            String addressInfo = addressId + ", '" + street + "', '" + city + "', '" + province + "', '" + postalCode
                    + "'";
            String userInfo = "'" + firstName + "', '" + middleName + "', '" + lastName + "', '" + gender + "', " + ssn
                    + ", '" + dateOfBirth + "'," + contactInformationId + "," + userId + "," + addressId;
            String sql1 = "INSERT INTO public.contactinformation values (" + contactInfo + ")";
            String sql2 = "INSERT INTO public.address values (" + addressInfo + ")";
            String sql3 = "INSERT INTO public.user values (" + userInfo + ")";
            String sql4 = "INSERT INTO public.Patient values (" + patientId + ", " + userId + ", '" + insuranceNumber
                    + "')";
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.addBatch(sql4);
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUserInformation(HashMap<String,String> map) {
        try {
            // Get contact information id
                preparedStatement = conn.prepareStatement("SELECT contactinfo_id FROM public.user, public.patient WHERE patient_id=" + Integer.parseInt(map.get("patientid"))+ " and public.patient.userid=public.user.user_id");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    contactInformationId= Integer.parseInt(resultSet.getString("contactinfo_id"));
                }

                preparedStatement = conn.prepareStatement("SELECT address_id FROM public.user, public.patient WHERE patient_id=" + Integer.parseInt(map.get("patientid"))+ " and public.patient.userid=public.user.user_id");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addressId= Integer.parseInt(resultSet.getString("address_id"));
                }

                preparedStatement = conn.prepareStatement("SELECT user_id FROM public.user, public.patient WHERE patient_id=" + Integer.parseInt(map.get("patientid"))+ " and public.patient.userid=public.user.user_id");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    userId= Integer.parseInt(resultSet.getString("user_id"));
                }


                String sql1 = "UPDATE public.contactInformation SET email= '" + map.get("email")+ "', phonenumber='" +map.get("phonenumber") + "' where contactinfo_id= " + contactInformationId;
                String sql2 = "UPDATE public.address SET streetaddress= '" + map.get("street")+ "', city= '" +map.get("city") + "', province= '" +map.get("province") + "', postalcode= '" +map.get("postalcode") +"' where address_id= " + addressId;
                String sql3 = "UPDATE public.user SET firstname= '" + map.get("firstname")+ "', middlename= '" +map.get("middlename") + "', lastname= '" +map.get("lastname") + "', gender= '" +map.get("gender") + "', ssn= " +map.get("ssn") + ", datebirth= '" +map.get("datebirth") +"' where user_id= " + userId;
                String sql4 = "UPDATE public.patient SET insurancenumber= '" + map.get("insurancenumber")+"' where userid= " + userId;

                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                statement.addBatch(sql1);
                statement.addBatch(sql2);
                statement.addBatch(sql3);
                statement.addBatch(sql4);

                statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<String> checkAvailableRoom(String date, String startime){
      try{

        preparedStatement = conn.prepareStatement("SELECT roomassigned FROM public.appointment WHERE date='" + date
                + "' and starttime='" + startime + "'");
        resultSet = preparedStatement.executeQuery();
        ArrayList<String> roomsTaken = new ArrayList<String>();
        ArrayList<String> roomAvail = new ArrayList<String>();
        while (resultSet.next()) {
            roomsTaken.add(resultSet.getString("roomassigned"));
        }
        for (int i = 0; i < rooms.length; i++) {
            if (!roomsTaken.contains(rooms[i])) {
                //System.out.println(rooms[i]);
                roomAvail.add(rooms[i]);
            }
        }
        return roomAvail;
      } catch (Exception e){
        e.printStackTrace();
        return null;
      }
    }
    public ArrayList<Integer> getAvailableDentists(String date, String startime, int branch){
      try {
        preparedStatement = conn.prepareStatement("SELECT employee_id FROM public.appointment WHERE date='" + date
                + "' and starttime='" + startime + "'");
        resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> busyDentists = new ArrayList<Integer>();
        ArrayList<Integer> availDentists = new ArrayList<Integer>();
        while (resultSet.next()) {
            busyDentists.add(resultSet.getInt("employee_id"));
        }
        // get all dentists
        preparedStatement = conn.prepareStatement(
                "SELECT employee_id FROM public.employee WHERE employee_role = 'Dentist' and branch_id=" + branch);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (!busyDentists.contains(resultSet.getInt("employee_id")))
                //System.out.println(resultSet.getInt("employee_id"));
                availDentists.add(resultSet.getInt("employee_id"));
        }
        return availDentists;
        } catch (Exception e){
          return null;
        }
    }

    public int getBranch(int employee_id){
        try{
            preparedStatement = conn.prepareStatement(
                    "SELECT branch_id FROM public.employee WHERE employee_id="+employee_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("branch_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getTreatmentTypes(){
        try{
            ArrayList<String> arr = new ArrayList<String>();
            preparedStatement = conn.prepareStatement(
                    "SELECT type FROM public.treatmenttype");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arr.add(resultSet.getString("type"));
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setAppointment() {
        try {
            // Get contact information id
            scanner = new Scanner(System.in);
            System.out.println("Please enter the Branch ID: (0-2)");
            int branch = Integer.parseInt(scanner.nextLine());

            ArrayList<Integer> patients = new ArrayList<Integer>();
            preparedStatement = conn.prepareStatement("SELECT patient_id FROM public.appointment");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                patients.add(resultSet.getInt("patient_id"));

            }
            boolean flag = true;
            while (flag) {
                System.out.println("Please enter the Patient ID:");
                patientId = Integer.parseInt(scanner.nextLine());

                if (patients.contains(patientId)) {
                    System.out.println("This patient already has an appointment");
                    System.out.println("patientId: " + patientId);
                } else {
                    System.out.println("patientId: " + patientId);
                    flag = false;
                }
            }

            System.out.println("Please enter the date (yyyy-mm-dd)");
            String date = scanner.nextLine();
            System.out.println("Please enter start time (in military time):");
            String startime = scanner.nextLine();
            System.out.println("Please enter end time (in military time):");
            String endtime = scanner.nextLine();

            preparedStatement = conn.prepareStatement("SELECT roomassigned FROM public.appointment WHERE date='" + date
                    + "' and starttime='" + startime + "'");
            resultSet = preparedStatement.executeQuery();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------");
            ArrayList<String> roomsTaken = new ArrayList<String>();
            while (resultSet.next()) {
                roomsTaken.add(resultSet.getString("roomassigned"));
            }
            System.out.println("The following rooms are available at that time:");
            for (int i = 0; i < rooms.length; i++) {
                if (!roomsTaken.contains(rooms[i])) {
                    System.out.println(rooms[i]);
                }
            }
            flag = true;
            String room = "";
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------");
            while (flag) {
                System.out.println("Please enter which available room you would like to book:");
                room = scanner.nextLine();
                if (Arrays.asList(rooms).contains(room) && !roomsTaken.contains(room))
                    flag = false;
                else {
                    System.out.println("Invalid Entry");
                }
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------");
            System.out.println("Are there any notes you like to add:");
            String notes = scanner.nextLine();
            flag = true;
            // get all busy dentists
            preparedStatement = conn.prepareStatement("SELECT employee_id FROM public.appointment WHERE date='" + date
                    + "' and starttime='" + startime + "'");
            resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> busyDentists = new ArrayList<Integer>();
            while (resultSet.next()) {
                busyDentists.add(resultSet.getInt("employee_id"));
            }
            // get all dentists
            preparedStatement = conn.prepareStatement(
                    "SELECT employee_id FROM public.employee WHERE employee_role = 'Dentist' and branch_id=" + branch);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> dentists = new ArrayList<Integer>();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------");
            System.out.println("The following Dentists are available:");
            while (resultSet.next()) {
                dentists.add(resultSet.getInt("employee_id"));
                if (!busyDentists.contains(resultSet.getInt("employee_id")))
                    System.out.println(resultSet.getInt("employee_id"));
            }

            while (flag) {
                System.out.println("Please enter the employee_id of the Dentist");
                dentistId = Integer.parseInt(scanner.nextLine());

                if (!dentists.contains(dentistId)) {
                    System.out.println("That is not a valid employee_id");
                } else if (busyDentists.contains(dentistId)) {
                    System.out.println("This Dentist is busy at that time");
                } else if (!busyDentists.contains(dentistId) && dentists.contains(dentistId)) {
                    flag = false;
                }
            }
            preparedStatement = conn.prepareStatement("SELECT max(appointment_id) FROM public.appointment");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                appointmentId = resultSet.getInt("max");
            appointmentId++;
            // preparedStatement = conn.prepareStatement("UPDATE "+ table + "SET " + field
            // +" = " +value +" WHERE SSN = "+ssn );
            System.out.println("1-PatientId= " + patientId);
            String appointmentInfo = appointmentId + ", '" + date + "', '" + startime + "', '" + endtime + "'," + null
                    + ",'" + room + "', '" + notes + "', " + patientId + ", " + dentistId;
            String sql1 = "INSERT INTO public.appointment values (" + appointmentInfo + ")";
            String[] arr = setProcedure();
            String sql2 = arr[0];
            String sql3 = arr[1];
            String sql4 = arr[2];
            String sql5 = arr[3];
            String sql6 = arr[4];
            String sql7 = arr[5];
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.addBatch(sql4);
            statement.addBatch(sql5);
            statement.addBatch(sql6);
            statement.addBatch(sql7);

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String[] setProcedure() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Please enter the Appointment type: (ex: cleaning, braces, etc.)");
            String appointmentType = scanner.nextLine();
            System.out.println("Please enter the procedure type: (ex: scaling, fluoride, removal, etc.)");
            String type = scanner.nextLine();
            System.out.println("Please enter the Description of what the procedure is for:");
            String description = scanner.nextLine();
            System.out.println("Please enter any notes the Dentist left:");
            String notes = scanner.nextLine();
            System.out.println("Please enter the toothinvolved: (ex: E45)");
            String tooth = scanner.nextLine();
            System.out.println("Please enter the number of procedures to be done:");
            int amount = Integer.parseInt(scanner.nextLine());//this will need to be changed to add x amount of procedures to databse, rn it only adds 1
            System.out.println("Is the patient on any medication? If yes type the medication below:");
            String medication = scanner.nextLine();
            System.out.println("What are the patients symptomps: (seperate them by commas)");
            String symptoms = scanner.nextLine();
            preparedStatement = conn.prepareStatement("SELECT max(proceduretype_id) FROM public.proceduretype");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                proceduretype = resultSet.getInt("max");
            proceduretype++;

            preparedStatement = conn.prepareStatement("SELECT max(procedurecode) FROM public.appointmentprocedure");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                procedurecode = resultSet.getInt("max");
            procedurecode++;

            preparedStatement = conn.prepareStatement("SELECT max(record_id) FROM public.record");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                recordId = resultSet.getInt("max");
            recordId++;

            preparedStatement = conn.prepareStatement("SELECT max(type_id) FROM public.appointmenttype");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                appointmenttypeId = resultSet.getInt("max");
            appointmenttypeId++;

            preparedStatement = conn.prepareStatement("SELECT max(treatment_id) FROM public.treatment");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                treatmentId = resultSet.getInt("max");
            treatmentId++;

            preparedStatement = conn.prepareStatement("SELECT max(treatmenttype_id) FROM public.treatmenttype");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                treatmenttypeId = resultSet.getInt("max");
            treatmenttypeId++;

            String procedureInfo = procedurecode + ", '" + description + "', '" + tooth + "', " + amount + ","
                    + appointmentId;
            String sql1 = "INSERT INTO public.appointmentprocedure values (" + procedureInfo + ")";
            String sql2 = "INSERT INTO public.proceduretype values (" + proceduretype + ", '" + type + "', "
                    + procedurecode + ")";
            System.out.println("PatientID: " + patientId);
            String sql3 = "INSERT INTO public.record values (" + recordId + ", '" + description + "', " + dentistId
                    + ", " + patientId + ")";
            String sql4 = "INSERT INTO public.appointmenttype values (" + appointmenttypeId + ", '" + appointmentType
                    + "', " + procedurecode + ")";
            String sql5 = "INSERT INTO public.treatment values (" + treatmentId + ", '" + medication + "', '" + symptoms
                    + "', '" + tooth + "', '" + notes + "', " + recordId + ", " + appointmentId + ", "
                    + appointmenttypeId + ")";
            String sql6 = "INSERT INTO public.treatmenttype values (" + treatmenttypeId + ", '" + appointmentType
                    + "')";

            String[] arr = new String[] { sql1, sql2, sql3, sql4, sql5, sql6 };

            return arr;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
