package TheConnection;
import java.sql.*;
public class DBConnection {
    public static Connection conn = null;
    public void Connection(){
        String user = "mzjycxzivsmkni";
        String pass = "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
        String LINK = "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";
        
        try {
            conn = DriverManager.getConnection(LINK, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
