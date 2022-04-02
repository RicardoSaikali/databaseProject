package Patient;

import java.util.*;
import java.util.Date;

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
    //TODO Add constraints on all inputs and change scanner to using UI
    public void getInformation(Connection conn){
        scanner = new Scanner(System.in);
        System.out.println("Please enter your First Name:");
        firstName = scanner.nextLine();
        System.out.println("Please enter your Middle Name:");
        middleName = scanner.nextLine();
        System.out.println("Please enter your Last Name:");
        lastName = scanner.nextLine();
        System.out.println("Please enter your Email:");
        email = scanner.nextLine();
        System.out.println("Please enter your Phone Number:");
        phonenumber = scanner.nextLine();
        System.out.println("Please enter your Gender:");
        gender = scanner.nextLine();
        System.out.println("Please enter your SSN:");
        ssn = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter your Date of Birth (yyyy-mm-dd):");
        dateOfBirth = scanner.nextLine();
        System.out.println("Please enter your Apartment Number:");
        try{
            apartmentNumber= (Integer) Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException ex){
            apartmentNumber=null;
        }
        System.out.println("Please enter your Street Number:");
        streetNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter your Street Address:");
        street = scanner.nextLine();
        System.out.println("Please enter your City:");
        city = scanner.nextLine();
        System.out.println("Please enter your Province:");
        province = scanner.nextLine();
        System.out.println("Please enter your Postal Code:");
        postalCode = scanner.nextLine();

        insertUserInformation(conn);
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

            
           
            String contactInfo = contactInformationId + ", '"+ email + "', '"+ phonenumber + "'";
            String addressInfo = addressId + ","+ apartmentNumber + ", "+ streetNumber + ", '"+ street + "', '"+ city + "', '" + province +"', '"+ postalCode +"'" ;
            String userInfo = "'"+firstName + "', '"+ middleName+"', '"+lastName+"', '"+gender+"', "+ssn+ ", '"+dateOfBirth+"',"+contactInformationId+","+ userId+","+addressId;
            String sql1 = "INSERT INTO public.contactinformation values ("+contactInfo + ")";
            String sql2 = "INSERT INTO public.address values ("+addressInfo + ")";
            String sql3 = "INSERT INTO public.user values ("+userInfo + ")";
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.executeBatch();

      } catch (SQLException e) {
          e.printStackTrace();
      }
    }   
}
