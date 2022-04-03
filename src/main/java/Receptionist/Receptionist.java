package Receptionist;
import java.util.*;
import java.util.Date;

import javax.swing.undo.StateEdit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.ObjectInputFilter.Status;
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
    private static String dateOfBirth;
    private static Integer apartmentNumber;
    private static int streetNumber;
    private static String street;
    private static String city;
    private static String province;
    private static String postalCode;
    private static String insuranceNumber;

    
    private static String email;
    private static String phonenumber;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int contactInformationId;
    private static int addressId;
    private static int userId;
    private static int patientId;
    private static Statement statement;

    
    
    public static void getSomething() {
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM public.USER");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println(resultSet.getString("firstname"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO Add constraints on all inputs and change scanner to using UI
    public void helper(Connection conn, boolean flag){
        scanner = new Scanner(System.in);
        System.out.println("Please enter the patients First Name:");
        firstName = scanner.nextLine();
        System.out.println("Please enter the patients Middle Name:");
        middleName = scanner.nextLine();
        System.out.println("Please enter the patients Last Name:");
        lastName = scanner.nextLine();
        System.out.println("Please enter the patients Email:");
        email = scanner.nextLine();
        System.out.println("Please enter the patients Phone Number:");
        phonenumber = scanner.nextLine();
        System.out.println("Please enter the patients Gender:");
        gender = scanner.nextLine();
        System.out.println("Please enter the patients SSN:");
        ssn = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the patients Date of Birth (yyyy-mm-dd):");
        dateOfBirth = scanner.nextLine();
        System.out.println("Please enter the patients Apartment Number:");
        try{
            apartmentNumber= (Integer) Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException ex){
            apartmentNumber=null;
        }
        System.out.println("Please enter the patients Street Number:");
        streetNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the patients Street Address:");
        street = scanner.nextLine();
        System.out.println("Please enter the patients City:");
        city = scanner.nextLine();
        System.out.println("Please enter the patients Province:");
        province = scanner.nextLine();
        System.out.println("Please enter the patients Postal Code:");
        postalCode = scanner.nextLine();
        System.out.println("Please enter the patients Insurance Number:");
        insuranceNumber = scanner.nextLine();
        if (flag) insertUserInformation(conn);
       
        return;
    }
    
    public boolean isReceptionist(int id){
        try {
            //Get contact information id 
            preparedStatement = conn.prepareStatement("SELECT employee_role FROM public.employee WHERE employee_id="+id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){ 
                if(resultSet.getString("employee_role").equals("Receptionist")){
                    return true;
                }
            }

      } catch (SQLException e) {
          e.printStackTrace();
      }
      return false;
    }
    //Insert user information into ContactInfo, Address and User Tables 
    public void insertUserInformation(Connection conn) {
      try {
            //Get contact information id 
            preparedStatement = conn.prepareStatement("SELECT max(contactinfo_id) FROM public.contactinformation");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) contactInformationId = resultSet.getInt("max");
            contactInformationId++;
            //Get address id 
            preparedStatement = conn.prepareStatement("SELECT max(address_id) FROM public.address");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) addressId = resultSet.getInt("max");
            addressId++;
            //Get User id 
            preparedStatement = conn.prepareStatement("SELECT max(user_id) FROM public.user");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) userId = resultSet.getInt("max");
            userId++;

            preparedStatement = conn.prepareStatement("SELECT max(patient_id) FROM public.patient");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) patientId = resultSet.getInt("max");
            patientId++;

            
           
            String contactInfo = contactInformationId + ", '"+ email + "', '"+ phonenumber + "'";
            String addressInfo = addressId + ","+ apartmentNumber + ", "+ streetNumber + ", '"+ street + "', '"+ city + "', '" + province +"', '"+ postalCode +"'" ;
            String userInfo = "'"+firstName + "', '"+ middleName+"', '"+lastName+"', '"+gender+"', "+ssn+ ", '"+dateOfBirth+"',"+contactInformationId+","+ userId+","+addressId;
            String sql1 = "INSERT INTO public.contactinformation values ("+contactInfo + ")";
            String sql2 = "INSERT INTO public.address values ("+addressInfo + ")";
            String sql3 = "INSERT INTO public.user values ("+userInfo + ")";
            String sql4 = "INSERT INTO public.Patient values ("+patientId + ", "+ userId + ", '"+ insuranceNumber+"')";
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
    public void editUserInformation(Connection conn){
        try {
            //Get contact information id 
            scanner = new Scanner(System.in);
            System.out.println("Please enter the SSN of the patient you would like to edit:");
            ssn = Integer.parseInt(scanner.nextLine());
            
            preparedStatement = conn.prepareStatement("SELECT * FROM public.user, public.address, public.contactinformation WHERE SSN="+ ssn+ "and public.user.address_id=public.address.address_id and public.user.contactinfo_id=public.contactinformation.contactinfo_id");
            resultSet = preparedStatement.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()){ 
                System.out.println("firstname: " +resultSet.getString("firstname") +",\nmiddlename: "+resultSet.getString("middlename")+ ",\nlastname: " +resultSet.getString("lastname")+ ",\ngender: " +resultSet.getString("gender") + ",\nssn: "+resultSet.getInt("ssn")+",\ndatebirth: "+ resultSet.getDate("datebirth")+
                ",\napartmentnumber: "+ resultSet.getInt("apartmentnumber")+ ",\nstreetnumber: "+resultSet.getInt("streetnumber")+ ",\nstreet: "+resultSet.getString("street")+ ",\ncity: "+resultSet.getString("city") + ",\nprovince: "+resultSet.getString("province")+ ",\npostalcode: "+resultSet.getString("postalcode") + ",\nemail: "+resultSet.getString("email")
                + ",\nphonenumber: "+resultSet.getString("phonenumber"));
                
                addressId = resultSet.getInt("address_id");
                userId = resultSet.getInt("user_id");
                contactInformationId = resultSet.getInt("contactinfo_id");
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");
           
            System.out.println("Please enter the field you would like to update :");
            String field = scanner.nextLine();
            
            System.out.println("What would you like to change it to :");
            Object value;            
            if(field.equals("apartmentnumber") || field.equals("streetnumber")|| field.equals("ssn")){
                value = Integer.parseInt(scanner.nextLine());
            }else{
                value = scanner.nextLine();
            }
            
            String table ="";
            int id=0;
            String type="";
            if(field.equals("email")||field.equals("phonenumber")){
                table = "public.contactInformation";
                id = contactInformationId;
                type="contactinfo_id";
            }else if(field.equals("apartmentnumber")||field.equals("streetnumber")||field.equals("street")||field.equals("city")||field.equals("province")||field.equals("postalcode")){
                table = "public.address";
                id = addressId;
                type="address_id";
            }else if(field.equals("firstname")||field.equals("middlename")||field.equals("lastname")||field.equals("gender")||field.equals("ssn")||field.equals("datebirth")){
                table = "public.user";
                type="user_id";
                id = userId;
            }
            // preparedStatement = conn.prepareStatement("UPDATE "+ table + "SET " + field +" = " +value +" WHERE SSN = "+ssn );
            String sql = "UPDATE "+ table + " SET " + field +" = '" +value +"' WHERE " + type +" = " +id;
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.addBatch(sql);
            statement.executeBatch();

      } catch (SQLException e) {
          e.printStackTrace();
      }
      
    }

    public void setAppointment(Connection conn){
        try {
            //Get contact information id 
            scanner = new Scanner(System.in);
            System.out.println("Please enter the SSN of the patient:");
            ssn = Integer.parseInt(scanner.nextLine());
            
            preparedStatement = conn.prepareStatement("SELECT * FROM public.user, public.address, public.contactinformation WHERE SSN="+ ssn+ "and public.user.address_id=public.address.address_id and public.user.contactinfo_id=public.contactinformation.contactinfo_id");
            resultSet = preparedStatement.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()){ 
                System.out.println("firstname: " +resultSet.getString("firstname") +",\nmiddlename: "+resultSet.getString("middlename")+ ",\nlastname: " +resultSet.getString("lastname")+ ",\ngender: " +resultSet.getString("gender") + ",\nssn: "+resultSet.getInt("ssn")+",\ndatebirth: "+ resultSet.getDate("datebirth")+
                ",\napartmentnumber: "+ resultSet.getInt("apartmentnumber")+ ",\nstreetnumber: "+resultSet.getInt("streetnumber")+ ",\nstreet: "+resultSet.getString("street")+ ",\ncity: "+resultSet.getString("city") + ",\nprovince: "+resultSet.getString("province")+ ",\npostalcode: "+resultSet.getString("postalcode") + ",\nemail: "+resultSet.getString("email")
                + ",\nphonenumber: "+resultSet.getString("phonenumber"));
                
                addressId = resultSet.getInt("address_id");
                userId = resultSet.getInt("user_id");
                contactInformationId = resultSet.getInt("contactinfo_id");
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");
           
            System.out.println("Please enter the field you would like to update :");
            String field = scanner.nextLine();
            
            System.out.println("What would you like to change it to :");
            Object value;            
            if(field.equals("apartmentnumber") || field.equals("streetnumber")|| field.equals("ssn")){
                value = Integer.parseInt(scanner.nextLine());
            }else{
                value = scanner.nextLine();
            }
            
            String table ="";
            int id=0;
            String type="";
            if(field.equals("email")||field.equals("phonenumber")){
                table = "public.contactInformation";
                id = contactInformationId;
                type="contactinfo_id";
            }else if(field.equals("apartmentnumber")||field.equals("streetnumber")||field.equals("street")||field.equals("city")||field.equals("province")||field.equals("postalcode")){
                table = "public.address";
                id = addressId;
                type="address_id";
            }else if(field.equals("firstname")||field.equals("middlename")||field.equals("lastname")||field.equals("gender")||field.equals("ssn")||field.equals("datebirth")){
                table = "public.user";
                type="user_id";
                id = userId;
            }
            // preparedStatement = conn.prepareStatement("UPDATE "+ table + "SET " + field +" = " +value +" WHERE SSN = "+ssn );
            String sql = "UPDATE "+ table + " SET " + field +" = '" +value +"' WHERE " + type +" = " +id;
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.addBatch(sql);
            statement.executeBatch();

      } catch (SQLException e) {
          e.printStackTrace();
      }
      
    }
    
}
